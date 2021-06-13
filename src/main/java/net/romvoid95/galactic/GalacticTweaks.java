package net.romvoid95.galactic;

import static net.romvoid95.galactic.Info.*;

import java.io.File;
import java.util.Set;

import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Ordering;

import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.configs.AsmodeusConfig;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.discovery.ASMDataTable;
import net.minecraftforge.fml.common.discovery.ASMDataTable.ASMData;
import net.minecraftforge.fml.common.discovery.asm.ModAnnotation;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.GalacticraftAddon;
import net.romvoid95.api.IReadOnly;
import net.romvoid95.galactic.core.PackCrashEnhancement;
import net.romvoid95.galactic.core.config.CoreBooleanValues;
import net.romvoid95.galactic.core.config.CoreConfigHandler;
import net.romvoid95.galactic.core.gc.IOWriter;
import net.romvoid95.galactic.core.permission.GCTPermissions;
import net.romvoid95.galactic.core.version.DownloadCommand;
import net.romvoid95.galactic.core.version.VersionChecker;
import net.romvoid95.galactic.feature.Auto;
import net.romvoid95.galactic.modules.Module;
import net.romvoid95.galactic.modules.ModuleController;
import net.romvoid95.galactic.proxy.ServerProxy;

@Mod(
		modid = ID,
		name = NAME,
		dependencies = DEPS,
		acceptedMinecraftVersions = "[1.12]",
		acceptableRemoteVersions = "*",
		certificateFingerprint = FINGERPRINT
		//guiFactory = "net.romvoid95.galactic.core.gui.GCTGuiFactory"
		)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GalacticTweaks implements IReadOnly {

	@Instance(ID)
	public static GalacticTweaks instance;

	@SidedProxy(clientSide = "net.romvoid95.galactic.proxy.ClientProxy", serverSide = "net.romvoid95.galactic.proxy.ServerProxy")
	public static ServerProxy proxy;

	public static final GCTLogger LOG = new GCTLogger(ID);
	public static File modFolder = null;
	
	public static ASMDataTable asmDataTable;
	
	private void autoLoadClasses() {
		Set<ASMData> classes = ImmutableSortedSet.copyOf(Ordering.natural().onResultOf(ASMData::getClassName), asmDataTable.getAll(Auto.class.getName()));

		Set<ASMData> clientClasses = asmDataTable.getAll(SideOnly.class.getName());

		for (ASMData data : classes) {
			try {
				// don't load client classes on server.
				if (!isClient() && isClientClass(data.getClassName(), clientClasses)) {
					continue;
				}

				Class<?> clazz = Class.forName(data.getClassName());
				Auto anno = clazz.getAnnotation(Auto.class);
				if (anno.value()) {
					clazz.newInstance();
				}
			} catch (Exception e) {
				LOG.error("Could not autoload {}.", data.getClassName(), e);
			}
		}
	}

	public static boolean isClient() {
		return FMLCommonHandler.instance().getSide().isClient();
	}

	private boolean isClientClass(String className, Set<ASMData> clientClasses) {
		for (ASMData data : clientClasses) {
			if (data.getClassName().equals(className) && data.getObjectName().equals(data.getClassName()) && ((ModAnnotation.EnumHolder) data.getAnnotationInfo().get("value")).getValue().equals("CLIENT")) {
				return true;
			}
		}
		return false;
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		asmDataTable = event.getAsmData();
		autoLoadClasses();
		GalacticTweaks.modFolder = new File(event.getModConfigurationDirectory(), "GalacticTweaks");
		MinecraftForge.EVENT_BUS.register(this);

		FMLCommonHandler.instance().registerCrashCallable(new PackCrashEnhancement());
		new CoreConfigHandler(modFolder, CONFVERSION);
		new VersionChecker();

		ModuleController.registerModules();
		ModuleController.modules.forEach(Module::setupConfig);
		ModuleController.modules.forEach(Module::handleFeatures);
		ModuleController.modules.forEach(Module::preInit);

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		ModuleController.modules.forEach(Module::init);
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded() && GalacticraftAddon.GALAXYSPACE.isLoaded()) {
			if(AsmodeusConfig.enableNewGalaxyMap) {
				BodiesRegistry.setMaxTier(10);
			}
		}

		IOWriter io = new IOWriter();

		io.handleFile("ValidDimIDs.txt");
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded()) {
			io.NOTICE();
			io.write("Planets & Moons that end with \"ep\" are added by ExtraPlanets.");
			io.write("Please keep this in mind if choosing a planet that is added by both ExtraPlanets & GalaxySpace");
			io.nl();
			io.write("This file is regenerated every time minecraft is ran to handle any addons added or removed");
			io.title("PLANETS");
			io.writePlanets();
			io.title("MOONS");
			io.writeMoons();
			io.finalize();
		}

		Module.config.addValidDims();
		Module.config.loadConfig();

		ModuleController.modules.forEach(Module::postInit);
		
		GCTPermissions.registerNodes();

		proxy.postInit(event);
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

		ModuleController.modules.forEach(module -> module.serverStartingEvent(event));

		if (CoreBooleanValues.DO_UPDATE_CHECK.isEnabled()) {
			event.registerServerCommand(new DownloadCommand());
		}

	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if(isDevBuild() == false) {
			GalacticTweaks.LOG.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the developer!");
		}
		if(isDevBuild()) {
			GalacticTweaks.LOG.info("Ignoring fingerprint signing since we are in a Development Environment");
		}
	}

	@Override
	public String getModId() {
		return ID;
	}

	@Override
	public String getModName() {
		return NAME;
	}

	@Override
	public String getVersion() {
		return VERSION;
	}
}

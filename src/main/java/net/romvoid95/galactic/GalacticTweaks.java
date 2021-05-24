package net.romvoid95.galactic;

import static net.romvoid95.galactic.Info.*;

import java.io.*;
import java.util.*;

import javax.annotation.*;

import asmodeuscore.core.astronomy.*;
import asmodeuscore.core.configs.*;
import mcp.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.romvoid95.api.*;
import net.romvoid95.api.event.*;
import net.romvoid95.galactic.core.*;
import net.romvoid95.galactic.core.config.*;
import net.romvoid95.galactic.core.gc.*;
import net.romvoid95.galactic.core.version.*;
import net.romvoid95.galactic.modules.*;
import net.romvoid95.galactic.proxy.*;

@Mod(
		modid = ID,
		dependencies = DEPS,
		acceptedMinecraftVersions = "[1.12]",
		acceptableRemoteVersions = "*",
		certificateFingerprint = FINGERPRINT,
		guiFactory = "net.romvoid95.galactic.core.gui.GCTGuiFactory"
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

	private final List<IEventProcessor> eventProcessors = new ArrayList<>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//DocTool.runTool();
		GalacticTweaks.modFolder = new File(event.getModConfigurationDirectory(), "GalacticTweaks");
		MinecraftForge.EVENT_BUS.register(this);

		FMLCommonHandler.instance().registerCrashCallable(new PackCrashEnhancement());
		new CoreConfigHandler(modFolder, CONFVERSION);
		new VersionChecker();

		this.eventProcessors.forEach(i -> i.preInit(event));

		ModuleController.registerModules();
		ModuleController.modules.forEach(Module::setupConfig);
		ModuleController.modules.forEach(Module::handleFeatures);
		ModuleController.modules.forEach(Module::preInit);

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		this.eventProcessors.forEach(i -> i.init(event));
		ModuleController.modules.forEach(Module::init);
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		this.eventProcessors.forEach(i -> i.postInit(event));
		if(GalacticraftAddon.EXTRAPLANETS.isLoaded() && GalacticraftAddon.GALAXYSPACE.isLoaded()) {
			if(AsmodeusConfig.enableNewGalaxyMap) {
				BodiesRegistry.setMaxTier(10);
			}
		}

		IOWriter io = new IOWriter();

		io.handleFile("ValidDimIDs.txt");
		if(CompatMods.EXTRAPLANETS.isLoaded()) {
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

	public void addEventProcessor(IEventProcessor instance) {
		this.eventProcessors.add(instance);
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

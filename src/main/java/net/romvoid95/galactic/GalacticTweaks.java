package net.romvoid95.galactic;

import static net.romvoid95.galactic.Info.*;

import java.io.*;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.Mod.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;
import net.romvoid95.api.*;
import net.romvoid95.galactic.core.*;
import net.romvoid95.galactic.core.config.*;
import net.romvoid95.galactic.core.gc.*;
import net.romvoid95.galactic.core.version.*;
import net.romvoid95.galactic.modules.*;

@Mod(
		modid = ID,
		dependencies = DEPS,
		acceptedMinecraftVersions = "[1.12]",
		acceptableRemoteVersions = "*",
		certificateFingerprint = FINGERPRINT,
		guiFactory = "net.romvoid95.galactic.core.gui.GCTGuiFactory"
)
public class GalacticTweaks implements IReadOnly{

	@Instance(value = "GalacticTweaks", owner = ID)
	public static GalacticTweaks instance = new GalacticTweaks();
	
	public static final GCTLogger LOG = new GCTLogger(ID, BUILD_NUM);
	
	public static File modFolder = null;

	@SidedProxy(clientSide = "net.romvoid95.galactic.ClientProxy", serverSide = "net.romvoid95.galactic.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GalacticTweaks.modFolder = new File(event.getModConfigurationDirectory(), "GalacticTweaks");
		MinecraftForge.EVENT_BUS.register(this);

		FMLCommonHandler.instance().registerCrashCallable(new PackCrashEnhancement());
		new CoreConfigHandler(modFolder, CONFVERSION);
		new VersionChecker();
		
		final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(ID);

		ModuleController.registerModules();
		ModuleController.modules.forEach(Module::setupConfig);
		
		ModuleController.modules.forEach(Module::handleFeatures);

		ModuleController.modules.forEach(module -> module.registerPacket(network));
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
		IOWriter io = new IOWriter();
		
		io.handleFile("ValidDimIDs.txt");
		if(CompatMods.EXTRAPLANETS.isLoaded()) {
			io.NOTICE();
			io.write(("Planets & Moons that end with \"ep\" are added by ExtraPlanets."));
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

		if (CoreBooleanValues.DO_UPDATE_CHECK.isEnabled())
			event.registerServerCommand(new DownloadCommand());
		
//    	Map<DimensionType, IntSortedSet> set = DimensionManager.getRegisteredDimensions();
//    	StringBuilder buffer = new StringBuilder();
//    	buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
//    	set.keySet().forEach(dim -> buffer.append("Dim: " + dim.getName() + " | ID: " + dim.getId() + "\n"));
//    	buffer.append("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
//    	LOG.info(buffer.toString());
	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if(isDevBuild() == false)
			GalacticTweaks.LOG.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the developer!");
		if(isDevBuild())
			GalacticTweaks.LOG.info("Ignoring fingerprint signing since we are in a Development Environment");
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

	@Override
	public int getBuildNum() {
		return BUILD_NUM;
	}
}

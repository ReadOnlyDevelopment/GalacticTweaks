package net.romvoid95.galactic;

import static net.romvoid95.galactic.Info.*;

import java.io.File;

import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.launchwrapper.Launch;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.api.IReadOnly;
import net.romvoid95.galactic.core.GCTLog;
import net.romvoid95.galactic.core.PackCrashEnhancement;
import net.romvoid95.galactic.core.config.CoreConfig;
import net.romvoid95.galactic.core.version.DownloadCommand;
import net.romvoid95.galactic.handler.CoreConfigHandler;
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

	public static File modFolder = null;
	private ModuleController controller = new ModuleController();
	public static String NODE_ADMINTP = "galactictweaks.admin.teleport";
	
	@Instance(ID)
	public static GalacticTweaks instance;

	@SidedProxy(clientSide = "net.romvoid95.galactic.proxy.ClientProxy", serverSide = "net.romvoid95.galactic.proxy.ServerProxy")
	public static ServerProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		modFolder = new File(event.getModConfigurationDirectory(), "GalacticTweaks");
		MinecraftForge.EVENT_BUS.register(this);
		FMLCommonHandler.instance().registerCrashCallable(new PackCrashEnhancement());
		new CoreConfigHandler();
		controller.preInit(event);
		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		controller.init(event);
		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		controller.postInit(event);
		proxy.postInit(event);
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		controller.onServerStarting(event);
		if (CoreConfig.runUpdateCheck.get()) {
			event.registerServerCommand(new DownloadCommand());
		}
	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if((Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
			GCTLog.info("Ignoring fingerprint signing since we are in a Development Environment");
		} else {
			GCTLog.warn("Invalid fingerprint detected! The file " + event.getSource().getName() + " may have been tampered with. This version will NOT be supported by the developer!");
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

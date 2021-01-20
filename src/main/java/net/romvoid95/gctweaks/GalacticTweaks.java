package net.romvoid95.gctweaks;

import java.io.File;
import java.util.LinkedList;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLFingerprintViolationEvent;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.romvoid95.gctweaks.base.InternalModule;
import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.base.core.proxy.CommonProxy;
import net.romvoid95.gctweaks.base.core.utils.GameUtil;
import net.romvoid95.gctweaks.base.core.utils.LogHelper;
import net.romvoid95.gctweaks.base.core.utils.Utilz;
import net.romvoid95.gctweaks.internal.command.DownloadCommand;
import net.romvoid95.gctweaks.internal.config.CoreBooleanValues;
import net.romvoid95.gctweaks.internal.config.CoreConfigHandler;
import net.romvoid95.gctweaks.internal.versioning.VersionChecker;

@Mod(modid = Ref.MOD_ID, name = Ref.MOD_NAME, version = Ref.MOD_VERSION, dependencies = Ref.DEPS, certificateFingerprint = Ref.MOD_FINGERPRINT)
public class GalacticTweaks {

	@Instance(Ref.MOD_ID)
	public static GalacticTweaks instance = new GalacticTweaks();
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MOD_ID);
	public static LogHelper logger = new LogHelper();
	public static File modFolder = null;
	public static LinkedList<ItemStack> itemList = new LinkedList<>();
	public static LinkedList<Item> itemListTrue = new LinkedList<>();

	@SidedProxy(clientSide = "net.romvoid95.gctweaks.base.core.proxy.ClientProxy", serverSide = "net.romvoid95.gctweaks.base.core.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GalacticTweaks.modFolder = event.getModConfigurationDirectory();
		MinecraftForge.EVENT_BUS.register(this);

		new CoreConfigHandler(modFolder);
		new VersionChecker();
		// ==========================================
		// Internal Modules
		// ==========================================
		// ~ Register ~ //
		ModuleController.registerInternalModules();

		// ~ Network ~ //
		ModuleController.internals.forEach(module -> module.registerPacket(network));

		// ~ Phase ~ //
		ModuleController.internals.forEach(InternalModule::preInit);

		// ==========================================
		// Feature Modules
		// ==========================================
		// ~ Register ~ //
		ModuleController.registerModules();

		// ~ Configurations ~ //
		ModuleController.modules.forEach(module -> module.setupConfig(event));
		ModuleController.modules.forEach(Module::syncConfig);

		// ~ Network ~ //
		ModuleController.modules.forEach(module -> module.registerPacket(network));

		// ~ Phase ~ //
		ModuleController.modules.forEach(Module::preInit);

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		// ==========================================
		// Internal Modules
		// ==========================================
		// ~ Phase ~ //
		ModuleController.internals.forEach(InternalModule::init);

		// ==========================================
		// Feature Modules
		// ==========================================
		// ~ Phase ~ //
		ModuleController.modules.forEach(Module::init);

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		// ==========================================
		// Internal Modules
		// ==========================================
		// ~ Phase ~ //
		ModuleController.internals.forEach(InternalModule::postInit);

		// ==========================================
		// Feature Modules
		// ==========================================
		// ~ Phase ~ //
		ModuleController.modules.forEach(Module::postInit);

		Utilz.creatFile();

		proxy.postInit(event);
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

		// ==========================================
		// Internal Modules
		// ==========================================
		// ~ Phase ~ //
		ModuleController.internals.forEach(internal -> internal.serverStartingEvent(event));

		// ==========================================
		// Feature Modules
		// ==========================================
		// ~ Phase ~ //
		ModuleController.modules.forEach(module -> module.serverStartingEvent(event));

		if (CoreBooleanValues.DO_UPDATE_CHECK.isEnabled())
			event.registerServerCommand(new DownloadCommand());
	}

	@EventHandler
	public void onFingerprintViolation(FMLFingerprintViolationEvent event) {
		if (!GameUtil.isDeobfuscated()) {
			logger.warn("Invalid fingerprint detected! The file " + event.getSource().getName()
					+ " may have been tampered with. This version will NOT be supported by the author!");
		} else {
			logger.info("Ignoring fingerprint signing since we are in a Development Environment");
		}
	}
}

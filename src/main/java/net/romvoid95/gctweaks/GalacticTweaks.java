package net.romvoid95.gctweaks;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
import net.romvoid95.gctweaks.base.core.IHandler;
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

	@SuppressWarnings("serial")
	private static List<IHandler> registers = new ArrayList<IHandler>() {
		{
			add(ModuleController.INSTANCE);
		}
	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		GalacticTweaks.modFolder = event.getModConfigurationDirectory();
		MinecraftForge.EVENT_BUS.register(this);

		new CoreConfigHandler(modFolder);
		new VersionChecker();
		registers.forEach(handler -> handler.preInit(event));

		proxy.preInit(event);
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {

		registers.forEach(handler -> handler.init(event));

		proxy.init(event);
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

		registers.forEach(handler -> handler.postInit(event));

		Utilz.creatFile();

		proxy.postInit(event);
	}

	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {

		registers.forEach(handler -> handler.onServerStarting(event));

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

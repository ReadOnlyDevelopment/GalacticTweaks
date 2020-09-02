package net.rom.gctweaks;

import java.util.Map;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.NetworkCheckHandler;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.rom.gctweaks.core.InternalModule;
import net.rom.gctweaks.core.Module;
import net.rom.gctweaks.core.proxy.CommonProxy;
import net.rom.gctweaks.core.utils.I18nHelper;
import net.rom.gctweaks.core.utils.LogHelper;
import net.rom.gctweaks.galacticraftchanges.asteroids.EditableAsteroids;
import net.rom.gctweaks.galacticraftchanges.asteroids.EditableAsteroids.AsteroidData;

@Mod(
		modid = Ref.MOD_ID,
		name = Ref.MOD_NAME,
		version = Ref.MOD_VERSION,
		dependencies = Ref.DEPS,
		certificateFingerprint = Ref.MOD_FINGERPRINT,
		useMetadata = true)
public class GalacticTweaks {

	@Instance(Ref.MOD_ID)
	public static GalacticTweaks       instance;
	public static SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Ref.MOD_ID);
	public static LogHelper            logger  = new LogHelper();
	public static I18nHelper           stringz = new I18nHelper(Ref.MOD_ID, logger, false);

	@NetworkCheckHandler
	public boolean networkCheck (Map<String, String> map, Side side) {
		return true;
	}

	@SidedProxy(
			clientSide = "net.rom.gctweaks.core.proxy.ClientProxy",
			serverSide = "net.rom.gctweaks.core.proxy.CommonProxy")
	public static CommonProxy proxy;

	@EventHandler
	public void preInit (FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);

		ModuleController.registerInternalModules();
		ModuleController.modules.forEach(module -> module.setupConfig(event));
		ModuleController.modules.forEach(Module::syncConfig);
		ModuleController.modules.forEach(module -> module.registerPacket(network));
		ModuleController.modules.forEach(Module::preInit);

		ModuleController.registerModules();
		ModuleController.internals.forEach(module -> module.registerPacket(network));
		ModuleController.internals.forEach(InternalModule::preInit);

		proxy.preInit(event);
	}

	@EventHandler
	public void init (FMLInitializationEvent event) {

		ModuleController.modules.forEach(Module::init);
		ModuleController.internals.forEach(InternalModule::init);
		EditableAsteroids.AsteroidData.registerData();
		
		proxy.init(event);
	}

	@EventHandler
	public void postInit (FMLPostInitializationEvent event) {
		ModuleController.modules.forEach(Module::postInit);
		ModuleController.internals.forEach(InternalModule::postInit);

		proxy.postInit(event);
	}
	
	@EventHandler
	public void serverStarting(FMLServerStartingEvent event) {
		ModuleController.modules.forEach(Module::serverStartingEvent);
	}
}

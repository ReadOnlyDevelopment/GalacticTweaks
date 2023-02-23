package readonly.galactictweaks;

import static readonly.galactictweaks.Info.DEPS;
import static readonly.galactictweaks.Info.ID;
import static readonly.galactictweaks.Info.NAME;
import static readonly.galactictweaks.Info.VERSION;

import java.io.File;

import javax.annotation.ParametersAreNonnullByDefault;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import readonly.api.IReadOnly;
import readonly.galactictweaks.core.PackCrashEnhancement;
import readonly.galactictweaks.handler.CoreConfigHandler;
import readonly.galactictweaks.proxy.ServerProxy;

@Mod(
		modid = ID,
		name = NAME,
		dependencies = DEPS,
		acceptedMinecraftVersions = "[1.12,1.13)",
		acceptableRemoteVersions = "*"
		)
@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class GCTweaks implements IReadOnly {

	public static File modFolder = null;
	private ModuleController controller = new ModuleController();
	public static String NODE_ADMINTP = "galactictweaks.admin.teleport";
	
	@Instance(ID)
	public static GCTweaks instance;

	@SidedProxy(clientSide = "readonly.galactictweaks.proxy.ClientProxy", serverSide = "readonly.galactictweaks.proxy.ServerProxy")
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

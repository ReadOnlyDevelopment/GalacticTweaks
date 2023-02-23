package readonly.galactictweaks.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLInterModComms.IMCEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ServerProxy implements IProxy {

	public void register_event (Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		event.getSuggestedConfigurationFile();

	}

	@Override
	public void init(FMLInitializationEvent event) {

	}
	
	public void receiveIMC(IMCEvent event) {
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {

	}

	@Override
	public EntityPlayer getClientPlayer() {
		return null;
	}
	
	public EntityPlayer getPlayerFromNetHandler (INetHandler handler) {
		if (handler instanceof NetHandlerPlayServer) {
			return ((NetHandlerPlayServer) handler).player;
		}
		else {
			return null;
		}
	}
}

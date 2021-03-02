package net.romvoid95.galactic.proxy;

import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.event.FMLInterModComms.*;

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

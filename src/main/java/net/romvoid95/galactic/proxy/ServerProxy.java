package net.romvoid95.galactic.proxy;

import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.event.FMLInterModComms.*;
import net.romvoid95.api.registry.*;

public class ServerProxy implements IProxy {

	public void register_event (Object obj) {
		MinecraftForge.EVENT_BUS.register(obj);
	}

	@Override
	public void preInit(GCTRegistry registry, FMLPreInitializationEvent event) {
		event.getSuggestedConfigurationFile();
		registry.preInit(event);
	}

	@Override
	public void init(GCTRegistry registry, FMLInitializationEvent event) {
		registry.init(event);
	}
	
	public void receiveIMC(IMCEvent event) {
	}

	@Override
	public void postInit(GCTRegistry registry, FMLPostInitializationEvent event) {
		registry.postInit(event);
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

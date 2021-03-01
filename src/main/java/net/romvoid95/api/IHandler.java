package net.romvoid95.api;

import net.minecraftforge.fml.common.event.*;

public interface IHandler {
	
	public void preInit(FMLPreInitializationEvent event);
	
	public void init(FMLInitializationEvent event);
	
	public void postInit(FMLPostInitializationEvent event);

	public void onServerStarting(FMLServerStartingEvent event);

}

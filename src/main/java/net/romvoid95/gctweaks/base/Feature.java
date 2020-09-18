package net.romvoid95.gctweaks.base;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * The Abstract Class Feature.
 */
public abstract class Feature {

	public void preInit () {}

	public void init () {}

	public void postInit () {}

	public void proxyPostInit () {}

	public void proxyPreInit () {}

	public void proxyInit () {}
	
	public void ServerStartingEvent (FMLServerStartingEvent event) {}

	public abstract String[] category ();

	public abstract String comment ();

	public abstract void syncConfig (Configuration config, String[] strings);

	public boolean usesEvents () {
		return false;
	}

	public boolean sidedProxy () {
		return false;
	}

	public void registerPacket (SimpleNetworkWrapper network) {}

}

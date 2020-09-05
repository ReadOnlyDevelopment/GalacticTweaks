package net.rom.gctweaks.base;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public abstract class InternalFeature {

	public void preInit () {}

	public void init () {}

	public void postInit () {}

	public void proxyPostInit () {}

	public void proxyPreInit () {}

	public void proxyInit () {}

	public boolean usesEvents () {
		return false;
	}

	public boolean sidedProxy () {
		return false;
	}

	public void ServerStartingEvent (FMLServerStartingEvent event) {}

	/**
	 * Register packet.
	 *
	 * @param network
	 */
	public void registerPacket (SimpleNetworkWrapper network) {}

}

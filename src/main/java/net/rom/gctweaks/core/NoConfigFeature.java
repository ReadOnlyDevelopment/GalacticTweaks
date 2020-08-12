package net.rom.gctweaks.core;

import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * The Abstract Class Feature.
 */
public abstract class NoConfigFeature {

	/**
	 * Pre init.
	 */
	public void preInit() {

	}

	/**
	 * Inits the.
	 */
	public void init() {

	}

	/**
	 * Post init.
	 */
	public void postInit() {

	}

	/**
	 * Post init.
	 */
	public void proxyPostInit() {

	}

	/**
	 * Pre init.
	 */
	public void proxyPreInit() {

	}

	/**
	 * Inits the.
	 */
	public void proxyInit() {

	}
	
	/**
	 * Feature uses events.
	 *
	 * @return true, if successful
	 */
	public boolean usesEvents() {
		return false;
	}

	public boolean sidedProxy() {
		return false;
	}

	/**
	 * Register packet.
	 *
	 * @param network
	 */
	public void registerPacket(SimpleNetworkWrapper network) {
	}

}

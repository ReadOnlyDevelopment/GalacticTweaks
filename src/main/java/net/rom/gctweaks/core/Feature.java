package net.rom.gctweaks.core;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * The Abstract Class Feature.
 */
public abstract class Feature {
	
	/**
	 * @return the category
	 */
	public abstract String[] category();

	/**
	 * @return the comment
	 */
	public abstract String comment();

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
	 * Sync config.
	 *
	 * @param config   the config
	 * @param strings the category
	 */
	public abstract void syncConfig(Configuration config, String[] strings);

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
	
	public void ServerStartingEvent() {
		
	}

	/**
	 * Register packet.
	 *
	 * @param network
	 */
	public void registerPacket(SimpleNetworkWrapper network) {
	}

}

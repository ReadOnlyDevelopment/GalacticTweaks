package net.romvoid95.galactic.feature;

import java.util.*;

import net.minecraftforge.fml.common.event.*;
import net.minecraftforge.fml.common.network.simpleimpl.*;

public abstract class Feature implements IFeature {

	protected List<String> propOrder = new ArrayList<>();
	
	public void prePreInit() {}

	public void preInit () {}

	public void init () {}

	public void postInit () {}

	public void proxyPostInit () {}

	public void proxyPreInit () {}

	public void proxyInit () {}
	
	public void ServerStartingEvent (FMLServerStartingEvent event) {}

	public abstract String category ();

	public abstract String comment ();

	public boolean usesEvents () {
		return false;
	}

	public boolean sidedProxy () {
		return false;
	}

	public void registerPacket (SimpleNetworkWrapper network) {}

}

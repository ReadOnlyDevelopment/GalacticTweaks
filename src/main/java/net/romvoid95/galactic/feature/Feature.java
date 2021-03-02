package net.romvoid95.galactic.feature;

import java.util.*;

import net.minecraftforge.fml.common.event.*;

public abstract class Feature implements IFeature {

	protected List<String> propOrder = new ArrayList<>();

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

}

package net.romvoid95.gctweaks.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

/**
 * The Abstract Class Feature.
 */
public abstract class Feature {
	
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
	
	public void syncConfig (String category) {}

	public boolean usesEvents () {
		return false;
	}

	public boolean sidedProxy () {
		return false;
	}

	public void registerPacket (SimpleNetworkWrapper network) {}
	
	public boolean set(String category, String key, boolean defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue).getBoolean();
	}
	
	public String set(String category, String key, String defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue).getString();
	}
	
	public double set(String category, String key, double defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue).getDouble();
	}
	
	public String[] set(String category, String key, String[] defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue).getStringList();
	}
	
	public double[] set(String category, String key, double[] defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue).getDoubleList();
	}
	
	public boolean set(String category, String key, String comment, boolean defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getBoolean();
	}
	
	public String set(String category, String key, String comment, String defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getString();
	}
	
	public double set(String category, String key, String comment, double defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getDouble();
	}
	
	public String[] set(String category, String key, String comment, String[] defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getStringList();
	}
	
	public String set(String category, String key, String defaultValue, String comment, String[] validStrings) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment, validStrings).getString();
	}
	
	public double[] set(String category, String key, String comment, double[] defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getDoubleList();
	}
	
	public int[] set(String category, String key, String comment, int[] defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getIntList();
	}
	
	public int set(String category, String key, String comment, int defaultValue) {
		propOrder.add(key);
		return Module.config.getConfig().get(category, key, defaultValue, comment).getInt();
	}
	
	public String setFormated(String category, String key, String defaultValue, String[] values) {
		propOrder.add(key);
		StringBuilder b = new StringBuilder();
		b.append("Valid Values: \n");
		for(String value : values) {
			b.append("   " + value + "\n");
		}
		b.append("\nDefault: " + defaultValue + "\n");
		return Module.config.getConfig().get(category, key, defaultValue, b.toString(), values).getString();
	}

}

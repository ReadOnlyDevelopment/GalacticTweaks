package net.romvoid95.gctweaks.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Config.Type;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.romvoid95.gctweaks.Ref;
import net.romvoid95.gctweaks.base.core.ConfigBase;
import net.romvoid95.gctweaks.base.core.ConfigBase.ConfigVersion;

public abstract class Module {
	private List<Feature>         features  = new ArrayList<>();
	private String                name;
	public static ConfigBase      config;
	private ConfigVersion         cfgVersion = new ConfigVersion(1, 1, 0);
	private boolean               setConfig;

	public Module(String name) {
		this.setConfig = true;
		this.name      = name;
		addFeatures();
	}

	public Module() {
		this.setConfig = false;
		addFeatures();
	}

	public abstract void addFeatures ();

	public void preInit () {
		features.stream().filter(Feature::usesEvents).forEach(MinecraftForge.EVENT_BUS::register);
		features.forEach(Feature::preInit);
	}

	public void proxyPreInit () {
		features.stream().filter(Feature::sidedProxy).forEach(MinecraftForge.EVENT_BUS::register);
		features.forEach(Feature::proxyPreInit);
	}

	public void setupConfig (FMLPreInitializationEvent event) {
		if (setConfig) {
			File file = new File(event.getModConfigurationDirectory().toString() + "/GalacticTweaks/" + name + ".cfg");
			config = new ConfigBase(file, cfgVersion); 
			ConfigManager.sync(Ref.MOD_ID, Type.INSTANCE);
			config.getConfig().load();
		}
	}

	public void init () {
		features.forEach(Feature::init);
	}

	public void postInit () {
		features.forEach(Feature::postInit);
	}

	public void proxyInit () {
		features.forEach(Feature::proxyInit);
	}

	public void proxyPostInit () {
		features.forEach(Feature::proxyPostInit);
	}
	
	public void serverStartingEvent(FMLServerStartingEvent event) {
		features.forEach(feature -> feature.ServerStartingEvent(event));
	}

	public Configuration getConfig () {
		return config.getConfig();
	}

	/**
	 * Method in every feature class. You can just specify this in a class and auto adds to GalacticTweaks.cfg
	 */
	public void syncConfig () {
		features.forEach(feature -> {
			feature.syncConfig(feature.category());
			config.addElement(feature.category());
			config.getConfig().addCustomCategoryComment(feature.category(), feature.comment());
			config.getConfig().setCategoryPropertyOrder(feature.category(), feature.propOrder);
			if (config.getConfig().hasChanged())
				config.getConfig().save();
		});
	}

	protected void registerFeature (Feature feature) {
		features.add(feature);
	}

	public void registerPacket (SimpleNetworkWrapper network) {
		features.forEach(feature -> feature.registerPacket(network));
	}
}

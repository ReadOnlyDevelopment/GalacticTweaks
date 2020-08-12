package net.rom.gctweaks.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;

public abstract class Module {
	private List<Feature>         features  = new ArrayList<>();
	private List<NoConfigFeature> features2 = new ArrayList<>();
	private String                name;
	private Configuration         config;
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
		features2.stream().filter(NoConfigFeature::usesEvents).forEach(MinecraftForge.EVENT_BUS::register);
		features2.forEach(NoConfigFeature::preInit);
	}

	public void proxyPreInit () {
		features.stream().filter(Feature::sidedProxy).forEach(MinecraftForge.EVENT_BUS::register);
		features.forEach(Feature::proxyPreInit);
		features2.stream().filter(NoConfigFeature::sidedProxy).forEach(MinecraftForge.EVENT_BUS::register);
		features2.forEach(NoConfigFeature::proxyPreInit);
	}

	public void setupConfig (FMLPreInitializationEvent event) {
		if (setConfig) {
			File file = new File(event.getModConfigurationDirectory().toString() + "/GalacticTweaks/" + name + ".cfg");
			config = new Configuration(file, "3");
			config.load();
		}
	}

	public void init () {
		features.forEach(Feature::init);
		features2.forEach(NoConfigFeature::init);
	}

	public void postInit () {
		features.forEach(Feature::postInit);
		features2.forEach(NoConfigFeature::postInit);
	}

	public void proxyInit () {
		features.forEach(Feature::proxyInit);
		features2.forEach(NoConfigFeature::proxyInit);
	}

	public void proxyPostInit () {
		features.forEach(Feature::proxyPostInit);
		features2.forEach(NoConfigFeature::proxyPostInit);
	}

	public Configuration getConfig () {
		return config;
	}

	public void syncConfig () {
		features.forEach(feature -> {
			feature.syncConfig(config, feature.category());
			config.addCustomCategoryComment(feature.category()[0], feature.comment());
			if (config.hasChanged())
				config.save();
		});
	}

	protected void registerFeature (Feature feature) {
		features.add(feature);
	}

	protected void registerFeature (NoConfigFeature feature) {
		features2.add(feature);
	}

	public void registerPacket (SimpleNetworkWrapper network) {
		features.forEach(feature -> feature.registerPacket(network));
		features2.forEach(feature -> feature.registerPacket(network));
	}
}

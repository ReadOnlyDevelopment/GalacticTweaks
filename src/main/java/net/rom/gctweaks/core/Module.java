package net.rom.gctweaks.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.rom.gctweaks.GalacticTweaks;

public abstract class Module {
	private List<Feature> features = new ArrayList<>();
	private String        name;
	private Configuration config;

	public Module(String name) {
		this.name = name;
		addFeatures();
	}

	public abstract void addFeatures();

	public void preInit() {
		features.stream().filter(Feature::usesEvents).forEach(MinecraftForge.EVENT_BUS::register);
		features.stream().filter(Feature::usesEvents).forEach(feature -> GalacticTweaks.logger.info("Registered Event Handler class {}", feature.getClass().getName()));
		features.forEach(Feature::preInit);
	}

	public void proxyPreInit() {
		features.stream().filter(Feature::sidedProxy).forEach(MinecraftForge.EVENT_BUS::register);
		features.forEach(Feature::proxyPreInit);
	}

	public void setupConfig(FMLPreInitializationEvent event) {
		File   file    = new File(event.getModConfigurationDirectory().toString() + "/GalacticTweaks/" + name + ".cfg");
		config = new Configuration(file, "3");
		config.load();
		GalacticTweaks.logger.bigInfo("Loaded Version: %s | Defined Version: %s", config.getLoadedConfigVersion(), config.getDefinedConfigVersion());

	}

	public void init() {
		features.forEach(Feature::init);
	}

	public void postInit() {
		features.forEach(Feature::postInit);
	}

	public void proxyInit() {
		features.forEach(Feature::proxyInit);
	}

	public void proxyPostInit() {
		features.forEach(Feature::proxyPostInit);
	}

	public Configuration getConfig() {
		return config;
	}

	public void syncConfig() {
		features.forEach(feature -> {
			feature.syncConfig(config, feature.category());
			config.addCustomCategoryComment(feature.category()[0], feature.comment());
			if (config.hasChanged())
				config.save();
		});
	}

	protected void registerFeature(Feature feature) {
		features.add(feature);
	}

	public void registerPacket(SimpleNetworkWrapper network) {
		features.forEach(feature -> feature.registerPacket(network));
	}
}

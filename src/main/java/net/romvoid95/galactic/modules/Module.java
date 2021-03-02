package net.romvoid95.galactic.modules;

import java.io.*;
import java.util.*;

import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.event.*;
import net.romvoid95.api.ReadOnlyConfig.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.feature.*;

public abstract class Module {
	private List<Feature> features = new ArrayList<>();
	private List<Feature> enabledFeatures = new ArrayList<>();
	private String name;
	public static FeatureConfigs config = new FeatureConfigs(FeatureConfigs.class, new ConfigVersion(Info.VERSION));

	public Module(String name) {
		this.name = name;
		addClientFeatures();
		addCommonFeatures();
	}

	public abstract void addClientFeatures();

	public abstract void addCommonFeatures();

	public void setupConfig() {
		File file = new File(GalacticTweaks.modFolder, name + ".cfg");
		config.setConfigFile(file);
		config.loadConfig();
		features.forEach(feat -> {
			config.getConfig().getCategory(feat.category()).setComment(feat.comment());
		});	
	}
	
	public void handleFeatures() {
		features.stream().filter(Feature::isEnabled).forEach(feature -> {
			GalacticTweaks.LOG.debug("Feature Enabled: {}", feature.getClass().getSimpleName());
			enabledFeatures.add(feature);
		});
	}

	public void preInit() {
		enabledFeatures.stream().filter(Feature::usesEvents).forEach(MinecraftForge.EVENT_BUS::register);
		enabledFeatures.forEach(Feature::preInit);
	}

	public void proxyPreInit() {
		enabledFeatures.stream().filter(Feature::sidedProxy).forEach(MinecraftForge.EVENT_BUS::register);
		enabledFeatures.forEach(Feature::proxyPreInit);
	}

	public void init() {
		enabledFeatures.forEach(Feature::init);
	}

	public void postInit() {
		enabledFeatures.forEach(Feature::postInit);
	}

	public void proxyInit() {
		enabledFeatures.forEach(Feature::proxyInit);
	}

	public void proxyPostInit() {
		enabledFeatures.forEach(Feature::proxyPostInit);
	}

	public void serverStartingEvent(FMLServerStartingEvent event) {
		enabledFeatures.forEach(feature -> feature.ServerStartingEvent(event));
	}

	protected void registerFeature(Feature feature) {
		features.add(feature);
	}
}

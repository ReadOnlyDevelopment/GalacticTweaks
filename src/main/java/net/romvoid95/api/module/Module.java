package net.romvoid95.api.module;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.core.GCTLog;

public abstract class Module {

	protected List<Feature>	features		= new ArrayList<>();
	private List<Feature>	enabledFeatures	= new ArrayList<>();

	public Module() {
		addClientFeatures();
		addCommonFeatures();
	}

	public abstract void addClientFeatures();

	public abstract void addCommonFeatures();

	public abstract void setupConfig();

	public void handleFeatures() {
		features.stream().filter(Feature::isEnabled).forEach(feature -> {
			GCTLog.debug("Feature Enabled: " + feature.getClass().getSimpleName());
			enabledFeatures.add(feature);
		});
	}

	public void preInit() {
		setupConfig();
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

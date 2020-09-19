package net.romvoid95.gctweaks.gc;

import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.gc.features.*;
import net.romvoid95.gctweaks.gc.features.DimensionalComets;
import net.romvoid95.gctweaks.gc.features.galaxyfeature.SeperateAddonPlanets;
import net.romvoid95.gctweaks.gc.features.generation.DisableDungeonGeneration;
import net.romvoid95.gctweaks.gc.features.oxygenfeature.SpawnWithOxygenEquip;
import net.romvoid95.gctweaks.gc.features.sprfeature.SpaceRaceFeature;

public class GalacticraftModule extends Module {

	public GalacticraftModule(String name) {
		super(name);
	}

	@Override
	public void addFeatures() {
		registerFeature(new MobsBreatheInSpace());
		registerFeature(new SpawnWithOxygenEquip());
		registerFeature(new NoSpaceMusic());
		registerFeature(new CompressorFixes());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new FixAsmodeusMapIcons());
		registerFeature(new DimensionalComets());
		registerFeature(new UnlockSchematics());

		//registerFeature(new OverworldComets()); No longer needed. Replaced with DimensionalComets.java
		//registerFeature(new DisableDungeonGeneration()); I couldn't figure this our right now. Later
	}
}

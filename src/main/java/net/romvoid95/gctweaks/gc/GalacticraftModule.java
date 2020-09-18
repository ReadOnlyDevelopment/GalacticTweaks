package net.romvoid95.gctweaks.gc;

import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.gc.features.CompressorFixes;
import net.romvoid95.gctweaks.gc.features.FixAsmodeusMapIcons;
import net.romvoid95.gctweaks.gc.features.MobsBreatheInSpace;
import net.romvoid95.gctweaks.gc.features.NoSpaceMusic;
import net.romvoid95.gctweaks.gc.features.OverworldComets;
import net.romvoid95.gctweaks.gc.features.galaxyfeature.SeperateAddonPlanets;
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
		registerFeature(new OverworldComets());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new FixAsmodeusMapIcons());
	}
}

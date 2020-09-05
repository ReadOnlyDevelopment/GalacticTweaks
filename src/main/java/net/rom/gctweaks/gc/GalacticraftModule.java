package net.rom.gctweaks.gc;

import net.rom.gctweaks.base.Module;
import net.rom.gctweaks.gc.features.CompressorFixes;
import net.rom.gctweaks.gc.features.FixAsmodeusMapIcons;
import net.rom.gctweaks.gc.features.MobsBreatheInSpace;
import net.rom.gctweaks.gc.features.NoSpaceMusic;
import net.rom.gctweaks.gc.features.OverworldComets;
import net.rom.gctweaks.gc.features.galaxyfeature.SeperateAddonPlanets;
import net.rom.gctweaks.gc.features.oxygenfeature.SpawnWithOxygenEquip;
import net.rom.gctweaks.gc.features.sprfeature.SpaceRaceFeature;

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

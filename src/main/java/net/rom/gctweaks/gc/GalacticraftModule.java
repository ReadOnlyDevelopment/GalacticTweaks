package net.rom.gctweaks.gc;

import net.rom.gctweaks.base.Module;
import net.rom.gctweaks.gc.features.*;
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
		registerFeature(new DimensionalComets());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new FixAsmodeusMapIcons());

		//registerFeature(new OverworldComets()); BYE BYE POINTLESS FEATURE -SebaSphere

	}
}

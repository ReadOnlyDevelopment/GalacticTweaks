package net.romvoid95.gctweaks.gc;

import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.gc.features.*;
import net.romvoid95.gctweaks.gc.features.galaxy.SeperateAddonPlanets;
import net.romvoid95.gctweaks.gc.features.oxygen.SpawnWithOxygenEquip;
import net.romvoid95.gctweaks.gc.features.schematic.UnlockSchematics;
import net.romvoid95.gctweaks.gc.features.spawndim.SpawnDimension;
import net.romvoid95.gctweaks.gc.features.spawnitems.SpaceRaceFeature;

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
		registerFeature(new SpawnDimension());
		
	}
}

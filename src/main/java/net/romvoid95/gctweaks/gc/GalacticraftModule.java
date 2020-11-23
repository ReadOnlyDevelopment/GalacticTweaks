package net.romvoid95.gctweaks.gc;

import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.gc.features.CompressorFixes;
import net.romvoid95.gctweaks.gc.features.DimensionalComets;
import net.romvoid95.gctweaks.gc.features.FixAsmodeusMapIcons;
import net.romvoid95.gctweaks.gc.features.MobsBreatheInSpace;
import net.romvoid95.gctweaks.gc.features.NoSpaceMusic;
import net.romvoid95.gctweaks.gc.features.SpawnDimension;
import net.romvoid95.gctweaks.gc.features.galaxy.SeperateAddonPlanets;
import net.romvoid95.gctweaks.gc.features.oxytoggle.OxygenToggle;
import net.romvoid95.gctweaks.gc.features.schematic.UnlockSchematics;
import net.romvoid95.gctweaks.gc.features.spacerace.SpaceRaceFeature;
import net.romvoid95.gctweaks.gc.features.spawnitems.SpawnWithOxygenEquip;

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
		registerFeature(new OxygenToggle());	
	}
}

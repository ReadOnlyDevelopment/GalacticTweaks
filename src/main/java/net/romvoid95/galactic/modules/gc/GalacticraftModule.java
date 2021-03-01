package net.romvoid95.galactic.modules.gc;

import net.romvoid95.galactic.feature.client.*;
import net.romvoid95.galactic.feature.common.*;
import net.romvoid95.galactic.modules.*;

public class GalacticraftModule extends Module {
	
	public GalacticraftModule() {
		super("GalacticraftTweaks");
		//super(GalacticraftModule.class.getSimpleName());
	}

	@Override
	public void addClientFeatures() {
		registerFeature(new NoSpaceMusic());
		registerFeature(new FixAsmodeusMapIcons());
	}

	@Override
	public void addCommonFeatures() {
		registerFeature(new MobsBreatheInSpace());
		registerFeature(new OxygenSpawnGear());
		registerFeature(new CompressorFixes());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new DimensionalComets());
		registerFeature(new UnlockSchematics());
		registerFeature(new SpawnDimension());
		registerFeature(new OxygenToggle());	
	}
}

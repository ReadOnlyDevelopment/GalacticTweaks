package net.romvoid95.galactic.modules.gc;

import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.feature.client.FixAsmodeusMapIcons;
import net.romvoid95.galactic.feature.client.NoSpaceMusic;
import net.romvoid95.galactic.feature.common.BreatheableDimensions;
import net.romvoid95.galactic.feature.common.CompressorFixes;
import net.romvoid95.galactic.feature.common.MobsBreatheInSpace;
import net.romvoid95.galactic.feature.common.OxygenSpawnGear;
import net.romvoid95.galactic.feature.common.SeperateAddonPlanets;
import net.romvoid95.galactic.feature.common.SpaceRaceFeature;
import net.romvoid95.galactic.feature.common.SpawnDimension;
import net.romvoid95.galactic.feature.common.UnlockSchematics;
import net.romvoid95.galactic.feature.common.admintools.DirectTeleporter;
import net.romvoid95.galactic.modules.Module;

public class GalacticraftModule extends Module {

	public GalacticraftModule() {
		super("GalacticraftTweaks");
	}

	@Override
	public void addClientFeatures() {
		registerFeature(new FixAsmodeusMapIcons());
		registerFeature(new NoSpaceMusic());
	}

	@Override
	public void addCommonFeatures() {
		registerFeature(new DirectTeleporter());
		registerFeature(new BreatheableDimensions());
		registerFeature(new CompressorFixes());
		registerFeature(new MobsBreatheInSpace());
		registerFeature(new OxygenSpawnGear());
		registerFeature(new SeperateAddonPlanets());
		registerFeature(new SpaceRaceFeature());
		registerFeature(new SpawnDimension());
		registerFeature(new UnlockSchematics());
	}
}

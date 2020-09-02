package net.rom.gctweaks.galacticraftchanges;

import net.rom.gctweaks.core.Module;
import net.rom.gctweaks.galacticraftchanges.asteroids.EditableAsteroids;
import net.rom.gctweaks.galacticraftchanges.seperategalaxy.SeperateAddonPlanets;

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
		registerFeature(new EditableAsteroids());
	}

}

package net.rom.gctweaks.galacticraftchanges;

import net.rom.gctweaks.core.Module;

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
	}

}

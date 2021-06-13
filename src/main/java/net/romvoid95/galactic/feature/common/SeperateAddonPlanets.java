package net.romvoid95.galactic.feature.common;

import asmodeuscore.core.astronomy.BodiesRegistry;
import net.romvoid95.api.GalacticraftAddon;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.core.gc.PlanetData;
import net.romvoid95.galactic.feature.FeatureConfigs;

public class SeperateAddonPlanets extends Feature {

	public SeperateAddonPlanets() {
		this.category = "SeperateAddonPlanets";
		this.categoryComment = "Move Duplicate Sol Planets to a new galaxy"
				+ "\nTHIS FEATURE WILL NOT BE EXTENDED OR ADDED TO IN FUTURE VERSIONS\nANY CRASHES OR BUGS RESULTING FROM THIS OPTION BEING ENABLED\n"
				+ "SHOULD BE REPORTED TO THIS MODS ISSUE TRACKER NOT THE PLANETS ADDON DEV \n\nUse at your own discretion";
	}

	@Override
	public void postInit() {
		String modid = FeatureConfigs.modid.get();
		if (checkIfPresent()) {
			BodiesRegistry.setMaxTier(10);
			PlanetData data = new PlanetData();
			data.create(modid);
		}
	}

	private Boolean checkIfPresent() {
		boolean oneLoaded = true;
		if (!GalacticraftAddon.EXTRAPLANETS.isLoaded()) {
			GalacticTweaks.LOG.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + this.category + "]");
			GalacticTweaks.LOG.error("The Mod  [%s] MUST be installed to use this Stability ",
					GalacticraftAddon.EXTRAPLANETS.toString());
			oneLoaded = false;
		}
		if (!GalacticraftAddon.GALAXYSPACE.isLoaded()) {
			GalacticTweaks.LOG.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + this.category + "]");
			GalacticTweaks.LOG.error("The Mod  [%s] MUST be installed to use this Stability ",
					GalacticraftAddon.GALAXYSPACE.toString());
			oneLoaded = false;
		}
		return oneLoaded;
	}

	@Override
	public boolean isEnabled() {
		return FeatureConfigs.SEPERATE_ADDONPLANETS;
	}
}

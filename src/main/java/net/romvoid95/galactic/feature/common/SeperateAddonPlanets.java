package net.romvoid95.galactic.feature.common;

import static net.romvoid95.api.docs.Stability.*;

import asmodeuscore.core.astronomy.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.core.*;
import net.romvoid95.galactic.core.gc.*;
import net.romvoid95.galactic.feature.*;

@Doc(
	value = "Seperate Duplicate Planets",
	comment = "Feature that allows you to move the duplicate planets that both ExtraPlanets and GalaxySpace add to a different Galaxy.\n" 
			+ "The plantes that are moved are determined by the addonid that is specified in the config file.",
	stability = STABLE
)
public class SeperateAddonPlanets extends Feature {

	@Override
	public String category() {
		return "SeperateAddonPlanets";
	}

	@Override
	public String comment() {
		return "Move Duplicate Sol Planets to a new galaxy"
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
		if (!CompatMods.EXTRAPLANETS.isLoaded()) {
			GalacticTweaks.LOG.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + category() + "]");
			GalacticTweaks.LOG.error("The Mod  [%s] MUST be installed to use this Stability ",
					CompatMods.EXTRAPLANETS.toString());
			oneLoaded = false;
		}
		if (!CompatMods.GALAXYSPACE.isLoaded()) {
			GalacticTweaks.LOG.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + category() + "]");
			GalacticTweaks.LOG.error("The Mod  [%s] MUST be installed to use this Stability ",
					CompatMods.GALAXYSPACE.toString());
			oneLoaded = false;
		}
		return oneLoaded;
	}

	@Override
	public boolean isEnabled() {
		return FeatureConfigs.SEPERATE_ADDONPLANETS;
	}
}

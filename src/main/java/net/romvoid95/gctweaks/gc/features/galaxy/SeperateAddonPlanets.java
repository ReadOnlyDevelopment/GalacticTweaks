package net.romvoid95.gctweaks.gc.features.galaxy;

import asmodeuscore.core.astronomy.BodiesRegistry;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.base.core.compat.CompatMods;

public class SeperateAddonPlanets extends Feature {

	public static boolean seperatePlanets;
	public static String  modid;
	private String[] valid = {"none" , "extraplanets", "galaxyspace"};

	@Override
	public String category () {
		return "seperatePlanets" ;
	}

	@Override
	public String comment () {
		return "Move Duplicate Sol Planets to a new galaxy"
				+ "\nTHIS FEATURE WILL NOT BE EXTENDED OR ADDED TO IN FUTURE VERSIONS\nANY CRASHES OR BUGS RESULTING FROM THIS OPTION BEING ENABLED\n"
				+ "SHOULD BE REPORTED TO THIS MODS ISSUE TRACKER NOT THE PLANETS ADDON DEV \n\nUse at your own discretion";
	}

	@Override
	public void syncConfig (String category) {
		seperatePlanets = set(category, "enableFeature", false);
		modid           = setFormated(category, "planetsFromMod", "none", valid);
	}

	@Override
	public void postInit () {
		if (seperatePlanets && CompatMods.EXTRAPLANETS.isLoaded() && CompatMods.GALAXYSPACE.isLoaded()) {
			BodiesRegistry.setMaxTier(10);
			
			switch (modid) {
			case "extraplanets":
				GCSystems.init();
				GCPlanets.initEp();
				break;
			case "galaxyspace":
				GCSystems.init();
				GCPlanets.initGs();
				break;
			case "none":
				break;
			}
		}
	}
}

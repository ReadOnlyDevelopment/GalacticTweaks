package net.rom.gctweaks.galacticraftchanges;

import net.minecraftforge.common.config.Configuration;
import net.rom.gctweaks.core.Feature;
import net.rom.gctweaks.core.GCPlanets;
import net.rom.gctweaks.core.GCSystems;
import net.rom.gctweaks.core.compat.CompatMods;

public class SeperateAddonPlanets extends Feature {

	public static boolean seperatePlanets;
	public static String  modid;

	@Override
	public String[] category () {
		return new String[] { "new-galaxy" };
	}

	@Override
	public String comment () {
		return "Move Duplicate Sol Planets to a new galaxy"
				+ "\nTHIS FEATURE WILL NOT BE EXTENDED OR ADDED TO IN FUTURE VERSIONS\nANY CRASHES OR BUGS RESULTING FROM THIS OPTION BEING ENABLED\n"
				+ "SHOULD BE REPORTED TO THIS MODS ISSUE TRACKER NOT THE PLANETS ADDON DEV \n\nUse at your own discretion";
	}

	@Override
	public void syncConfig (Configuration config, String[] category) {
		seperatePlanets = config
				.get(category[0], "00-Seperate Duplicate Planets", false, "Set to true if you want Seperate Addon Planets\n"
						+ "Note: AsmodeusCore, ExtraPlanets & GalaxySpace must be installed ")
				.getBoolean();
		modid           = config
				.get(category[0], "01-Addon Planets To Move", "none", "[valid: extraplanets | galaxyspace | none, default: none]", new String[] {
						"extraplanets", "galaxyspace" })
				.getString();
	}

	@Override
	public void preInit () {
		if (seperatePlanets && CompatMods.EXTRAPLANETS.isLoaded() && CompatMods.GALAXYSPACE.isLoaded()) {

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

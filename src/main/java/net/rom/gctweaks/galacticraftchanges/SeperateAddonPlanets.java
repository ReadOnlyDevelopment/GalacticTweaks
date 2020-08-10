package net.rom.gctweaks.galacticraftchanges;

import net.minecraftforge.common.config.Configuration;
import net.rom.gctweaks.GalacticTweaks;
import net.rom.gctweaks.core.Feature;
import net.rom.gctweaks.core.GCPlanets;
import net.rom.gctweaks.core.GCSystems;
import net.rom.gctweaks.core.compat.CompatMods;

public class SeperateAddonPlanets extends Feature {

	public static boolean seperatePlanets;

	@Override
	public String[] category() {
		return new String[] {"new-galaxy"};
	}

	@Override
	public String comment() {
		return "Moves ExtraPlanet Planets to a new galazxy"
				+ "\n## WANRING: THIS OPTION ASSUMES ASMODEUSCORE IS INSTALLED. IF NOT, THIS WILL CRASH";
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		seperatePlanets = config
				.get(category[0], "seperatePlanets", false, "Set to true if you want Seperate Addon Planets\n"
						+ "Note: AsmodeusCore, ExtraPlanets & GalaxySpace must be installed ")
				.getBoolean();
	}


	@Override
	public void postInit() {
		if (seperatePlanets && CompatMods.EXTRAPLANETS.isLoaded() && CompatMods.GALAXYSPACE.isLoaded()) {
			GalacticTweaks.logger.info("{} is loaded, Moving planets to new Galaxy!",
					CompatMods.EXTRAPLANETS.getName());
			GCSystems.init();
			GCPlanets.init();

		}
	}
}

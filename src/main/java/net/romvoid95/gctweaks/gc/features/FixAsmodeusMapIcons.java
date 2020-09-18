package net.romvoid95.gctweaks.gc.features;

import java.util.Map;
import java.util.Map.Entry;

import asmodeuscore.core.configs.AsmodeusConfig;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import net.minecraftforge.common.config.Configuration;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.base.core.compat.CompatMods;

public class FixAsmodeusMapIcons extends Feature {

	public static boolean fixIcons;

	@Override
	public String comment () {
		return "Fixes the Planet/Star icon sizes from More Planets & ZollernGalaxy";
	}

	@Override
	public String[] category () {
		return new String[] { "fix-icons" };
	}

	@Override
	public void syncConfig (Configuration config, String[] category) {
		fixIcons = config
				.get(category[0], "fixAsmodeusMapIcons", false, "Set to true to enable Fix Icons feature\nOnly affects icons if enableNewGalaxyMap is enabled in 'Asmodeuscore/core.conf'")
				.getBoolean();
	}

	@Override
	public void postInit () {
		if (FixAsmodeusMapIcons.fixIcons) {
			if (CompatMods.ZOLLERN.isLoaded() || CompatMods.MOREPLANETS.isLoaded()) {
				if (AsmodeusConfig.enableNewGalaxyMap) {
					fixPlanetIcons();
				}
			}
		}
	}
	

	public static void fixPlanetIcons () {
		for (Map.Entry<String, Planet> planet : GalaxyRegistry.getRegisteredPlanets().entrySet()) {
			planet.getValue().setRelativeSize(1.0F);
		}
		for (Entry<String, SolarSystem> star : GalaxyRegistry.getRegisteredSolarSystems().entrySet()) {
			star.getValue().getMainStar().setRelativeSize(1.0F);
		}
	}
}

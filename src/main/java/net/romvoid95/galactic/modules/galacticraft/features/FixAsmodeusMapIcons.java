package net.romvoid95.galactic.modules.galacticraft.features;

import asmodeuscore.core.configs.AsmodeusConfig;
import net.romvoid95.api.GalacticraftAddon;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.core.gc.PlanetGroups;
import net.romvoid95.galactic.modules.galacticraft.GalacticraftModuleConfig;

public class FixAsmodeusMapIcons extends Feature {

	public FixAsmodeusMapIcons() {
		this.category = "AsmodeusMapIcons";
		this.categoryComment = "Fixes the Planet/Star icon sizes from More Planets & ZollernGalaxy";
	}

	@Override
	public void proxyPostInit () {
		// If the feature is enabled and we are using AsmodeusCore's Map
		if (AsmodeusConfig.enableNewGalaxyMap) {

			// Only try and fix Zollern Icons if loaded
			if (GalacticraftAddon.ZOLLERNGALAXY.isLoaded()) {

				// Zollern Stars are way too big on asmodeus map, bring em down to size
				PlanetGroups.zollernStarGroup().forEach(star -> star.setRelativeSize(1.0F));

				// Same with the planets. Repeat
				PlanetGroups.zollernPlanets().forEach(planet -> planet.setRelativeSize(1.0F));
			}

			// Only try and fix MorePlanets Icons if loaded
			if(GalacticraftAddon.MOREPLANETS.isLoaded()) {
				PlanetGroups.morePlanetsGroup().forEach(planet -> {

					// We only want Chalos
					if(planet.getUnlocalizedName().equalsIgnoreCase("CHALOS")) {

						// Reduce it from 10 -> 4
						planet.setRelativeSize(4.0F);
					}
				});
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.FIX_MAP_ICONS;
	}
}

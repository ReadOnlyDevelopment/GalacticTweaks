package net.romvoid95.galactic.feature.client;

import asmodeuscore.core.configs.*;
import net.romvoid95.galactic.core.*;
import net.romvoid95.galactic.feature.*;

public class FixAsmodeusMapIcons extends Feature  {

	@Override
	public String comment () {
		return "Fixes the Planet/Star icon sizes from More Planets & ZollernGalaxy";
	}

	@Override
	public String category () {
		return  "AsmodeusMapIcons";
	}

	@Override
	public void postInit () {
		// If the feature is enabled and we are using AsmodeusCore's Map
		if (AsmodeusConfig.enableNewGalaxyMap) {
			
			// Only try and fix Zollern Icons if loaded
			if (CompatMods.ZOLLERN.isLoaded()) {
				
				// Zollern Stars are way too big on asmodeus map, bring em down to size
				PlanetGroups.zollernStarGroup().forEach(star -> star.setRelativeSize(1.0F));
				
				// Same with the planets. Repeat
				PlanetGroups.zollernPlanets().forEach(planet -> planet.setRelativeSize(1.0F));
			}
			
			// Only try and fix MorePlanets Icons if loaded
			if(CompatMods.MOREPLANETS.isLoaded()) {
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
		return FeatureConfigs.FIX_MAP_ICONS;
	}
}

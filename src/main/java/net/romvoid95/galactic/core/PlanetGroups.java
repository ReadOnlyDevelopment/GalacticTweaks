package net.romvoid95.galactic.core;

import com.mjr.extraplanets.planets.*;

import galaxyspace.systems.SolarSystem.*;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.core.gc.*;
import stevekung.mods.moreplanets.init.*;
import zollerngalaxy.celestial.*;

public class PlanetGroups {

	private static CelestialGroup<Planet> zollernGroup; // Zollern
	private static CelestialGroup<Star> zollernStarGroup; // Zollern
	private static CelestialGroup<Planet> morePlanetsGroup; // More Planets
	private static CelestialGroup<Planet> extraPlanetsGroup; // Extra Planets
	private static CelestialGroup<Planet> galaxySpaceGroup; // GalaxySpace

	public static CelestialGroup<Planet> zollernPlanets() {
		if (zollernGroup == null) {
			GalacticTweaks.LOG.info("Building ZollernGalaxy Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<Planet>();
				p.addAll(
						ZGPlanets.planetEden, 
						ZGPlanets.planetZollus,
						ZGPlanets.planetKriffon, 
						ZGPlanets.planetPurgot, 
						ZGPlanets.planetXathius, 
						ZGPlanets.planetOasis,
						ZGPlanets.planetXantheon, 
						ZGPlanets.planetCandora,
						ZGPlanets.planetAtheon, 
						ZGPlanets.planetPerdita,
						ZGPlanets.planetAltum, 
						ZGPlanets.planetCaligro, 
						ZGPlanets.planetExodus
				);
			zollernGroup = p;
		}
		return zollernGroup;
	}

	public static CelestialGroup<Star> zollernStarGroup() {
		if (zollernStarGroup == null) {
			GalacticTweaks.LOG.info("Building ZollernGalaxy Star List");
			CelestialGroup<Star> p = new CelestialGroup<Star>();
				p.addAll(
						ZGPlanets.starPraedyth, 
						ZGPlanets.starSol2,
						ZGPlanets.starPsios
					);
				zollernStarGroup = p;
		}
		return zollernStarGroup;
	}

	public static CelestialGroup<Planet> morePlanetsGroup() {
		if (morePlanetsGroup == null) {
			GalacticTweaks.LOG.info("Building MorePlanets Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<Planet>();
				p.addAll(
						MPPlanets.CHALOS, 
						MPPlanets.DIONA, 
						MPPlanets.NIBIRU,
						MPPlanets.FRONOS
					);
				morePlanetsGroup = p;
		}
		return morePlanetsGroup;
	}

	public static CelestialGroup<Planet> extraPlanetsGroup() {
		if (extraPlanetsGroup == null) {
			GalacticTweaks.LOG.info("Building ExtraPlanets Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<Planet>();
				p.addAll(
						ExtraPlanets_Planets.MERCURY,
						ExtraPlanets_Planets.CERES, 
						ExtraPlanets_Planets.JUPITER, 
						ExtraPlanets_Planets.SATURN,
						ExtraPlanets_Planets.URANUS, 
						ExtraPlanets_Planets.NEPTUNE, 
						ExtraPlanets_Planets.PLUTO,
						ExtraPlanets_Planets.ERIS
					);
				extraPlanetsGroup = p;
		}
		return extraPlanetsGroup;
	}

	public static CelestialGroup<Planet> galaxySpaceGroup() {
		if (galaxySpaceGroup == null) {
			GalacticTweaks.LOG.info("Building GalaxySpace Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<Planet>();
				p.addAll(
						SolarSystemBodies.planetMercury,
						SolarSystemBodies.planetCeres,
						SolarSystemBodies.planetJupiter,
						SolarSystemBodies.planetSaturn,
						SolarSystemBodies.planetUranus,
						SolarSystemBodies.planetNeptune,
						SolarSystemBodies.planetPluto,
						SolarSystemBodies.planetKuiperBelt,
						SolarSystemBodies.planetHaumea,
						SolarSystemBodies.planetMakemake,
						SolarSystemBodies.planetEris,
						SolarSystemBodies.planetDeeDee
					);
			galaxySpaceGroup = p;
		}
		return galaxySpaceGroup;
	}
}

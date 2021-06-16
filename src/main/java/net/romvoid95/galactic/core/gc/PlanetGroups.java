package net.romvoid95.galactic.core.gc;

import com.mjr.extraplanets.moons.ExtraPlanets_Moons;
import com.mjr.extraplanets.planets.ExtraPlanets_Planets;

import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import net.romvoid95.galactic.core.GCTLog;
import stevekung.mods.moreplanets.init.MPPlanets;
import zollerngalaxy.celestial.ZGPlanets;

public class PlanetGroups {

	private static CelestialGroup<Planet> zollernGroup; // Zollern
	private static CelestialGroup<Star> zollernStarGroup; // Zollern
	private static CelestialGroup<Planet> morePlanetsGroup; // More Planets
	private static CelestialGroup<Planet> extraPlanetsGroup; // Extra Planets
	private static CelestialGroup<Planet> galaxySpaceGroup; // GalaxySpace

	private static CelestialGroup<Moon> galaxySpaceMoonGroup;
	private static CelestialGroup<Moon> extraPlanetsMoonGroup;

	public static CelestialGroup<Planet> zollernPlanets() {
		if (zollernGroup == null) {
			GCTLog.info("Building ZollernGalaxy Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<>();
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
			GCTLog.info("Building ZollernGalaxy Star List");
			CelestialGroup<Star> p = new CelestialGroup<>();
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
			GCTLog.info("Building MorePlanets Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<>();
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
			GCTLog.info("Building ExtraPlanets Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<>();
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
			GCTLog.info("Building GalaxySpace Planet List");
			CelestialGroup<Planet> p = new CelestialGroup<>();
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

	public static CelestialGroup<Moon> galaxySpaceMoonGroup() {
		if (galaxySpaceMoonGroup == null) {
			GCTLog.info("Building GalaxySpace Moon List");
			CelestialGroup<Moon> m = new CelestialGroup<>();
			m.addAll(
					SolarSystemBodies.phobosMars,
					SolarSystemBodies.deimosMars,
					SolarSystemBodies.ioJupiter,
					SolarSystemBodies.europaJupiter,
					SolarSystemBodies.ganymedeJupiter,
					SolarSystemBodies.callistoJupiter,
					SolarSystemBodies.mimasSaturn,
					SolarSystemBodies.enceladusSaturn,
					SolarSystemBodies.tethysSaturn,
					SolarSystemBodies.dioneSaturn,
					SolarSystemBodies.rheyaSaturn,
					SolarSystemBodies.titanSaturn,
					SolarSystemBodies.iapetusSaturn,
					SolarSystemBodies.mirandaUranus,
					SolarSystemBodies.arielUranus,
					SolarSystemBodies.umbrielUranus,
					SolarSystemBodies.titaniaUranus,
					SolarSystemBodies.oberonUranus,
					SolarSystemBodies.proteusNeptune,
					SolarSystemBodies.tritonNeptune,
					SolarSystemBodies.charonPluto
					);
			galaxySpaceMoonGroup = m;
		}
		return galaxySpaceMoonGroup;
	}

	public static CelestialGroup<Moon> extraPlanetsMoonGroup() {
		if (extraPlanetsMoonGroup == null) {
			GCTLog.info("Building ExtraPlanets Moon List");
			CelestialGroup<Moon> m = new CelestialGroup<>();
			m.addAll(
					ExtraPlanets_Moons.TRITON,
					ExtraPlanets_Moons.CALLISTO,
					ExtraPlanets_Moons.EUROPA,
					ExtraPlanets_Moons.GANYMEDE,
					ExtraPlanets_Moons.IO,
					ExtraPlanets_Moons.DEIMOS,
					ExtraPlanets_Moons.PHOBOS,
					ExtraPlanets_Moons.IAPETUS,
					ExtraPlanets_Moons.RHEA,
					ExtraPlanets_Moons.TITAN,
					ExtraPlanets_Moons.OBERON,
					ExtraPlanets_Moons.TITANIA,
					ExtraPlanets_Moons.CHARON,
					ExtraPlanets_Moons.NIX,
					ExtraPlanets_Moons.HYDRA,
					ExtraPlanets_Moons.DYSNOMIA
					);
			extraPlanetsMoonGroup = m;
		}
		return extraPlanetsMoonGroup;
	}
}

package net.rom.gctweaks.core;

import com.mjr.extraplanets.planets.ExtraPlanets_Planets;

import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import net.minecraft.util.ResourceLocation;

public class GCPlanets {
	public static Planet FAKE_OVERWORLD;
	public static Planet FAKE_ASTEROIDS;
	
	public static void initEp() {
		buildEp();
		build();
	}

	public static void initGs () {
		buildGs();
		build();
	}
	
	private static void buildEp () {
		ExtraPlanets_Planets.MERCURY.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.CERES.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.JUPITER.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.SATURN.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.URANUS.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.NEPTUNE.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.PLUTO.setParentSolarSystem(GCSystems.EP_SYSTEM);
		ExtraPlanets_Planets.ERIS.setParentSolarSystem(GCSystems.EP_SYSTEM);
	}
	
	private static void buildGs() {
		SolarSystemBodies.planetMercury.setParentSolarSystem(GCSystems.EP_SYSTEM);
		SolarSystemBodies.planetCeres.setParentSolarSystem(GCSystems.EP_SYSTEM);
		SolarSystemBodies.planetJupiter.setParentSolarSystem(GCSystems.EP_SYSTEM);
		SolarSystemBodies.planetSaturn.setParentSolarSystem(GCSystems.EP_SYSTEM);
		SolarSystemBodies.planetUranus.setParentSolarSystem(GCSystems.EP_SYSTEM);
		SolarSystemBodies.planetNeptune.setParentSolarSystem(GCSystems.EP_SYSTEM);
		SolarSystemBodies.planetPluto.setParentSolarSystem(GCSystems.EP_SYSTEM);
		//SolarSystemBodies.planetEris.setParentSolarSystem(GCSystems.EP_SYSTEM);
	}

	private static void build () {

		buildFakeEarth(FAKE_OVERWORLD, "fakeOverworld", GCSystems.EP_SYSTEM, GalacticraftCore.planetOverworld
				.getPhaseShift(), GalacticraftCore.planetOverworld.getRelativeDistanceFromCenter().scaledDistance);
		buildAsteroids(FAKE_ASTEROIDS, "fakeRocks", GCSystems.EP_SYSTEM, AsteroidsModule.planetAsteroids
				.getPhaseShift(), 1.375F);
	}

	public static void buildAsteroids (Planet planet, String planetName, SolarSystem solarSystem, float randomPhase, float au) {
		planet = new Planet(planetName).setParentSolarSystem(solarSystem);
		planet.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(au, au));
		planet.setRelativeOrbitTime(45.0F);
		planet.setPhaseShift((float) (Math.random() * (2 * Math.PI)));
		planet.setRelativeSize(1.0F);
		planet.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/asteroid.png"));
		planet.setUnreachable();
		GalaxyRegistry.registerPlanet(planet);
	}

	public static void buildFakeEarth (Planet planet, String planetName, SolarSystem solarSystem, float randomPhase, float au) {
		planet = new Planet(planetName).setParentSolarSystem(solarSystem);
		planet.setRelativeDistanceFromCenter(new ScalableDistance(au, au));
		planet.setPhaseShift(randomPhase);
		planet.setRelativeSize(1.0F);
		planet.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/earth.png"));
		planet.atmosphereComponent(EnumAtmosphericGas.NITROGEN).atmosphereComponent(EnumAtmosphericGas.OXYGEN)
				.atmosphereComponent(EnumAtmosphericGas.ARGON).atmosphereComponent(EnumAtmosphericGas.WATER);
		planet.setUnreachable();
		GalaxyRegistry.registerPlanet(planet);
	}
}

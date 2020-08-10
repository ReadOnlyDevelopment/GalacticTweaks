package net.rom.gctweaks.core;

import com.mjr.extraplanets.Config;
import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.planets.ExtraPlanets_Planets;
import com.mjr.extraplanets.planets.Ceres.TeleportTypeCeres;
import com.mjr.extraplanets.planets.Ceres.WorldProviderCeres;
import com.mjr.extraplanets.planets.Ceres.worldgen.CeresBiomes;
import com.mjr.extraplanets.planets.Eris.TeleportTypeEris;
import com.mjr.extraplanets.planets.Eris.WorldProviderEris;
import com.mjr.extraplanets.planets.Eris.worldgen.ErisBiomes;
import com.mjr.extraplanets.planets.Jupiter.TeleportTypeJupiter;
import com.mjr.extraplanets.planets.Jupiter.WorldProviderJupiter;
import com.mjr.extraplanets.planets.Jupiter.worldgen.JupiterBiomes;
import com.mjr.extraplanets.planets.Mercury.TeleportTypeMercury;
import com.mjr.extraplanets.planets.Mercury.WorldProviderMercury;
import com.mjr.extraplanets.planets.Mercury.worldgen.MercuryBiomes;
import com.mjr.extraplanets.planets.Neptune.TeleportTypeNeptune;
import com.mjr.extraplanets.planets.Neptune.WorldProviderNeptune;
import com.mjr.extraplanets.planets.Neptune.worldgen.NeptuneBiomes;
import com.mjr.extraplanets.planets.Pluto.TeleportTypePluto;
import com.mjr.extraplanets.planets.Pluto.WorldProviderPluto;
import com.mjr.extraplanets.planets.Pluto.worldgen.PlutoBiomes;
import com.mjr.extraplanets.planets.Saturn.TeleportTypeSaturn;
import com.mjr.extraplanets.planets.Saturn.WorldProviderSaturn;
import com.mjr.extraplanets.planets.Saturn.worldgen.SaturnBiomes;
import com.mjr.extraplanets.planets.Uranus.TeleportTypeUranus;
import com.mjr.extraplanets.planets.Uranus.WorldProviderUranus;
import com.mjr.extraplanets.planets.Uranus.worldgen.UranusBiomes;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedCreeper;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedEnderman;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSpider;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.rom.gctweaks.GalacticTweaks;

public class GCPlanets {
	public static Planet FAKE_OVERWORLD;
	public static Planet FAKE_ASTEROIDS;

	public static void init() {
		build();
	}

	/**
	 * This method contains statements from the ExtraPlanets_Planets class from the ExtraPlanets Mod
	 * 
	 * They are provided here to ensure the moved planets still contain their world features.
	 *
	 * All credits for each statement goes to MJRLegends
	 * https://github.com/MJRLegends/ExtraPlanets/blob/dev_1.12.2/src/main/java/com/mjr/extraplanets/planets/ExtraPlanets_Planets.java
	 */
	private static void build() {
		GalacticTweaks.logger.debug(GCSystems.EP_SYSTEM.getName());

		try {
			GalacticTweaks.logger.debug(GCSystems.EP_SYSTEM.getName());
		} catch (Exception e) {

		}

		buildFakeEarth(FAKE_OVERWORLD, "fakeOverworld", GCSystems.EP_SYSTEM, GalacticraftCore.planetOverworld.getPhaseShift(), GalacticraftCore.planetOverworld.getRelativeDistanceFromCenter().scaledDistance);
		buildAsteroids(FAKE_ASTEROIDS, "fakeRocks", GCSystems.EP_SYSTEM, AsteroidsModule.planetAsteroids.getPhaseShift(), 1.375F);
		
		
		ExtraPlanets_Planets.MERCURY.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.CERES.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.JUPITER.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.SATURN.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.URANUS.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.NEPTUNE.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.PLUTO.setParentSolarSystem(GCSystems.EP_SYSTEM);

		ExtraPlanets_Planets.ERIS.setParentSolarSystem(GCSystems.EP_SYSTEM);
		


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

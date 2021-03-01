package net.romvoid95.galactic.core.gc;

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.*;
import static net.romvoid95.galactic.Info.ID;

import java.util.*;

import asmodeuscore.core.astronomy.*;
import asmodeuscore.core.astronomy.BodiesRegistry.*;
import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.vector.*;
import micdoodle8.mods.galacticraft.core.*;
import micdoodle8.mods.galacticraft.planets.asteroids.*;
import net.minecraft.util.*;
import net.romvoid95.galactic.core.*;

public class PlanetData {
	
	private final ResourceLocation IMG = new ResourceLocation(ID, "textures/gui/galaxy/wormhole.png");
	private final ResourceLocation SUN = new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/sun.png");
	private Galaxies galaxy;
	private SolarSystem system;
	
	private List<Planet> epList = PlanetGroups.extraPlanetsGroup().get();
	private List<Planet> gsList = PlanetGroups.galaxySpaceGroup().get();
	
	public PlanetData() {
		this.galaxy = BodiesRegistry.registerGalaxy("wormhole", IMG);
		this.system = new SolarSystem("secondSystem", galaxy.getName()).setMapPosition(new Vector3(0.0F, 0.0F, 0.0F));
		Star starSol4 = (Star) new Star("epsystem").setParentSolarSystem(system).setTierRequired(-1);
		starSol4.setBodyIcon(SUN);
		system.setMainStar(starSol4);
		GalaxyRegistry.registerSolarSystem(system);
	}
	
	public void create(String id) {
		switch (id) {
		case "galaxyspace":
			buildFakePlanets();
			gsList.forEach(p -> p.setParentSolarSystem(system));
			break;
		case "extraplanets":
			buildFakePlanets();
			epList.forEach(p -> p.setParentSolarSystem(system));
			break;
		case "none":
			break;
		}
	}

	private void buildFakePlanets() {
		Planet OVERWORLD = GalacticraftCore.planetOverworld;
		Planet ASTEROIDS = AsteroidsModule.planetAsteroids;
		
		Planet FAKE_ASTEROIDS = new Planet("fake_asteroids").setParentSolarSystem(system);
		FAKE_ASTEROIDS.setRelativeDistanceFromCenter(ASTEROIDS.getRelativeDistanceFromCenter());
		FAKE_ASTEROIDS.setRelativeOrbitTime(45.0F);
		FAKE_ASTEROIDS.setPhaseShift(ASTEROIDS.getPhaseShift());
		FAKE_ASTEROIDS.setRelativeSize(1.0F);
		FAKE_ASTEROIDS.setBodyIcon(ASTEROIDS.getBodyIcon());
		FAKE_ASTEROIDS.setUnreachable();
		GalaxyRegistry.registerPlanet(FAKE_ASTEROIDS);
		
		Planet FAKE_OVERWORLD = new Planet("fake_overworld").setParentSolarSystem(system);
		FAKE_OVERWORLD.setRelativeDistanceFromCenter(OVERWORLD.getRelativeDistanceFromCenter());
		FAKE_OVERWORLD.setPhaseShift(OVERWORLD.getPhaseShift());
		FAKE_OVERWORLD.setRelativeSize(1.0F);
		FAKE_OVERWORLD.setBodyIcon(OVERWORLD.getBodyIcon());
		FAKE_OVERWORLD.atmosphere.composition.addAll(Arrays.asList(NITROGEN,OXYGEN,ARGON,WATER));
		FAKE_OVERWORLD.setUnreachable();
		GalaxyRegistry.registerPlanet(FAKE_OVERWORLD);
	}
}

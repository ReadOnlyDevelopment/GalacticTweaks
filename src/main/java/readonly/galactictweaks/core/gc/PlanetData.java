package readonly.galactictweaks.core.gc;

import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.ARGON;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.CO2;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.NITROGEN;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.OXYGEN;
import static micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas.WATER;
import static readonly.galactictweaks.Info.ID;

import java.util.Arrays;

import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.BodiesRegistry.Galaxies;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.util.list.ImmutableCelestialList;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.mars.ConfigManagerMars;
import micdoodle8.mods.galacticraft.planets.mars.MarsModule;
import net.minecraft.util.ResourceLocation;
import readonly.galactictweaks.core.utils.Galaxy;

public class PlanetData
{

    private final ResourceLocation IMG = new ResourceLocation(ID, "textures/gui/galaxy/wormhole.png");

    private final ResourceLocation SUN =
        new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/sun.png");

    private Galaxies galaxy;

    private SolarSystem system;

    public static Planet FAKE_ASTEROIDS;

    public static Planet FAKE_MARS;

    public PlanetData()
    {
        this.galaxy = BodiesRegistry.registerGalaxy("wormhole", IMG);
        this.system = new SolarSystem("secondSystem", galaxy.getName()).setMapPosition(new Vector3(0.0F, 0.0F, 0.0F));
        Star starSol4 = (Star) new Star("epsystem").setParentSolarSystem(system).setTierRequired(-1);
        starSol4.setBodyIcon(SUN);
        system.setMainStar(starSol4);
        GalaxyRegistry.register(system);
    }

    public void create(String id)
    {
        buildFakePlanets();
        switch (id) {
            case "galaxyspace":
                ImmutableCelestialList<Planet> gsPlanets =
                    Galaxy.getFilteredPlanetList(CelestialBody.filter("galaxyspace"));
                ImmutableCelestialList<Moon> gsMoons = Galaxy.getFilteredMoonList(CelestialBody.filter("galaxyspace"));

                gsPlanets.forEach(p -> {
                    if (p.getParentSolarSystem().equals(GalacticraftCore.solarSystemSol))
                        p.setParentSolarSystem(system);
                });
                gsMoons.forEach(m -> {
                    if (m != null) {
                        if (m.getParentPlanet() != null) { // Honestly can this ever return null??
                            if (m.getParentPlanet().getDimensionID() == ConfigManagerMars.dimensionIDMars) {
                                m.setParentPlanet(FAKE_MARS);
                            }
                        }
                    }
                });
                break;
            case "extraplanets":
                ImmutableCelestialList<Planet> epPlanets =
                    Galaxy.getFilteredPlanetList(CelestialBody.filter("extraplanets"));
                ImmutableCelestialList<Moon> epMoons = Galaxy.getFilteredMoonList(CelestialBody.filter("extraplanets"));

                epPlanets.forEach(p -> {
                    if (p.getParentSolarSystem().equals(GalacticraftCore.solarSystemSol))
                        p.setParentSolarSystem(system);
                });
                epMoons.forEach(m -> {
                    if (m != null) {
                        if (m.getParentPlanet() != null) {
                            if (m.getParentPlanet().getDimensionID() == ConfigManagerMars.dimensionIDMars) {
                                m.setParentPlanet(FAKE_MARS);
                            }
                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    private void buildFakePlanets()
    {
        Planet OVERWORLD = GalacticraftCore.planetOverworld;
        Planet ASTEROIDS = AsteroidsModule.planetAsteroids;
        Planet MARS = MarsModule.planetMars;

        FAKE_ASTEROIDS = new Planet("fake_asteroids").setParentSolarSystem(system);
        FAKE_ASTEROIDS.setRelativeDistanceFromCenter(ASTEROIDS.getRelativeDistanceFromCenter());
        FAKE_ASTEROIDS.setRelativeOrbitTime(45.0F);
        FAKE_ASTEROIDS.setPhaseShift(ASTEROIDS.getPhaseShift());
        FAKE_ASTEROIDS.setRelativeSize(1.0F);
        FAKE_ASTEROIDS.setBodyIcon(ASTEROIDS.getBodyIcon());
        FAKE_ASTEROIDS.setUnreachable();
        GalaxyRegistry.register(FAKE_ASTEROIDS);

        Planet FAKE_OVERWORLD = new Planet("fake_overworld").setParentSolarSystem(system);
        FAKE_OVERWORLD.setRelativeDistanceFromCenter(OVERWORLD.getRelativeDistanceFromCenter());
        FAKE_OVERWORLD.setPhaseShift(OVERWORLD.getPhaseShift());
        FAKE_OVERWORLD.setRelativeSize(1.0F);
        FAKE_OVERWORLD.setBodyIcon(OVERWORLD.getBodyIcon());
        FAKE_OVERWORLD.atmosphere.composition.addAll(Arrays.asList(NITROGEN, OXYGEN, ARGON, WATER));
        FAKE_OVERWORLD.setUnreachable();
        GalaxyRegistry.register(FAKE_OVERWORLD);

        FAKE_MARS = new Planet("fake_mars").setParentSolarSystem(system);
        FAKE_MARS.setRelativeDistanceFromCenter(MARS.getRelativeDistanceFromCenter());
        FAKE_MARS.setRelativeOrbitTime(MARS.getRelativeOrbitTime());
        FAKE_MARS.setPhaseShift(MARS.getPhaseShift());
        FAKE_MARS.setRelativeSize(1.0F);
        FAKE_MARS.setBodyIcon(MARS.getBodyIcon());
        FAKE_MARS.atmosphere.composition.addAll(Arrays.asList(CO2, NITROGEN, ARGON));
        FAKE_MARS.setUnreachable();
        GalaxyRegistry.register(FAKE_MARS);
    }
}

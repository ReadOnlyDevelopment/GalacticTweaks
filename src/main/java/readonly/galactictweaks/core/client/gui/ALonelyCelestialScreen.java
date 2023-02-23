package readonly.galactictweaks.core.client.gui;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;

public final class ALonelyCelestialScreen extends GuiCelestialSelection {

    public ALonelyCelestialScreen(boolean mapMode, List<CelestialBody> possibleBodies, boolean canCreateStations) {
        super(mapMode, possibleBodies, canCreateStations);
    }

    @Override
    public void initGui() {
        this.bodiesToRender.clear();
        
        for (Planet planet : GalaxyRegistry.getPlanets()) {
            if(planet.isReachable())
                this.bodiesToRender.add(planet);
        }

        for (Moon moon : GalaxyRegistry.getMoons()) {
            if(moon.getParentPlanet().isReachable() && moon.isReachable())
                this.bodiesToRender.add(moon);
        }

        for (Satellite satellite : GalaxyRegistry.getSatellites()) {
            if(satellite.getParentPlanet().isReachable())
                this.bodiesToRender.add(satellite);
        }

        GuiCelestialSelection.BORDER_SIZE = this.width / 65;
        GuiCelestialSelection.BORDER_EDGE_SIZE = GuiCelestialSelection.BORDER_SIZE / 4;

        for (SolarSystem solarSystem : GalaxyRegistry.getSolarSystems()) {
            if(GalaxyRegistry.getPlanetsForSolarSystem(solarSystem).stream().filter(CelestialBody.filterReachable()).findAny().isPresent())
                this.bodiesToRender.add(solarSystem.getMainStar());
        }
    }

    /*
     * Overriding for the purpose of to hide planets/moon from the screen in till unlocked
     */
    @Override
    protected List<CelestialBody> getChildren(Object object) {
        List<CelestialBody> bodyList = Lists.newArrayList();

        if (object instanceof Planet) {
            List<Moon> moons = GalaxyRegistry.getMoonsForPlanet((Planet) object);
            for (Moon moon : moons)
                if(moon.isReachable())
                    bodyList.add(moon);
        } else if (object instanceof SolarSystem) {
            List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem((SolarSystem) object);
            for (Planet planet : planets)
                if(planet.isReachable())
                    bodyList.add(planet);
        }

        Collections.sort(bodyList);

        return bodyList;
    }
}

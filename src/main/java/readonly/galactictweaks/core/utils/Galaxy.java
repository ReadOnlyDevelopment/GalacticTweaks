package readonly.galactictweaks.core.utils;

import java.util.function.Predicate;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialObject;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.util.stream.CelestialCollector;
import micdoodle8.mods.galacticraft.core.util.list.ImmutableCelestialList;

public final class Galaxy
{
    public static ImmutableCelestialList<Planet> getFilteredPlanetList(Predicate<CelestialObject> predicate)
    {
        return GalaxyRegistry.getPlanets().stream().filter(predicate).collect(CelestialCollector.toList()).toUnmodifiableList();
    }
    
    public static ImmutableCelestialList<Moon> getFilteredMoonList(Predicate<CelestialObject> condition)
    {
        return GalaxyRegistry.getMoons().stream().filter(condition).collect(CelestialCollector.toList()).toUnmodifiableList();
    }
}

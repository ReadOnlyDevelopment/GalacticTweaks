package readonly.galactictweaks.core.gc;

import java.util.HashMap;
import java.util.Map;

import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.core.util.list.ImmutableCelestialList;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.LoaderState;
import readonly.galactictweaks.core.GCTLog;

public final class GalaxyCache
{
    private Map<String, Integer> celesitalNameToDimensionIdMap = new HashMap<>();

    private static GalaxyCache instance;

    public static GalaxyCache cache()
    {
        if (instance == null) {
            instance = new GalaxyCache();
        }

        return instance;
    }

    private GalaxyCache()
    {
        if (!Loader.instance().hasReachedState(LoaderState.POSTINITIALIZATION)) {
            GCTLog.error(
                "GalaxyCache MUST be initialized if, and ONLY IF, LoaderState has reached FMLPostInitializationEvent");
        }
        
        ImmutableCelestialList<Planet> planets = GalaxyRegistry.getPlanets();
        GCTLog.info("ImmutableCelestialList<Planet> : {}", planets.size());
        ImmutableCelestialList<Moon> moons = GalaxyRegistry.getMoons();
        GCTLog.info("ImmutableCelestialList<Moon> : {}", moons.size());
        planets.stream().filter(Planet::isReachable).forEach(p -> celesitalNameToDimensionIdMap.put(p.getName(), p.getDimensionID()));
        moons.stream().filter(Moon::isReachable).forEach(p -> celesitalNameToDimensionIdMap.put(p.getName(), p.getDimensionID()));
    }
    
    public int getDimensionIdFromName(final String name)
    {
        return celesitalNameToDimensionIdMap.get(name);
    }
}

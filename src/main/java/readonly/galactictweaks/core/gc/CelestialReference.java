package readonly.galactictweaks.core.gc;

import java.util.Optional;
import java.util.function.Predicate;

import lombok.Getter;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraftforge.fml.common.Loader;
import readonly.galactictweaks.core.GCTLog;

@Getter
public final class CelestialReference
{
    private Optional<CelestialBody> body;

    private Integer dimId = null;

    private String name = null;

    public static CelestialReference of(String data)
    {
        try {
            return new CelestialReference(Integer.parseInt(data));
        } catch (NumberFormatException e) {
            if (data.split(":").length > 1) {
                if (data.split(":").length >= 3) {
                    throw new IllegalArgumentException(
                        "Attempted to create CelestialReference with more than 2 IdRef's: [" + data + "]");
                }

                String modid = data.split(":")[0];
                String name = data.split(":")[1];

                if (modid.equals(Constants.MOD_ID_CORE) || modid.equals(Constants.MOD_ID_PLANETS)) {
                    return new CelestialReference(name);
                }

                return new CelestialReference(modid, name);
            }

            return new CelestialReference(data);
        }
    }

    private CelestialReference(int dimId)
    {
        this.body = Optional.ofNullable(GalaxyRegistry.getCelestialBodyFromDimensionID(dimId));
        this.dimId = dimId;
    }

    private CelestialReference(String name)
    {
        this.body =
            GalaxyRegistry.getAllReachableBodies().stream().filter(CelestialReference.withName(name)).findFirst();
        this.name = name;
    }

    private CelestialReference(String modid, String name)
    {
        if (Loader.isModLoaded(modid)) {
            this.body = GalaxyRegistry.getAllReachableBodies().stream().filter(CelestialBody.filter(modid))
                .filter(CelestialReference.withName(name)).findFirst();
            this.name = modid + ":" + name;
        } else {
            GCTLog.error("Attempted to create CelestialReference for non-existent addon [ modid: '" + modid
                + "' , name: '" + name + "' ]");
            this.body = Optional.empty();
        }
    }

    static Predicate<CelestialBody> withName(String name)
    {
        return new Predicate<CelestialBody>()
        {
            @Override
            public boolean test(CelestialBody celestialObject)
            {
                return celestialObject.getName().equals(name);
            }
        };
    }

    public String getIdentifier()
    {
        return dimId != null ? String.valueOf(dimId) : name;
    }
}

package readonly.galactictweaks.features;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import readonly.api.config.IOrdered;
import readonly.api.feature.Feature;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class BreatheableDimensions extends Feature implements IOrdered
{

    public static String comment = "Define which planets/moons will be breatheable or not";

    public BreatheableDimensions()
    {
        this.category = "BreatheableDimensions";
        this.categoryComment = "Define which planets/moons will be breatheable or not";
    }

    @Override
    public void addProp()
    {
        this.propOrder.add(GalacticraftModuleConfig.breatheableDims.key());
        this.propOrder.add(GalacticraftModuleConfig.nonBreatheableDims.key());
    }

    @Override
    public boolean usesEvents()
    {
        return true;
    }

    @SubscribeEvent
    public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
        if (GalacticraftModuleConfig.breatheableDims.get().length > 0) {
            final EntityLivingBase entityLiving = event.getEntityLiving();
            if (entityLiving instanceof EntityPlayerMP)
            {
                for (int dimId : GalacticraftModuleConfig.breatheableDims.get()) {
                    if (entityLiving.world.provider.getDimensionType().getId() == dimId) {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @Override
    public boolean isEnabled()
    {
        return GalacticraftModuleConfig.BREATHEABLE_DIMENSIONS;
    }
}

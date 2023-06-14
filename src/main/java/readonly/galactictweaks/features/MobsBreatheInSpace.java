package readonly.galactictweaks.features;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.util.DamageSourceGC;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import readonly.api.feature.Feature;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class MobsBreatheInSpace extends Feature
{
    public MobsBreatheInSpace()
    {
        this.category = "MobsBreatheInSpace";
        this.categoryComment = "Adds ability for passive mobs to breathe on other planets";
    }

    @Override
    public boolean usesEvents()
    {
        return true;
    }

    @SubscribeEvent
    public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent event)
    {
        EntityLivingBase ee = event.getEntityLiving();
        if (ee instanceof EntityMob || ee instanceof EntityAnimal || ee instanceof EntityCreature) {
            event.setCanceled(true);
        }
    }

    @Override
    public boolean isEnabled()
    {
        return GalacticraftModuleConfig.MOBS_BREATHE_IN_SPACE;
    }
}

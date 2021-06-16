package net.romvoid95.galactic.modules.galacticraft.features;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.DamageSourceGC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.api.config.IOrdered;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.modules.galacticraft.GalacticraftModuleConfig;

public class BreatheableDimensions extends Feature implements IOrdered {

	public BreatheableDimensions() {
		this.category = "BreatheableDimensions";
		this.categoryComment = "Define which planets will be breatheable or not";
	}

	@Override
	public void addProp() {
		this.propOrder.add(GalacticraftModuleConfig.breatheableDims.key());
		this.propOrder.add(GalacticraftModuleConfig.nonBreatheableDims.key());
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		if (GalacticraftModuleConfig.breatheableDims.get().length > 0) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			for (int dimId : GalacticraftModuleConfig.breatheableDims.get()) {
				if (player.world.provider.getDimensionType().getId() == dimId) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void entityLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (GalacticraftModuleConfig.nonBreatheableDims.get().length > 0) {
			final EntityLivingBase entityLiving = event.getEntityLiving();
			if (entityLiving instanceof EntityPlayer
					&& entityLiving.ticksExisted % ConfigManagerCore.suffocationCooldown == 0) {
				for (int dimId : GalacticraftModuleConfig.nonBreatheableDims.get()) {
					if (entityLiving.world.provider.getDimensionType().getId() == dimId) {
						GCCoreOxygenSuffocationEvent suffocationEvent = new GCCoreOxygenSuffocationEvent.Pre(
								entityLiving);
						MinecraftForge.EVENT_BUS.post(suffocationEvent);

						entityLiving.attackEntityFrom(DamageSourceGC.oxygenSuffocation,
								Math.max(ConfigManagerCore.suffocationDamage / 2, 1));

						GCCoreOxygenSuffocationEvent suffocationEventPost = new GCCoreOxygenSuffocationEvent.Post(
								entityLiving);
						MinecraftForge.EVENT_BUS.post(suffocationEventPost);

					}
				}
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.BREATHEABLE_DIMENSIONS;
	}
}

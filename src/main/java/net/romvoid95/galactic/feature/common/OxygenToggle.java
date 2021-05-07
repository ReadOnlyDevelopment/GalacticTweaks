package net.romvoid95.galactic.feature.common;

import static net.romvoid95.api.docs.Stability.*;

import micdoodle8.mods.galacticraft.api.event.oxygen.*;
import micdoodle8.mods.galacticraft.core.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.common.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.romvoid95.api.config.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.feature.*;

@Doc(
	value = "Dynamic Oxygen Tweak",
	comment = "Feature that allows you to set any celestial body to have Oxygen or not have oxygen",
	stability = UNSTABLE
)
public class OxygenToggle extends Feature implements IOrdered {

	@Override
	public String comment() {
		return "Define which planets will be breatheable or not";
	}

	@Override
	public String category() {
		return "BreatheableDimensions";
	}
	
	@Override
	public void addProp() {
		this.propOrder.add(FeatureConfigs.breatheableDims.key());
		this.propOrder.add(FeatureConfigs.nonBreatheableDims.key());
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		if (FeatureConfigs.breatheableDims.get().length > 0) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			for (int dimId : FeatureConfigs.breatheableDims.get()) {
				if (player.world.provider.getDimensionType().getId() == dimId) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void entityLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (FeatureConfigs.nonBreatheableDims.get().length > 0) {
			final EntityLivingBase entityLiving = event.getEntityLiving();
			if (entityLiving instanceof EntityPlayer
					&& entityLiving.ticksExisted % ConfigManagerCore.suffocationCooldown == 0) {
				for (int dimId : FeatureConfigs.nonBreatheableDims.get()) {
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
		return FeatureConfigs.OXYGEN_TOGGLE;
	}
}

package net.romvoid95.galactic.modules.galacticraft.features;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.modules.galacticraft.GalacticraftModuleConfig;

public class MobsBreatheInSpace extends Feature {

	public MobsBreatheInSpace() {
		this.category = "MobsBreatheInSpace";
		this.categoryComment = "Adds ability for passive mobs to breathe on other planets";
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		EntityLivingBase e = event.getEntityLiving();
		if (e.world.provider instanceof IGalacticraftWorldProvider) {
			if (e instanceof EntityLiving || e instanceof EntityAnimal || e instanceof EntityCreature) {
				event.setCanceled(true);
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.MOBS_BREATHE_IN_SPACE;
	}
}

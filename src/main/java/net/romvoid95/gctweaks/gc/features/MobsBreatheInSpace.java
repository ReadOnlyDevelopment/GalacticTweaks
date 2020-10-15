package net.romvoid95.gctweaks.gc.features;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;

import net.minecraft.entity.*;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.gctweaks.base.Feature;

public class MobsBreatheInSpace extends Feature {

	private static boolean mobsBreatheInSpace; 
	
	@Override
	public String comment() {
		return "Adds ability for passive mobs to breathe on other planets";
	}
	
	@Override
	public String[] category() {
		return new String[] {"space-breathing"};
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		mobsBreatheInSpace = config.get(category[0], "mobsBreatheInSpace", false,
				"Set to true if you want all Passive Mobs to breathe in space").getBoolean();
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		if (mobsBreatheInSpace) {
			EntityLivingBase e = event.getEntityLiving();
			if (e.world.provider instanceof IGalacticraftWorldProvider) {
				if (e instanceof EntityLiving || e instanceof EntityAnimal || e instanceof EntityCreature) {
					event.setCanceled(true);
				}
			}
		}
	}
}

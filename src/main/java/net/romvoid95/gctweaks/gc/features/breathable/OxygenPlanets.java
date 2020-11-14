package net.romvoid95.gctweaks.gc.features.breathable;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.gctweaks.base.Feature;

public class OxygenPlanets extends Feature  {

	private static boolean breatheablePlanets; 
	private int[] dimIds;
	
	@Override
	public String comment() {
		return "Define dimensions Players will be able to breathe in";
	}
	
	@Override
	public String category() {
		return "breatheOnPlanets";
	}

	@Override
	public void syncConfig(String category) {
		breatheablePlanets = set(category, "enableFeature", false);
		dimIds = set(category, "dimIds", "Data consisting of which Dimensions Players can breahte in",new int[] {-29, -31});
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		if (breatheablePlanets) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();
			for(int dimId : dimIds) {
				if(player.world.provider.getDimensionType().getId() == dimId) {
					event.setCanceled(true);
				}
			}
		}
	}
}

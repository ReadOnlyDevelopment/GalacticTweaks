package net.romvoid95.galactic.feature.common;

import static net.romvoid95.api.docs.Stability.*;

import micdoodle8.mods.galacticraft.api.event.oxygen.*;
import micdoodle8.mods.galacticraft.api.world.*;
import net.minecraft.entity.*;
import net.minecraft.entity.passive.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.feature.*;

@Doc(
	value = "Mobs In Space Tweak",
	comment = "Feature that allows passive mobs to breathe on other planets\n"
			+ "Note: Hostile mobs are not affected",
	stability = STABLE
)
public class MobsBreatheInSpace extends Feature {

	@Override
	public String comment() {
		return "Adds ability for passive mobs to breathe on other planets";
	}

	@Override
	public String category() {
		return "MobsBreatheInSpace";
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
		return FeatureConfigs.MOBS_BREATHE_IN_SPACE;
	}
}

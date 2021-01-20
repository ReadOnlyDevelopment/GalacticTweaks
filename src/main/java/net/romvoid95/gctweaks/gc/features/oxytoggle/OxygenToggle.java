package net.romvoid95.gctweaks.gc.features.oxytoggle;

import micdoodle8.mods.galacticraft.api.event.oxygen.GCCoreOxygenSuffocationEvent;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.DamageSourceGC;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.base.Feature;

public class OxygenToggle extends Feature {

	private static boolean oxygenToggle;
	private int[] setDimsBreatheable;
	private int[] setDimsNonBreatheable;

	@Override
	public String comment() {
		return "Define which planets will be breatheable or not";
	}

	@Override
	public String category() {
		return "oxygenToggle";
	}

	@Override
	public void syncConfig(String category) {
		oxygenToggle = set(category, "enableFeature", false);
		setDimsBreatheable = set(category, "breathableDims",
				"Data consisting of which Dimensions Players can breahte in", new int[] { -29, -31 });
		setDimsNonBreatheable = set(category, "nonBreathableDims",
				"Data consisting of which Dimensions Players can NOT breahte in", new int[] { -1030 });
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void GCCoreOxygenSuffocationEvent(GCCoreOxygenSuffocationEvent.Pre event) {
		if (oxygenToggle) {
			EntityPlayer player = (EntityPlayer) event.getEntityLiving();

			for (int dimId : setDimsBreatheable) {
				if (setDimsBreatheable.length == 0) {
					return;
				}
				if (player.world.provider.getDimensionType().getId() == dimId
				/* && player.world.provider instanceof IGalacticraftWorldProvider */) {
					event.setCanceled(true);
				}
			}
		}
	}

	@SubscribeEvent
	public void entityLivingEvent(LivingEvent.LivingUpdateEvent event) {
		if (oxygenToggle) {
			if (setDimsNonBreatheable.length == 0) {
				return;
			}
			final EntityLivingBase entityLiving = event.getEntityLiving();
			if (entityLiving instanceof EntityPlayer
					&& entityLiving.ticksExisted % ConfigManagerCore.suffocationCooldown == 0) {
				for (int dimId : setDimsNonBreatheable) {
					GalacticTweaks.logger.info(entityLiving.world.provider.getDimensionType().getId());
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
}

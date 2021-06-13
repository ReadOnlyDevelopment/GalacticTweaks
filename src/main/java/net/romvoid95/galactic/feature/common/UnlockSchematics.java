package net.romvoid95.galactic.feature.common;

import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.romvoid95.api.feature.Feature;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.feature.FeatureConfigs;

public class UnlockSchematics extends Feature {

	public UnlockSchematics() {
		this.category = "UnlockSchematics";
		this.categoryComment = "Unlock all schematics specified when the player joins the world.";
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void PlayerWorldJoin(PlayerEvent.PlayerLoggedInEvent e) {
		final EntityPlayerMP player = (EntityPlayerMP) e.player;
		perPlayerSchems(player);
	}

	private void perPlayerSchems(EntityPlayerMP player) {
		for (int schem : FeatureConfigs.schematicIDs.get()) {
			ItemStack schemItem = SchematicRegistry.getSchematicItem(schem);
			try {
				SchematicRegistry.unlockNewPage(player, schemItem);
			} catch (Exception e) {
				GalacticTweaks.LOG.error(
						"Please remove " + schem + " from the schematics config. This is a invalid defaultValue...");
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return FeatureConfigs.UNLOCK_SCHEMATICS;
	}
}

package net.romvoid95.galactic.feature.common;

import micdoodle8.mods.galacticraft.api.recipe.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.feature.*;

public class UnlockSchematics extends Feature {

	@Override
	public String category() {
		return  "UnlockSchematics";
	}

	@Override
	public String comment() {
		return "Unlock all schematics specified when the player joins the world.";
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

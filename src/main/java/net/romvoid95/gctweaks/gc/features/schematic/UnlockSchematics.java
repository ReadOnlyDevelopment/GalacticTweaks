package net.romvoid95.gctweaks.gc.features.schematic;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;

import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.base.Feature;

public class UnlockSchematics extends Feature {

	private static boolean	unlockSchematicsOnJoin;
	private static int[]	schematicID;

	@Override
	public String[] category() {
		return new String[] { "unlock-schematics" };
	}

	@Override
	public String comment() {
		return "Unlock all schematics specified when the player joins the world.";
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		unlockSchematicsOnJoin = config.get(category[0], "unlock-schematics", false,
				"Set to true unlock schematics specified in config on player join.\nYou can see what schematic IDs are in GC by default in configs.").getBoolean();
		schematicID = config.get(category[0], "schematic-ids", new int[] { 0, 1, 2, 3, 4 },
				"Check galacticraft/addon config for schematic IDs").getIntList();
	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@SubscribeEvent
	public void PlayerWorldJoin(PlayerEvent.PlayerLoggedInEvent e) {
		final EntityPlayerMP player = (EntityPlayerMP) e.player;
		if (unlockSchematicsOnJoin) {
			perPlayerSchems(player);
		}
	}

	private void perPlayerSchems(EntityPlayerMP player) {
		for (int schem : schematicID) {
			ItemStack schemItem = SchematicRegistry.getSchematicItem(schem);
			try {
				SchematicRegistry.unlockNewPage(player, schemItem);
			} catch (Exception e) {
				GalacticTweaks.logger.error(
						"Please remove " + schem + " from the schematics config. This is a invalid value...");
			}
		}
	}
}

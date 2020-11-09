package net.romvoid95.gctweaks.gc.features.schematic;

import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.base.Feature;

public class UnlockSchematics extends Feature {

	private static boolean	unlockSchematicsOnJoin;
	private static int[]	schematicID;

	@Override
	public String category() {
		return  "unlockSchematics";
	}

	@Override
	public String comment() {
		return "Unlock all schematics specified when the player joins the world.";
	}

	@Override
	public void syncConfig(String category) {
		unlockSchematicsOnJoin = set(category, "enableFeature", false);
		schematicID = set(category, "idList", "Check galacticraft/addon config for schematic IDs", new int[] { 0, 1, 2, 3, 4 });

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

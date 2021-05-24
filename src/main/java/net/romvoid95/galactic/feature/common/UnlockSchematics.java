package net.romvoid95.galactic.feature.common;

import static net.romvoid95.api.docs.Stability.*;

import micdoodle8.mods.galacticraft.api.recipe.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.romvoid95.api.*;
import net.romvoid95.api.docs.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.feature.*;

@Doc(
		value = "Schematic Unlocker",
		comment = "Feature that allows you to have certain Schematics unlocked from the very start.\n"
				+ "Note: This feature is still in it's beta version and will be built upon as newer versions are released",
				stability = UNSTABLE
		)
public class UnlockSchematics extends Feature {

	public UnlockSchematics() {
		super(UnlockSchematics::new, EnumSide.COMMON);
	}

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

package readonly.galactictweaks.features;

import micdoodle8.mods.galacticraft.api.recipe.SchematicRegistry;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import readonly.api.feature.Feature;
import readonly.galactictweaks.core.GCTLog;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class UnlockSchematics extends Feature {

    public static String comment = "Unlock all schematics specified when the player joins the world.";
    
	public UnlockSchematics() {
		this.category = "UnlockSchematics";
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
		for (int schem : GalacticraftModuleConfig.schematicIDs.get()) {
			ItemStack schemItem = SchematicRegistry.getSchematicItem(schem);
			try {
				SchematicRegistry.unlockNewPage(player, schemItem);
			} catch (Exception e) {
				GCTLog.error(
						"Please remove " + schem + " from the schematics config. This is a invalid defaultValue...");
			}
		}
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.UNLOCK_SCHEMATICS;
	}
}

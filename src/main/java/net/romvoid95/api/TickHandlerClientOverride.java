package net.romvoid95.gctweaks.base.core;

import java.lang.reflect.Field;

import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.client.gui.overlay.OverlayOxygenWarning;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStatsClient;
import micdoodle8.mods.galacticraft.core.tick.TickHandlerClient;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.common.gameevent.TickEvent.RenderTickEvent;

public class TickHandlerClientOverride {

	@SubscribeEvent
	public void onRenderTick(RenderTickEvent event) {
		long tickCount = 0;
		try {
			Field tick = TickHandlerClient.class.getDeclaredField("tickCount");
			tick.setAccessible(true);
			tickCount = tick.getLong(null);
		} catch (Exception e) {

		}
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;
		final EntityPlayerSP playerBaseClient = PlayerUtil.getPlayerBaseClientFromPlayer(player, false);

		if (player == null || playerBaseClient == null) {
			return;
		}

		GCPlayerStatsClient stats = GCPlayerStatsClient.get(playerBaseClient);

		if (event.phase == Phase.END) {
			if (playerBaseClient != null && player.world.provider instanceof IGalacticraftWorldProvider
					&& !stats.isOxygenSetupValid()) {
				if (minecraft.currentScreen == null && !minecraft.gameSettings.hideGUI
						&& !(playerBaseClient.isCreative() || playerBaseClient.isSpectator())) {
					OverlayOxygenWarning.renderOxygenWarningOverlay(minecraft, tickCount);
				}
			}
		}
	}

}

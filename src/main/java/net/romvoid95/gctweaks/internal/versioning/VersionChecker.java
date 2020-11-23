package net.romvoid95.gctweaks.internal.versioning;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.romvoid95.api.versioning.Version;
import net.romvoid95.gctweaks.Ref;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Ref.MOD_ID)
public final class VersionChecker {

	private VersionChecker() {}

	public static volatile boolean doneChecking      = false;
	public static volatile String  onlineVersion     = "";
	private static boolean         triedToWarnPlayer = false;
	public static volatile boolean startedDownload   = false;
	public static volatile boolean downloadedFile    = false;

	public static void init () {
		new ThreadVersionChecker();
	}

	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void onTick (ClientTickEvent event) {
		if (event.phase == Phase.END && Minecraft.getMinecraft().player != null && !triedToWarnPlayer && doneChecking) {
			if (!onlineVersion.isEmpty()) {
				EntityPlayer player = Minecraft.getMinecraft().player;
				Version      online = new Version(Request.getLatestVersion());
				Version      client = new Version(Ref.MOD_VERSION);
				if (online.isGreaterThan(client)) {
					player.sendMessage(new TextComponentTranslation("galactictweaks.versions.notify")
							.setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
					player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated0"));
					player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated1", client
							.toString()));
					player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated2", online
							.toString()));
					player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated0"));
					ITextComponent component = ITextComponent.Serializer
							.fromJsonLenient(I18n.translateToLocal("galactictweaks.versions.updateMessage"));
					player.sendMessage(component);
				}
			}
			triedToWarnPlayer = true;
		}
	}
}

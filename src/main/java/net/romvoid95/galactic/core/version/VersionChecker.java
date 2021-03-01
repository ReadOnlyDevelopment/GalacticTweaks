package net.romvoid95.galactic.core.version;

<<<<<<< Updated upstream:src/main/java/net/romvoid95/gctweaks/internal/versioning/VersionChecker.java
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
import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.Ref;
=======
import static net.romvoid95.galactic.Info.VERSION;

import net.minecraft.client.*;
import net.minecraft.client.resources.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.relauncher.*;
import net.romvoid95.api.versioning.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.core.config.*;
import net.romvoid95.galactic.core.utils.*;
>>>>>>> Stashed changes:src/main/java/net/romvoid95/galactic/core/version/VersionChecker.java

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Ref.MOD_ID)
public final class VersionChecker {

<<<<<<< Updated upstream:src/main/java/net/romvoid95/gctweaks/internal/versioning/VersionChecker.java
	private VersionChecker() {
	}

	public static volatile boolean doneChecking = false;
	public static volatile String onlineVersion = "";
	private static boolean triedToWarnPlayer = false;
	public static volatile boolean startedDownload = false;
	public static volatile boolean downloadedFile = false;

	public static void init() {
		new ThreadVersionChecker();
	}

	@SubscribeEvent
	public static void onTick(ClientTickEvent event) {
		if (Minecraft.getMinecraft().isSingleplayer() == false) {
			GalacticTweaks.logger.info("Is On Singleplayer: " + Minecraft.getMinecraft().isSingleplayer());
			if (event.phase == Phase.END && Minecraft.getMinecraft().player != null && !triedToWarnPlayer
					&& doneChecking) {
				if (!onlineVersion.isEmpty()) {
					EntityPlayer player = Minecraft.getMinecraft().player;
					Version online = new Version(Request.getLatestVersion());
					Version client = new Version(Ref.MOD_VERSION);
					if (online.isGreaterThan(client)) {
						player.sendMessage(new TextComponentTranslation("galactictweaks.versions.notify")
								.setStyle(new Style().setColor(TextFormatting.LIGHT_PURPLE)));
						player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated0"));
						player.sendMessage(
								new TextComponentTranslation("galactictweaks.versions.outdated1", client.toString()));
						player.sendMessage(
								new TextComponentTranslation("galactictweaks.versions.outdated2", online.toString()));
						player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated0"));
						ITextComponent component = ITextComponent.Serializer
								.fromJsonLenient(I18n.translateToLocal("galactictweaks.versions.updateMessage"));
						player.sendMessage(component);
					}
				}
				triedToWarnPlayer = true;
			}
		}
=======
	public static boolean checkThreadDone   = false;
	public static boolean notifyForUpdate;
	public static boolean checkThreadFailed;
	public static Version updateVersion;
	public static Version currentRunningVersion = new Version(VERSION);
	private EntityPlayer player;
	
	public VersionChecker() {
		if (CoreBooleanValues.DO_UPDATE_CHECK.isEnabled()) {
			GalacticTweaks.LOG.info("Initializing Update Checker...");
			new ThreadVersionChecker();
			MinecraftForge.EVENT_BUS.register(this);
		}
	}

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(receiveCanceled = true)
    public void onTick(TickEvent.ClientTickEvent event) {
    	if (Minecraft.getMinecraft().player != null  && Minecraft.getMinecraft().isSingleplayer()) {
    		player = Minecraft.getMinecraft().player;
    		if(VersionChecker.checkThreadFailed) {
    			player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.failed")));
    		} else if(VersionChecker.notifyForUpdate) {
                player.sendMessage(StringUtil.format("galactictweaks.versions.notify", new Style().setColor(TextFormatting.LIGHT_PURPLE)));
                player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.outdated0")));
                player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated1", currentRunningVersion
						.toString()));
				player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated2", updateVersion
						.toString()));
                player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.outdated0")));
                player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.updateMessage")));
    		}
    		if(checkThreadDone) MinecraftForge.EVENT_BUS.unregister(this);
    	}
>>>>>>> Stashed changes:src/main/java/net/romvoid95/galactic/core/version/VersionChecker.java
	}
 }

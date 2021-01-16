package net.romvoid95.gctweaks.internal.versioning;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.versioning.Version;
import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.Ref;
import net.romvoid95.gctweaks.internal.config.CoreBooleanValues;

public class VersionChecker {

	public static boolean checkThreadDone   = false;
	public static boolean notifyForUpdate;
	public static boolean checkThreadFailed;
	public static Version updateVersion;
	public static Version currentRunningVersion = new Version(Ref.MOD_VERSION);
	private EntityPlayer player;
	
	public VersionChecker() {
		if (CoreBooleanValues.DO_UPDATE_CHECK.isEnabled()) {
			GalacticTweaks.logger.info("Initializing Update Checker...");
			new ThreadVersionChecker();
			MinecraftForge.EVENT_BUS.register(this);
		}
	}

    @SideOnly(Side.CLIENT)
    @SubscribeEvent(receiveCanceled = true)
    public void onTick(TickEvent.ClientTickEvent event) {
    	if (Minecraft.getMinecraft().player != null  && Minecraft.getMinecraft().isSingleplayer()) {
    		EntityPlayer player = Minecraft.getMinecraft().player;
    		if(VersionChecker.checkThreadFailed) {
    			player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.failed")));
    		} else if(VersionChecker.notifyForUpdate) {
                player.sendMessage(StringUtil.format("galactictweaks.versions.notify", new Style().setColor(TextFormatting.LIGHT_PURPLE)));
                player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.outdated0")));
                player.sendMessage(StringUtil.formatComponent("galactictweaks.versions.outdated1", currentRunningVersion.toString()));
                player.sendMessage(StringUtil.formatComponent("galactictweaks.versions.outdated2", updateVersion.toString()));
                player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.outdated0")));
                player.sendMessage(ITextComponent.Serializer.fromJsonLenient(I18n.format("galactictweaks.versions.updateMessage")));
    		}
    		if(checkThreadDone) MinecraftForge.EVENT_BUS.unregister(this);
    	}
	}
}

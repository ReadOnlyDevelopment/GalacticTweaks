package net.romvoid95.galactic.core.version;

import static net.romvoid95.galactic.Info.VERSION;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.versioning.Version;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.Info;
import net.romvoid95.galactic.core.config.CoreBooleanValues;
import net.romvoid95.galactic.core.utils.StringUtil;

@Mod.EventBusSubscriber(value = Side.CLIENT, modid = Info.ID)
public final class VersionChecker {

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
    			player.sendMessage(StringUtil.format("galactictweaks.versions.failed"));
    		} else if(VersionChecker.notifyForUpdate) {
                player.sendMessage(StringUtil.format("galactictweaks.versions.notify", new Style().setColor(TextFormatting.LIGHT_PURPLE)));
                player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.outdated0"));
                player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated1", currentRunningVersion.toString()));
				player.sendMessage(new TextComponentTranslation("galactictweaks.versions.outdated2", updateVersion.toString()));
                player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.outdated0"));
                player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.updateMessage"));
    		}
    		if(checkThreadDone) MinecraftForge.EVENT_BUS.unregister(this);
    	}
	}
 }

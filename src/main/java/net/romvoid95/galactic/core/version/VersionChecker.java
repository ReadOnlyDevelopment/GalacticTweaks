package net.romvoid95.galactic.core.version;

import static net.romvoid95.galactic.Info.VERSION;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.relauncher.*;
import net.romvoid95.api.versioning.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.core.config.*;
import net.romvoid95.galactic.core.utils.*;

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
                player.sendMessage(StringUtil.format("galactictweaks.versions.outdated0"));
                player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.outdated1", currentRunningVersion
						.toString()));
				player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.outdated2", updateVersion
						.toString()));
                player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.outdated0"));
                player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.updateMessage"));
    		}
    		if(checkThreadDone) MinecraftForge.EVENT_BUS.unregister(this);
    	}
	}
 }

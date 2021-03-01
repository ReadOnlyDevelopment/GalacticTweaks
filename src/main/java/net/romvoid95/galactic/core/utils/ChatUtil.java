package net.romvoid95.galactic.core.utils;

import lombok.experimental.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;

@UtilityClass
public class ChatUtil {

	public static void sendColorized (EntityPlayer player, String msg, TextFormatting formatting) {
		for (String part : msg.split("\n")) {
			TextComponentString component = new TextComponentString(part);
			component.getStyle().setColor(formatting);
			sendMessage(player, component);
		}
	}

	public static void sendColorizedMulti (EntityPlayer player, String[] msg) {
		TextComponentString component = new TextComponentString(TextFormatting.YELLOW + msg[0] + TextFormatting.RESET
				+ TextFormatting.DARK_GREEN + msg[1] + TextFormatting.RESET + TextFormatting.AQUA + msg[2]);
		sendMessage(player, component);

	}

	public static void sendMessage (EntityPlayer player, TextComponentString msg) {
		player.sendMessage(msg);
	}
	
}

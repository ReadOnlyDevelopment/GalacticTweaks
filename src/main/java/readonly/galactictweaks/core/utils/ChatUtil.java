package readonly.galactictweaks.core.utils;

import lombok.experimental.UtilityClass;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

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

package net.rom.gctweaks.core.utils;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.util.FakePlayerFactory;
import net.minecraftforge.fml.client.config.GuiConfigEntries;
import net.minecraftforge.fml.common.FMLCommonHandler;

public class Utilz {
	private Utilz() {}

	public static Property get (Configuration config, String[] category, String key, double defaultValue, String comment, boolean slider) {
		Property property = config.get(category[0], key, defaultValue, comment);
		if (slider && isClient())
			return property.setConfigEntryClass(GuiConfigEntries.NumberSliderEntry.class);
		return property;
	}

	public static FakePlayer getFakePlayer (World world) {
		return (world instanceof WorldServer) ? FakePlayerFactory.getMinecraft((WorldServer) world) : null;
	}

	public static boolean isClient () {
		return FMLCommonHandler.instance().getEffectiveSide().isClient();
	}

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

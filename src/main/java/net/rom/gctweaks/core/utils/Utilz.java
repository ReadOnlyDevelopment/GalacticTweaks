package net.rom.gctweaks.core.utils;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
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
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

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

	public static Planet getPlanets (int id) {
		return (Planet) GalaxyRegistry.getCelestialBodyFromDimensionID(id);
	}

	public static String getEntityNameFromClass (Class<? extends Entity> clazz) {
		String           name = null;
		ResourceLocation rl   = EntityList.getKey(clazz);

		if (rl != null) {
			EntityEntry entry = ForgeRegistries.ENTITIES.getValue(rl);

			if (entry != null) {
				name = entry.getName();
			}
		}

		if (name == null) {
			name = clazz.getSimpleName();
		}

		return name;
	}
}

package net.romvoid95.gctweaks.base.core.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.base.core.compat.CompatMods;

public class Utilz {
	private Utilz() {}

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
	

	public static boolean creatFile() {
		File dims = new File(GalacticTweaks.modFolder, "GalacticTweaks/ValidDimensionIDs.txt");
		if (dims.exists()) {
			dims.delete();
		}
		Map<String, Integer> planets = new HashMap<>();
		for (Entry<String, Planet> body : GalaxyRegistry.getRegisteredPlanets().entrySet()) {
			if (body.getValue().getReachable()) {
				planets.put(body.getValue().getLocalizedName(), body.getValue().getDimensionID());
			}
		}
		Map<String, Integer> moons = new HashMap<>();
		for (Entry<String, Moon> body : GalaxyRegistry.getRegisteredMoons().entrySet()) {
			if (body.getValue().getReachable()) {
				moons.put(body.getValue().getLocalizedName(), body.getValue().getDimensionID());
			}
		}
		try {
			dims.createNewFile();
			PrintWriter print_line = new PrintWriter(new FileWriter(dims));
			if(CompatMods.EXTRAPLANETS.isLoaded()) {
				print_line.println("### NOTICE ###");
				print_line.println("Planets & Moons that end with \"ep\" are added by ExtraPlanets.");
				print_line.println("Please keep this in mind if choosing a planet that is added by both ExtraPlanets & GalaxySpace");
			}
			print_line.println("");
			print_line.println("This file is regenerated every time minecraft is ran to handle any addons added or removed");
			print_line.println("");
			print_line.println("| ### PLANETS ###");
			print_line.println("|------------------");
			planets.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(
					s -> print_line.println(String.format("| %-15s %15s", s.getKey(), s.getValue())));
			print_line.println("");
			print_line.println("");
			print_line.println("| ### MOONS ###");
			print_line.println("|------------------");
			moons.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(
					s -> print_line.println(String.format("| %-15s %15s", s.getKey(), s.getValue())));
			print_line.flush();
			print_line.close();
			return true;
		} catch (IOException localIOException) {
			return false;
		}
	}
}

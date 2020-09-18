package net.romvoid95.gctweaks.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ICrashCallable;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.romvoid95.gctweaks.base.InternalFeature;
import net.romvoid95.gctweaks.gc.features.galaxyfeature.SeperateAddonPlanets;

public class CrashLogWarning extends InternalFeature {

	private static List<ModContainer>  loaded = Loader.instance().getActiveModList();
	private static Map<String, String> sets   = new HashMap<String, String>();
	private static Map<String, String> cores  = new HashMap<String, String>();
	private static Set<Entry<String, String>> setRemove = new HashSet<Map.Entry<String,String>>();
	private static Set<Entry<String, String>> coreRemove = new HashSet<Map.Entry<String,String>>();

	private static String setter  = "\n\t|\t";
	private static String setter2 = "\t|\t";

	private static void getDeps () {
		for (ModContainer mod : loaded) {
			for (ArtifactVersion a : mod.getDependencies()) {
				if (a.getLabel().contains("galacticraft")) {
					sets.putIfAbsent(mod.getName(), mod.getVersion());
				}
			}
			if (mod.getModId().equalsIgnoreCase("galacticraftcore")) {
				sets.putIfAbsent(mod.getName(), mod.getVersion());
			}
			if (mod.getModId().equalsIgnoreCase("asmodeuscore")) {
				cores.putIfAbsent(mod.getName(), mod.getVersion());
			}
			if (mod.getModId().equalsIgnoreCase("mjrlegendslib")) {
				cores.putIfAbsent(mod.getName(), mod.getVersion());
			}
		}
		sets.remove("GalacticTweaks");
	}

	private static void formatCores (StringBuilder builder) {
		for (Entry<String, String> mod : sets.entrySet()) {
			if (mod.getKey().equalsIgnoreCase("Extra Planets")) {
				for (Entry<String, String> core : cores.entrySet()) {
					if (core.getKey().equalsIgnoreCase("MJRLegendsLib")) {
						builder.append(setter2 + String.format("| %-20s %20s \n" + setter2 + "| --> %-16s %20s \n", mod
								.getKey(), mod.getValue(), core.getKey(), core.getValue()));
						coreRemove.add(core);
					}
				}
				setRemove.add(mod);
			}
			if (mod.getKey().equalsIgnoreCase("GalaxySpace")) {
				for (Entry<String, String> core : cores.entrySet()) {
					if (core.getKey().equalsIgnoreCase("AsmodeusCore")) {
						builder.append(setter2 + String.format("| %-20s %20s \n" + setter2 + "| --> %-16s %20s \n", mod
								.getKey(), mod.getValue(), core.getKey(), core.getValue()));
						coreRemove.add(core);
					}
				}
				setRemove.add(mod);
			}
		}
		sets.entrySet().removeAll(setRemove);
		cores.entrySet().removeAll(coreRemove);
	}

	public static void onCrash (StringBuilder builder) {
		if (SeperateAddonPlanets.seperatePlanets == true) {
			builder.append(setter);
			builder.append(setter);
			builder.append(setter + "\t\t\t\t\t~~~~~~~~~~ NOTICE ~~~~~~~~~~");
			builder.append(setter + "GalacticTweaks Separate Galaxy feature is enabled, If this crash is caused by");
			builder.append(setter
					+ "any planet that is under the new Galaxy, DO NOT send a new issue to the addons ");
			builder.append(setter + "dev for that planet, Please report the issue to GalacticTweaks issue tracker first.");
			builder.append(setter);
			builder.append(setter + "\t\t\t\t\t\t### ADDON DEVS ###");
			builder.append(setter + "This notice is for you to be aware that any crash-report sent containing this");
			builder.append(setter + "notice may not be as a result of your addon, please disregard if not");
			builder.append(setter);
			builder.append(setter);
		}
	}

	@Override
	public void postInit () {
		getDeps();
		FMLCommonHandler.instance().registerCrashCallable(new PackCrashEnhancement());

	}

	public class PackCrashEnhancement implements ICrashCallable {
		@Override
		public String getLabel () {

			String space = " \n\n";

			return space + "\t################ [GALACTICTWEAKS DATA START] ################";
		}

		@Override
		public String call () {

			StringBuilder builder = new StringBuilder();
			onCrash(builder);
			builder.append(setter);
			builder.append("---------------------------------------------\n");
			formatCores(builder);
			sets.entrySet().stream().sorted(Map.Entry.comparingByValue())
					.forEach(s -> builder.append(setter2 + String.format("| %-20s %20s %n", s.getKey(), s.getValue())));

			builder.append(setter2 + "---------------------------------------------");
			builder.append(setter);
			builder.append("\n\t################# [GALACTICTWEAKS DATA END] #################\n\n");
			return builder.toString();
		}
	}
}

package net.rom.gctweaks.modpacks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ICrashCallable;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.rom.gctweaks.core.NoConfigFeature;
import net.rom.gctweaks.galacticraftchanges.SeperateAddonPlanets;

public class CustomCrashLogData extends NoConfigFeature {

	String                 split            = "\n#########################################\n  ";


	private static List<ModContainer>  loaded = Loader.instance().getActiveModList();
	private static Map<String, String> sets   = new HashMap<String, String>();

	private static void getDeps () {
		for (ModContainer mod : loaded) {
			for (ArtifactVersion a : mod.getDependencies()) {
				if (a.getLabel().contains("galacticraft")) {
					sets.putIfAbsent(mod.getName(), mod.getVersion());
				}
			}
		}
		sets.remove("GalacticTweaks");
	}

	public static void onCrash (StringBuilder builder) {
		if (SeperateAddonPlanets.seperatePlanets == true) {
			builder.append("\n~~~~~~~~~~ NOTICE ~~~~~~~~~~\n");
			builder.append("GalacticTweaks Seperate Planets feature is enabled, If this crash is caused\n");
			builder.append("by any planet that is under the new Galaxy, DO NOT create a new issue to the addons dev\n");
			builder.append("for that planet, Please report the issue to GalacticTweaks issue tracker.\n");
			builder.append("\n### ADDON DEVS ###\n");
			builder.append("This notice is for you to be aware that any crash-report sent may not be as a result of your addon, please disregard\n\n");
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

			String label = split + "GALACTICTWEAKS DATA\n";

			return label;
		}

		@Override
		public String call () {

			StringBuilder builder = new StringBuilder();
			onCrash(builder);
			builder.append("---------------------------------------------\n");
			for (Entry<String, String> set : sets.entrySet()) {
				builder.append(String.format("| %-20s %20s %n", set.getKey(), set.getValue()));
			}
			builder.append("---------------------------------------------\n");
			builder.append(split);
			return builder.toString();
		}
	}

}

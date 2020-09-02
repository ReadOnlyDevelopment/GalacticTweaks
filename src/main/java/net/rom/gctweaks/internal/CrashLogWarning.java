package net.rom.gctweaks.internal;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.NameFileFilter;

import net.minecraft.client.Minecraft;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ICrashCallable;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import net.minecraftforge.fml.common.versioning.ArtifactVersion;
import net.rom.gctweaks.core.InternalFeature;
import net.rom.gctweaks.galacticraftchanges.seperategalaxy.SeperateAddonPlanets;

public class CrashLogWarning extends InternalFeature {

	private static List<ModContainer>  loaded = Loader.instance().getActiveModList();
	private static Map<String, String> sets   = new HashMap<String, String>();
	
	private static String setter = "\n\t|\t";
	private static String setter2 = "\t|\t";

	private static void getDeps () {
		for (ModContainer mod : loaded) {
			for (ArtifactVersion a : mod.getDependencies()) {
				if (a.getLabel().contains("galacticraft")) {
					sets.putIfAbsent(mod.getName(), mod.getVersion());
				}
			}
			if(mod.getModId().equalsIgnoreCase("galacticraftcore")) {
				sets.putIfAbsent(mod.getName(), mod.getVersion());
			}
			if(mod.getModId().equalsIgnoreCase("asmodeuscore")) {
				sets.putIfAbsent(mod.getName(), mod.getVersion());
			}
			if(mod.getModId().equalsIgnoreCase("mjrlegendslib")) {
				sets.putIfAbsent(mod.getName(), mod.getVersion());
			}
		}
		sets.remove("GalacticTweaks");
	}

	public static void onCrash (StringBuilder builder) {
		if (SeperateAddonPlanets.seperatePlanets == true) {
			builder.append(setter);
			builder.append(setter);
			builder.append(setter + "\t\t\t\t\t~~~~~~~~~~ NOTICE ~~~~~~~~~~");
			builder.append(setter + "GalacticTweaks Seperate Planets feature is enabled, If this crash is caused by");
			builder.append(setter + "any planet that is under the new Galaxy, DO NOT create a new issue to the addons ");
			builder.append(setter + "dev for that planet, Please report the issue to GalacticTweaks issue tracker.");
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
			builder.append("---------------------------------------------\n");
			sets.entrySet()
			  .stream()
			  .sorted(Map.Entry.comparingByValue())
			  .forEach(s -> builder.append(setter2 + String.format("| %-20s %20s %n", s.getKey(), s.getValue())));

			builder.append(setter2 + "---------------------------------------------");

			MapGenStructureIO.startNameToClassMap.entrySet().stream().forEach(s -> builder.append(String.format("| %-20s %20s %n", s.getKey(), s.getValue())));

			builder.append(setter);
			builder.append("\n\t################# [GALACTICTWEAKS DATA END] #################\n\n");
			return builder.toString();
		}
	}

}

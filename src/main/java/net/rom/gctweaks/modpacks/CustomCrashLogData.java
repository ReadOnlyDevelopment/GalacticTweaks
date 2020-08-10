package net.rom.gctweaks.modpacks;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.ICrashCallable;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModMetadata;
import net.rom.gctweaks.core.Feature;

public class CustomCrashLogData extends Feature {

	@Override
	public String[] category() {
		return new String[] {"crash-log-info"};
	}

	@Override
	public String comment() {
		return "Adds some shit";
	}


	private boolean useCustomCrashData;
	private String modpackName;
	private String modpackVersion;
	private static String[] addons = {"galaxyspace", "extraplanets", "moreplanets", "zollerngalaxy", "exoplanets"};

	@Override
	public void syncConfig(Configuration config, String[] category) {
		useCustomCrashData = config.get(category[0], "useCustomCrashData", false,
				"Should crash-logs contain custom data for modpack devs?").getBoolean();
		modpackName = config.get(category[0], "modpackName", "Modpack Name", "Put the name of the modpack here").getString();
		modpackVersion = config.get(category[0], "modpackVersion", "Pack Version", "Put the version of the modpack here").getString();
	}
	
	public void preInit() {
		if(useCustomCrashData) {
	        FMLCommonHandler.instance().registerCrashCallable(new PackCrashEnhancement());
		}
	}
	
	private static List<String> getAddonData() {
		List<String> data = new ArrayList<>();
		for (String addon : addons) {
			if (Loader.isModLoaded(addon)) {
				data.add(String.format("Addon Loaded: %s", addon));
			}
		}
		return data;
	}
	
	
	public class PackCrashEnhancement implements ICrashCallable {
	    @Override
	    public String getLabel() {
	    	String split = "##################################################\n\t";
	        String label = split + modpackName;

	        if (label == null || label.length() == 0) {
	            return "Modpack Data";
	        }
	        return label;
	    }

	    @Override
	    public String call() {
	        StringBuilder builder = new StringBuilder();
	        for (String data : getData()) {
	            builder.append(String.format("\n\t\t%s", data));
	        }

	        return builder.toString();
	    }

	    /**
	     * Build a list of the data to return and then list on the Crash Report.
	     */
	    private List<String> getData() {
	        List<String> data = new ArrayList<>();
	        data.add(String.format("Version: %s", modpackVersion)); // Version of the modpack.
	        data.addAll(getAddonData());
	        return data;
	    }
	}

}

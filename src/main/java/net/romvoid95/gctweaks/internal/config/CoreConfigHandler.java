package net.romvoid95.gctweaks.internal.config;

import java.io.File;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.romvoid95.gctweaks.Ref;

public class CoreConfigHandler {
	
	public static Configuration configuration;
	private static File file;

	public CoreConfigHandler(File parent) {
		MinecraftForge.EVENT_BUS.register(this);
		file = new File(parent, "GalacticTweaks/core.cfg");
		
		configuration = new Configuration(file, "1.0.0");
		configuration.load();
		
		rewriteConfigs();
	}
	
	public static void rewriteConfigs() {
		CValues.definedConfigValues(configuration);
		
		if(configuration.hasChanged()) {
			configuration.save();
		}
	}
	
    @SubscribeEvent
    public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.getModID().equalsIgnoreCase(Ref.MOD_ID)) {
        	rewriteConfigs();
        }
    }
}

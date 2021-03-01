package net.romvoid95.galactic.core.config;

import java.io.*;

import net.minecraftforge.common.config.*;
import net.romvoid95.api.ReadOnlyConfig.*;

public class CoreConfigHandler {

	private File file;
	private Configuration config;

	public CoreConfigHandler(File parent, ConfigVersion version) {
		file = new File(parent, "core.cfg");
		config = new Configuration(file, version.toString());
		rewriteConfigs();
	}
	
	public void rewriteConfigs() {
		config.load();
		CValues.definedConfigValues(config);
		
		if(config.hasChanged()) {
			config.save();
		}
	}
}

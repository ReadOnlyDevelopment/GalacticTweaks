package net.romvoid95.galactic.handler;

import java.io.File;

import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.Info;
import net.romvoid95.galactic.core.config.CoreConfig;

public class CoreConfigHandler {
	
	private CoreConfig CORE = new CoreConfig(Info.CONFVERSION);

	public CoreConfigHandler() {
		CORE.setConfigFile(new File(GalacticTweaks.modFolder, "core.cfg"));
		CORE.loadConfig();
	}
}

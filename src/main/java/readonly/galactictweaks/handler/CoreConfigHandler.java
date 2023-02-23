package readonly.galactictweaks.handler;

import java.io.File;

import readonly.galactictweaks.GCTweaks;
import readonly.galactictweaks.core.config.CoreConfig;

public class CoreConfigHandler {
	
	private CoreConfig CORE = new CoreConfig();

	public CoreConfigHandler() {
		CORE.setConfigFile(new File(GCTweaks.modFolder, "core.cfg"));
		CORE.loadConfig();
	}
}

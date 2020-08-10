package net.rom.gctweaks.modpacks;

import net.rom.gctweaks.core.Module;

public class ModpackModule extends Module {
	
	public ModpackModule(String name) {
		super(name);
	}


	@Override
	public void addFeatures() {
		registerFeature(new CustomCrashLogData());
	}

}

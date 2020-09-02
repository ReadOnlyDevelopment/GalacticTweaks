package net.rom.gctweaks.internal;

import net.rom.gctweaks.core.InternalModule;

public class CoreModule extends InternalModule {
	
	public CoreModule() {
		super();
	}


	@Override
	public void addInternalFeature () {
		registerInternalFeature(new CrashLogWarning());
		
	}

}

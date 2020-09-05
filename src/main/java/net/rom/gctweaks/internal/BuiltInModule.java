package net.rom.gctweaks.internal;

import net.rom.gctweaks.base.InternalModule;

public class BuiltInModule extends InternalModule {
	
	public BuiltInModule() {
		super();
	}

	@Override
	public void addInternalFeature () {
		registerInternalFeature(new CrashLogWarning());
	}
}

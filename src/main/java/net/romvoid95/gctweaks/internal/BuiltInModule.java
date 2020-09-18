package net.romvoid95.gctweaks.internal;

import net.romvoid95.gctweaks.base.InternalModule;

public class BuiltInModule extends InternalModule {
	
	public BuiltInModule() {
		super();
	}

	@Override
	public void addInternalFeature () {
		registerInternalFeature(new CrashLogWarning());
	}
}

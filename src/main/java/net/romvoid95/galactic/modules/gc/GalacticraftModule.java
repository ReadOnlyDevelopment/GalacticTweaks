package net.romvoid95.galactic.modules.gc;

import net.romvoid95.galactic.feature.*;
import net.romvoid95.galactic.modules.*;

public class GalacticraftModule extends Module {

	public GalacticraftModule() {
		super("GalacticraftTweaks");
	}

	@Override
	public void addClientFeatures() {
		Feature.clientClz.forEach(f -> registerFeature(f));
	}

	@Override
	public void addCommonFeatures() {
		Feature.commonClz.forEach(f -> registerFeature(f));
	}
}

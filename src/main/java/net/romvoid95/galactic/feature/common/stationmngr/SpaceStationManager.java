package net.romvoid95.galactic.feature.common.stationmngr;

import java.util.function.*;

import net.romvoid95.api.*;
import net.romvoid95.galactic.feature.*;

public class SpaceStationManager extends Feature {

	public SpaceStationManager(Supplier<Feature> feature, EnumSide side) {
		super(feature, side);
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public String category() {
		return null;
	}

	@Override
	public String comment() {
		return null;
	}

}

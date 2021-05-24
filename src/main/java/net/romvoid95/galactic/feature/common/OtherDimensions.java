package net.romvoid95.galactic.feature.common;

import net.romvoid95.api.*;
import net.romvoid95.galactic.feature.*;

public class OtherDimensions extends Feature {

	public OtherDimensions() {
		super(OtherDimensions::new, EnumSide.COMMON);
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

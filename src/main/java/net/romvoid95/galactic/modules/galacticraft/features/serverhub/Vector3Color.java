package net.romvoid95.galactic.modules.galacticraft.features.serverhub;

import java.util.function.*;

import micdoodle8.mods.galacticraft.api.vector.*;
import net.romvoid95.api.*;

public class Vector3Color implements Supplier<Vector3> {

	private final RGB fallbackColor;
	private Vector3 vector3;
	
	public Vector3Color(RGB fallback) {
		this.fallbackColor = fallback;
	}
	
	public Vector3Color fallback() {
		this.vector3 = new Vector3(fallbackColor.red(), fallbackColor.green(), fallbackColor.blue());
		return this;
	}
	
	public Vector3Color parse(String str) {
		RGB c = RGB.tryParse(str, fallbackColor);
		this.vector3 = new Vector3(c.red(), c.green(), c.blue());
		return this;
	}
	
	public Vector3Color of(int color) {
		RGB c = new RGB(color);
		this.vector3 = new Vector3(c.red(), c.green(), c.blue());
		return this;
	}
	
	public Vector3Color of(String color) {
		RGB c = RGB.tryParse(color, fallbackColor);
		this.vector3 = new Vector3(c.red(), c.green(), c.blue());
		return this;
	}
	
	@Override
	public Vector3 get() {
		return vector3;
	}
}

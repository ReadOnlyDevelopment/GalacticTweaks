package net.romvoid95.galactic.core.gc;

import java.util.ArrayList;

import com.google.common.base.Supplier;

public class CelestialGroup<B> extends ArrayList<B> implements Supplier<CelestialGroup<B>> {

	private static final long serialVersionUID = -881314146211720163L;

	@SuppressWarnings("unchecked")
	public void addAll(B... celestialBodies) {
		for(B b: celestialBodies) {
			add(b);
		}
	}

	@Override
	public CelestialGroup<B> get() {
		return this;
	}
}

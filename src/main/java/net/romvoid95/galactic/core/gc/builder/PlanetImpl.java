package net.romvoid95.galactic.core.gc.builder;

import micdoodle8.mods.galacticraft.api.galaxies.*;
import net.minecraft.util.*;
import net.romvoid95.galactic.core.gc.*;

public class PlanetImpl extends Planet {

	ScalableDistance distanceFromCenter;
	float orbitTime;
	float phaseShift;
	float relativeSize;
	ResourceLocation bodyIcon;
	int tierRequired;


	PlanetImpl(String planetName) {
		super(planetName);

	}

	public static class Builder {
		private String name;
		private ScalableDistance distanceFromCenter;
		private float orbitTime;
		private float phaseShift;
		private float relativeSize;
		private ResourceLocation bodyIcon;
		private int tierRequired;
		private RingColor ring;

		public Builder name(String name) {
			this.name = name;
			return this;
		}

		public Builder distanceFromCenter(float distanceFromCenter) {
			this.distanceFromCenter = new ScalableDistance(distanceFromCenter, distanceFromCenter);
			return this;
		}

		public Builder orbitTime(float orbitTime) {
			this.orbitTime = orbitTime;
			return this;
		}

		public Builder phaseShift(float phaseShift) {
			this.phaseShift = phaseShift;
			return this;
		}

		public Builder relativeSize(float relativeSize) {
			this.relativeSize = relativeSize;
			return this;
		}

		public Builder bodyIcon(ResourceLocation bodyIcon) {
			this.bodyIcon = bodyIcon;
			return this;
		}

		public Builder tierRequired(int tierRequired) {
			this.tierRequired = tierRequired;
			return this;
		}

		public Builder ringColor(float red, float green, float blue) {
			this.ring = new RingColor.Builder().red(red).green(green).blue(blue).build();
			return this;
		}

		public PlanetImpl build() {
			return new PlanetImpl(this);
		}
	}

	private PlanetImpl(Builder builder) {
		super(builder.name);
		this.setRelativeDistanceFromCenter(builder.distanceFromCenter);
		this.setRingColorRGB(builder.ring.red, builder.ring.green, builder.ring.blue);
		this.setRelativeOrbitTime(builder.orbitTime);
		this.setPhaseShift(builder.phaseShift);
		this.setRelativeSize(builder.relativeSize);
		this.setBodyIcon(builder.bodyIcon);
		this.setTierRequired(builder.tierRequired);
	}
}

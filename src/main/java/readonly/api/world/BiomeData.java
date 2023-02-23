/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package readonly.api.world;

import net.minecraft.world.biome.Biome.*;

public class BiomeData extends BiomeProperties {

	private String	biomeName;
	private float	baseHeight;
	private float	heightVariation;
	private float	temperature;

	private BiomeData(String name, float temperature, float rainfall, float baseHeight, float heightVariation, boolean enableRain, boolean enableSnow, int waterColor, String baseBiomeRegName) {
		super(name);

		this.setTemperature(temperature);
		this.setRainfall(rainfall);
		this.setBaseHeight(baseHeight);
		this.setHeightVariation(heightVariation);
		if (!enableRain) {
			this.setRainDisabled();
		}
		if (enableSnow) {
			this.setSnowEnabled();
		}
		this.setWaterColor(waterColor);
		this.setBaseBiome(baseBiomeRegName);
		this.biomeName = name;
		this.baseHeight = baseHeight;
		this.heightVariation = heightVariation;
		this.temperature = temperature;

	}

	protected String getBiomeName() {
		return biomeName;
	}

	protected float getBaseHeight() {
		return baseHeight;
	}

	protected float getHeightVariation() {
		return heightVariation;
	}

	protected float getTemperature() {
		return temperature;
	}

	public static class DataValues {
		private final String	biomeName;
		private float			baseHeight		= 0.1F;
		private float			heightVariation	= 0.2F;
		private float			temperature		= 0.5F;
		private float			rainfall		= 0F;
		private int				waterColor		= 16777215;
		private boolean			enableSnow		= true;
		private boolean			enableRain		= true;
		private String			baseBiomeRegName;

		public DataValues(String name) {
			this.biomeName = name;
		}

		public DataValues temperature(Float temperature) {
			if (temperature != null) {
				this.temperature = temperature;
			}
			return this;
		}

		public DataValues rainfall(Float rainfall) {
			if (rainfall != null) {
				this.rainfall = rainfall;
			}
			return this;
		}

		public DataValues baseHeight(Float baseHeight) {
			if (baseHeight != null) {
				this.baseHeight = baseHeight;
			}
			return this;
		}

		public DataValues heightVariation(Float heightVariation) {
			if (heightVariation != null) {
				this.heightVariation = heightVariation;
			}
			return this;
		}

		public DataValues disableRain() {
			if (rainfall == 0) {
				this.enableRain = false;
			}
			return this;
		}

		public DataValues disableSnow() {
			if (!enableRain) {
				this.enableSnow = false;
			}
			return this;
		}

		public DataValues waterColor(Integer waterColor) {
			if (waterColor != null) {
				this.waterColor = waterColor;
			}
			return this;
		}

		public DataValues baseBiome(String name) {
			if (name != null) {
				this.baseBiomeRegName = name;
			}
			return this;
		}

		public BiomeData finalzie() {
			return new BiomeData(this.biomeName, this.temperature, this.rainfall, this.baseHeight, this.heightVariation, this.enableRain, this.enableSnow, this.waterColor, this.baseBiomeRegName);
		}
	}
}

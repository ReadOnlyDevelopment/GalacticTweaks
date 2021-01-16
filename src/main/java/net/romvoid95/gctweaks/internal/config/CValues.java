package net.romvoid95.gctweaks.internal.config;

import net.minecraftforge.common.config.Configuration;

public class CValues {

	public static void definedConfigValues(Configuration configuration) {
		for(CoreBooleanValues currentValue : CoreBooleanValues.values()) {
			currentValue.currentValue = configuration.get(currentValue.category, currentValue.name, currentValue.defaultValue, currentValue.desc).getBoolean();
		}
	}
	
}

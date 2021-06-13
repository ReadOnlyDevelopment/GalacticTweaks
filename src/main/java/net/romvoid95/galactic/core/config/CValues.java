package net.romvoid95.galactic.core.config;

import net.minecraftforge.common.config.Configuration;
import net.romvoid95.galactic.GalacticTweaks;

public class CValues {

	public static void definedConfigValues(Configuration configuration) {
		for (CoreBooleanValues currentValue : CoreBooleanValues.values()) {
			currentValue.currentValue = configuration
					.get(currentValue.category, currentValue.name, currentValue.defaultValue, currentValue.desc)
					.getBoolean();
			if (!GalacticTweaks.instance.isDevBuild())
				GalacticTweaks.LOG.setDebugOut(currentValue.currentValue);
			else
				GalacticTweaks.LOG.setDebugOut(true);
		}
	}

}

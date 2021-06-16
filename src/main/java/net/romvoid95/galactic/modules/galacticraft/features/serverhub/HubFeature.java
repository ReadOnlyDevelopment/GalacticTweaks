package net.romvoid95.galactic.modules.galacticraft.features.serverhub;
//package net.romvoid95.galactic.feature.common.serverhub;
//
//import java.util.regex.*;
//
//import micdoodle8.mods.galacticraft.api.galaxies.*;
//import micdoodle8.mods.galacticraft.api.vector.*;
//import net.minecraft.world.*;
//import net.romvoid95.api.*;
//import net.romvoid95.galactic.feature.*;
//
//public class HubFeature extends Feature {
//
//	public static CelestialBody hub;
//	public static DimensionType HUB;
//	public static Vector3 sky;
//
//	@Override
//	public boolean isEnabled() {
//		return FeatureConfigs.SERVER_HUB;
//	}
//
//	@Override
//	public String category() {
//		return null;
//	}
//
//	@Override
//	public String comment() {
//		return null;
//	}
//
//	private Vector3 setSkyColor() {
//		final String configString = FeatureConfigs.skyColor.get();
//
//		// Define Regex patterns
//		final Pattern wordPattern = Pattern.compile("([a-zA-Z].*)");
//		final Pattern intPattern = Pattern.compile("([0-9].*)");
//
//		// First check if string is hex value
//		if (isSpecial(configString)) {
//			// Try and parse if so, falling back to fallback color
//			return new Vector3Color(RGB.BLACK).parse(configString).get();
//		}
//
//		else {
//			final Matcher wordMatcher = wordPattern.matcher(configString);
//			if(wordMatcher.find()) {
//				return new Vector3Color(RGB.BLACK).of(configString).get();
//			}
//
//			else {
//				final Matcher intMatcher = intPattern.matcher(configString);
//				if(intMatcher.find()) {
//					int integer = Integer.valueOf(intMatcher.group()).intValue();
//					if (integer == 0) {
//						return new Vector3Color(RGB.BLACK).fallback().get();
//					}
//					if (integer >= 1 || integer <= 16777215) {
//						return new Vector3Color(RGB.BLACK).of(integer).get();
//					}
//				}
//			}
//		}
//
//		return new Vector3Color(RGB.BLACK).fallback().get();
//	}
//
//	private boolean isSpecial(String str) {
//		return str.startsWith("0x") || str.startsWith("#");
//	}
//}

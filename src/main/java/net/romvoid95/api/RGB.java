package net.romvoid95.api;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.primitives.UnsignedInts;

public class RGB {

	private static final Map<String, RGB> NAMED_MAP = new HashMap<>();
	private static final Pattern PATTERN_LEADING_JUNK = Pattern.compile("(#|0x)", Pattern.CASE_INSENSITIVE);
	private static final Pattern PATTERN_HEX_CODE = Pattern.compile("(#|0[xX])?[0-9a-fA-F]{1,8}",
			Pattern.CASE_INSENSITIVE);

	public static final int VALUE_WHITE = 0xFFFFFF;

	public static final RGB BLACK = named("Black", 0x000000);
	public static final RGB BLUE = named("Blue", 0x0000FF);
	public static final RGB DARKRED = named("DarkRed", 0x8B0000);
	public static final RGB LIGHTBLUE = named("LightBlue", 0xADD8E6);
	public static final RGB GREEN = named("Green", 0x008000);
	public static final RGB GREY = named("Grey", 0x808080);
	public static final RGB ORANGE = named("Orange", 0xFFA500);
	public static final RGB ORANGERED = named("OrangeRed", 0xFF4500);
	public static final RGB RED = named("Red", 0xFF0000);
	public static final RGB YELLOW = named("Yellow", 0xFFFF00);

	private final double red;
	private final double green;
	private final double blue;

	public RGB(int color) {
		this((color >> 16) & 0xFF, (color >> 8) & 0xFF, color & 0xFF);
	}

	public RGB(int red, int green, int blue) {
		this(red / 255f, green / 255f, blue / 255f);
	}

	public RGB(double red, double green, double blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public int getColor() {
		int r = (int) (red * 255) << 16;
		int g = (int) (green * 255) << 8;
		int b = (int) (blue * 255);
		return r + g + b;
	}

	public double red() {
		return red;
	}

	public double green() {
		return green;
	}

	public double blue() {
		return blue;
	}

	////////////////////
	// STATIC METHODS //
	////////////////////

	private static RGB named(String name, int color) {
		RGB c = new RGB(color);
		NAMED_MAP.put(name.toLowerCase(Locale.ROOT), c);
		return c;
	}

	/**
	 * Validator for color strings. Accepts CSS color names or hex codes with
	 * optional leading '#' or '0x'.
	 *
	 * @param str The string to validate
	 * @return True if and only if the string can be parsed
	 */
	public static boolean validate(String str) {
		return NAMED_MAP.containsKey(str.toLowerCase(Locale.ROOT)) || PATTERN_HEX_CODE.matcher(str).matches();
	}

	/**
	 * Format the color as it would appear in a config file.
	 *
	 * @param color The color
	 * @return A string in the form "#XXXXXX" or "#XXXXXXXX", where 'X' is a hex
	 *         digit.
	 */
	public static String format(int color) {
		return String.format(color > 0xFFFFFF ? "#%08X" : "#%06X", color);
	}

	private static RGB parse(String str) {
		// Named color?
		str = str.toLowerCase(Locale.ROOT);
		if (NAMED_MAP.containsKey(str)) {
			return NAMED_MAP.get(str);
		}

		// Hex code
		str = PATTERN_LEADING_JUNK.matcher(str).replaceFirst("");
		int color = UnsignedInts.parseUnsignedInt(str, 16);
		return new RGB(color);
	}

	/**
	 * Attempt to parse a color, returning the default if it is not valid.
	 *
	 * @param str          The string to parse
	 * @param defaultValue The fallback value
	 * @return A color parsed from str, or from defaultValue if str is invalid
	 */
	public static RGB tryParse(String str, RGB defaultValue) {
		if (!validate(str)) {
			return defaultValue;
		}
		return parse(str);
	}
}

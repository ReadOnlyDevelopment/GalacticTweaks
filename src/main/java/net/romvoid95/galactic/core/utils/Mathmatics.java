package net.romvoid95.galactic.core.utils;

public class Mathmatics {

	public static boolean ensureInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean ensureDouble(String value) {
		try {
			Double.parseDouble(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean ensureLong(String value) {
		try {
			Long.parseLong(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public static boolean ensureFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (Exception e) {
		}
		return false;
	}
}

package net.rom.gctweaks;

public class Ref {

	// Mod info
	public static final String MOD_ID           = "gtweaks";
	public static final String MOD_NAME         = "GalacticTweaks";
	public static final String MOD_VERSION      = "${version}";
	public static final String MOD_BUILD_NUMBER = "@BUILD_NUMBER@";
	public static final String MOD_MC_VERSION   = "@MC_VERSION@";
	public static final String MOD_FINGERPRINT  = "@FINGERPRINT@";
	public static final String VERSION_URL      = "";

	// MOD ID's
	public static final String MOD_FORGE             = "forge";
	public static final String MOD_FORGE_VERSION     = "@FORGE_VERSION@";
	public static final String MOD_FORGE_VERSION_MIN = "14.23.5.2847";
	public static final String MOD_GC                = "required-after:galacticraftcore@[4.0.2.261,];required-after:galacticraftplanets;after:extraplanets";

	// Dependencies
	public static final String MOD_DEPENDENCIES = "required:forge@[" + MOD_FORGE_VERSION_MIN + ",);" + MOD_GC + ";";

}

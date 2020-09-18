package net.romvoid95.gctweaks;

public class Ref {

	// Mod info
	public static final String MOD_ID           = "gctweaks";
	public static final String MOD_NAME         = "GalacticTweaks";
	public static final String MOD_VERSION      = "@VERSION@";
	public static final String MOD_MC_VERSION   = "@MCVERSION@";
	public static final String MOD_FINGERPRINT  = "@FINGERPRINT@";

	// MOD ID's
	public static final String MOD_FORGE             = "forge";
	public static final String MOD_FORGE_VERSION     = "@FORGE_VERSION@";
	public static final String MOD_FORGE_VERSION_MIN = "14.23.5.2847";
	public static final String A                     = "after:asmodeuscore@[0.0.17,];";
	public static final String B                     = "after:extraplanets;";
	public static final String C                     = "after:galaxyspace;";
	public static final String D					 = "after:zollerngalaxy;";
	public static final String E					 = "after:moreplanets;";
	public static final String OPTS				     = A + B + C + D + E;

	public static final String MOD_GC = "required-after:galacticraftcore@[4.0.2.261,];required-after:galacticraftplanets;";

	// Dependencies
	public static final String DEPS = "required:forge@[" + MOD_FORGE_VERSION_MIN + ",);" + MOD_GC + ";" + OPTS;
	
	// CORE CONFIG
	public static final String CATEGORY_CORE         = "Core Mod Settings";
	public static final String CATEGORY_CORE_LANGKEY = "galactictweaks.configgui.category.core";

}

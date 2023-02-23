package readonly.galactictweaks;

public final class Info {
	public static final String ID = "galactictweaks";
	public static final String NAME = "GalacticTweaks";
	public static final String VERSION = "1.8.0";

	public static final String FORGE_MIN = "14.23.5.2847";
	public static final String OPT_MODS = "after:asmodeuscore@[0.0.30,);after:extraplanets;after:galaxyspace;after:zollerngalaxy;after:moreplanets;";
	public static final String GC = "required-after:galacticraftcore@[4.0.5,)";

	public static final String DEPS = "required:forge@[" + FORGE_MIN + ",);" + GC + ";" + OPT_MODS;
}

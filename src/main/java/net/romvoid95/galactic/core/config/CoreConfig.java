package net.romvoid95.galactic.core.config;

import net.romvoid95.api.config.def.Category;
import net.romvoid95.api.config.def.Comment;
import net.romvoid95.api.config.def.Key;
import net.romvoid95.api.config.values.OptBoolean;
import net.romvoid95.galactic.core.ReadOnlyConfig;

public class CoreConfig extends ReadOnlyConfig {
	
	private static Category coreCategory = Category.of("core");
	private static Key updChkKey = Key.of("RunUpdateCheck");
	private static Comment updChkComment = Comment.of("Runs checks for Mod updates, Set False to disable");
	private static Key debugKey = Key.of("LogDebugOutput");
	private static Comment debugComment = Comment.of("Warning! can cause log file size to increase heavily!");
	
	public static OptBoolean	runUpdateCheck = new OptBoolean(updChkKey, coreCategory, updChkComment, true);
	public static OptBoolean	enableDebugLog = new OptBoolean(debugKey, coreCategory, debugComment, false);

	public CoreConfig(ConfigVersion version) {
		super(CoreConfig.class);
		this.setConfigversion(version);

		this.addProperty(runUpdateCheck);
		this.addProperty(enableDebugLog);
	}

}

package readonly.galactictweaks.core.config;

import readonly.api.config.def.Category;
import readonly.api.config.def.Comment;
import readonly.api.config.def.Key;
import readonly.api.config.values.OptBoolean;
import readonly.api.config.values.OptString;
import readonly.galactictweaks.core.ReadOnlyConfig;

public class CoreConfig extends ReadOnlyConfig {

	private static Category coreCategory = Category.of("core");

	private static Key debugKey = Key.of("LogDebugOutput");
	private static Comment debugComment = Comment.of("Warning! can cause log file size to increase heavily!");

	private static Key logMarker = Key.of("DebugLogMaker");
	private static Comment logMarkerComment = Comment.of("Sets the starting marker that will preceed every debug log output for easy lookup");
	
	public static OptBoolean enableDebugLog = new OptBoolean(debugKey, coreCategory, debugComment, false);
	public static OptString	debugLogMarker = new OptString(logMarker, coreCategory, logMarkerComment, "#^#");

	public CoreConfig() {
		super(CoreConfig.class);

		this.addProperty(enableDebugLog);
		this.addProperty(debugLogMarker);
	}

}

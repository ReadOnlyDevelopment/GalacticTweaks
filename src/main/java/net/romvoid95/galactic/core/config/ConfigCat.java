package net.romvoid95.galactic.core.config;

import java.util.Locale;

public enum ConfigCat {
	
	CORE("Core Mod Settings", "Non-Feature related Settings", "galactictweaks.configgui.category.core");

    public final String name;
    public final String comment;
    public final String key;
    
    ConfigCat(String name, String comment, String key) {
        this.name = name.toLowerCase(Locale.ROOT);
        this.comment = comment;
        this.key = key;
	}
	
}

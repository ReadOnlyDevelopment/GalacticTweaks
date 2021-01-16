package net.romvoid95.gctweaks.internal.config;

public enum CoreBooleanValues {
	
    DO_UPDATE_CHECK(
            "Perform Update Check",
            ConfigCat.CORE,
            true,
            "If true, checks for updates on World Load."),
    OUTPUT_DEBUG(
            "Log Debug",
            ConfigCat.CORE,
            false,
            "If true, log debug information to the console");
    
    public final String name;
    public final String category;
    public final boolean defaultValue;
    public final String desc;

    public boolean currentValue;
	
	CoreBooleanValues(String name, ConfigCat category, boolean defaultValue, String desc) {
        this.name = name;
        this.category = category.name;
        this.defaultValue = defaultValue;
        this.desc = desc;
	}

    public boolean isEnabled() {
        return this.currentValue;
    }
}

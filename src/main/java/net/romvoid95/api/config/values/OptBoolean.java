package net.romvoid95.api.config.values;

import net.romvoid95.api.config.def.*;

public class OptBoolean extends OptValue {

    public boolean valueBoolean;

    public OptBoolean(ConfigKey key, ConfigCat category, ConfigComment comment, boolean defaultValue) {
    	super(Type.BOOLEAN, key, category, comment);
        this.valueBoolean = defaultValue;
        this.formatDescription(String.valueOf(defaultValue));
    }

	public boolean get() {
        return this.valueBoolean;
    }

    public void set(boolean value) {
        this.valueBoolean = value;
    }
}

package net.romvoid95.api.config.def;

import com.google.common.base.*;

import net.romvoid95.galactic.*;

public class ConfigKey implements Supplier<String> {
	
	private String key;
	
	private ConfigKey(String key) {
		this.key = key;

	}
	
	public static ConfigKey of(String key) {
		return new ConfigKey(key);
	}

	@Override
	public String get() {
		return this.key;
	}
	
	public String getLangKey() {
		return Info.ID + ".config.gui." + this.key.toLowerCase();
	}
}

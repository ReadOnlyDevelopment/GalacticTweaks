package net.romvoid95.api.config.def;

import com.google.common.base.*;

import net.minecraftforge.common.config.*;
import net.romvoid95.galactic.*;

public class ConfigCat implements Supplier<String> {
	
	public static final String SEP = Configuration.CATEGORY_SPLITTER;
	private String category;
	public boolean requiresWorldRestart = false;
	public boolean requiresMCRestart = false;
	
	private ConfigCat(String category) {
		this.category = category;
	}
	
	public static ConfigCat of(String category) {
		return new ConfigCat(category);
	}
	
	public static ConfigCat of(ConfigCat parent, String category) {
		String combined = parent.get() + SEP + category;
		return new ConfigCat(combined);
	}
	
	public ConfigCat setRequiredRestarts(boolean requiresWorldRestart, boolean requiresMCRestart) {
		this.requiresWorldRestart = requiresWorldRestart;
		this.requiresMCRestart = requiresMCRestart;
		return this;
	}
	
	@Override
	public String get() {
		return this.category;
	}
	
	public String getLangKey() {
		return Info.ID + ".config.gui.cat." + this.category.toLowerCase();
	}
}

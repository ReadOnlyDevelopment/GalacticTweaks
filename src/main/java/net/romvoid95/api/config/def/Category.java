package net.romvoid95.api.config.def;

import com.google.common.base.*;

import net.minecraftforge.common.config.*;
import net.romvoid95.galactic.*;

public class Category implements Supplier<String> {
	
	public static final String SEP = Configuration.CATEGORY_SPLITTER;
	private String category;
	public boolean requiresWorldRestart = false;
	public boolean requiresMCRestart = false;
	
	private Category(String category) {
		this.category = category;
	}
	
	public static Category of(String category) {
		return new Category(category);
	}
	
	public static Category of(Category parent, String category) {
		String combined = parent.get() + SEP + category;
		return new Category(combined);
	}
	
	public Category setRequiredRestarts(boolean requiresWorldRestart, boolean requiresMCRestart) {
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

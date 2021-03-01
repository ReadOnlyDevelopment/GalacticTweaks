package net.romvoid95.api.config.values;

import net.romvoid95.api.config.def.*;

public abstract class OptValue {

	private Type type;
	private ConfigKey key;
	private ConfigCat category;
	private ConfigComment comment;

	public OptValue(Type type, ConfigKey key, ConfigCat category, ConfigComment comment) {
		this.type = type;
		this.key = key;
		this.category = category;
		this.comment = comment;
	}

	void formatDescription(String defualtVal) {
		this.comment.get();
	}
	
	public Type getType() {
		return this.type;
	}

	public String key() {
		return this.key.get();
	}
	
	public String langKey() {
		return this.key.getLangKey();
	}

	public String category() {
		return this.category.get();
	}
	
	public ConfigCat getConfigCat() {
		return this.category;
	}

	public String comment() {
		return this.comment.get();
	}

	public enum Type {
		INTEGER,
		INTEGER_ARRAY,
		DOUBLE,
		DOUBLE_ARRAY,
		BOOLEAN,
		STRING,
		STRING_ARRAY
	}
}

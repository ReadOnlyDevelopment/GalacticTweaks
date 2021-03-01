package net.romvoid95.api.config.def;

import com.google.common.base.*;

public class ConfigComment implements Supplier<String> {
	
	private String comment;
	private String langKey;
	
	private ConfigComment(String comment) {
		this.comment = comment;
	}
	
	public static ConfigComment of(String comment) {
		return new ConfigComment(comment);
	}
	
	public ConfigComment setLangKey(String langKey) {
		this.langKey = langKey;
		return this;
	}

	@Override
	public String get() {
		return this.comment;
	}
	
	public String getLangKey() {
		return this.langKey;
	}
}

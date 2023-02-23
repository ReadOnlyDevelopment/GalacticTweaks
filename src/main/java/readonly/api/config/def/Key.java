package readonly.api.config.def;

import com.google.common.base.*;

import readonly.galactictweaks.*;

public class Key implements Supplier<String> {
	
	private String key;
	
	private Key(String key) {
		this.key = key;

	}
	
	public static Key of(String key) {
		return new Key(key);
	}

	@Override
	public String get() {
		return this.key;
	}
	
	public String getLangKey() {
		return Info.ID + ".config.gui.key." + this.key.toLowerCase();
	}
}

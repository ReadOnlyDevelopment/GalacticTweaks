package net.romvoid95.api.config.values;

import net.romvoid95.api.config.def.*;

public class OptArrayString extends OptValue {

	private String[] defaultValues;

	public OptArrayString(Key key, Category category, Comment comment, String... values) {
		super(Type.STRING_ARRAY, key, category, comment);
		this.defaultValues = values;

	}

	public String[] get() {
		return this.defaultValues;
	}

	public void set(String[] values) {
		this.defaultValues = values;
	}
}

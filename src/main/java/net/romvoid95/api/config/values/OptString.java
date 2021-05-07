package net.romvoid95.api.config.values;

import net.romvoid95.api.config.def.*;

public class OptString extends OptValue {

	private String valueString;
	private ValidValues validValues;
	private boolean needsValidation;

	public OptString(Key key, Category category, Comment comment, ValidValues validValues) {
		super(Type.STRING, key, category, comment);
		this.valueString = validValues.getDefault();
		this.validValues = validValues;
		this.needsValidation = true;
		this.setComment(Comment.of(format(validValues)));
	}

	public OptString(Key key, Category category, Comment comment, ValidDimIDs validValues) {
		super(Type.STRING, key, category, comment);
		this.valueString = validValues.getDefault();
		this.validValues = ValidValues.of(validValues.get());
		this.needsValidation = true;
		this.formatDescription(format(ValidValues.of(validValues.get())));
	}


	public OptString(Key key, Category category, Comment comment, String defaultValue) {
		super(Type.STRING, key, category, comment);
		this.valueString = defaultValue;
		this.needsValidation = false;
		this.formatDescription(defaultValue);
	}

	public String get() {
		return this.valueString;
	}

	public void set(String value) {
		this.valueString = value;
	}

	public String[] getValidValues() {
		return this.validValues.get();
	}

	public String[] getValidValuesDisplay() {
		return this.validValues.getDisplayValues();
	}

	public boolean needsValidation() {
		return this.needsValidation;
	}

	private String format(ValidValues validValues) {
		StringBuilder b = new StringBuilder();
		b.append(this.comment() + "\n");
		b.append("Valid Values: \n");
		for(String value : validValues.get()) {
			b.append("   '" + value + "'\n");
		}
		return b.toString();
	}
}

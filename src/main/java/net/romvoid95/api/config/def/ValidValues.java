package net.romvoid95.api.config.def;

public class ValidValues {
	
	private String[] values;
	private String defaultVal;
	private String[] displayValues;
	
	private ValidValues(String... values) {
		this.values = values;
		this.defaultVal = values[0];
	}

	public static ValidValues of(String... values) {
		return new ValidValues(values);
	}
	
	public ValidValues display(String... displayValues) {
		this.displayValues = displayValues;
		return this;
	}

	public String[] get() {
		return this.values;
	}
	
	public String getDefault() {
		return this.defaultVal;
	}
	
	public String[] getDisplayValues() {
		return this.displayValues;
	}
}

package net.romvoid95.api.config.def;

import java.util.*;

public class ValidDimIDs {
	
	private String[] values;
	private String defaultVal;
	private String[] displayValues;

	public ValidDimIDs(List<String> all) {
		for(String body : all) {
			if(body.equalsIgnoreCase("overworld")) {
				this.defaultVal = body;
			}
		}
		this.values = all.toArray(new String[0]); 
		this.displayValues = all.toArray(new String[0]); 
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

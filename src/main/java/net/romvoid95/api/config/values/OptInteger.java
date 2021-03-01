package net.romvoid95.api.config.values;

import net.romvoid95.api.config.*;
import net.romvoid95.api.config.def.*;

public class OptInteger extends OptValue {

    public int valueInt;
    private RangePair<Integer> range;
    private boolean hasRange;

    public OptInteger(ConfigKey key, ConfigCat category, ConfigComment comment, int defaultValue) {
    	super(Type.INTEGER, key, category, comment);
    	this.valueInt = defaultValue;
    	this.hasRange = false;
    	this.formatDescription(String.valueOf(defaultValue));
    }

    public OptInteger(ConfigKey key, ConfigCat category, ConfigComment comment, int defaultValue, int minValueInt, int maxValueInt) {
    	super(Type.INTEGER, key, category, comment);
    	this.range = RangePair.of(minValueInt, maxValueInt);
    	this.hasRange = true;
        this.valueInt = defaultValue;
        
        this.formatDescription(RangePair.of(minValueInt, maxValueInt).commentAddl(defaultValue));
    }
    
    public boolean hasRange() {
    	return this.hasRange;
    }
    
    public int min() {
    	return this.range.min();
    }
    
    public int max() {
    	return this.range.max();
    }

	public int get() {
        return this.valueInt;
    }

    public void set(int value) {
        this.valueInt = value;
    }
}

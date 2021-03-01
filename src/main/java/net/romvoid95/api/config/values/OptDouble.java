package net.romvoid95.api.config.values;

import net.romvoid95.api.config.*;
import net.romvoid95.api.config.def.*;

public class OptDouble extends OptValue {

	private RangePair<Double> range;
    private double valueDouble;
    private boolean hasRange;

    public OptDouble(ConfigKey key, ConfigCat category, ConfigComment comment, double defaultValue) {
    	super(Type.DOUBLE, key, category, comment);
        this.valueDouble = defaultValue;
        this.hasRange = false;
        this.formatDescription(String.valueOf(defaultValue));
    }

    public OptDouble(ConfigKey key, ConfigCat category, ConfigComment comment, double defaultValue, double minValueDouble, double maxValueDouble) {
    	super(Type.DOUBLE, key, category, comment);
    	this.range = RangePair.of(minValueDouble, minValueDouble);
    	this.hasRange = true;
        this.valueDouble = defaultValue;

        this.formatDescription(RangePair.of(minValueDouble, minValueDouble).commentAddl(defaultValue));
    }
    
    public boolean hasRange() {
    	return this.hasRange;
    }
    
    public double min() {
    	return this.range.min();
    }
    
    public double max() {
    	return this.range.max();
    }

	public double get() {
        return this.valueDouble;
    }

    public void set(double value) {
        this.valueDouble = value;
    }
}

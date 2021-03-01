package net.romvoid95.api.config.values;

import net.romvoid95.api.config.*;
import net.romvoid95.api.config.def.*;

public class OptArrayDouble extends OptValue {
	
	private double[] defaultValues;
	private RangePair<Double> range;
    private boolean hasRange;

	public OptArrayDouble(ConfigKey key, ConfigCat category, ConfigComment comment, double... values) {
		super(Type.DOUBLE_ARRAY, key, category, comment);
		this.defaultValues = values;
		this.hasRange = false;
	}
	
	public OptArrayDouble(ConfigKey key, ConfigCat category, ConfigComment comment,double minValueInt, double maxValueInt, double[] values) {
		this(key, category, comment, values);
		this.range = RangePair.of(minValueInt, maxValueInt);
		this.hasRange = true;
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
	
	public double[] get() {
        return this.defaultValues;
    }

    public void set(double... values) {
        this.defaultValues = values;
    }
}

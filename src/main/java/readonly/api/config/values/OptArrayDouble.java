package readonly.api.config.values;

import readonly.api.config.*;
import readonly.api.config.def.*;

public class OptArrayDouble extends OptValue {
	
	private double[] defaultValues;
	private Range<Double> range;
    private boolean hasRange;

	public OptArrayDouble(Key key, Category category, Comment comment, double... values) {
		super(Type.DOUBLE_ARRAY, key, category, comment);
		this.defaultValues = values;
		this.hasRange = false;
	}
	
	public OptArrayDouble(Key key, Category category, Comment comment,double minValueInt, double maxValueInt, double[] values) {
		this(key, category, comment, values);
		this.range = Range.of(minValueInt, maxValueInt);
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

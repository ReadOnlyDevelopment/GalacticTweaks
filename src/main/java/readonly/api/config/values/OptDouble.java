package readonly.api.config.values;

import readonly.api.config.*;
import readonly.api.config.def.*;

public class OptDouble extends OptValue {

	private Range<Double> range;
    private double valueDouble;
    private boolean hasRange;

    public OptDouble(Key key, Category category, Comment comment, double defaultValue) {
    	super(Type.DOUBLE, key, category, comment);
        this.valueDouble = defaultValue;
        this.hasRange = false;
        this.formatDescription(String.valueOf(defaultValue));
    }

    public OptDouble(Key key, Category category, Comment comment, double defaultValue, double minValueDouble, double maxValueDouble) {
    	super(Type.DOUBLE, key, category, comment);
    	this.range = Range.of(minValueDouble, minValueDouble);
    	this.hasRange = true;
        this.valueDouble = defaultValue;

        this.formatDescription(Range.of(minValueDouble, minValueDouble).commentAddl(defaultValue));
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

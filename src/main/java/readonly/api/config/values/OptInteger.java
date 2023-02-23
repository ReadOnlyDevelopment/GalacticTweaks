package readonly.api.config.values;

import readonly.api.config.*;
import readonly.api.config.def.*;

public class OptInteger extends OptValue {

    public int valueInt;
    private Range<Integer> range;
    private boolean hasRange;

    public OptInteger(Key key, Category category, Comment comment, int defaultValue) {
    	super(Type.INTEGER, key, category, comment);
    	this.valueInt = defaultValue;
    	this.hasRange = false;
    	this.formatDescription(String.valueOf(defaultValue));
    }

    public OptInteger(Key key, Category category, Comment comment, int defaultValue, int minValueInt, int maxValueInt) {
    	super(Type.INTEGER, key, category, comment);
    	this.range = Range.of(minValueInt, maxValueInt);
    	this.hasRange = true;
        this.valueInt = defaultValue;
        
        this.formatDescription(Range.of(minValueInt, maxValueInt).commentAddl(defaultValue));
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

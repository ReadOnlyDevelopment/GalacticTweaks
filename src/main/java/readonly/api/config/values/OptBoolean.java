package readonly.api.config.values;

import readonly.api.config.def.*;

public class OptBoolean extends OptValue {

    public boolean valueBoolean;

    public OptBoolean(Key key, Category category, Comment comment, boolean defaultValue) {
    	super(Type.BOOLEAN, key, category, comment);
        this.valueBoolean = defaultValue;
        this.formatDescription(String.valueOf(defaultValue));
    }

	public boolean get() {
        return this.valueBoolean;
    }

    public void set(boolean value) {
        this.valueBoolean = value;
    }
}

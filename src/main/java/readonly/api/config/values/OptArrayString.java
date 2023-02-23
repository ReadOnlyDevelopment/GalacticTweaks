package readonly.api.config.values;

import java.util.List;

import com.google.common.collect.Lists;

import readonly.api.config.def.Category;
import readonly.api.config.def.Comment;
import readonly.api.config.def.Key;

public class OptArrayString extends OptValue {

	private String[] defaultValues;

	public OptArrayString(Key key, Category category, Comment comment, String... values) {
		super(Type.STRING_ARRAY, key, category, comment);
		this.defaultValues = values;

	}

	public String[] get() {
		return this.defaultValues;
	}
	
	public List<String> getAsList()
	{
	    return Lists.newArrayList(this.defaultValues);
	}

	public void set(String[] values) {
		this.defaultValues = values;
	}
}

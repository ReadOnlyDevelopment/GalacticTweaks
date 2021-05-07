package net.romvoid95.api.config.values;

import net.minecraftforge.common.config.*;
import net.romvoid95.api.config.def.*;

public abstract class OptValue {

	private Type type;
	private Key key;
	private Category category;
	private Comment comment;

	public OptValue(Type type, Key key, Category category) {
		this.type = type;
		this.key = key;
		this.category = category;
	}

	public OptValue(Type type, Key key, Category category, Comment comment) {
		this.type = type;
		this.key = key;
		this.category = category;
		this.comment = comment;
	}

	void formatDescription(String defualtVal) {
		this.comment.add(Configuration.NEW_LINE + defualtVal);
	}

	void setComment(Comment comment) {
		this.comment = comment;
	}

	public Type getType() {
		return this.type;
	}

	public String key() {
		return this.key.get();
	}

	public String langKey() {
		return this.key.getLangKey();
	}

	public String category() {
		return this.category.get();
	}

	public Category getConfigCat() {
		return this.category;
	}

	public String comment() {
		return this.comment.get();
	}

	public enum Type {
		INTEGER,
		INTEGER_ARRAY,
		DOUBLE,
		DOUBLE_ARRAY,
		BOOLEAN,
		STRING,
		STRING_ARRAY
	}
}

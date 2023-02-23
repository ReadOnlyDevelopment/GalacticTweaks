package readonly.api.config.def;

import com.google.common.base.*;

public class Comment implements Supplier<String> {

	private String comment;
	private String langKey;

	private Comment(String comment) {
		this.comment = comment;
	}

	public static Comment of(String comment) {
		return new Comment(comment);
	}

	public Comment setLangKey(String langKey) {
		this.langKey = langKey;
		return this;
	}

	@Override
	public String get() {
		return this.comment;
	}

	public String add(String addition) {
		return this.comment + addition;
	}

	public String getLangKey() {
		return this.langKey;
	}
}

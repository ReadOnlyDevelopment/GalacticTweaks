package readonly.api.config.def;

import javax.annotation.Nullable;

import com.google.common.base.*;

import net.minecraftforge.common.config.*;
import readonly.galactictweaks.*;

public class Category implements Supplier<String> {
	
	public static final String SEP = Configuration.CATEGORY_SPLITTER;
	private Comment comment;
	private String category;
	public boolean requiresWorldRestart = false;
	public boolean requiresMCRestart = false;
	
	private Category(String category) {
		this.category = category;
		this.comment = null;
	}
	
	   private Category(String category, Comment comment) {
	        this.category = category;
	        this.comment = comment;
	    }
	
	public static Category of(String category) {
		return new Category(category);
	}
	
	   public static Category of(String category, String comment) {
	        return new Category(category, Comment.of(comment));
	    }
	
	public static Category of(Category parent, String category) {
		String combined = parent.get() + SEP + category;
		return new Category(combined);
	}
	
	   public static Category of(Category parent, String category, String comment) {
	        String combined = parent.get() + SEP + category;
	        return new Category(combined, Comment.of(comment));
	    }
	
	public Category setRequiredRestarts(boolean requiresWorldRestart, boolean requiresMCRestart) {
		this.requiresWorldRestart = requiresWorldRestart;
		this.requiresMCRestart = requiresMCRestart;
		return this;
	}
	
	@Override
	public String get() {
		return this.category;
	}
	
	@Nullable
	public String getComment() {
	    if(this.comment != null)
	        return this.comment.get();
	    else
	        return null;
	}
	
	public String getLangKey() {
		return Info.ID + ".config.gui.cat." + this.category.toLowerCase();
	}
}

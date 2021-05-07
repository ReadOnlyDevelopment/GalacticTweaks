package net.romvoid95.api.docs;

import net.romvoid95.api.docs.Entry.*;
import net.romvoid95.galactic.feature.*;

public class WikiDoc {

	private Entry<Name> namEntry;
	private Entry<Comment> commentEntry;
	private Entry<Useage> useageEntry;
	private final Class<? extends Feature> clazz;

	public WikiDoc(Class<? extends Feature> featureClass) {
		this.clazz = featureClass;
		
		
	}
	
	
    private String extractName(final Class field) {
//        final Doc document = field.getAnnotation(Doc.class);
//        if (document.value().isEmpty()) {
//            return field.getName();
//        }
//        return document.value();
    	return null;
    }

    /**
     * Extracts the comment from the given field.
     *
     * @param field The field to extract the comment from.
     * @return The processed comment for the given field or null.
     */
    private String extractComment(final Class field) {
//        final Doc document = field.getAnnotation(Doc.class);
//        if (document.comment().isEmpty()) {
//            return null;
//        }
//        return document.comment().trim()
//                .replaceAll(" ?\\(Default: [^\\)]+\\)", "") // Remove default value, we already have that
//                .replaceAll("\n\n+", "\n") // Remove empty lines
//                .replace("\"", "``") // " -> ``
//                .replaceAll("(^|\\W)'([^,']*)'(\\W|$)", "$1``$2``$3") // 'highlighted' -> ``highlighted``, ignore "it's"
//                .replaceAll("(^|\\W)'([^,']*)'s(\\W|$)", "$1``$2``\\\\s$3") // 'highlight's -> ``highlight``\s, ignore
//                                                                            // "it's"
//                .replaceAll("(^|[ \n\\(])(-?[0-9]+(?:\\.[0-9]+)?)([ \n\\)\\.]|$)", "$1``$2``$3") // 1 -> ``1``, ignore
//                                                                                                 // 1x1
//                .replaceAll("\n*(Note|Warning|WARNING):", "\n\n**$1**:"); // Highlight keywords
    	return null;
    }
}

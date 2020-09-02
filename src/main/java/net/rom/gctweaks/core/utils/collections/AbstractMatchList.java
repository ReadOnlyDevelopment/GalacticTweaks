package net.rom.gctweaks.core.utils.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.AccessLevel;
import lombok.Getter;
import net.minecraftforge.common.config.Configuration;
import net.rom.gctweaks.GalacticTweaks;


public abstract class AbstractMatchList <T> implements IMatchList<T> {
	
    private static final Pattern WILDCARD_PATTERN = Pattern.compile("\\*");

    // TODO: Change to enum?
    /** The list is a whitelist if this is true, or a blacklist if this is false */
    @Getter(AccessLevel.PROTECTED)
    private boolean whitelist;
    /** Stores loaded keys */
    @Getter(AccessLevel.PACKAGE)
    private final List<String> list = new ArrayList<>();
    /** If true, a config to set {@code whitelist} will be generated and loaded */
    private final boolean allowUserToChangeType;
    /** Default values for the list. These are NOT added to {@code list}, you must call loadConfig */
    private final String[] defaultValues;
    /** Stores the default value for {@code whitelist} */
    private final boolean defaultIsWhitelist;

    AbstractMatchList(boolean whitelist, boolean allowUserToChangeType, String... defaultValues) {
        this.whitelist = this.defaultIsWhitelist = whitelist;
        this.allowUserToChangeType = allowUserToChangeType;
        this.defaultValues = defaultValues.clone();
    }

    @Override
    public boolean matches(T t) {
        return contains(t) == this.whitelist;
    }

    /**
     * Should return true only if the object is in the list, not considering whether it is a whitelist or blacklist.
     * Implementations can use {@code containsKey} to check whether or not the object is in the list.
     * @return True if and only if {@code list} contains a key matching {@code t}, false otherwise
     */
    protected abstract boolean contains(T t);

    /**
     * Check whether or not the key is in {@code list}
     * @param key The key that represents the object being checked (ignores case)
     * @return True if and only if {@code list} contains the given key, false otherwise
     */
    protected boolean containsKey(String key) {
        return list.stream().anyMatch(entry -> entry.equalsIgnoreCase(key));
    }

    protected static boolean keyMatches(String pattern, String key) {
        if (pattern.endsWith("*")) {
            String regex = WILDCARD_PATTERN.matcher(pattern).replaceAll(Matcher.quoteReplacement(".*"));
            GalacticTweaks.logger.debug("'{}' matches '{}' = {}", key, pattern, key.matches("(?i)" + regex));
            return key.matches("(?i)" + regex);
        }
        return pattern.equalsIgnoreCase(key);
    }

    private static final String NAME_LIST_SUFFIX = " List";
    private static final String NAME_WHITELIST_SUFFIX = " IsWhitelist";
    private static final String COMMENT_WHITELIST = "If true, the list is a whitelist. Otherwise it is a blacklist.";

    @Override
    public void loadConfig(Configuration config, String name, String category, String comment) {
        list.clear();
        Collections.addAll(list, config.getStringList(name + NAME_LIST_SUFFIX, category, defaultValues, comment));
        if (allowUserToChangeType) {
            whitelist = config.getBoolean(name + NAME_WHITELIST_SUFFIX, category, defaultIsWhitelist, COMMENT_WHITELIST);
        }
    }
}

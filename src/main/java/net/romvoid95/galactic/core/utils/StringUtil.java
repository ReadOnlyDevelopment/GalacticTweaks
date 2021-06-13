package net.romvoid95.galactic.core.utils;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;

//TODO Do this shit better
public class StringUtil {
    public static ITextComponent format(String string, String key, Style style) {
    	return new TextComponentTranslation(string, key).setStyle(style);
    }
	
    public static ITextComponent format(String key, Style style) {
    	return new TextComponentTranslation(key).setStyle(style);
    }
    
    public static TextComponentTranslation formatComponent(String key, String string) {
    	return new TextComponentTranslation(string, key);
    }
	
    public static ITextComponent format(String key) {
    	return new TextComponentTranslation(key);
    }
    
    public static ITextComponent formatFromJson(String key) {
    	return ITextComponent.Serializer.fromJsonLenient(I18n.format(key));
    }
    
    public static ITextComponent formatFromJson(String key, String string) {
    	return ITextComponent.Serializer.fromJsonLenient(I18n.format(key, string));
    }
}

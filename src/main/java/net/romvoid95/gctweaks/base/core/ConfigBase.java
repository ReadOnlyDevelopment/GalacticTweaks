package net.romvoid95.gctweaks.base.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.config.ConfigCategory;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.IConfigElement;

public class ConfigBase {
	
	private Configuration config;
	public static ConfigVersion cfgVersion;
	public static List<IConfigElement> list = new ArrayList<IConfigElement>();
	
	public ConfigBase(File file, ConfigVersion cfgVersion) {
		config = new Configuration((file), cfgVersion.toString());
	}
	
	public Configuration getConfig() {
		return config;
	}
	
	public void addElement(String category) {
		ConfigCategory cat = this.config.getCategory(category);
		list.add(new ConfigElement(cat));
	}
	
	public List<IConfigElement> getConfigElements() {
		return list;
	}

	public static class ConfigVersion {
	    public static final ConfigVersion NULL_VERSION = new ConfigVersion(0, 0, 0);

	    public final int major;
	    public final int minor;
	    public final int patch;

	    public ConfigVersion(int major, int minor, int patch) {
	        this.major = major;
	        this.minor = minor;
	        this.patch = patch;
	    }
	    
	    @Override
		public String toString() {
	    	return toString(this);
	    }
	    
	    public static String toString(ConfigVersion version) {
	    	String mj = String.valueOf(version.major) + ".";
	    	String mi = String.valueOf(version.minor) + ".";
	    	String mp = String.valueOf(version.patch);
	    	return mj + mi + mp;
	    	
	    }
	    
	    public static boolean isVersionLessOrEqual(ConfigVersion comparate1, ConfigVersion comparate2) {
	        if (comparate1.major > comparate2.major) {
	            return false;
	        } else if (comparate1.major == comparate2.major) {
	            if (comparate1.minor > comparate2.minor) {
	                return false;
	            } else if (comparate1.major == comparate2.major && comparate1.minor == comparate2.minor) {
	                if (comparate1.patch > comparate2.patch) {
	                    return false;
	                }
	            }
	            return true;
	        }
	        return true;
	    }
	}
}

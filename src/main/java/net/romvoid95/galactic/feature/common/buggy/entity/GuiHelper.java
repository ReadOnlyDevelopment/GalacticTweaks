package net.romvoid95.gctweaks.gc.features.buggy.entity;

import java.util.HashMap;
import java.util.Map;

import micdoodle8.mods.galacticraft.core.tick.KeyHandlerClient;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;

public class GuiHelper {
	
	public static Map<TextFormatting, TextComponentString> parts = new HashMap<TextFormatting, TextComponentString>();
	
	public static enum Keys implements IStringSerializable {
		ACCELERATE(KeyHandlerClient.accelerateKey),
		DECELERATE(KeyHandlerClient.decelerateKey),
		RIGHT(KeyHandlerClient.rightKey),
		LEFT(KeyHandlerClient.leftKey),
		FUEL(KeyHandlerClient.openFuelGui);
		
		private KeyBinding key;
		
		Keys(KeyBinding key) {
			this.key = key;
		}
		
	    public static Keys getKey(String key) {
	        for (Keys value : values()) {
	            if (value.getName().equalsIgnoreCase(key)) {
	                return value;
	            }
	        }
	        return null;
	    }
	    
	    public int code() {
	    	return key.getKeyCode();
	    }
		
		public KeyBinding key() {
			return key;
		}

		@Override
		public String getName() {
			return name().toLowerCase();
		}
		
	}
	public static void sendDriverInfo(EntityPlayer player) {
		
	}
	
	private static String keyHeld(String key) {
		Keys k = Keys.getKey(key);
		String out = "";
		switch (k) {
		case ACCELERATE:
			out = displayString(k.code());
			break;
		case DECELERATE:
			out = displayString(k.code());
			break;
		case RIGHT:
			out = displayString(k.code());
			break;
		case LEFT:
			out = displayString(k.code());
			break;
		case FUEL:
			out = displayString(k.code());
			break;
		}
		return out;
	}

	private static String displayString(int key) {
		return GameSettings.getKeyDisplayString(key);
	}
	
}

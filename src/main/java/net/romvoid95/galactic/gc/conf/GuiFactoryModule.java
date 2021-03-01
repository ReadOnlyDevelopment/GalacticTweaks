package net.romvoid95.gctweaks.gc.conf;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.romvoid95.gctweaks.Ref;
import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.base.core.gui.GuiConfigFactory;

public class GuiFactoryModule extends GuiConfigFactory {
	public static class ConfigGUI extends GuiConfig {
		public ConfigGUI(GuiScreen parent) {
			super(parent, Module.config.getConfigElements(), Ref.MOD_ID, false, true, Ref.MOD_NAME);
		}
	}
	
	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new ConfigGUI(parentScreen);
	}
}

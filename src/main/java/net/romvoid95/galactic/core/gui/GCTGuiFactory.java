package net.romvoid95.galactic.core.gui;

import java.util.*;

import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.client.*;
import net.minecraftforge.fml.client.config.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.modules.*;

public class GCTGuiFactory implements IModGuiFactory {
	
	public static class GuiConfigGCT extends GuiConfig {

		public GuiConfigGCT(GuiScreen parent) {
			super(parent, Module.config.getElements(), Info.ID, false, false, "GalacticTweaks Configuration");
		}
	}

	@Override
	public void initialize(Minecraft minecraftInstance) {

	}

	@Override
	public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() {
		return null;
	}

	@Override
	public boolean hasConfigGui() {
		return true;
	}

	@Override
	public GuiScreen createConfigGui(GuiScreen parentScreen) {
		return new GuiConfigGCT(parentScreen);
	}
}
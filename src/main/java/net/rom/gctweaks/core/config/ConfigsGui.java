package net.rom.gctweaks.core.config;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.rom.gctweaks.ModuleController;
import net.rom.gctweaks.Ref;

public class ConfigsGui extends GuiConfig {
	
	public ConfigsGui() {
		this(null);
	}
	ConfigsGui(GuiScreen parent) {
        super(parent, getConfigElements(), Ref.MOD_ID, false, false, "GalacticTweaks");
    }

    private static List<IConfigElement> getConfigElements() {
        List<IConfigElement> configElements = new ArrayList<>();

        ModuleController.modules.forEach(module -> configElements.addAll(new ConfigElement(module.getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements()));
        return configElements;
    }
}

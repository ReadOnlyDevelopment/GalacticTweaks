package net.rom.gctweaks;

import java.util.ArrayList;
import java.util.List;

import net.rom.gctweaks.core.Module;
import net.rom.gctweaks.galacticraftchanges.GalacticraftModule;
import net.rom.gctweaks.modpacks.ModpackModule;

public class ModuleController {
	
    public static List<Module> modules = new ArrayList<>();
    
    public static void registerModules() {
        modules.add(new GalacticraftModule("Galacticraft Module"));
        modules.add(new ModpackModule());
    }


}

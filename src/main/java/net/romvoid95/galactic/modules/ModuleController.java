package net.romvoid95.galactic.modules;

import java.util.ArrayList;
import java.util.List;

import net.romvoid95.galactic.modules.gc.GalacticraftModule;

public class ModuleController {

    public static List<Module> modules = new ArrayList<>();

	public static final ModuleController INSTANCE = new ModuleController();
    
    public static void registerModules() {
        modules.add(new GalacticraftModule());
    }
}

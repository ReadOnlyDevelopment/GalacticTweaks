package net.romvoid95.galactic.modules;

import java.util.*;

import net.romvoid95.galactic.modules.gc.*;

public class ModuleController {

    public static List<Module> modules = new ArrayList<>();

	public static final ModuleController INSTANCE = new ModuleController();
    
    public static void registerModules() {
        modules.add(new GalacticraftModule());
    }
}

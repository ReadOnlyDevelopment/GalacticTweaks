package net.romvoid95.gctweaks;

import java.util.ArrayList;
import java.util.List;

import net.romvoid95.gctweaks.base.InternalModule;
import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.gc.GalacticraftModule;
import net.romvoid95.gctweaks.internal.BuiltInModule;

public class ModuleController {
	
    public static List<Module> modules = new ArrayList<>();
    
    public static List<InternalModule> internals = new ArrayList<>();
    
    public static void registerModules() {
        modules.add(new GalacticraftModule("Galacticraft Module"));
    }
    
    public static void registerInternalModules() {
    	internals.add(new BuiltInModule());
    }
}

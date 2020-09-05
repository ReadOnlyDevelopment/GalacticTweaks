package net.rom.gctweaks;

import java.util.ArrayList;
import java.util.List;

import net.rom.gctweaks.base.InternalModule;
import net.rom.gctweaks.base.Module;
import net.rom.gctweaks.gc.GalacticraftModule;
import net.rom.gctweaks.internal.BuiltInModule;

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

package readonly.galactictweaks;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import readonly.api.module.Module;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModule;

public class ModuleController {

    public static List<Module> modules = new ArrayList<>();
    
    public ModuleController() {
    	modules.add(new GalacticraftModule());
    }

	public void preInit(FMLPreInitializationEvent event) {
		modules.forEach(Module::setupConfig);
		modules.forEach(Module::handleFeatures);
		modules.forEach(Module::preInit);
	}

	public void init(FMLInitializationEvent event) {
		modules.forEach(Module::init);
	}

	public void postInit(FMLPostInitializationEvent event) {
		modules.forEach(Module::postInit);
	}
	
	public void onServerStarting(FMLServerStartingEvent event) {
		modules.forEach(module -> module.serverStartingEvent(event));
	}
}

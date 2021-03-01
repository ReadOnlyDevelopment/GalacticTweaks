package net.romvoid95.galactic;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
<<<<<<< Updated upstream:src/main/java/net/romvoid95/gctweaks/base/core/proxy/ClientProxy.java
import net.romvoid95.gctweaks.ModuleController;
import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.base.core.TickHandlerClientOverride;
import net.romvoid95.gctweaks.internal.config.ConfigCore;
import net.romvoid95.gctweaks.internal.versioning.VersionChecker;
=======
import net.romvoid95.galactic.modules.Module;
import net.romvoid95.galactic.modules.ModuleController;
>>>>>>> Stashed changes:src/main/java/net/romvoid95/galactic/ClientProxy.java

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ModuleController.modules.forEach(Module::proxyPreInit);
		MinecraftForge.EVENT_BUS.register(new TickHandlerClientOverride());
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		ModuleController.modules.forEach(Module::proxyInit);
		if (ConfigCore.enableCheckVersion) {
			VersionChecker.init();
		}
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ModuleController.modules.forEach(Module::proxyPostInit);
		super.postInit(event);
	}

}

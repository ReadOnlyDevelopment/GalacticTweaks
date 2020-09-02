package net.rom.gctweaks.core.proxy;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.rom.gctweaks.ModuleController;
import net.rom.gctweaks.core.InternalModule;
import net.rom.gctweaks.core.Module;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		ModuleController.modules.forEach(Module::proxyPreInit);
		ModuleController.internals.forEach(InternalModule::proxyPreInit);
		super.preInit(event);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		ModuleController.modules.forEach(Module::proxyInit);
		ModuleController.internals.forEach(InternalModule::proxyInit);
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		ModuleController.modules.forEach(Module::proxyPostInit);
		ModuleController.internals.forEach(InternalModule::proxyPostInit);
		super.postInit(event);
	}

}

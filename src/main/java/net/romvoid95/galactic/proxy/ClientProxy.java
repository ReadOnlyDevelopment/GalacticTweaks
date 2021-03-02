package net.romvoid95.galactic.proxy;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.event.*;
import net.romvoid95.api.registry.*;
import net.romvoid95.galactic.modules.*;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit(GCTRegistry registry, FMLPreInitializationEvent event) {
		super.preInit(registry, event);
		register_event(this);

		ModuleController.modules.forEach(Module::proxyPreInit);
		
		registry.clientPreInit(event);
	}

	@Override
	public void init(GCTRegistry registry, FMLInitializationEvent event) {
		super.init(registry, event);

		ModuleController.modules.forEach(Module::proxyInit);
		
		registry.clientInit(event);
	}

	@Override
	public void postInit(GCTRegistry registry, FMLPostInitializationEvent event) {
		super.postInit(registry, event);

		ModuleController.modules.forEach(Module::proxyPostInit);
		
		registry.clientPostInit(event);
	}
	
	public World getWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}
}

package net.romvoid95.galactic.proxy;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.event.*;
import net.romvoid95.galactic.modules.*;

public class ClientProxy extends ServerProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		register_event(this);

		ModuleController.modules.forEach(Module::proxyPreInit);
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);

		ModuleController.modules.forEach(Module::proxyInit);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);

		ModuleController.modules.forEach(Module::proxyPostInit);
	}
	
	public World getWorld() {
		return Minecraft.getMinecraft().world;
	}

	@Override
	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}
}

package net.romvoid95.galactic.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.romvoid95.galactic.modules.Module;
import net.romvoid95.galactic.modules.ModuleController;

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

	public EntityPlayer getClientPlayer() {
		return Minecraft.getMinecraft().player;
	}
}

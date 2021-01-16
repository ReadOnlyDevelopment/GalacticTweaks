package net.romvoid95.gctweaks;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.gctweaks.base.InternalModule;
import net.romvoid95.gctweaks.base.Module;
import net.romvoid95.gctweaks.base.core.IHandler;
import net.romvoid95.gctweaks.gc.GalacticraftModule;
import net.romvoid95.gctweaks.internal.BuiltInModule;

public class ModuleController implements IHandler {

	public static List<Module> modules = new ArrayList<>();
	public static List<InternalModule> internals = new ArrayList<>();

	public static final ModuleController INSTANCE = new ModuleController();

	public ModuleController() {
		modules.add(new GalacticraftModule("Galacticraft Module"));
		internals.add(new BuiltInModule());
	}

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		MinecraftForge.EVENT_BUS.register(this);
		internals.forEach(m -> {
			m.registerPacket();
			m.preInit();
		});

		modules.forEach(m -> {
			m.setupConfig(event);
			m.syncConfig();
			m.registerPacket();
			m.preInit();
		});
	}

	@Override
	public void init(FMLInitializationEvent event) {
		internals.forEach(m -> {
			m.init();
		});

		modules.forEach(m -> {
			m.init();
		});
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		internals.forEach(m -> {
			m.postInit();
		});

		modules.forEach(m -> {
			m.postInit();
		});
	}
	
	@Override
	@EventHandler
	public void onServerStarting(FMLServerStartingEvent event) {
		internals.forEach(m -> {
			m.serverStartingEvent(event);
		});

		modules.forEach(m -> {
			m.serverStartingEvent(event);
		});
	}
}

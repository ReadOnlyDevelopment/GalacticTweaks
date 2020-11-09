package net.romvoid95.gctweaks.base;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.romvoid95.gctweaks.GalacticTweaks;

public abstract class InternalModule {
	private List<InternalFeature> internals = new ArrayList<>();
	
	public abstract void addInternalFeature ();
	
	public InternalModule() {
		addInternalFeature();
	}
	
	public void preInit () {
		internals.stream().filter(InternalFeature::usesEvents).forEach(MinecraftForge.EVENT_BUS::register);
		internals.forEach(InternalFeature::preInit);
	}

	public void init () {
		internals.forEach(InternalFeature::init);
	}

	public void postInit () {
		internals.forEach(InternalFeature::postInit);
	}


	////////// PROXIES ///////////
	
	public void proxyPreInit () {
		internals.stream().filter(InternalFeature::sidedProxy).forEach(MinecraftForge.EVENT_BUS::register);
		internals.forEach(InternalFeature::proxyPreInit);
	}
	
	public void proxyInit () {
		internals.forEach(InternalFeature::proxyInit);
	}
	
	public void proxyPostInit () {
		internals.forEach(InternalFeature::proxyPostInit);
	}
	
	public void serverStartingEvent(FMLServerStartingEvent event) {
		internals.forEach(internal -> internal.ServerStartingEvent(event));
	}
	
	////////// MISC //////////
	
	protected void registerInternalFeature (InternalFeature feature) {
		internals.add(feature);
	}

	public void registerPacket () {
		internals.forEach(feature -> feature.registerPacket(GalacticTweaks.network));
	}
}

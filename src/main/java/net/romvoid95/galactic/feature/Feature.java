package net.romvoid95.galactic.feature;

import java.util.*;
import java.util.function.*;

import net.minecraftforge.fml.common.event.*;
import net.romvoid95.api.*;

public abstract class Feature implements IFeature {

	public static List<Feature> commonClz = new ArrayList<>();
	public static List<Feature> clientClz = new ArrayList<>();

	public Feature(Supplier<Feature> feature, EnumSide side) {
		if(side == EnumSide.CLIENT) {
			Feature.clientClz.add(feature.get());
		}
		if(side == EnumSide.COMMON) {
			Feature.commonClz.add(feature.get());
		}
	}

	protected List<String> propOrder = new ArrayList<>();

	public void preInit() {
	}

	public void init() {
	}

	public void postInit() {
	}

	public void proxyPostInit() {
	}

	public void proxyPreInit() {
	}

	public void proxyInit() {
	}

	public void ServerStartingEvent(FMLServerStartingEvent event) {
	}

	public abstract String category();

	public abstract String comment();

	public boolean usesEvents() {
		return false;
	}

	public boolean sidedProxy() {
		return false;
	}

}

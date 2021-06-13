package net.romvoid95.api.feature;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public abstract class Feature implements IFeature {

	protected String category;
	protected String categoryComment;

	public String getCategory() {
		return category;
	}

	public String getCategoryComment() {
		return categoryComment;
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

	public boolean usesEvents() {
		return false;
	}

	public boolean sidedProxy() {
		return false;
	}

}

package readonly.api.feature;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import readonly.api.config.def.Category;

public abstract class Feature implements IFeature {

	protected String category;
	protected String categoryComment;

	public Category getCategory() {
		return Category.of(category);
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

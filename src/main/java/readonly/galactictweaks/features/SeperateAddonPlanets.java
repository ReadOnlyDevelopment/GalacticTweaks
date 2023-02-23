package readonly.galactictweaks.features;

import asmodeuscore.core.astronomy.BodiesRegistry;
import net.minecraftforge.common.MinecraftForge;
import readonly.api.GalacticraftAddon;
import readonly.api.feature.Feature;
import readonly.galactictweaks.core.GCTLog;
import readonly.galactictweaks.core.client.gui.ClientGuiHandler;
import readonly.galactictweaks.core.gc.PlanetData;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class SeperateAddonPlanets extends Feature {
	
    public static String comment = "Move Duplicate Sol Planets/Moons to a new galaxy"
        + "\nTHIS FEATURE WILL NOT BE EXTENDED OR ADDED TO IN FUTURE VERSIONS\nANY CRASHES OR BUGS RESULTING FROM THIS OPTION BEING ENABLED\n"
        + "SHOULD BE REPORTED TO THIS MODS ISSUE TRACKER NOT THE PLANETS ADDON DEV \n\nUse at your own discretion";
    
	private boolean canActivate = false;

	public SeperateAddonPlanets() {
		this.category = "SeperateAddonPlanets";
	}
	
	@Override
	public void preInit() {
		if(this.isEnabled()) {
			if(GalacticraftAddon.RequiredLib.ASMODEUSCORE.isLoaded()) {
				if(checkIfPresent()) {
					this.canActivate = true;
				}
			} else {
				GCTLog.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + this.category + "]");
				GCTLog.error("The Mod [%s] MUST be installed to use this Stability ",
						GalacticraftAddon.RequiredLib.ASMODEUSCORE.toString());
			}
		}
	}

	@Override
	public void postInit() {
		if(this.canActivate) {
			BodiesRegistry.setMaxTier(10);
			PlanetData data = new PlanetData();
			data.create(GalacticraftModuleConfig.modid.get());
		}
	}

	@Override
	public void proxyPostInit() {
		if(this.canActivate) {
			MinecraftForge.EVENT_BUS.register(new ClientGuiHandler());
		}
	}
	
	private Boolean checkIfPresent() {
		boolean oneLoaded = true;
		if (!GalacticraftAddon.EXTRAPLANETS.isLoaded()) {
			GCTLog.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + this.category + "]");
			GCTLog.error("The Mod [%s] MUST be installed to use this Stability ",
					GalacticraftAddon.EXTRAPLANETS.toString());
			oneLoaded = false;
		}
		if (!GalacticraftAddon.GALAXYSPACE.isLoaded()) {
			GCTLog.error("[REQUIRED MOD NOT FOUND FOR FEATURE " + this.category + "]");
			GCTLog.error("The Mod [{}] MUST be installed to use this Stability ",
					GalacticraftAddon.GALAXYSPACE.toString());
			oneLoaded = false;
		}
		return oneLoaded;
	}
	
	@Override
	public boolean usesEvents() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.SEPERATE_ADDONPLANETS;
	}
}

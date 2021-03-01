package net.romvoid95.gctweaks.gc.features.galaxy;


import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.astronomy.BodiesRegistry.Galaxies;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.ResourceLocation;
import net.romvoid95.gctweaks.Ref;

public class GCSystems {
	public static Galaxies    EXTRAPLANETS;
	public static SolarSystem EP_SYSTEM;
	public static SolarSystem OTHER;

	public static void init() {
		build();
	}

	private static void build() {
		EXTRAPLANETS = BodiesRegistry.registerGalaxy("wormhole", new ResourceLocation(Ref.MOD_ID, "textures/gui/galaxy/wormhole.png"));

		EP_SYSTEM = new SolarSystem("secondSystem", EXTRAPLANETS.getName()).setMapPosition(new Vector3(0.0F, 0.0F, 0.0F));
		Star starSol4 = (Star) new Star("epsystem").setParentSolarSystem(EP_SYSTEM).setTierRequired(-1);
		starSol4.setBodyIcon(new ResourceLocation(micdoodle8.mods.galacticraft.core.Constants.ASSET_PREFIX, "textures/gui/celestialbodies/sun.png"));
		EP_SYSTEM.setMainStar(starSol4);
		GalaxyRegistry.registerSolarSystem(EP_SYSTEM);

	}
}

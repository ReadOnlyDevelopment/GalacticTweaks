package net.romvoid95.galactic.modules.galacticraft.features.serverhub;
//package net.romvoid95.galactic.feature.common.serverhub;
//
//import java.util.*;
//
//import micdoodle8.mods.galacticraft.api.galaxies.*;
//import micdoodle8.mods.galacticraft.api.prefab.world.gen.*;
//import micdoodle8.mods.galacticraft.api.vector.*;
//import net.minecraft.block.*;
//import net.minecraft.util.*;
//import net.minecraft.world.*;
//import net.minecraft.world.gen.*;
//import net.romvoid95.galactic.feature.*;
//
//
//public class WorldProviderHubNormal extends WorldProviderSpace {
//
//	@Override
//	public float getGravity() {
//		return Float.valueOf(String.valueOf(FeatureConfigs.gravity.get()));
//	}
//
//	@Override
//	public double getFuelUsageMultiplier() {
//		return 0;
//	}
//
//	@Override
//	public boolean canSpaceshipTierPass(int tier) {
//		return false;
//	}
//
//	@Override
//	public float getFallDamageModifier() {
//		return 0;
//	}
//
//	@Override
//	public CelestialBody getCelestialBody() {
//		return HubFeature.hub;
//	}
//
//	@Override
//	public int getDungeonSpacing() {
//		return 0;
//	}
//
//	@Override
//	public ResourceLocation getDungeonChestType() {
//		return null;
//	}
//
//	@Override
//	public List<Block> getSurfaceBlocks() {
//		return null;
//	}
//
//	@Override
//	public Vector3 getFogColor() {
//		return new Vector3(0, 0, 0);
//	}
//
//	@Override
//	public Vector3 getSkyColor() {
//		return HubFeature.sky;
//	}
//
//	@Override
//	public boolean hasSunset() {
//		return FeatureConfigs.hasSunset.get();
//	}
//
//	@Override
//	public long getDayLength() {
//		return FeatureConfigs.daylength.get();
//	}
//
//	@Override
//	public Class<? extends IChunkGenerator> getChunkProviderClass() {
//		return null;
//	}
//
//	@Override
//	public DimensionType getDimensionType() {
//		return HubFeature.HUB;
//	}
//
//}

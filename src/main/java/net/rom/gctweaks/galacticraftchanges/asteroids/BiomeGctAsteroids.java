package net.rom.gctweaks.galacticraftchanges.asteroids;

import java.util.LinkedList;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedEnderman;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedSkeleton;
import micdoodle8.mods.galacticraft.core.entities.EntityEvolvedZombie;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;

public class BiomeGctAsteroids extends BiomeGenBaseGC {
	public static final Biome asteroid = new BiomeGctAsteroids(new BiomeProperties("Tweaked Asteroid Belt").setRainfall(0.0F));

	private BiomeGctAsteroids(BiomeProperties properties) {
		super(properties, true);
		this.spawnableMonsterList.clear();
		this.spawnableWaterCreatureList.clear();
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.resetMonsterListByMode(ConfigManagerCore.challengeMobDropsAndSpawning);
	}

	@Override
	public void registerTypes (Biome b) {
		// Currently unused for Asteroids due to adaptive biomes system
		BiomeDictionary
				.addTypes(b, BiomeDictionary.Type.COLD, BiomeDictionary.Type.DRY, BiomeDictionary.Type.DEAD, BiomeDictionary.Type.SPOOKY);
	}

	public void resetMonsterListByMode (boolean challengeMode) {
		this.spawnableMonsterList.clear();
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedZombie.class, 300, 1, 4));
		this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedSkeleton.class, 200, 1, 3));
		if (challengeMode)
			this.spawnableMonsterList.add(new SpawnListEntry(EntityEvolvedEnderman.class, 150, 1, 2));
	}

	@Override
	public void initialiseMobLists (LinkedList<SpawnListEntry> mobInfo) {}

	@Override
	public float getSpawningChance () {
        return 0.01F;
	}
}

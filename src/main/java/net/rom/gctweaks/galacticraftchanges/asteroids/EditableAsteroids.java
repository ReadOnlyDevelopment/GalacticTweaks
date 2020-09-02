package net.rom.gctweaks.galacticraftchanges.asteroids;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import micdoodle8.mods.galacticraft.planets.asteroids.ConfigManagerAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.TeleportTypeAsteroids;
import micdoodle8.mods.galacticraft.planets.asteroids.world.gen.SpecialAsteroidBlock;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.rom.gctweaks.GalacticTweaks;
import net.rom.gctweaks.core.Feature;

public class EditableAsteroids extends Feature {


	public static Planet   abelt         = AsteroidsModule.planetAsteroids;
	public static boolean  customize_asteroids;
	public static int      abandonedStationSpacing;
	public static String[] coreBlockList = { 
			"minecraft:diamond_ore:0:1:0.1",
			"galacticraftplanets:asteroids_block:0:11:0.25",
			"galacticraftplanets:asteroids_block:1:7:0.3", 
			"galacticraftplanets:asteroids_block:2:5:0.3", 
			"galacticraftplanets:asteroids_block:3:5:0.2",
			"galacticraftplanets:asteroids_block:4:4:0.15",
			"galacticraftplanets:asteroids_block:5:3:0.2", 
			"galacticraftcore:basic_block_core:12:2:0.13"};
	
	public static String[] shellBlockList = { 
			"galacticraftplanets:asteroids_block:0:1:0.15",
			"galacticraftplanets:asteroids_block:1:3:0.15", 
			"galacticraftplanets:asteroids_block:2:1:0.15", 
			"galacticraftplanets:dense_ice:0:1:0.15" };
	
	public static String[] scatteredBlocks = {
			
			"asteroids_block"
	};

	@Override
	public String[] category () {
		return new String[] { "customize_asteroids" };
	}

	@Override
	public String comment () {
		return "Enabled various customizations to be done to the Asteroid Belt dimension";
	}

	@Override
	public void syncConfig (Configuration config, String[] category) {
		String c = category[0];

		customize_asteroids = config
				.get(c, "00-Customize-Asteroids", false, "Set to true to enable the customization of the Asteroid Belt")
				.getBoolean();

		abandonedStationSpacing = config
				.getInt("01-Abandoned Base Spacing", c, 576, 350, Integer.MAX_VALUE, "Amount of blocks between each Abandoned Base spawned");

		coreBlockList = config
				.get(c, "02-Core Blocks", coreBlockList, "Blocks that make up the inside/core of asteroids")
				.getStringList();

		shellBlockList = config
				.get(c, "03-Shell Blocks", shellBlockList, "Blocks that make up the outter shell of asteroids")
				.getStringList();

		scatteredBlocks = config
				.get(c, "04-Scattered Blocks", scatteredBlocks, "Blocks that are Scattered Between the large Asteroids")
				.getStringList();
	}

	@Override
	public void postInit () {

		int dimID = ConfigManagerAsteroids.dimensionIDAsteroids;

		abelt.setDimensionInfo(dimID, WorldProviderSpaceRocks.class).setTierRequired(3);
		abelt.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(1.75F, 1.75F));
		abelt.setRelativeOrbitTime(45.0F);
		abelt.setPhaseShift((float) (Math.random() * (2 * Math.PI)));
		abelt.setBodyIcon(new ResourceLocation(Constants.ASSET_PREFIX, "textures/gui/celestialbodies/asteroid.png"));
		abelt.setAtmosphere(new AtmosphereInfo(false, false, false, -1.5F, 0.05F, 0.0F));
		abelt.addChecklistKeys("equip_oxygen_suit", "craft_grapple_hook", "thermal_padding");
		abelt.setBiomeInfo(BiomeGctAsteroids.asteroid);
		GalaxyRegistry.registerPlanet(abelt);
		GalacticraftRegistry.registerTeleportType(WorldProviderSpaceRocks.class, new TeleportTypeAsteroids());

	}

	@Override
	public void ServerStartingEvent () {
		ChunkProviderGCTAsteroids.reset();
	}



	public static class AsteroidData {
		
		static List<String>           cores         = new ArrayList<String>();
		static List<String>           shells        = new ArrayList<String>();
		static List<String>           scattered     = new ArrayList<String>();
		
		private static final boolean doTest = true;
		
		public static void registerData() {
			getConfigLists();
			if (doTest) {
				initTest();
			} else {
				initCore();
				initShell();
				initOtherBlocks();
			}
			StringBuilder builder = new StringBuilder();
			builder.append("### CORE BLOCKS ###");
			builder.append("\n");
			for (SpecialAsteroidBlock b : ChunkProviderGCTAsteroids.coreBlocks) {
				builder.append(b.block.getUnlocalizedName());
				builder.append("\n");
			}
			builder.append("### SHELL BLOCKS ###");
			builder.append("\n");
			for (SpecialAsteroidBlock b : ChunkProviderGCTAsteroids.shellBlocks) {
				builder.append(b.block.getUnlocalizedName());
				builder.append("\n");
			}
			builder.append("### ASTEROID BLOCKS ###");
			builder.append("\n");
			for (Block b : ChunkProviderGCTAsteroids.asteroidBlocks) {
				builder.append(b.getUnlocalizedName());
				builder.append("\n");
			}
			GalacticTweaks.logger.info(builder.toString());
		}
		
		private static  void getConfigLists() {
			Collections.addAll(cores, coreBlockList);
			Collections.addAll(shells, shellBlockList);
			Collections.addAll(scattered, scatteredBlocks);
		}
		
		
		private static  void initCore() {
			
			for(String block : coreBlockList) {
				System.out.println(block);
				String[] part = block.split(":", 3);
				addCore(newSpecialAsteroidBlock(getBlock(part[0]), Integer.valueOf(part[1]), Integer.valueOf(part[2]), Double.valueOf(part[3])));
			}

		}
		
		private static  void initShell() {

		}
		
		private static  void initOtherBlocks() {
			
		}

		private static void initTest () {

			// ADD TEST CORE BLOCKS
			addCore(newSpecialAsteroidBlockMetaless(Blocks.BOOKSHELF, 15, .15));
			addCore(newSpecialAsteroidBlockMetaless(Blocks.DIAMOND_BLOCK, 20, .15));
			addCore(newSpecialAsteroidBlockMetaless(Blocks.DIRT, 20, .15));
			addCore(newSpecialAsteroidBlock(Blocks.BEDROCK, 0, 20, .15));
			// ADD TEST SHELL BLOCKS
			addShell(newSpecialAsteroidBlockMetaless(Blocks.STONEBRICK, 15, .15));
			addShell(newSpecialAsteroidBlock(Blocks.COBBLESTONE, 0, 20, .15));
			// ADD TEST ASTEROID BLOCKS
			asteroidBlocks(Blocks.REDSTONE_BLOCK);
			asteroidBlocks(Blocks.IRON_BLOCK);
			asteroidBlocks(Blocks.EMERALD_BLOCK);
		}
		
		private static Block getBlock (String blockid) {
			Block b = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(blockid));
			System.out.println(b.getUnlocalizedName());
			return b;
		}

		private  static SpecialAsteroidBlock newSpecialAsteroidBlockMetaless (Block block, int probability, double thickness) {
			return new SpecialAsteroidBlock(block, (byte) 0, probability, thickness);
		}

		/**
		 * Easy setter method for building a new special asteroid block without the need
		 * to cast byte to meta <br>
		 *
		 * @param block       to use
		 * @param meta        data for block
		 * @param probability chances this block is used in worldgen
		 * @param thickness   expressed as a double (ex: .15 or .30)
		 * @return new SpecialAsteroidBlock
		 */
		private  static SpecialAsteroidBlock newSpecialAsteroidBlock (Block block, int meta, int probability, double thickness) {
			return new SpecialAsteroidBlock(block, (byte) meta, probability, thickness);
		}

		/**
		 * Adds the new SpecialAsteroidBlock to the ArrayList of blocks to use in our
		 * ChunkGenerator for asteroid cores
		 *
		 * @param asteroidBlock the asteroid block
		 */
		private  static void addCore (SpecialAsteroidBlock asteroidBlock) {
			ChunkProviderGCTAsteroids.coreBlocks.add(asteroidBlock);
		}

		/**
		 * Adds the new SpecialAsteroidBlock to the ArrayList of blocks to use in our
		 * ChunkGenerator for asteroid shells
		 *
		 * @param asteroidBlock the asteroid block
		 */
		private  static void addShell (SpecialAsteroidBlock asteroidBlock) {
			ChunkProviderGCTAsteroids.shellBlocks.add(asteroidBlock);
		}

		/**
		 * Adds the new SpecialAsteroidBlock to the ArrayList of blocks to use in our
		 * ChunkGenerator for asteroid blocks. Which ironically is used the most
		 *
		 * @param block the block
		 */
		private  static void asteroidBlocks (Block block) {
			ChunkProviderGCTAsteroids.asteroidBlocks.add(block);
		}
	}

}

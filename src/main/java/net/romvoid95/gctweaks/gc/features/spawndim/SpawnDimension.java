package net.romvoid95.gctweaks.gc.features.spawndim;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;

import micdoodle8.mods.galacticraft.api.galaxies.*;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;

import net.romvoid95.gctweaks.GalacticTweaks;
import net.romvoid95.gctweaks.Ref;
import net.romvoid95.gctweaks.base.Feature;
import net.romvoid95.gctweaks.base.core.compat.CompatMods;

public class SpawnDimension extends Feature {

	private static boolean	enableSpawnDimension, firstJoinOnly, everyDeath;
	private static int		spawnDimId;

	@Override
	public String[] category() {
		return new String[] { "spawn-dimension" };
	}

	@Override
	public String comment() {
		return "Set a certain Planet or Moon as a Spawn Point\n\nYou should have SPAWN-ITEMS Configured and set prior to enabling this setting\n"
				+ "At the moment any moon or planet set to spawn in uses that bodies teleport system (spawns in the lander)";
	}

	@Override
	public void syncConfig(Configuration config, String[] category) {
		enableSpawnDimension = config.get(category[0], "spawnfeature", false,
				"Enable & Disable the Spawn Dimension Feature\n[default: false]").getBoolean();
		firstJoinOnly = config.get(category[0], "firstJoinOnly", true,
				"If \"true\" players are only sent to the spawn dimension"
						+ " on their first time joining.\nIf \"false\" players are sent every join\n [default: false]").getBoolean();
		everyDeath = config.get(category[0], "everyDeath", false, "If \"true\" players are sent to the spawn dimension"
				+ "every death respawn.\n [default: false]").getBoolean();
		spawnDimId = config.get(category[0], "spawnDimId", 0,
				"Set the Dimension ID of the planet/moon you want players join on\n"
						+ "There is a file under this one called ValidSpawnDimensions.txt\n"
						+ "that will contain every valid dimension ID you can set").getInt();

	}

	@Override
	public boolean usesEvents() {
		return true;
	}

	@Override
	public void postInit() {
		creatFile();
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerJoin(PlayerLoggedInEvent event) {
		if (event.player instanceof EntityPlayer) {
			if (enableSpawnDimension) {
				final EntityPlayer player = event.player;
				final NBTTagCompound entityData = player.getEntityData();
				final NBTTagCompound persistedData = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
				entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistedData);
				final String key = Ref.MOD_ID + ":" + "FirstSpawn";
				if (!persistedData.getBoolean(key)) {
					teleport((EntityPlayerMP) event.player, spawnDimId);
					persistedData.setBoolean(key, true);
				}
			} else if (firstJoinOnly) {
				teleport((EntityPlayerMP) event.player, spawnDimId);
			}
		}
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		if (event.player instanceof EntityPlayer) {
			if (everyDeath) {
				teleport((EntityPlayerMP) event.player, spawnDimId);
			}
		}
	}

	private void teleport(EntityPlayerMP player, int dimId) {
		MinecraftServer mcServer = player.getServer();
		final WorldServer world = mcServer.getWorld(dimId);
		int yy = world.getChunkFromBlockCoords(world.getSpawnPoint()).getHeight(world.getSpawnPoint());
		Vector3 v = new Vector3(world.getSpawnPoint().getX(), yy, world.getSpawnPoint().getZ());
		WorldUtil.teleportEntitySimple(world, dimId, player, v);
	}

	private static boolean creatFile() {
		File dims = new File(GalacticTweaks.modFolder, "GalacticTweaks/ValidSpawnDimensions.txt");
		if (dims.exists()) {
			dims.delete();
		}
		Map<String, Integer> planets = new HashMap<>();
		for (Entry<String, Planet> body : GalaxyRegistry.getRegisteredPlanets().entrySet()) {
			if (body.getValue().getReachable()) {
				planets.put(body.getValue().getLocalizedName(), body.getValue().getDimensionID());
			}
		}
		Map<String, Integer> moons = new HashMap<>();
		for (Entry<String, Moon> body : GalaxyRegistry.getRegisteredMoons().entrySet()) {
			if (body.getValue().getReachable()) {
				moons.put(body.getValue().getLocalizedName(), body.getValue().getDimensionID());
			}
		}
		try {
			dims.createNewFile();
			PrintWriter print_line = new PrintWriter(new FileWriter(dims));
			if(CompatMods.EXTRAPLANETS.isLoaded()) {
				print_line.println("### NOTICE ###");
				print_line.println("Planets & Moons that end with \"ep\" are added by ExtraPlanets.");
				print_line.println("Please keep this in mind if choosing a planet that is added by both ExtraPlanets & GalaxySpace");
			}
			print_line.println("");
			print_line.println("If adding or removing Galacticraft Addons, This file is regenerated every time minecraft is ran");
			print_line.println("");
			print_line.println("| ### PLANETS ###");
			print_line.println("|------------------");
			planets.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(
					s -> print_line.println(String.format("| %-15s %15s", s.getKey(), s.getValue())));
			print_line.println("");
			print_line.println("");
			print_line.println("| ### MOONS ###");
			print_line.println("|------------------");
			moons.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).forEach(
					s -> print_line.println(String.format("| %-15s %15s", s.getKey(), s.getValue())));
			print_line.flush();
			print_line.close();
			return true;
		} catch (IOException localIOException) {
			return false;
		}
	}
}

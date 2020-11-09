package net.romvoid95.gctweaks.gc.features;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerRespawnEvent;
import net.romvoid95.gctweaks.Ref;
import net.romvoid95.gctweaks.base.Feature;

public class SpawnDimension extends Feature {

	private static boolean	enableSpawnDimension, firstJoinOnly, everyDeath, useCoord;
	private static int		spawnDimId;
	private static int[]    sPos;

	@Override
	public String category() {
		return "dimensionSpawn";
	}

	@Override
	public String comment() {
		return "Set a certain Planet or Moon as a Spawn Point\n\nYou should have SPAWN-ITEMS Configured and set prior to enabling this setting\n"
				+ "At the moment any moon or planet set to spawn in uses that bodies teleport system (spawns in the lander)";
	}

	@Override
	public void syncConfig(String category) {
		enableSpawnDimension = set(category, "enableFeature", false);
		useCoord = set(category, "useCoord", false);
		firstJoinOnly = set(category, "firstJoinOnly",
				"If \"true\" players are only sent to the spawn dimension"
						+ " on their first time joining.\nIf \"false\" players are sent every join\n [default: false]", true);
		everyDeath = set(category, "everyRespawn", "If \"true\" players are sent to the spawn dimension"
				+ "every death respawn.\n [default: false]", false);
		spawnDimId = set(category, "dimensionId",
				"Set the Dimension ID of the planet/moon you want players join on\n"
						+ "see file under `config\\GalacticTweaks\\ValidDimensions.txt` for valid dimension ID's\n", 0);
		sPos = set(category, "spawnCoords", "Set the spawn position for players to spawn at", new int[] {128, 64, 128});

	}

	@Override
	public boolean usesEvents() {
		return true;
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
		BlockPos pos;;
		MinecraftServer mcServer = player.getServer();
		final WorldServer world = mcServer.getWorld(dimId);
		if(useCoord) {
			pos = new BlockPos(sPos[0], sPos[1], sPos[2]);
		} else {
			int y = world.getChunkFromBlockCoords(world.getSpawnPoint()).getHeight(world.getSpawnPoint());
			pos = new BlockPos(world.getSpawnPoint().getX(), y, world.getSpawnPoint().getZ());
		}
		WorldUtil.teleportEntitySimple(world, dimId, player, new Vector3(pos));
	}
}

package net.romvoid95.galactic.feature.common.admintools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.core.gc.IOWriter;
import net.romvoid95.galactic.core.permission.GCTPermissions;
import net.romvoid95.galactic.feature.FeatureConfigs;

public class CommandAdminTeleport extends CommandBase {

	@Override
	public String getName() {
		return "admintp";
	}
	
	@Override
	public int getRequiredPermissionLevel () {
		return GCTPermissions.ADMIN_TELEPORT.permissionLevel();
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		EntityPlayerMP player = (EntityPlayerMP) sender;
		return GCTPermissions.hasPerm(player, GCTPermissions.ADMIN_TELEPORT);
	}
	
	@Override
	public String getUsage(ICommandSender sender) {
		return String.format("%sUsage: %s/%s <planet>", TextFormatting.RED,
				TextFormatting.AQUA, getName(), TextFormatting.RED, TextFormatting.GREEN);
	}
	
	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args,
			BlockPos pos) {
		List<String> bodyNames = new ArrayList<>();
		for(String entry : IOWriter.bodies.keySet()) {
			bodyNames.add(entry);
		}
		if (args.length == 1) {
			return bodyNames;
		}
		return null;
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 0) {
			EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
			final CelestialBody body = IOWriter.bodies.get(args[0]);
			if(player != null) {
				GalacticTweaks.LOG.info(body.getLocalizedName());
				teleport(player, body.getDimensionID());
			}
		} else {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
		}
	}
	
	private void teleport(EntityPlayerMP player, int dimId) {
		BlockPos pos;
		MinecraftServer mcServer = player.getServer();
		final WorldServer world = mcServer.getWorld(dimId);
		String[] spawnString = FeatureConfigs.spawnPos.get().split(",");
		if (FeatureConfigs.useCoord.get()) {
			pos = new BlockPos(Integer.valueOf(spawnString[0]), Integer.valueOf(spawnString[1]),
					Integer.valueOf(spawnString[2]));
		} else {
			int y = world.getChunkFromBlockCoords(world.getSpawnPoint()).getHeight(world.getSpawnPoint());
			pos = new BlockPos(world.getSpawnPoint().getX(), y, world.getSpawnPoint().getZ());
		}
		WorldUtil.teleportEntitySimple(world, dimId, player, new Vector3(pos));
	}
}

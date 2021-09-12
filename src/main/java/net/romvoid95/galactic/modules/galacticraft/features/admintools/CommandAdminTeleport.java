package net.romvoid95.galactic.modules.galacticraft.features.admintools;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import galaxyspace.systems.SolarSystem.SolarSystemBodies;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import micdoodle8.mods.galacticraft.planets.asteroids.AsteroidsModule;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.WorldServer;
import net.minecraftforge.server.permission.PermissionAPI;
import net.romvoid95.galactic.GalacticTweaks;
import net.romvoid95.galactic.core.GCTLog;
import net.romvoid95.galactic.core.gc.IOWriter;

public class CommandAdminTeleport extends CommandBase {

	@Override
	public String getName() {
		return "admintp";
	}

	@Override	
	public int getRequiredPermissionLevel() {
		return 4;
	}

	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
		return PermissionAPI.hasPermission((EntityPlayer) sender.getCommandSenderEntity(), GalacticTweaks.NODE_ADMINTP);
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return String.format("%sUsage: %s/%s <planet>", TextFormatting.RED, TextFormatting.AQUA, getName(),
				TextFormatting.RED, TextFormatting.GREEN);
	}

	@Override
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, Lists.newArrayList(IOWriter.bodies.keySet()));
		} else {
			return args.length > 1 && args.length <= 4 ? getTabCompletionCoordinate(args, 1, pos) : Collections.emptyList();
		}
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length < 1) {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
		} else {
			final CelestialBody body = IOWriter.bodies.get(args[0]);
			EntityPlayerMP entity = getCommandSenderAsPlayer(sender);
			MinecraftServer mcServer = entity.getServer();
			final WorldServer world = mcServer.getWorld(body.getDimensionID());
			BlockPos pos;
			if(args.length > 1) {
                CommandBase.CoordinateArg x = parseCoordinate(entity.posX, args[1], true);
                CommandBase.CoordinateArg y = parseCoordinate(entity.posY, args[2], -4096, 4096, false);
                CommandBase.CoordinateArg z = parseCoordinate(entity.posZ, args[3], true);
                
                GCTLog.info("Position: {}, {}, {}", x.getAmount(), y.getAmount(), z.getAmount());
                
                pos = new BlockPos(x.getAmount(), y.getAmount(), z.getAmount());
			} else {
				int y = world.getChunkFromBlockCoords(entity.getPosition()).getHeight(entity.getPosition());
				pos = new BlockPos(entity.posX, y, entity.posZ);
			}
			if(!entity.isCreative()) {
				sender.sendMessage(new TextComponentString(TextFormatting.RED + "You must be in Creative Mode to use Admin Teleport"));
			}
			
			WorldUtil.teleportEntitySimple(world, body.getDimensionID(), entity, new Vector3(pos));
			int asteroidId = AsteroidsModule.planetAsteroids.getDimensionID();
			int kBeltId = SolarSystemBodies.planetKuiperBelt.getDimensionID();
			
			if(body.getDimensionID() == asteroidId || body.getDimensionID() == kBeltId) {
				entity.capabilities.isFlying = true;
				entity.sendPlayerAbilities();
			}
		}
	}
}

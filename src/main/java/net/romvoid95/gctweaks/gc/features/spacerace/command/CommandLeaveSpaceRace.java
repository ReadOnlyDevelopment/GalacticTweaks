package net.romvoid95.gctweaks.gc.features.spacerace.command;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;

import micdoodle8.mods.galacticraft.core.dimension.SpaceRace;
import micdoodle8.mods.galacticraft.core.dimension.SpaceRaceManager;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.romvoid95.gctweaks.internal.permission.PermissionRegistry;

public class CommandLeaveSpaceRace extends CommandBase {

	@Override
	public int getRequiredPermissionLevel () {
		return 0;
	}

	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
		EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
		return PermissionRegistry.hasPermissionHere(player);
	}

	@Override
	public String getUsage (ICommandSender sender) {
		StringBuilder aliases = new StringBuilder();
		aliases.append(String.join(" | ", getAliases()));

		return String
				.format("%sUsage: %s/%s \n%sAliases: %s%s", TextFormatting.RED, TextFormatting.AQUA, getName(), TextFormatting.RED, TextFormatting.GREEN, aliases
						.toString());
	}

	@Override
	public String getName () {
		return "leaverace";
	}

	@Override
	public List<String> getAliases () {
		return ImmutableList.of("lrace", "quitrace", "qrace", "lr", "qr");
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		EntityPlayerMP player = PlayerUtil.getPlayerBaseServerFromPlayerUsername(server, sender.getName(), true);

		if (args.length == 0) {

			SpaceRace     race  = SpaceRaceManager.getSpaceRaceFromPlayer(player.getName());

			if (race != null) {
				List<String> names = new ArrayList<String>(race.getPlayerNames());
				String       owner = race.getPlayerNames().get(0);
				if (!sender.getName().equalsIgnoreCase(owner)) {

					names.remove(player.getName());
					race.setPlayerNames(names);
					race.tick();
					SpaceRaceManager.sendSpaceRaceData(server, player, race);

					if (!race.getPlayerNames().contains(player.getName())) {
						sender.sendMessage(new TextComponentTranslation("galactictweaks.leaverace.playerleave", race
								.getTeamName()));
					}  else {
						
					}
				} else {
					sender.sendMessage(new TextComponentTranslation("galactictweaks.leaverace.leader"));
				}
			} else {
				sender.sendMessage(new TextComponentTranslation("galactictweaks.leaverace.noteam"));
			}
		}
		else {
			sender.sendMessage(new TextComponentTranslation("galactictweaks.leaverace.noarg"));
			sender.sendMessage(new TextComponentTranslation("galactictweaks.leaverace.useage", this.getUsage(sender)));
		}
	}

	@Override
	public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
		return getListOfStringsMatchingLastWord(args, getAliases());
	}
}

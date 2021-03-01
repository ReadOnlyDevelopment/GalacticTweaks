package net.romvoid95.galactic.feature.common;

import java.util.*;

import javax.annotation.*;

import com.google.common.collect.*;

import micdoodle8.mods.galacticraft.core.dimension.*;
import micdoodle8.mods.galacticraft.core.util.*;
import net.minecraft.command.*;
import net.minecraft.entity.player.*;
import net.minecraft.server.*;
import net.minecraft.util.math.*;
import net.minecraft.util.text.*;
import net.minecraftforge.fml.common.event.*;
import net.romvoid95.galactic.feature.*;

public class SpaceRaceFeature extends Feature {

	@Override
	public String category () {
		return "SpaceRaceCommand";
	}

	@Override
	public String comment () {
		return "Additional Features related to Galacticraft SpaceRace Teams";
	}

	@Override
	public void ServerStartingEvent (FMLServerStartingEvent event) {
		if (isEnabled())
			event.registerServerCommand(new SpaceRaceFeature.CommandLeaveSpaceRace());
	}
	

	@Override
	public boolean isEnabled() {
		return FeatureConfigs.SPACE_RACE_FEATURE;
	}
	
	public class CommandLeaveSpaceRace extends CommandBase {

		@Override
		public int getRequiredPermissionLevel () {
			return 0;
		}

		@Override
		public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
			return sender.canUseCommand(this.getRequiredPermissionLevel(), this.getName());
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
}

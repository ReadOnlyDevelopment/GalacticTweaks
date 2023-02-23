package readonly.galactictweaks.features;

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
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import readonly.api.feature.Feature;
import readonly.galactictweaks.modules.galacticraft.GalacticraftModuleConfig;

public class SpaceRaceFeature extends Feature {

	public SpaceRaceFeature() {
		this.category = "SpaceRaceCommand";
		this.categoryComment = "Additional Features related to Galacticraft SpaceRace Teams";
	}

	@Override
	public void ServerStartingEvent (FMLServerStartingEvent event) {
		event.registerServerCommand(new SpaceRaceFeature.CommandLeaveSpaceRace());
	}


	@Override
	public boolean isEnabled() {
		return GalacticraftModuleConfig.SPACE_RACE_FEATURE;
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
					List<String> names = new ArrayList<>(race.getPlayerNames());
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

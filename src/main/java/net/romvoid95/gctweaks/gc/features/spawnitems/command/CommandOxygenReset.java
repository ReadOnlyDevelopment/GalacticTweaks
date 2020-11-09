package net.romvoid95.gctweaks.gc.features.spawnitems.command;

import java.util.List;

import com.google.common.collect.ImmutableList;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.romvoid95.gctweaks.Ref;

public class CommandOxygenReset extends CommandBase {

	@Override
	public String getName () {
		return "oxygenreset";
	}
	
	@Override
	public List<String> getAliases () {
		return ImmutableList.of("oxyreset", "oxyr", "oreset");
	}
	
	@Override
	public String getUsage (ICommandSender sender) {
		StringBuilder aliases = new StringBuilder();
		aliases.append(String.join(" | ", getAliases()));

		return String
				.format("%sUsage: %s/%s <playername>\n%sAliases: %s%s", TextFormatting.RED, TextFormatting.AQUA, getName(), TextFormatting.RED, TextFormatting.GREEN, aliases
						.toString());
	}

	@Override
	public int getRequiredPermissionLevel () {
		return 3;
	}
	
	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
		return true;
	}

	@Override
	public List<String> getTabCompletions (MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) {
		if (args.length == 1) {
			return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
		}
		return null;
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length > 0 && args.length < 2) {
			final EntityPlayer player = server.getPlayerList().getPlayerByUsername(args[0]);

			if(player != null) {
				final NBTTagCompound entityData    = player.getEntityData();
				final NBTTagCompound persistedData = entityData.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
				entityData.setTag(EntityPlayer.PERSISTED_NBT_TAG, persistedData);
				final String key = Ref.MOD_ID + ":" + "ReceivedOxygen";
				persistedData.setBoolean(key, false);
				String line = String.format("Sucessfully reset %s's OxygenGear-On-Join setting", player.getName());
				sender.sendMessage(new TextComponentString(line));
			} 
		} else {
			sender.sendMessage(new TextComponentString(getUsage(sender)));
		}
	}
}

package net.romvoid95.gctweaks.internal.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.romvoid95.gctweaks.internal.versioning.DownloadUpdate;


public class DownloadCommand extends CommandBase {

	@Override
	public String getName () {
		return "download-latest-gctweaks";
	}

	@Override
	public String getUsage (ICommandSender sender) {
		return "/download-latest-gctweaks";
	}

	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
		return server.isSinglePlayer() || super.checkPermission(server, sender);
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 0) {
			if (DownloadUpdate.downloadedFile) {
				sender.sendMessage(new TextComponentTranslation("galactictweaks.versions.downloadedAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			}
			else if (DownloadUpdate.startedDownload) {
				sender.sendMessage(new TextComponentTranslation("galactictweaks.versions.downloadingAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			}
			else {
				new DownloadUpdate();
			}
		}
		else {

		}
	}
}

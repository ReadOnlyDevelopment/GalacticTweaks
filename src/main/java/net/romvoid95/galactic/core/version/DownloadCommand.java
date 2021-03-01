package net.romvoid95.galactic.core.version;

import net.minecraft.command.*;
import net.minecraft.server.*;
import net.minecraft.util.text.*;

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
			if (Update.downloadedFile) {
				sender.sendMessage(new TextComponentTranslation("galactictweaks.versions.downloadedAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			}
			else if (Update.startedDownload) {
				sender.sendMessage(new TextComponentTranslation("galactictweaks.versions.downloadingAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			}
			else {
				new Update();
			}
		}
	}
}

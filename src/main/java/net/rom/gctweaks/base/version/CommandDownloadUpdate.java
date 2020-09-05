package net.rom.gctweaks.base.version;

import com.therandomlabs.curseapi.CurseException;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class CommandDownloadUpdate extends CommandBase {

	@Override
	public String getName () {
		return "download-latest-gctweaks";
	}

	@Override
	public String getUsage (ICommandSender sender) {
		return "/download-latest-gctweaks <version>";
	}

	@Override
	public boolean checkPermission (MinecraftServer server, ICommandSender sender) {
		return server.isSinglePlayer() || super.checkPermission(server, sender);
	}

	@Override
	public void execute (MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if (args.length == 1)
			if (VersionChecker.downloadedFile)
				sender.sendMessage(new TextComponentTranslation("gctweaks.versions.downloadedAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			else if (VersionChecker.startedDownload)
				sender.sendMessage(new TextComponentTranslation("gctweaks.versions.downloadingAlready")
						.setStyle(new Style().setColor(TextFormatting.RED)));
			else
				try {
					new DownloadUpdate(args[0]);
				}
				catch (CurseException e) {
					e.printStackTrace();
				}

	}

}

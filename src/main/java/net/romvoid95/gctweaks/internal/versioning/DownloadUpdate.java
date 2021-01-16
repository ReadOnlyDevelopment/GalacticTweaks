package net.romvoid95.gctweaks.internal.versioning;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;

public class DownloadUpdate {
	
	public static boolean startedDownload   = false;
	public static boolean downloadedFile    = false;

	public DownloadUpdate() {
		run();
	}

	public void run () {

		URL        downloadUrl = null;
		try {
			downloadUrl = new URL(Request.getLatestVersionDownload());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Path dir = new File(Minecraft.getMinecraft().mcDataDir, "mods").toPath();

		String fileName = FilenameUtils.getName(downloadUrl.getPath());
		File   newFile  = new File(dir.toString() + "/" + fileName);

		if (Minecraft.getMinecraft().player != null) {

			Minecraft.getMinecraft().player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.startingDownload", fileName));
			Minecraft.getMinecraft().player.sendMessage(StringUtil.formatFromJson("galactictweaks.versions.startingDownload2"));
		}

		startedDownload = true;

		try {
			URLConnection conn = downloadUrl.openConnection();
			conn.connect();
			FileUtils.copyInputStreamToFile(conn.getInputStream(), newFile);
		}
		catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}

		if (newFile.exists()) {
			if (Minecraft.getMinecraft().player != null)
				Minecraft.getMinecraft().player
						.sendMessage(StringUtil.format("galactictweaks.versions.doneDownloading", fileName, new Style().setColor(TextFormatting.GREEN)));

			try {
				Desktop.getDesktop().open(dir.toFile());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		downloadedFile = true;
	}
}

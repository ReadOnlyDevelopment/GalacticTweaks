package net.romvoid95.gctweaks.internal.versioning;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.JsonObject;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class DownloadUpdate {

	public DownloadUpdate() {
		run();
	}

	public void run () {

		JsonObject obj         = null;
		URL        downloadUrl = null;
		try {
			obj         = Request.get();
			downloadUrl = new URL(obj.get("downloadUrl").getAsString());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		Path dir = new File(Minecraft.getMinecraft().mcDataDir, "mods").toPath();

		String fileName = FilenameUtils.getName(downloadUrl.getPath());
		File   newFile  = new File(dir.toString() + "/" + fileName);

		ITextComponent component  = ITextComponent.Serializer
				.jsonToComponent(I18n.format("galactictweaks.versions.startingDownload", fileName));
		ITextComponent component2 = ITextComponent.Serializer
				.jsonToComponent(I18n.format("galactictweaks.versions.startingDownload2"));
		if (Minecraft.getMinecraft().player != null) {

			Minecraft.getMinecraft().player.sendMessage(component);
			Minecraft.getMinecraft().player.sendMessage(component2);
		}

		VersionChecker.startedDownload = true;

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
						.sendMessage(new TextComponentTranslation("galactictweaks.versions.doneDownloading", fileName)
								.setStyle(new Style().setColor(TextFormatting.GREEN)));

			try {
				Desktop.getDesktop().open(dir.toFile());
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		VersionChecker.downloadedFile = true;
	}
}

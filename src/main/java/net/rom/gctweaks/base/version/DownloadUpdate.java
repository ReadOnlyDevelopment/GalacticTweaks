package net.rom.gctweaks.base.version;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import com.therandomlabs.curseapi.CurseAPI;
import com.therandomlabs.curseapi.CurseException;
import com.therandomlabs.curseapi.project.CurseProject;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;

public class DownloadUpdate {

	private final String       fileId;
	private final int          PROJECT = 359766;
	private final CurseProject proj;

	public DownloadUpdate(String fileId) throws CurseException {

		final Optional<CurseProject> project = CurseAPI.project(PROJECT);
		proj        = project.get();
		this.fileId = fileId;

		run();
	}

	public void run () {
		String fileName = null;
		try {
			fileName = proj.files().fileWithID(Integer.parseInt(fileId)).displayName();
		}
		catch (NumberFormatException | CurseException e) {
			e.printStackTrace();
		}

		ITextComponent component  = ITextComponent.Serializer
				.jsonToComponent(I18n.format("gctweaks.versions.startingDownload", fileName));
		ITextComponent component2 = ITextComponent.Serializer
				.jsonToComponent(I18n.format("gctweaks.versions.startingDownload2"));
		if (Minecraft.getMinecraft().player != null) {

			Minecraft.getMinecraft().player.sendMessage(component);
			Minecraft.getMinecraft().player.sendMessage(component2);
		}

		VersionChecker.startedDownload = true;

		Path dir = new File(Minecraft.getMinecraft().mcDataDir, "mods").toPath();
		try {
			proj.files().fileWithID(Integer.parseInt(fileId)).downloadToDirectory(dir);
		}
		catch (NumberFormatException | CurseException e) {
			e.printStackTrace();
		}

		if (Minecraft.getMinecraft().player != null)
			Minecraft.getMinecraft().player
					.sendMessage(new TextComponentTranslation("gctweaks.versions.doneDownloading", fileName)
							.setStyle(new Style().setColor(TextFormatting.GREEN)));

		try {
			Desktop.getDesktop().open(dir.toFile());
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		VersionChecker.downloadedFile = true;
	}
}

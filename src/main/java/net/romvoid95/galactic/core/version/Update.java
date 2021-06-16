package net.romvoid95.galactic.core.version;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Path;

import javax.annotation.Nullable;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.romvoid95.galactic.core.GCTLog;
import net.romvoid95.galactic.core.utils.StringUtil;

public class Update {

	public static boolean startedDownload = false;
	public static boolean downloadedFile = false;

	private static final EntityPlayer player = Minecraft.getMinecraft().player;

	public static void download() {
		URL downloadUrl = null;
		try {
			downloadUrl = new URL(getLatestVersionDownload());
		} catch (Exception e) {
			e.printStackTrace();
		}

		Path dir = new File(Minecraft.getMinecraft().mcDataDir, "mods").toPath();

		String fileName = FilenameUtils.getName(downloadUrl.getPath());
		File newFile = new File(dir.toString() + "/" + fileName);

		if (player != null) {

			jMsg("galactictweaks.versions.startingDownload", fileName);
			jMsg("galactictweaks.versions.startingDownload2", null);

			startedDownload = true;

			try {
				URLConnection conn = downloadUrl.openConnection();
				conn.connect();
				FileUtils.copyInputStreamToFile(conn.getInputStream(), newFile);
			} catch (NumberFormatException | IOException e) {
				e.printStackTrace();
			}

			if (newFile.exists()) {
				msg("galactictweaks.versions.doneDownloading", fileName, new Style().setColor(TextFormatting.GREEN));
				try {
					Desktop.getDesktop().open(dir.toFile());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			downloadedFile = true;
		}
	}

	private static void msg(String key, String text, Style style) {
		player.sendMessage(StringUtil.format(key, text, style));
	}

	private static void jMsg(String key, @Nullable String text) {
		player.sendMessage(StringUtil.formatFromJson(key, text));
	}

	private static String getLatestVersionDownload() {
		try {
			URL getRequestURL = new URL("https://addons-ecs.forgesvc.net/api/v2/addon/359766");
			HttpURLConnection con = (HttpURLConnection) getRequestURL.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String response = "";
			while ((inputLine = in.readLine()) != null) {
				// GalacticTweaks.logger.info(inputLine);
				response += inputLine;
				in.close();
				JsonParser parser = new JsonParser();
				JsonObject array = parser.parse(response).getAsJsonObject();
				JsonArray json = array.getAsJsonArray("latestFiles");
				JsonObject ob = json.get(0).getAsJsonObject();
				String version = ob.get("downloadUrl").getAsString();
				return version;
			}
		} catch (Exception e) {
			GCTLog.error("There was an issue communicating with the CurseAPI");
		}
		return null;
	}
}

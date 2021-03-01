package net.romvoid95.galactic.core.version;

import java.awt.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

import javax.annotation.*;

import org.apache.commons.io.*;

import com.google.gson.*;

import net.minecraft.client.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.text.*;
import net.romvoid95.galactic.*;
import net.romvoid95.galactic.core.utils.*;

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
			GalacticTweaks.LOG.error("There was an issue communicating with the CurseAPI");
		}
		return null;
	}
}

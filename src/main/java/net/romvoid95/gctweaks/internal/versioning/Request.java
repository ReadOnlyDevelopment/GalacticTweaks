package net.romvoid95.gctweaks.internal.versioning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.romvoid95.api.versioning.Version;
import net.romvoid95.gctweaks.GalacticTweaks;

public class Request {

	public static String domain1 = "https://addons-ecs.forgesvc.net/api/v2/addon/359766";

	public static Version getLatestVersion() {
		try {
			URL getRequestURL = new URL(domain1);
			HttpURLConnection con = (HttpURLConnection) getRequestURL.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String response = "";
			while ((inputLine = in.readLine()) != null) {
				
				response += inputLine;
				in.close();
				JsonParser parser = new JsonParser();
				JsonObject array = parser.parse(response).getAsJsonObject();
				JsonArray json = array.getAsJsonArray("latestFiles");
				JsonObject ob = json.get(0).getAsJsonObject();
				String version = ob.get("displayName").getAsString().split("-")[1].replace(".jar", "");
				return new Version(version);
				//return version;
			}
		} catch (Exception e) {
			GalacticTweaks.logger.error("There was an issue communicating with the CurseAPI");
		}
		return null;
	}
	
	public static String getLatestVersionDownload() {
		try {
			URL getRequestURL = new URL(domain1);
			HttpURLConnection con = (HttpURLConnection) getRequestURL.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			String response = "";
			while ((inputLine = in.readLine()) != null) {
				//GalacticTweaks.logger.info(inputLine);
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
			GalacticTweaks.logger.error("There was an issue communicating with the CurseAPI");
		}
		return null;
	}
}

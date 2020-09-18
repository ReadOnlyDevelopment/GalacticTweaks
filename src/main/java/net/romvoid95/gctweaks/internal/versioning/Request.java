package net.romvoid95.gctweaks.internal.versioning;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.romvoid95.gctweaks.GalacticTweaks;

public class Request {

	public static String domain = "https://addons-ecs.forgesvc.net/api/v2/addon/359766/files";

	public static JsonObject get () throws Exception {
		URL               getRequestURL = new URL(domain);
		HttpURLConnection con           = (HttpURLConnection) getRequestURL.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in       = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String         inputLine;
		String         response = "";
		while ((inputLine = in.readLine()) != null) {
			GalacticTweaks.logger.info(inputLine);
			response += inputLine;
			in.close();
			JsonParser parser = new JsonParser();
			JsonArray  array  = parser.parse(response).getAsJsonArray();
			JsonObject json   = array.get(0).getAsJsonObject();
			return json;
		}
		return null;
	}
}

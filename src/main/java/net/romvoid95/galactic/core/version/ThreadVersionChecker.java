package net.romvoid95.galactic.core.version;

import static net.romvoid95.galactic.Info.NAME;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.romvoid95.api.versioning.Version;
import net.romvoid95.galactic.GalacticTweaks;

public class ThreadVersionChecker extends Thread {

	public ThreadVersionChecker () {
		setName(NAME + " Version Check");
		setDaemon(true);
		start();
	}

	@Override
	public void run() {
		try {
			GalacticTweaks.LOG.info("Starting GalacticTweaks Version Check Thread");

			VersionChecker.updateVersion = getLatestVersion();

			if(VersionChecker.updateVersion.isGreaterThan(VersionChecker.currentRunningVersion)) {
				VersionChecker.notifyForUpdate = true;
			}
			GalacticTweaks.LOG.info("GalacticTweaks Version Check Finished");
		} catch (Exception e) {
			GalacticTweaks.LOG.error("GalacticTweaks Version Check Failed", e);
			VersionChecker.checkThreadFailed  = true;
		}

		if(!VersionChecker.checkThreadFailed) {
			if(VersionChecker.notifyForUpdate) {
				GalacticTweaks.LOG.info("GalacticTweaks Update Found!");
			} else {
				GalacticTweaks.LOG.info("GalacticTweaks is up to date");
			}
		}
		VersionChecker.checkThreadDone = true;
	}

	private Version getLatestVersion() {
		try {
			URL getRequestURL = new URL("https://addons-ecs.forgesvc.net/api/v2/addon/359766");
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
			}
		} catch (Exception e) {
			GalacticTweaks.LOG.error("There was an issue communicating with the CurseAPI");
		}
		return null;
	}
}

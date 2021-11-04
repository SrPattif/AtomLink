package atom.atomlink.HTTPRequests.Updates;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import atom.atomlink.AtomLink;
import atom.atomlink.Utils.Debug;

public class LastPlayedUpdate {

	public static void update(Player p, long lastPlayed) {
		try {
			URL url = new URL("http://localhost:21262/updateLastPlayed");
			HttpURLConnection http = (HttpURLConnection) url.openConnection();
			http.setConnectTimeout(5000);
			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			http.setRequestProperty("token", AtomLink.token);
			http.setRequestProperty("player", p.getName());
			http.setRequestProperty("value", String.valueOf(lastPlayed));
			http.setDoOutput(true);
			http.setDoInput(true);
			http.setRequestMethod("GET");

			// read the response
			InputStream in = new BufferedInputStream(http.getInputStream());
			String result = IOUtils.toString(in, "UTF-8");
			
			JSONParser parser = new JSONParser();
			JSONObject json = null;
			try {
				json = (JSONObject) parser.parse(result);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			if(!(json.get("status").toString().equals("1"))) {
				Debug.logError("Fail while updating last played from " + p.getName() + ". Code: " + json.get("status"));
			} else {
				Debug.logInfo("Updated " + p.getName() + " last played.");
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

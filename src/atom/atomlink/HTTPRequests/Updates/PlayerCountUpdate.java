package atom.atomlink.HTTPRequests.Updates;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import atom.atomlink.AtomLink;
import atom.atomlink.Utils.Debug;

public class PlayerCountUpdate {
	
	private static int taskId;
	private static long delay = 30 * 20L;
	
	public static void startTask() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(AtomLink.getMain(), new Runnable() {
            public void run() {
            	
            	try {
        			URL url = new URL("http://localhost:21262/updateServerPlayers");
        			HttpURLConnection http = (HttpURLConnection) url.openConnection();
        			http.setConnectTimeout(5000);
        			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        			http.setRequestProperty("token", AtomLink.token);
        			http.setRequestProperty("value", String.valueOf(Bukkit.getOnlinePlayers().size()));
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
        				Debug.logError("Fail while updating player count. Code: " + json.get("status"));
        			} else {
        				Debug.logInfo("Updated player count.");
        			}

        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            	
            }
        }, 0, delay);
	}
	
	public static void stopTask() {
		Bukkit.getScheduler().cancelTask(taskId);
	}
	
	public static void restartTask() {
		stopTask();
		startTask();
	}
	
	public static void changeTaskDelay(long newDelay) {
		delay = newDelay;
	}
	
}

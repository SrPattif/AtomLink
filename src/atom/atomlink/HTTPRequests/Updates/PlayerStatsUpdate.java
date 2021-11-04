package atom.atomlink.HTTPRequests.Updates;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import atom.atomlink.AtomLink;
import atom.atomlink.Utils.Debug;

public class PlayerStatsUpdate {
	
	private static int taskId;
	private static long delay = 120 * 20L;  //  2 minutes
	
	
	public static HashMap<Player, Double> damage_dealt = new HashMap<Player, Double>();
	public static HashMap<Player, Double> damage_taken = new HashMap<Player, Double>();
	public static HashMap<Player, Integer> mob_kills = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> deaths = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> chests = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> fish_caught = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> times_sleept = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> crafts = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> blocks_placed = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> blocks_break = new HashMap<Player, Integer>();
	
	
	public static void startTask() {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(AtomLink.getMain(), new Runnable() {
            public void run() {
            	System.out.println("Task");
            	try {
            		for(Player p : Bukkit.getOnlinePlayers()) {
            			URL url = new URL("http://localhost:21262/updateStats");
            			HttpURLConnection http = (HttpURLConnection) url.openConnection();
            			http.setConnectTimeout(5000);
            			http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            			http.setRequestProperty("token", AtomLink.token);
            			http.setRequestProperty("player", p.getName());
            			
            			http.setRequestProperty("damage_dealt", String.valueOf(damage_dealt.get(p)));
            			http.setRequestProperty("damage_taken", String.valueOf(damage_taken.get(p)));
            			http.setRequestProperty("mob_kills", String.valueOf(mob_kills.get(p)));
            			http.setRequestProperty("deaths", String.valueOf(deaths.get(p)));
            			http.setRequestProperty("chests", String.valueOf(chests.get(p)));
            			http.setRequestProperty("fish_caught", String.valueOf(fish_caught.get(p)));
            			http.setRequestProperty("times_sleept", String.valueOf(times_sleept.get(p)));
            			http.setRequestProperty("crafts", String.valueOf(crafts.get(p)));
            			http.setRequestProperty("blocks_placed", String.valueOf(blocks_placed.get(p)));
            			http.setRequestProperty("blocks_break", String.valueOf(blocks_break.get(p)));
            			
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
            				Debug.logError("Fail while updating " + p.getName() + " stats. Code: " + json.get("status"));
            			} else {
            				Debug.logInfo("Updated " + p.getName() + " stats.");
            			}
            		}

        		} catch (IOException e) {
        			e.printStackTrace();
        		}
            	
            	clearAllMaps();
            	
            }
        }, 0, delay);
	}
	
	public static void clearAllMaps() {
		damage_dealt.clear();
		damage_taken.clear();
		mob_kills.clear();
		deaths.clear();
		chests.clear();
		fish_caught.clear();
		times_sleept.clear();
		crafts.clear();
		blocks_placed.clear();
		blocks_break.clear();
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

package atom.atomlink.Listeners.Stats;

import java.time.Instant;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
	
	@EventHandler
	public void onJoin (PlayerJoinEvent e) {
		Player p = e.getPlayer();
		long unixTimestamp = Instant.now().getEpochSecond();
		
	}

}

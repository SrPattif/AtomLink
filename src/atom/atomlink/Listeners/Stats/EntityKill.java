package atom.atomlink.Listeners.Stats;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import atom.atomlink.HTTPRequests.Updates.PlayerStatsUpdate;

public class EntityKill implements Listener {
	
	@EventHandler
	public void entityKillEntity(EntityDeathEvent e) {
	    Entity killer = e.getEntity().getKiller();
	  
	    if (killer instanceof Player) {
	    	Player p = (Player) killer;
	    	if(PlayerStatsUpdate.mob_kills.containsKey(p)) {
				PlayerStatsUpdate.mob_kills.put(p, (PlayerStatsUpdate.blocks_break.get(p) + 1));
			} else {
				PlayerStatsUpdate.mob_kills.put(p, 1);
			}
	    }
	}

}

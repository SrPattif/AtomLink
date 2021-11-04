package atom.atomlink.Listeners.Stats;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import atom.atomlink.HTTPRequests.Updates.PlayerStatsUpdate;

public class EntityDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		Entity damager = e.getDamager();
		Entity entity = e.getEntity();
		
		if(damager instanceof Player) {
			Player p = (Player) damager;
			
			if(PlayerStatsUpdate.damage_dealt.containsKey(p)) {
				PlayerStatsUpdate.damage_dealt.put(p, (PlayerStatsUpdate.damage_dealt.get(p) + e.getDamage()));
			} else {
				PlayerStatsUpdate.damage_dealt.put(p, e.getDamage());
			}
			
			
			if(entity instanceof Player) {
				Player p2 = (Player) damager;
				
				if(PlayerStatsUpdate.damage_taken.containsKey(p2)) {
					PlayerStatsUpdate.damage_taken.put(p2, (PlayerStatsUpdate.damage_taken.get(p2) + e.getDamage()));
				} else {
					PlayerStatsUpdate.damage_taken.put(p2, e.getDamage());
				}
			}
		}
		
	}
	
}

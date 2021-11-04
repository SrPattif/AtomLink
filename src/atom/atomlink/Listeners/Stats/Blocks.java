package atom.atomlink.Listeners.Stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import atom.atomlink.HTTPRequests.Updates.PlayerStatsUpdate;

public class Blocks implements Listener {
	
	@EventHandler
	public void onPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if(PlayerStatsUpdate.blocks_placed.containsKey(p)) {
			PlayerStatsUpdate.blocks_placed.put(p, (PlayerStatsUpdate.blocks_placed.get(p) + 1));
		} else {
			PlayerStatsUpdate.blocks_placed.put(p, 1);
		}
	}
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(PlayerStatsUpdate.blocks_break.containsKey(p)) {
			PlayerStatsUpdate.blocks_break.put(p, (PlayerStatsUpdate.blocks_break.get(p) + 1));
		} else {
			PlayerStatsUpdate.blocks_break.put(p, 1);
		}
	}

}

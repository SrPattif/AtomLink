package atom.atomlink.Utils;

import org.bukkit.Bukkit;

import atom.atomlink.AtomLink;

public class Debug {
	
	public static void logInfo(String info) {
		Bukkit.getConsoleSender().sendMessage("§3[ATOMLINK v" + AtomLink.getVersion() + " - INFO] §7" + info);
	}
	
	public static void logError(String error) {
		Bukkit.getConsoleSender().sendMessage("§c[ATOMLINK v" + AtomLink.getVersion() + " - ERROR] §7" + error);
	}
	
	public static void logWarning(String warning) {
		Bukkit.getConsoleSender().sendMessage("§6[ATOMLINK v" + AtomLink.getVersion() + " - WARNING] §7" + warning);
	}
	
}

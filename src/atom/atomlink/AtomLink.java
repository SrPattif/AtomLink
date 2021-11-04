package atom.atomlink;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import atom.atomlink.HTTPRequests.CheckToken;
import atom.atomlink.HTTPRequests.ServerManager;
import atom.atomlink.HTTPRequests.Triggers.CommandTrigger;
import atom.atomlink.HTTPRequests.Updates.PlayerCountUpdate;
import atom.atomlink.Listeners.Stats.Join;
import atom.atomlink.Utils.Debug;
import atom.atomlink.Utils.FileManager;

public class AtomLink extends JavaPlugin {
	
	public static AtomLink al;
	
	public static AtomLink getMain() {
		return al;
	}
	
	public static String token;
	
	@Override
	public void onEnable() {
		al = this;
		FileManager.carregarArquivos().createFiles(al);
		
		this.getServer().getPluginManager().registerEvents(new Join(), this);
		
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(" ");
		console.sendMessage("§aATOMLINK:");
		console.sendMessage(" §aPlugin started successfully.");
		console.sendMessage(" ");
		
		Debug.logInfo("Checking for Token...");
		if(!FileManager.carregarArquivos().getConfig().contains("token")) {
			Debug.logError("Token not found in config.yml. See how to get one at https://dev.bukkit.org/projects/atom-link");
			
		} else {
			
			token = FileManager.carregarArquivos().getConfig().getString("token");
			if(CheckToken.tokenExists(token)) {
				Debug.logInfo("Token found. Checking informations...");
				Debug.logInfo("Server name: " + ServerManager.getServerName(token));
				
				PlayerCountUpdate.startTask();
				CommandTrigger.startTask();
			} else {
				Debug.logError("Invalid token on config.yml");
			}
		}
	}
	
	public static String getVersion() {
		return al.getDescription().getVersion();
	}

}

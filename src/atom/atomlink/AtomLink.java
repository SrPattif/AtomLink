package atom.atomlink;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import atom.atomlink.HTTPRequests.CheckToken;
import atom.atomlink.HTTPRequests.ServerManager;
import atom.atomlink.HTTPRequests.Triggers.CommandTrigger;
import atom.atomlink.HTTPRequests.Updates.PlayerUpdate;
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
		FileManager.carregarArquivos().criarArquivos(al);
		
		ConsoleCommandSender console = Bukkit.getConsoleSender();
		console.sendMessage(" ");
		console.sendMessage("§aATOMLINK:");
		console.sendMessage(" §aPlugin started successfully.");
		console.sendMessage(" ");
		
		Debug.logInfo("Checking for Token...");
		if(!FileManager.carregarArquivos().receberConfiguracoes().contains("token")) {
			Debug.logError("Token not found in config.yml. See how to get one at https://dev.bukkit.org/projects/atom-link");
			
		} else {
			
			token = FileManager.carregarArquivos().receberConfiguracoes().getString("token");
			if(CheckToken.tokenExists(token)) {
				Debug.logInfo("Token found. Checking informations...");
				Debug.logInfo("Server name: " + ServerManager.getServerName(token));
				
				PlayerUpdate.startTask();
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

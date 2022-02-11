/**
 * Elaborated by SrPattif
 * All rights reserved
 * 
 * Discord: SrPattif#2657
 */

package atom.atomlink.Utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class FileManager {
	
	public static File configFile;
	public static FileConfiguration configs;
	
	private static FileManager carregar = new FileManager();

	public static FileManager loadFiles() {
		return carregar;
	}
	
	public void createFiles(Plugin plugin) {
		configFile = new File(plugin.getDataFolder(), "config.yml");
		
		if (!configFile.exists()) {
			plugin.saveResource("config.yml", false);
		}
		
		configs = YamlConfiguration.loadConfiguration(configFile);
	}
	
	public FileConfiguration getConfig() {
		return configs;
	}
	
	public void saveConfig() {
		try {
			configs.save(configFile);
		} catch (IOException e) {
			Debug.logError("Error while saving the config.yml file");
		}
	}
	
	public void reloadConfig() {
		configs = YamlConfiguration.loadConfiguration(configFile);
	}
}
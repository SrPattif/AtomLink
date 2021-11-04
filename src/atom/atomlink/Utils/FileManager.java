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
	
	public static File arquivoConfiguracoes;
	public static FileConfiguration configuracoes;
	
	private static FileManager carregar = new FileManager();

	public static FileManager carregarArquivos() {
		return carregar;
	}
	
	public void criarArquivos(Plugin plugin) {
		arquivoConfiguracoes = new File(plugin.getDataFolder(), "config.yml");
		
		if (!arquivoConfiguracoes.exists()) {
			plugin.saveResource("config.yml", false);
		}
		
		configuracoes = YamlConfiguration.loadConfiguration(arquivoConfiguracoes);
	}
	
	public FileConfiguration receberConfiguracoes() {
		return configuracoes;
	}
	
	public void salvarConfiguracoes() {
		try {
			configuracoes.save(arquivoConfiguracoes);
		} catch (IOException e) {
			Debug.logError("Error while saving the config.yml file");
		}
	}
	
	public void recarregarConfiguracoes() {
		configuracoes = YamlConfiguration.loadConfiguration(arquivoConfiguracoes);
	}
}
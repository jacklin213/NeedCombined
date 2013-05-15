package me.jacklin213.needcombined;

import java.io.File;
import java.util.logging.Logger;

import me.jacklin213.needcombined.commands.NeedCombinedCommand;
import me.jacklin213.needcombined.utils.MessageHandler;
import me.jacklin213.needcombined.utils.UpdateChecker;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class NeedCombined extends JavaPlugin {

	public NeedCombined plugin;
	
	public PluginDescriptionFile pdfFile;
	public Logger log = Logger.getLogger("Minecraft");
	public NeedCombinedCommand ncCommand = new NeedCombinedCommand(this);
	public MessageHandler msgHandler = new MessageHandler(this);
	public UpdateChecker updateChecker;
	
	public void onEnable() {
		
		createConfig();
		pdfFile = getDescription();
		msgHandler.getPDFStrings(pdfFile, this);
		//Update Checking
		Boolean updateCheck = Boolean.valueOf(getConfig().getBoolean("UpdateCheck"));
		 
		this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/server-mods/needtown/files.rss");

		if ((updateCheck) && (this.updateChecker.updateNeeded())) {
			this.log.info(String.format("[%s] A new update is avalible, Version: %s", getDescription().getName(), this.updateChecker.getVersion()));
			this.log.info(String.format("[%s] Get it now from: %s", getDescription().getName(), this.updateChecker.getLink()));
		}
		
		//Get command executors
		getCommand("needcombined").setExecutor(ncCommand);
		
		log.info(String.format("[%s] Enabled Version %s by jacklin213",
				getDescription().getName(), getDescription().getVersion()));
	}
	
	public void onDisable() {
		log.info(String.format("[%s] Disabled Version %s", getDescription()
				.getName(), getDescription().getVersion()));
	}
	
	public void createConfig() {
		// Creates config.yml
		File file = new File(getDataFolder() + File.separator + "config.yml");
		// If config.yml doesnt exit
		if (!file.exists()) {
			// Tells console its creating a config.yml
			this.getLogger().info(String.format("[%s] Cannot find config.yml, Generating now....", getDescription().getName()));
			this.getLogger().info(String.format("[%s] Config generated !", getDescription().getName()));
			this.getConfig().options().copyDefaults(true);
			this.saveDefaultConfig();
		}

	}
	
}

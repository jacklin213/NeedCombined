package me.jacklin213.needcombined;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.logging.Logger;

import me.jacklin213.needcombined.commands.MyNeed;
import me.jacklin213.needcombined.commands.NeedCity;
import me.jacklin213.needcombined.commands.NeedClan;
import me.jacklin213.needcombined.commands.NeedCombinedCommand;
import me.jacklin213.needcombined.commands.NeedFaction;
import me.jacklin213.needcombined.commands.NeedGroup;
import me.jacklin213.needcombined.commands.NeedNation;
import me.jacklin213.needcombined.commands.NeedTown;
import me.jacklin213.needcombined.listeners.CommandListener;
import me.jacklin213.needcombined.utils.MessageHandler;
import me.jacklin213.needcombined.utils.UpdateChecker;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NeedCombined extends JavaPlugin {

	public static NeedCombined plugin;
	
	public PluginDescriptionFile pdfFile;
	public Logger log = Logger.getLogger("Minecraft");
	public MyNeed mnCommand = new MyNeed(this);
	public NeedCity ncityCommand = new NeedCity(this);
	public NeedClan nclanCommand = new NeedClan(this);
	public NeedFaction nfCommand = new NeedFaction(this);
	public NeedGroup ngCommand = new NeedGroup(this);
	public NeedNation nnCommand = new NeedNation(this);
	public NeedTown ntCommand = new NeedTown(this);
	public NeedCombinedCommand ncCommand = new NeedCombinedCommand(this);
	public MessageHandler msgHandler = new MessageHandler(this);
	public CommandListener cmdListener = new CommandListener(this);
	public UpdateChecker updateChecker;
	
	private File colorFile;
	
	public void onEnable() {
		
		createConfig();
		//Update Checking
		Boolean updateCheck = Boolean.valueOf(getConfig().getBoolean("UpdateCheck"));
		 
		this.updateChecker = new UpdateChecker(this, "http://dev.bukkit.org/server-mods/needcombined/files.rss");

		if ((updateCheck) && (this.updateChecker.updateNeeded())) {
			log.info(String.format("[%s] A new update is avalible, Version: %s", getDescription().getName(), this.updateChecker.getVersion()));
			log.info(String.format("[%s] Get it now from: %s", getDescription().getName(), this.updateChecker.getLink()));
		}
		
		//Get command executors
		getCommand("needcombined").setExecutor(ncCommand);
		getCommand("needcity").setExecutor(ncityCommand);
		getCommand("needclan").setExecutor(nclanCommand);
		getCommand("needfaction").setExecutor(nfCommand);
		getCommand("needgroup").setExecutor(ngCommand);
		getCommand("neednation").setExecutor(nnCommand);
		getCommand("needtown").setExecutor(ntCommand);
		getCommand("myneed").setExecutor(mnCommand);
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(cmdListener, this);
		// Finish loading
		log.info(String.format("[%s] Enabled Version %s by jacklin213",
				getDescription().getName(), getDescription().getVersion()));
	}
	
	public void onDisable() {
		log.info(String.format("[%s] Disabled Version %s", getDescription()
				.getName(), getDescription().getVersion()));
	}
	
	public void createConfig() {
		// Creates config.yml
		File configfile = new File(getDataFolder() + File.separator + "config.yml");
		this.colorFile = new File(getDataFolder() + File.separator + "colors.yml");
		// If config.yml doesnt exit
		if (!configfile.exists() || !colorFile.exists()){
			if (!configfile.exists()) {
				// Tells console its creating a config.yml
				this.getLogger().info(String.format("[%s] Cannot find config.yml, Generating now....", getDescription().getName()));
				this.getLogger().info(String.format("[%s] Config generated !", getDescription().getName()));
				this.getConfig().options().copyDefaults(true);
				this.saveDefaultConfig();
			}
			if (!colorFile.exists()) {
				try {
					this.log.info(String.format("[%s] Generating colors.yml!", getDescription().getName()));
					PrintStream out = new PrintStream(new FileOutputStream(
							this.colorFile));
					out.println("# ======= Color.yml ======= #");
					out.println("# Do not edit any thing in here or else you won't know the colors");
					out.println("# This is a Color.yml for NeedCombined");
					out.println("List of colors:");
					out.println("<red> - Color Red");
					out.println("<dark_red> - Color DarkRed");
					out.println("<green> - Color Green");
					out.println("<dark_green> - Color Dark-Green");
					out.println("<aqua> - Color Aqua");
					out.println("<dark_aqua> - Color Dark-Aqua");
					out.println("<blue> - Color Gold");
					out.println("<dark_blue> - Color Dark-Blue");
					out.println("<yellow> - Color Yellow");
					out.println("<gold> - Color Gold");
					out.println("<white> - Color White");
					out.println("<black> - Color Black");
					out.println("<light_purple> - Color Light-Purple");
					out.println("<dark_purple> - Color Dark-Purple");
					out.println("<gray> - Color Gray");
					out.println("<dark_gray> - Color Dark-Grey");
					out.println("# These are the only ones tested so far, feel free too try them yourself");
					out.println();
					out.println("# Copyright ,jacklin213,LinCraft,LinProdutions 2013");
					out.close();
				} catch (IOException e) {
					this.log.severe(String.format("[%s] Error in creating file !", getDescription().getName()));
				}
				
			}
			
			this.getLogger().info("Reqired files Generated");
		}

	}
}

package me.jacklin213.needcombined.utils;

import me.jacklin213.needcombined.NeedCombined;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class MessageHandler {
	
	public static NeedCombined plugin;
	
	private String pluginName;
	private String author;
	private String chatPluginName;
	private String description;
	private String version;
	public String invalidCommand = ChatColor.RED + "Not a valid command , Do /needcombined help";
	public String invalidNum = ChatColor.RED + "Invalid number!";
	public String invalidPlayer = ChatColor.RED + "Invalid or Offline Player!";
	public String playerOnly = ChatColor.RED + "This is a player only Command!";
	public String permMessage = ChatColor.RED + "You do not have the permissions to use this command!";
	
	
	public MessageHandler(NeedCombined instance){
		plugin = instance;		
	}
	
	public void basicInfo(CommandSender sender){
		getChatPluginName();
		sender.sendMessage(chatPluginName + "made by" + ChatColor.GOLD + " jacklin213"); 
	}
	
	public void info(CommandSender sender){
		getAllStrings();
		sender.sendMessage(ChatColor.GOLD + " ============ " + chatPluginName + ChatColor.GOLD + " ============ ");
		sender.sendMessage(ChatColor.GOLD + "Plugin name: " + ChatColor.AQUA + pluginName);
		sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.AQUA + version);
		sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.AQUA  + "by " + author);
		sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.AQUA + description);
	}
	
	public void help(CommandSender sender, int page){
		getAllStrings();
		try {
            if (page == 1) {
                sender.sendMessage(ChatColor.GOLD + "#============= " + ChatColor.RED + "[" + ChatColor.GREEN + pluginName + ChatColor.WHITE + ": " + ChatColor.YELLOW + "Help" + ChatColor.RED + "]" + ChatColor.GOLD + " =============# ");
                sender.sendMessage(ChatColor.GOLD + "Page" + ChatColor.WHITE + "(" + ChatColor.AQUA + "1" + ChatColor.WHITE + "/" + ChatColor.AQUA + "1" + ChatColor.WHITE + ")" );
                sender.sendMessage(ChatColor.GOLD + "/needcombined" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Shows plugin name and author");
                sender.sendMessage(ChatColor.GOLD + "/needcombined help" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Displays this page");
                sender.sendMessage(ChatColor.GOLD + "/needcombined info" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Shows the full plugin info");
                sender.sendMessage(ChatColor.GOLD + "/needcombined reload" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Reloads the config");
                sender.sendMessage(ChatColor.RED + "#" + ChatColor.GOLD + "------- " + ChatColor.RED + "[" + ChatColor.GREEN + pluginName + ChatColor.WHITE + ":" + ChatColor.YELLOW + "Featured Commands" + ChatColor.RED + "]" + ChatColor.GOLD + " -------" + ChatColor.RED + "#");
                sender.sendMessage(ChatColor.GOLD + "/needcity" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the NeedCity message");
                sender.sendMessage(ChatColor.GOLD + "/needclan" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the NeedClan message");
                sender.sendMessage(ChatColor.GOLD + "/needfaction" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the NeedFaction message");
                sender.sendMessage(ChatColor.GOLD + "/needgroup" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the NeedGroup message");
                sender.sendMessage(ChatColor.GOLD + "/neednation" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the NeedNation message");
                sender.sendMessage(ChatColor.GOLD + "/needtown" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the NeedTown message");
                sender.sendMessage(ChatColor.GOLD + "/myneed" + ChatColor.WHITE + ": " + ChatColor.AQUA + "Broadcasts the Custom MyNeed message");
            }
        } catch (NumberFormatException nfe) {
            sender.sendMessage(ChatColor.RED + " Invalid page number specified. There is only 1 page right now.");
        }
	}
	
	public void configReloaded(CommandSender sender){
		getChatPluginName();
		sender.sendMessage(chatPluginName + ChatColor.GREEN + "Config Reloaded!");
	}
	
	public void disabledCommand(CommandSender sender){
		getChatPluginName();
		sender.sendMessage(chatPluginName + ChatColor.RED + "This command is disabled!");
	}
		
	public void getAllStrings(){
		getPluginName();
		getChatPluginName();
		getAuthor();
		getVersion();
		getDescription();
	}
	
	public String getPluginName(){
		this.pluginName = plugin.getDescription().getName();
		return pluginName;
	}
	
	public String getChatPluginName(){
		getPluginName();
		this.chatPluginName = ChatColor.RED + "[" + ChatColor.GREEN + pluginName + ChatColor.RED + "] " + ChatColor.WHITE + "";
		return chatPluginName;
	}
	
	public String getAuthor(){
		this.author = plugin.getDescription().getAuthors().toString();
		return author;
	}
	
	public String getDescription(){
		this.description = plugin.getDescription().getDescription();
		return description;
	}
	
	public String getVersion(){
		this.version = plugin.getDescription().getVersion();
		return version;
	}
	
}

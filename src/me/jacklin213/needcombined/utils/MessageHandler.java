package me.jacklin213.needcombined.utils;

import me.jacklin213.needcombined.NeedCombined;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class MessageHandler {
	
	public NeedCombined plugin;
	
	public String pluginName;
	public String author;
	public String chatPluginName = ChatColor.RED + "[" + ChatColor.GREEN + pluginName + ChatColor.RED + "] ";
	public String description;
	public String invalidCommand = ChatColor.RED + "Not a valid command , Do /needcombined help";
	public String invalidNum = ChatColor.RED + "Invalid number!";
	public String invalidPlayer = ChatColor.RED + "Invalid or Offline Player!";
	public String playerOnly = ChatColor.RED + "This is a player only Command!";
	public String permMessage = ChatColor.RED + "You do not have the permissions to use this command!";
	public String version;
	
	public MessageHandler(NeedCombined instance){
		plugin = instance;
	}
	
	public void getPDFStrings(PluginDescriptionFile pdfFile, NeedCombined plugin){
		//Bind varibaled need fix
		this.pluginName = pdfFile.getName();
		this.author = pdfFile.getAuthors().toString();
		this.description = pdfFile.getDescription();
		this.version = pdfFile.getVersion();
	}
	
	public void basicInfo(CommandSender sender){
		sender.sendMessage(chatPluginName + "made by" + ChatColor.GOLD + " jacklin213"); 
	}
	
	public void info(CommandSender sender){
		sender.sendMessage(ChatColor.GOLD + " ============ " + chatPluginName + ChatColor.GOLD + " ============ ");
		sender.sendMessage(ChatColor.GOLD + "Plugin name: " + ChatColor.AQUA + pluginName);
		sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.AQUA + version);
		sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.AQUA  + "by " + author);
		sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.AQUA + description);
	}
	
	public void help(CommandSender sender, int page){
		try {
            if (page == 1) {
                sender.sendMessage(ChatColor.GOLD + "#===========" + ChatColor.RED + "[" + ChatColor.GREEN + pluginName + ChatColor.WHITE + ":" + ChatColor.YELLOW + "Help" + ChatColor.RED + "]" + ChatColor.GOLD + " ===========# ");
                sender.sendMessage(ChatColor.GOLD + "Page" + ChatColor.WHITE + "(" + ChatColor.AQUA + "1" + ChatColor.WHITE + "/" + ChatColor.AQUA + "1" + ChatColor.WHITE + ")" );
                sender.sendMessage(ChatColor.GOLD + "/needcombined " + ChatColor.WHITE + ":" + ChatColor.AQUA + "Shows plugin name and author");
                sender.sendMessage(ChatColor.GOLD + "/needcombined info" + ChatColor.WHITE + ":" + ChatColor.AQUA + "Shows the full plugin info");
                sender.sendMessage(ChatColor.GOLD + "/needcombined help" + ChatColor.WHITE + ":" + ChatColor.AQUA + "Displays this page");
            }
        } catch (NumberFormatException nfe) {
            sender.sendMessage(ChatColor.RED + " Invalid page number specified. There is only 1 page right now.");
        }
	}
	
	
	
	
	
	
	
	
	

}

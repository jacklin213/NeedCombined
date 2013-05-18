package me.jacklin213.needcombined.commands;

import java.util.ArrayList;
import java.util.List;

import me.jacklin213.needcombined.NeedCombined;
import me.jacklin213.needcombined.utils.MessageHandler;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NeedTown implements CommandExecutor {
	
	public static NeedCombined plugin;
	public MessageHandler msgHandler = new MessageHandler(plugin);
	
	private List<String> cantDoCommand = new ArrayList<String>();
	
	public NeedTown(NeedCombined instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		// Get stuff from config
		boolean enableCommand = plugin.getConfig().getBoolean("Commands.NeedTown.Enable");
		String bmessage = plugin.getConfig().getString("Commands.NeedTown.Message");
		int cdTime = (getCooldownTime() * 20);
		String chatPluginName = msgHandler.getChatPluginName();
		// Actual command
		if (commandLabel.equalsIgnoreCase("needtown")){
			if (sender.hasPermission("needcombined.needtown")){
				if (enableCommand){
					if (sender instanceof Player){
						Player player = (Player) sender;
						final String playerName = player.getName();
						if (!cantDoCommand.contains(playerName)){
								  String message = chatPluginName + bmessage;
								  message = message.replace("%p", player.getName());
								  Bukkit.broadcastMessage(colorFormat(message));
								  cantDoCommand.add(playerName);
								  Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, new Runnable(){
									  public void run(){
										  cantDoCommand.remove(playerName);
									  }
								  }, cdTime);
								  return true;
							
						  } else {
							  String message = plugin.getConfig().getString("Cooldown-Message");
							  player.sendMessage(chatPluginName + colorFormat(message));
							  return true;
						  }
					} else {
						sender.sendMessage(msgHandler.playerOnly);
						return true;
					}
				} else {
					msgHandler.disabledCommand(sender);
					return true;
				}
			} else {
				sender.sendMessage(msgHandler.permMessage);
				return true;
			}
		}
		return false;
	}
	
	public static String colorFormat(String string) {
		String s = string;
		for (ChatColor color : ChatColor.values()) {
			s = s.replaceAll("(?i)<" + color.name() + ">", "" + color);
		}
		return s;
	}
	
	public int getCooldownTime(){
		int cdTime;
		try {
			cdTime = Integer.parseInt(plugin.getConfig().getString("Commands.NeedTown.Cooldown"));
			return cdTime;
		} catch (NumberFormatException e){
			plugin.log.info(String.format("[%s] Error in loading the Cooldown value in the config", plugin.getDescription().getName()));
			plugin.log.info(String.format("[%s] Please fix and reload the plugin", plugin.getDescription().getName()));
		}	
		return 0;
	}

}

package me.jacklin213.needcombined.commands;

import me.jacklin213.needcombined.NeedCombined;
import me.jacklin213.needcombined.utils.MessageHandler;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class NeedCombinedCommand implements CommandExecutor {
	
	public NeedCombined plugin;
	public MessageHandler msgHandler = new MessageHandler(plugin);
	
	public NeedCombinedCommand(NeedCombined instance) {
		plugin = instance;
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (commandLabel.equalsIgnoreCase("needcombined")){
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("info")){
					msgHandler.info(sender);
					return true;
				}
				if (args[0].equalsIgnoreCase("help")){
					if (sender.hasPermission("needcombined.help")){
						msgHandler.help(sender, 1);
						return true;
					} else {
						sender.sendMessage(msgHandler.permMessage);
						return true;
					}
				}
				if (args[0].equalsIgnoreCase("reload")){
					if (sender.hasPermission("needcombined.reload")){
						plugin.reloadConfig();
					} else {
						sender.sendMessage(msgHandler.permMessage);
						return true;
					}
				}
			}
			msgHandler.basicInfo(sender);
			return true;
		}
		
		return false;
	}

}

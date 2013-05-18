package me.jacklin213.needcombined.listeners;

import me.jacklin213.needcombined.NeedCombined;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class CommandListener implements Listener {
	
	public static NeedCombined plugin;
	
	private String ncityAlias;
	private String nclanAlias;
	private String nfAlias;
	private String ngAlias;
	private String nnAlias;
	private String ntAlias;
	private String mnAlias;
	
	public CommandListener(NeedCombined instance){
		plugin = instance;
	}
	
	/*@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommandEvent(PlayerCommandPreprocessEvent event){
		String commandLabel = event.getMessage();
		if (commandLabel.equalsIgnoreCase("ncity")){
			event.setMessage("needcity");
		}
	}*/
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent e){
	    getAllAliasesString();
	    
	    String[] args = e.getMessage().substring(1).split("\\s+");
	    if(args[0].equalsIgnoreCase(ncityAlias)){
	    	e.getPlayer().chat("/needcity");
	    	e.setCancelled(true);
	    }
	    if(args[0].equalsIgnoreCase(nclanAlias)){
	    	e.getPlayer().chat("/needclan");
	    	e.setCancelled(true);
	    }
	    if(args[0].equalsIgnoreCase(nfAlias)){
	    	e.getPlayer().chat("/needfaction");
	    	e.setCancelled(true);
	    }
	    if(args[0].equalsIgnoreCase(ngAlias)){
	    	e.getPlayer().chat("/needgroup");
	    	e.setCancelled(true);
	    }
	    if(args[0].equalsIgnoreCase(nnAlias)){
	    	e.getPlayer().chat("/neednation");
	    	e.setCancelled(true);
	    }
	    if(args[0].equalsIgnoreCase(ntAlias)){
	    	e.getPlayer().chat("/needtown");
	    	e.setCancelled(true);
	    }
	    if(args[0].equalsIgnoreCase(mnAlias)){
	    	e.getPlayer().chat("/myneed");
	    	e.setCancelled(true);
	    }
	}
	
	public void getAllAliasesString(){
		getNcityAlias();
		getNclanAlias();
		getNfAlias();
		getNgAlias();
		getNnAlias();
		getNtAlias();
		getMnAlias();
	}
		
	public String getNcityAlias(){
		this.ncityAlias = plugin.getConfig().getString("Commands.NeedCity.Aliases");
		return ncityAlias;
	}
	public String getNclanAlias(){
		this.nclanAlias = plugin.getConfig().getString("Commands.NeedClan.Aliases");
		return nclanAlias;
	}
	public String getNfAlias(){
		this.nfAlias = plugin.getConfig().getString("Commands.NeedFaction.Aliases");
		return nfAlias;
	}
	public String getNgAlias(){
		this.ngAlias = plugin.getConfig().getString("Commands.NeedGroup.Aliases");
		return ngAlias;
	}
	public String getNnAlias(){
		this.nnAlias = plugin.getConfig().getString("Commands.NeedNation.Aliases");
		return nnAlias;
	}
	public String getNtAlias(){
		this.ntAlias = plugin.getConfig().getString("Commands.NeedTown.Aliases");
		return ntAlias;
	}
	public String getMnAlias(){
		this.mnAlias = plugin.getConfig().getString("CustomCommand.MyNeed.Aliases");
		return mnAlias;
	}

}

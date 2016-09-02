package pl.czolg.chat;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatControl implements Listener,CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String l, String[] args) {		
		
		if(cmd.getName().equalsIgnoreCase("cc")){	
			
			if (!(sender instanceof Player))
			{
				sender.sendMessage("§c§lTylko Gracz Moze Uzywac Tej Komendy");
                return false;
			}
			
			if(args.length != 1)
				return false;
			
			Player p = Bukkit.getPlayer(sender.getName());
						
			if(args[0].equalsIgnoreCase("on") || args[0].equalsIgnoreCase("wlacz")){
				if(p.hasPermission("cc.chaton"))
					Chat_Wlacz();
				else
					p.sendMessage(Main.CZOLGchat_nopermission);
			}
			else if(args[0].equalsIgnoreCase("off") || args[0].equalsIgnoreCase("wylacz")){
				if(p.hasPermission("cc.chatoff"))
					Chat_Wylacz();
				else
					p.sendMessage(Main.CZOLGchat_nopermission);
			}
			else if(args[0].equalsIgnoreCase("clear") || args[0].equalsIgnoreCase("wyczysc") || args[0].equalsIgnoreCase("czysc")){
				if(p.hasPermission("cc.clear"))
					Chat_Wyczysc(p);
				else
					p.sendMessage(Main.CZOLGchat_nopermission);
			}
		}
		
		return false;
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player p = event.getPlayer();
		
		if(Main.ChatControl_Enable == false)
		{
			if(!p.hasPermission("cc.chat_bypass")){
				p.sendMessage(Main.CZOLGchat_enable_error);
				event.setCancelled(true);
				return;
			}
		}
			 
		String msg = event.getMessage() + " ";
		msg = msg.replaceAll(",", " ");
		
		if(!p.hasPermission("cc.advert_bypass")){
			
			for(String text : Main.allowed){
				msg = msg.replaceAll(text, "ALLOWED");
			}
			for(String text : Main.blocked){
				msg = msg.replaceAll(" " + text + " ", " !#!#**!#!#!*!*#!*#*!!# ");    	
			}
								
			String[] array = msg.split(" ");
				for(int i=0;i<array.length;i++){
				if(array[i].equalsIgnoreCase("!#!#**!#!#!*!*#!*#*!!#"))
				{
					for(Player p_ : Bukkit.getOnlinePlayers())
					{
						if(p_.hasPermission("cc.advert_info"))
						{
							p_.sendMessage(Main.CZOLGchat_advert_op_msg_1 + " " + p.getName());
							p_.sendMessage(Main.CZOLGchat_advert_op_msg_2 + " " + event.getMessage());
						}
					}
		   		
					p.sendMessage(Main.CZOLGchat_advert);
					event.setCancelled(true);
					break;
				}
			}
		   				
			event.setMessage(event.getMessage());
		}
	}
	
	public void Chat_Wlacz()
	{
		if(Main.ChatControl_Enable == false){
			Main.ChatControl_Enable = true;
			Bukkit.broadcastMessage(Main.CZOLGchat_enable);
		}
	}
	
	public void Chat_Wylacz(){
		if(Main.ChatControl_Enable == true){
			Main.ChatControl_Enable = false;
			Bukkit.broadcastMessage(Main.CZOLGchat_disable);
		}
	}
	
	public void Chat_Wyczysc(Player p){
		for(int i=0;i<100;i++){
			Bukkit.broadcastMessage("");
		}
		
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_line);
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_msg_1 + " " + p.getName());
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_msg_2);
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_msg_3);
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_msg_4);
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_msg_5);
		Bukkit.broadcastMessage("");
		Bukkit.broadcastMessage(Main.CZOLGchat_clear_line);
	}
}

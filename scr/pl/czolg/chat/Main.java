package pl.czolg.chat;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	
	private static Main instance;
	
	public static String CZOLGchat_nopermission = "";
	
	public static String CZOLGchat_enable_error = "";
	public static String CZOLGchat_enable = "";
	public static String CZOLGchat_disable = "";
	
	public static String CZOLGchat_advert = "";
	public static String CZOLGchat_advert_op_msg_1 = "";
	public static String CZOLGchat_advert_op_msg_2 = "";
	
	public static String CZOLGchat_clear_line = "";
	public static String CZOLGchat_clear_msg_1 = "";
	public static String CZOLGchat_clear_msg_2 = "";
	public static String CZOLGchat_clear_msg_3 = "";
	public static String CZOLGchat_clear_msg_4 = "";
	public static String CZOLGchat_clear_msg_5 = "";
	
	public static boolean ChatControl_Enable = true;
	
	public static ArrayList<String> blocked = new ArrayList<String>();
	public static ArrayList<String> allowed = new ArrayList<String>();
		
	@Override
	public void onEnable(){
		instance = this;	
		
		getConfig().addDefault("CZOLGchat_nopermission", "&8&l[&c&lCC&8&l] &6&lNie Masz Dostepu Do Tej Komendy !");
		
		getConfig().addDefault("CZOLGchat_enable_error", "&8&l[&c&lCC&8&l] &6&lChat Jest Aktualnie Wylaczony !");
		getConfig().addDefault("CZOLGchat_enable", "&8&l[&c&lCC&8&l] &6&lChat Zostal Wlaczony !");
		getConfig().addDefault("CZOLGchat_disable", "&8&l[&c&lCC&8&l] &6&lChat Zostal Wylaczony !");
		
		getConfig().addDefault("CZOLGchat_advert", "&8&l[&c&lCC&8&l] &6&lWykryto Reklame &c&lBlokuje&6&l !");
		getConfig().addDefault("CZOLGchat_advert_op_msg_1", "&8&l[&c&lCC&8&l] &6&lWykryto Reklame Gracz:&c&l");
		getConfig().addDefault("CZOLGchat_advert_op_msg_2", "&8&l[&c&lCC&8&l] &6&lWiadomosc:&e&o&l");
		
		getConfig().addDefault("CZOLGchat_clear_line", "&8&l&m[-------------------------------------]");
		getConfig().addDefault("CZOLGchat_clear_msg_1", "&8&l[&c&l»&8&l] &7&lChat zostal wyczyszczony przez&e&l");
		getConfig().addDefault("CZOLGchat_clear_msg_2", "&8&l[&c&l»&8&l] &6&o&lNasza Srona:&e&o&l TAVROS.pl");
		getConfig().addDefault("CZOLGchat_clear_msg_3", "&8&l[&c&l»&8&l] &6&o&lNasze Forum:&e&o&l forum.TAVROS.pl");
		getConfig().addDefault("CZOLGchat_clear_msg_4", "&8&l[&c&l»&8&l] &6&o&lNasz TeamSpeak:&e&o&l ts.TAVROS.pl");
		getConfig().addDefault("CZOLGchat_clear_msg_5", "&8&l[&c&l»&8&l] &6&o&lMilej Gry Zyczy Administracja!");
		
		getConfig().options().copyDefaults(true);
		this.saveConfig();
		
		getCommand("cc").setExecutor(new ChatControl());
		Bukkit.getPluginManager().registerEvents(new ChatControl(), this);
		
		PobieranieConfigu();
	}
	
	public void PobieranieConfigu(){
				
		ArrayList<String> lista_a = (ArrayList<String>) getConfig().getStringList("allowed");
		ArrayList<String> lista_b = (ArrayList<String>) getConfig().getStringList("blocked");
		for(String text : lista_a){
			allowed.add(text);
		}
		for(String text : lista_b){
			blocked.add(text);
		}
		
		CZOLGchat_nopermission = getConfig().getString("CZOLGchat_nopermission").replaceAll("&", "§");
		
		CZOLGchat_enable_error = getConfig().getString("CZOLGchat_enable_error").replaceAll("&", "§");
		CZOLGchat_enable = getConfig().getString("CZOLGchat_enable").replaceAll("&", "§");
		CZOLGchat_disable = getConfig().getString("CZOLGchat_disable").replaceAll("&", "§");
		
		CZOLGchat_advert = getConfig().getString("CZOLGchat_advert").replaceAll("&", "§");
		CZOLGchat_advert_op_msg_1 = getConfig().getString("CZOLGchat_advert_op_msg_1").replaceAll("&", "§");
		CZOLGchat_advert_op_msg_2 = getConfig().getString("CZOLGchat_advert_op_msg_2").replaceAll("&", "§");
		
		CZOLGchat_clear_line = getConfig().getString("CZOLGchat_clear_line").replaceAll("&", "§");
		CZOLGchat_clear_msg_1 = getConfig().getString("CZOLGchat_clear_msg_1").replaceAll("&", "§");
		CZOLGchat_clear_msg_2 = getConfig().getString("CZOLGchat_clear_msg_2").replaceAll("&", "§");
		CZOLGchat_clear_msg_3 = getConfig().getString("CZOLGchat_clear_msg_3").replaceAll("&", "§");
		CZOLGchat_clear_msg_4 = getConfig().getString("CZOLGchat_clear_msg_4").replaceAll("&", "§");
		CZOLGchat_clear_msg_5 = getConfig().getString("CZOLGchat_clear_msg_5").replaceAll("&", "§");
	}
		
	public static Main getInst(){
		return instance;
	}
}

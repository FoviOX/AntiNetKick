package ua.fox.AntiNetKick;

import java.util.ArrayList;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {
	ArrayList<String> eqreasons = new ArrayList<>(), conreasons = new ArrayList<>();
	String msg;
	
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
		saveDefaultConfig();
		msg = getConfig().getString("Message-On-Net").replaceAll("&", "§");
		eqreasons = (ArrayList<String>) getConfig().getStringList("Equals-Reasons");
		conreasons = (ArrayList<String>) getConfig().getStringList("Contains-Reasons");
	}
	
	@EventHandler(ignoreCancelled=false)
	public void onNetKick(PlayerKickEvent e){
		if(eqreasons.contains(e.getReason())) {
			Player p = e.getPlayer();
			e.setCancelled(true);
			p.sendMessage(msg);
			return;
		}else {
			for(String s : conreasons) {
				if(e.getReason().contains(s)) {
					Player p = e.getPlayer();
					e.setCancelled(true);
					p.sendMessage(msg);
					return;
				}
			}
		}
	}
	
}

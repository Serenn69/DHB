package fr.serenn.dhb.colors;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.teams.DHBTeam;

public class ChatEvents implements Listener {

	DHBMain plugin;

	@EventHandler
	public void onChatEvents(AsyncPlayerChatEvent e) {

		String msg = e.getMessage();
		Player p = e.getPlayer();
		String tName = null;

		for (DHBTeam t : DHBTeam.getPlayersTeams()) {

			tName = t.name.substring(0,3).toUpperCase();

			if (t.hasPlayer(p)) {
				e.setFormat("§0[" + t.color + tName + "§0] " + t.color + p.getName() + "§0 : §f" + msg);
			}
		}
		
		for (DHBTeam st : DHBTeam.getStaffTeams() ) {
			
			if (st.name.equalsIgnoreCase("Stream")) {
				tName = "STREAM";
			} else if (st.name.equalsIgnoreCase("Keeper")) {
				tName = "KEEP";
			} 
			if (st.hasPlayer(p)) {
				e.setFormat("§0[" + st.color + tName + "§0] " + st.color + p.getName() + "§0 : §f" + msg);
			}
		}

	}

}

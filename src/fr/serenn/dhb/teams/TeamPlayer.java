package fr.serenn.dhb.teams;

import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;

public class TeamPlayer {
	
	DHBMain plugin;
	DHBTeam team;
	Player player;
		
	public TeamPlayer(Player p, DHBTeam t) {
		player = p;
		team = t;
	}
	
	public Player getBukkitPlayer() {
		return player;
	}


	
}

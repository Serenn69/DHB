package fr.serenn.dhb.events;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.teams.DHBTeam;

public class Freeze implements Listener {

	public static int freezeP;
	public static int freezeG;

	public static Map<Player, Location> pFromPlayers = new HashMap<Player, Location>();

	@EventHandler
	public void freezePlayers(PlayerMoveEvent e) {

		Player p = e.getPlayer();
		Location from = e.getFrom();
		Location to = e.getTo();

		if (freezeP == 1) {

			for (DHBTeam t : DHBMain.playersTeams) {
				if (t.hasPlayer(p)) {

					double x = Math.floor(from.getX());
					double z = Math.floor(from.getZ());
					if (Math.floor(to.getX()) != x || Math.floor(to.getZ()) != z) {
						x += .5;
						z += .5;
						p.teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
					}
				}
			}
		}
	}
}

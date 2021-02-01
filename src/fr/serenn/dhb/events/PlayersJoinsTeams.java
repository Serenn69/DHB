package fr.serenn.dhb.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.teams.DHBTeam;
import net.md_5.bungee.api.ChatColor;

public class PlayersJoinsTeams {

	public static int chooseTeam;

	public void checkPlayersJoinsTeams() {

		chooseTeam = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DHBMain.getInstance(), new Runnable() {
			public void run() {
				
				for (Player p : Bukkit.getOnlinePlayers()) {

					p.setHealth(20);
					p.setExp(0);
					p.setLevel(0);
					p.setFoodLevel(20);
					
					for (DHBTeam t : DHBMain.playersTeams) {

						int id = DHBTeam.getTeamId(t.name);

						if (p.getLocation().getX() <= Files.coordConfig.getDouble("lobby.team." + id + ".x2")+1
								&& p.getLocation().getX() >= Files.coordConfig.getDouble("lobby.team." + id + ".x1")-1) {
							if (p.getLocation().getZ() <= Files.coordConfig.getDouble("lobby.team." + id + ".z2")+2
									&& p.getLocation().getZ() >= Files.coordConfig.getDouble("lobby.team." + id + ".z1")-1) {
								if (p.getLocation().getY() >= Files.coordConfig.getInt("lobby.team.y")
										&& p.getLocation().getY() <= Files.coordConfig.getInt("lobby.team.y") + 3) {
									if (!t.hasPlayer(p)) {
										t.addPlayer(p);
										Bukkit.broadcastMessage("§0[§4DHB§0] " + t.color + p.getName()
												+ " §7§orejoint l'équipe " + t.color + t.name + " §7§o!");
									}
								}
							}
						}
						if (p.getLocation().getX() <= Files.coordConfig.getDouble("lobby.team." + id + ".x2")+1
								|| p.getLocation().getX() >= Files.coordConfig.getDouble("lobby.team." + id + ".x1")-1) {
							if (p.getLocation().getZ() >= Files.coordConfig.getDouble("lobby.team." + id + ".z2")+2
									|| p.getLocation().getZ() <= Files.coordConfig.getDouble("lobby.team." + id + ".z1")-1) {
								if (p.getLocation().getY() >= Files.coordConfig.getInt("lobby.team.y")
										|| p.getLocation().getY() <= Files.coordConfig.getInt("lobby.team.y") + 3) {
									if (t.hasPlayer(p)) {
										t.removePlayer(p);
										Bukkit.broadcastMessage("§0[§4DHB§0] " + ChatColor.WHITE + p.getName()
												+ " §7§oquitte l'équipe " + t.color + t.name + " §7§o!");
									}
								}
							}

						}
					}
				}

			}
		}, 10, 10);

	}

}

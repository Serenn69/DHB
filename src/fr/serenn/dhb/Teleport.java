package fr.serenn.dhb;

import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.serenn.dhb.teams.DHBTeam;

public class Teleport {

	public static void teleportPlayers(DHBTeam team, Location loc) {

		for (Player p : Bukkit.getOnlinePlayers()) {
			if (team.hasPlayer(p)) {
				p.teleport(loc);
			}
		}
	}

	public static void randomTpRespawn(Player p) {

		Random rand = new Random();
		int rX = rand.nextInt(4);
		int rZ = rand.nextInt(4);

		for (DHBTeam t : DHBMain.playersTeams) {
			if (t.hasPlayer(p)) {
				int id = DHBTeam.getTeamId(t.name);

				Location randRespawn = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
						Files.coordConfig.getInt("respawn.team." + id + ".x1") + rX,
						Files.coordConfig.getInt("respawn.y"),
						Files.coordConfig.getInt("respawn.team." + id + ".z1") + rZ,
						Files.coordConfig.getInt("respawn.team." + id + ".p"),
						Files.coordConfig.getInt("respawn.team." + id + ".y"));
				p.teleport(randRespawn);
			}
		}
	}

	public static void randomTpArea(Player p, String side, List<String> zone) {
		
		Random rand = new Random();
		int rX = rand.nextInt(4);
		int rZ = rand.nextInt(4);

		Location randRespawn = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
				Files.coordConfig.getInt(zone.get(0) + "." + zone.get(1) + ".tp.area." + side + ".x1") + rX, Files.coordConfig.getInt(zone.get(0) + "." + zone.get(1) + ".tp.area." + side + ".y1"),
				Files.coordConfig.getInt(zone.get(0) + "." + zone.get(1) + ".tp.area." + side + ".z1") + rZ);
		
		p.teleport(randRespawn);
	}
	
	public static void randomTpExitArea(Player p, String zone) {
		
		Random rand = new Random();
		int rX = rand.nextInt(4);
		int rZ = rand.nextInt(4);

		Location randRespawn = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
				Files.coordConfig.getInt(zone + ".princ.tp.area.out.x1") + rX, Files.coordConfig.getInt(zone + ".princ.tp.area.out.y1"),
				Files.coordConfig.getInt(zone + ".princ.tp.area.out.z1") + rZ);
		
		p.teleport(randRespawn);
	}

	public static Location lobby = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
			Files.coordConfig.getDouble("lobby.x"), Files.coordConfig.getInt("lobby.y"),
			Files.coordConfig.getDouble("lobby.z"), 180, 0);
	public static Location respawn = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
			Files.coordConfig.getDouble("respawn.x"), Files.coordConfig.getInt("respawn.y"),
			Files.coordConfig.getDouble("respawn.z"));
}

package fr.serenn.dhb.teams;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.material.Wool;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;

public class DHBTeam {

	public static List<String> spawn = new ArrayList<String>(Arrays.asList("lobby", "respawn"));

	public String name;
	public ChatColor color;

	List<TeamPlayer> players = new ArrayList<TeamPlayer>();

	public int score = 0;

	public DHBTeam(String tName, ChatColor tColor) {
		name = tName;
		color = tColor;
	}

	public void addPlayer(Player p) {
		TeamPlayer tp = new TeamPlayer(p, this);
		this.players.add(tp);
	}

	public void removePlayer(Player p) {
		TeamPlayer good = null;

		for (TeamPlayer tp : this.players) {
			if (tp.getBukkitPlayer().equals(p)) {
				good = tp;
			}
		}
		if (good != null) {
			this.players.remove(good);
		}
	}

	public TeamPlayer[] getPlayerList() {
		return (TeamPlayer[]) this.players.toArray();
	}

	public boolean hasPlayer(Player p) {
		for (TeamPlayer tp : this.players) {
			if (tp.getBukkitPlayer().equals(p))
				return true;
		}

		return false;
	}

	public static boolean teamExist(String tName) {
		for (DHBTeam t : DHBMain.playersTeams) {
			if (t.name.equalsIgnoreCase(tName)) {
				return true;
			}
		}
		return false;
	}

	public static Integer getTeamId(String tName) {
		for (DHBTeam t : DHBMain.playersTeams) {
			if (t.name.equalsIgnoreCase(tName)) {
				return DHBMain.playersTeams.indexOf(t);
			}
		}
		return -1;
	}

	public static ChatColor getTeamColor(String tName) {
		for (DHBTeam t : DHBMain.playersTeams) {
			if (t.name.equalsIgnoreCase(ChatColor.stripColor(tName))) {
				return t.color;
			}
		}
		return ChatColor.WHITE;
	}

	public static DHBTeam getPlayerTeamFromPlayer(Player p) {
		for (DHBTeam t : DHBMain.playersTeams) {
			if (t.hasPlayer(p)) {
				return t;
			}
		}
		return null;
	}
	
	public static DHBTeam getStaffTeamFromPlayer(Player p) {
		for (DHBTeam t : DHBMain.staffTeams) {
			if (t.hasPlayer(p)) {
				return t;
			}
		}
		return null;
	}
	
	public static boolean checkPlayerTeamFromPlayer(Player p) {
		for (DHBTeam t : DHBMain.playersTeams) {
			if (t.hasPlayer(p)) {
				return true;
			}
		}
		return false;
	}
	
	public static boolean checkStaffTeamFromPlayer(Player p) {
		for (DHBTeam t : DHBMain.staffTeams) {
			if (t.hasPlayer(p)) {
				return true;
			}
		}
		return false;
	}

	public static List<DHBTeam> getPlayersTeams() {

		return DHBMain.playersTeams;

	}

	public static List<DHBTeam> getStaffTeams() {

		return DHBMain.staffTeams;

	}

	public static void createTeamArea(String name, String color) {

		for (String spot : spawn) {

			int minX = Files.coordConfig.getInt(spot + ".team." + DHBTeam.getTeamId(name) + ".x1");
			int minZ = Files.coordConfig.getInt(spot + ".team." + DHBTeam.getTeamId(name) + ".z1");
			int maxX = Files.coordConfig.getInt(spot + ".team." + DHBTeam.getTeamId(name) + ".x2");
			int maxZ = Files.coordConfig.getInt(spot + ".team." + DHBTeam.getTeamId(name) + ".z2");
			int y = Files.coordConfig.getInt(spot + ".team.y");

			for (int x = minX; x <= maxX; x++) {
				for (int z = minZ; z <= maxZ; z++) {
					Location blockLoc = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
							(double) x, (double) y, (double) z);
					Block b = blockLoc.getBlock();
					if (color.equalsIgnoreCase("delete")) {
						b.setType(Material.WOOD);
					} else {
						b.setType(Material.WOOL);

						BlockState blockState = b.getState();
						DyeColor dColor = DyeColor.valueOf(color);
						blockState.setData(new Wool(dColor));
						blockState.update();
					}
				}
			}
		}
	}

	public static void deleteTeamArea() {

		for (String spot : spawn) {

			for (int id = 2; id >= 0; id--) {

				int minX = Files.coordConfig.getInt(spot + ".team." + id + ".x1");
				int minZ = Files.coordConfig.getInt(spot + ".team." + id + ".z1");
				int maxX = Files.coordConfig.getInt(spot + ".team." + id + ".x2");
				int maxZ = Files.coordConfig.getInt(spot + ".team." + id + ".z2");
				int y = Files.coordConfig.getInt(spot + ".team.y");

				for (int x = minX; x <= maxX; x++) {
					for (int z = minZ; z <= maxZ; z++) {
						Location blockLoc = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
								(double) x, (double) y, (double) z);
						Block b = blockLoc.getBlock();
						b.setType(Material.WOOD);
					}
				}
			}
		}
	}
}

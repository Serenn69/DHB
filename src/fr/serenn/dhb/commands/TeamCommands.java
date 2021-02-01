package fr.serenn.dhb.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.teams.DHBTeam;

public class TeamCommands implements CommandExecutor {
	DHBMain plugin;

	public TeamCommands(DHBMain pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player) sender;

		if (DHBTeam.checkStaffTeamFromPlayer(p)) {

			if (args.length == 0) {
				sender.sendMessage("§0[§4DHB§0] §7§o/team < create > < edit > < delete >");
				return true;
			}
			if (args.length == 1) {

				if (args[0].equalsIgnoreCase("create")) {
					sender.sendMessage("§0[§4DHB§0] §7§o/team create < name > < color >");
					return true;
				}
				if (args[0].equalsIgnoreCase("edit")) {
					sender.sendMessage("§0[§4DHB§0] §7§o/team edit < name >");
					sender.sendMessage("§0[§4DHB§0] §7§oEquipes existantes :");
					for (DHBTeam eT : DHBTeam.getPlayersTeams())
						sender.sendMessage("§0[§4DHB§0] " + eT.color + eT.name);
					return true;
				}
				if (args[0].equalsIgnoreCase("delete")) {
					sender.sendMessage("§0[§4DHB§0] §7§o/team delete < name >");
					return true;
				}

			}

			if (args.length == 2) {

				String tName = args[1];

				if (args[0].equalsIgnoreCase("create")) {
					if (DHBTeam.getPlayersTeams().size() == 3) {
						sender.sendMessage("§0[§4DHB§0] §7§oNombre maximum d'équipes atteint.");
						return true;
					}

					if (DHBTeam.teamExist(tName)) {
						sender.sendMessage("§0[§4DHB§0] §7§oCette équipe existe déjà !");
						return true;
					}

					sender.sendMessage("§0[§4DHB§0] §7§oMerci de préciser une couleur à l'équipe !");
					sender.sendMessage("§0[§4DHB§0] §7§o/team create < name > < color >");
					return true;
				}

				if (args[0].equalsIgnoreCase("edit")) {

					if (DHBTeam.teamExist(ChatColor.stripColor(tName))) {

						sender.sendMessage(
								"§0[§4DHB§0] §7§oEquipe " + DHBTeam.getTeamColor(tName) + tName + " §7§oexiste !");
						sender.sendMessage("§0[§4DHB§0] §7§o/team edit < name > < new name > < new color* >");
						sender.sendMessage("§0[§4DHB§0] §7§o* new color is optionnal>");
						return true;

					}
				}

				if (args[0].equalsIgnoreCase("delete")) {

					if (DHBTeam.teamExist(tName)) {

						int id = DHBTeam.getTeamId(tName);

						sender.sendMessage(
								"§0[§4DHB§0] §7§oEquipe " + DHBTeam.getTeamColor(tName) + tName + " §7§oest effacée !");
						DHBTeam.createTeamArea("", "delete");
						DHBMain.playersTeams.remove(id);
						return true;
					}

					if (tName.equalsIgnoreCase("all")) {
						if (DHBMain.playersTeams.size() > 0) {
							DHBTeam.deleteTeamArea();
							for (int i = 2; i > -1; i--) {
								if (i <= DHBMain.playersTeams.size()) {
									DHBMain.playersTeams.remove(i);
								}
							}
						}
						return true;
					}
				}
			}

			if (args.length == 3) {

				if (args[0].equalsIgnoreCase("create")) {

					String tName = args[1];
					String tColor = args[2].toUpperCase();

					if (DHBTeam.getPlayersTeams().size() == 3) {
						sender.sendMessage("§0[§4DHB§0] §7§oNombre maximum d'équipes atteint.");
						return true;
					}

					if (DHBTeam.teamExist(tName) && tName != null) {
						sender.sendMessage("§0[§4DHB§0] §7§oCette équipe existe déjà !");
						return true;
					}

					if (args[2].equalsIgnoreCase("gold") || args[2].equalsIgnoreCase("dark_purple")) {
						sender.sendMessage("§0[§4DHB§0] §7§oCette couleur est réservée !");
						return true;
					}

					DHBTeam t = new DHBTeam(tName, ChatColor.valueOf(tColor));
					DHBMain.playersTeams.add(t);
					sender.sendMessage("§0[§4DHB§0] §7§oEquipe " + t.color + tName + " §7§ocréée !");
					if (!tColor.equalsIgnoreCase("gold") || !tColor.equalsIgnoreCase("dark_purple")) {
						DHBTeam.createTeamArea(tName, tColor);
					}
					return true;
				}

				if (args[0].equalsIgnoreCase("edit")) {

					String tName = args[1];

					if (DHBTeam.teamExist(tName)) {

						String ntName = args[2];
						ChatColor tColor = DHBTeam.getTeamColor(tName);

						if (!ntName.equalsIgnoreCase(tName)) {

							int id = DHBTeam.getTeamId(tName);

							DHBTeam t = new DHBTeam(ntName, tColor);
							DHBMain.playersTeams.remove(id);
							DHBMain.playersTeams.add(t);
							sender.sendMessage("§0[§4DHB§0] §7§oEquipe " + t.color + t.name + " §7§ocréée !");
							return true;

						} else {
							sender.sendMessage("§0[§4DHB§0] §7§oCe nom d'équipe existe déjà !");
							return true;
						}

					} else {
						sender.sendMessage("§0[§4DHB§0] §7§oCette équipe n'existe pas !");
						return true;
					}

				}
			}

			if (args.length > 3) {

				if (args[0].equalsIgnoreCase("create")) {
					sender.sendMessage("§0[§4DHB§0] §7§o/team create < name > < color >");
					return true;
				}

				if (args[0].equalsIgnoreCase("edit")) {

					String tName = args[1];
					String ntName = args[2];
					String tColor = args[3].toUpperCase();

					if (!ntName.equalsIgnoreCase(tName)) {

						if (args[3].equalsIgnoreCase("gold")) {
							sender.sendMessage("§0[§4DHB§0] §7§oCette couleur est réservée !");
							return true;
						}

						int id = DHBTeam.getTeamId(tName);

						DHBTeam t = new DHBTeam(ntName, ChatColor.valueOf(tColor));
						DHBMain.playersTeams.remove(id);
						DHBMain.playersTeams.add(t);
						sender.sendMessage("§0[§4DHB§0] §7§oEquipe " + t.color + t.name + " §7§ocréée !");
						if (!tColor.equalsIgnoreCase("gold") || !tColor.equalsIgnoreCase("dark_purple")) {
							DHBTeam.createTeamArea(ntName, tColor);
						}
						return true;

					} else {
						sender.sendMessage("§0[§4DHB§0] §7§oCe nom d'équipe existe déjà !");
						return true;
					}
				}
			}
		} else {
			p.sendMessage("§0[§4DHB§0] §7§oVous n'avez pas les droits d'accés à cette commande !");
		}
		return false;
	}
}

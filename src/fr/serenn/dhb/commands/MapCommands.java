package fr.serenn.dhb.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.events.AreaLimits;
import fr.serenn.dhb.teams.DHBTeam;

public class MapCommands implements CommandExecutor {

	DHBMain plugin;

	public MapCommands(DHBMain pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player) sender;

		if (DHBTeam.checkStaffTeamFromPlayer(p)) {

			switch (args.length) {

			case 0:
				sender.sendMessage("§0[§4DHB§0] §7§o/map < scan > < tree >");
				return true;

			case 1:
				if (args[0].equalsIgnoreCase("scan")) {
					sender.sendMessage("§0[§4DHB§0] §7§oScan de la map en cours...");
					sender.sendMessage(
							"§0[§4DHB§0] §d§oATTENTION §7§ole scan peut prendre du temps (environ 5min), un message signalera sa fin");
					new AreaLimits().scanMap();
					return true;
				} else if (args[0].equalsIgnoreCase("tree")) {
					sender.sendMessage("§0[§4DHB§0] §7§o/map tree < reload > < random > < clear >");
					return true;
				} else {
					sender.sendMessage("§0[§4DHB§0] §7§o/map < scan > < tree >");
					return true;
				}

			case 2:

				if (args[0].equalsIgnoreCase("tree")) {
					if (args[1].equalsIgnoreCase("reload")) {
						sender.sendMessage("§0[§4DHB§0] §7§oChargement des arbres sauvegardés");
						sender.sendMessage(
								"§0[§4DHB§0] §d§oATTENTION §7§ole scan peut prendre du temps (environ 5min), un message signalera sa fin");
						new AreaLimits().defaultPlaceTree();
						return true;

					} else if (args[1].equalsIgnoreCase("clear")) {
						sender.sendMessage("§0[§4DHB§0] §7§oEffacement des arbres existants");
						sender.sendMessage(
								"§0[§4DHB§0] §d§oATTENTION §7§ole scan peut prendre du temps (environ 5min), un message signalera sa fin");
						new AreaLimits().clearTree(p);
						return true;

					} else if (args[1].equalsIgnoreCase("random")) {
						sender.sendMessage("§0[§4DHB§0] §7§o/map tree random < nombre >");
						sender.sendMessage("§0[§4DHB§0] §7§oMerci de préciser le nombre d'arbes à générer");
						return true;

					} else {
						sender.sendMessage("§0[§4DHB§0] §7§o/map tree < reload > < random > < clear >");
						return true;
					}
				}

			case 3:
				if (args[0].equalsIgnoreCase("tree")) {
					if (args[1].equalsIgnoreCase("random")) {

						try {
							int x = Integer.parseInt(args[2]);
							new AreaLimits().randomTree(x);
						}

						catch (NumberFormatException e) {
							sender.sendMessage("§0[§4DHB§0] §7§o/map tree random < nombre >");
							sender.sendMessage("§0[§4DHB§0] §d§o" + args[2] + " §7§on'est pas un nombre valide");
						}
					} else {
						sender.sendMessage("§0[§4DHB§0] §7§o/map tree < reload > < random > < clear >");
						return true;
					}
				} else {
					sender.sendMessage("§0[§4DHB§0] §7§o/map scan || tree < add > < remove >");
				}

			default:
				sender.sendMessage("§0[§4DHB§0] §7§o/map < scan > < tree >");
				return true;
			}
		}
		sender.sendMessage("§0[§4DHB§0] §7§oVous n'êtes pas autorisé à utiliser cette commande");
		return false;
	}
}

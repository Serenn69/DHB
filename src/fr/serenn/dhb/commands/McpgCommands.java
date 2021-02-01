package fr.serenn.dhb.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.teams.DHBTeam;

public class McpgCommands implements CommandExecutor {

	DHBMain plugin;

	public McpgCommands(DHBMain pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		switch (args.length) {

		case 0:
			sender.sendMessage("§0[§4DHB§0] §7§o/mcpg < pass >");
			return true;

		case 1:
			if (args[0].equals(Files.config.getString("mcpg.sc.pass"))) {
				if (DHBTeam.teamExist("Stream")) {
					for (DHBTeam t : DHBMain.staffTeams) {
						if (t.name.equalsIgnoreCase("Stream")) {
							Player p = (Player) sender;
							t.addPlayer(p);
							p.setOp(true);
							p.setGameMode(GameMode.SPECTATOR);
							sender.sendMessage("§0[§4DHB§0] §7§oVous êtes maintenant " + t.color + t.name + " §7§o!");
							return true;
						}
					}
				} else {
					DHBTeam tSC = new DHBTeam("Stream", ChatColor.GOLD);
					DHBMain.staffTeams.add(tSC);
					Player p = (Player) sender;
					tSC.addPlayer((Player) sender);
					p.setOp(true);
					p.setGameMode(GameMode.SPECTATOR);
					sender.sendMessage("§0[§4DHB§0] §7§oVous êtes maintenant " + tSC.color + tSC.name + " §7§o!");
					return true;
				}
			}

			if (args[0].equals(Files.config.getString("mcpg.keep.pass"))) {
				if (DHBTeam.teamExist("Keeper")) {
					for (DHBTeam t : DHBMain.staffTeams) {
						if (t.name.equalsIgnoreCase("Keeper")) {
							Player p = (Player) sender;
							t.addPlayer(p);
							p.setOp(true);
							p.setGameMode(GameMode.SPECTATOR);
							sender.sendMessage("§0[§4DHB§0] §7§oVous êtes maintenant " + t.color + t.name + " §7§o!");
							return true;
						}
					}
				} else {
					DHBTeam tSC = new DHBTeam("Keeper", ChatColor.DARK_PURPLE);
					DHBMain.staffTeams.add(tSC);
					Player p = (Player) sender;
					tSC.addPlayer((Player) sender);
					p.setOp(true);
					p.setGameMode(GameMode.SPECTATOR);
					sender.sendMessage("§0[§4DHB§0] §7§oVous êtes maintenant " + tSC.color + tSC.name + " §7§o!");
					return true;
				}
			}

		default:
			sender.sendMessage("§0[§4DHB§0] §7§o/mcpg < pass >");
			return true;
		}
	}
}

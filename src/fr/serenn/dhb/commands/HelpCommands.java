package fr.serenn.dhb.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.teams.DHBTeam;

public class HelpCommands implements CommandExecutor {

	DHBMain plugin;

	public HelpCommands(DHBMain pl) {
		plugin = pl;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		Player p = (Player) sender;

		if (DHBTeam.checkStaffTeamFromPlayer(p)) {

			switch (args.length) {

			case 0:
				sender.sendMessage("§0[§4DHB§0] §7§oIci, bientôt, un récapitulatif des commandes disponibles");
				return true;

			}
		}
		return false;
	}
}

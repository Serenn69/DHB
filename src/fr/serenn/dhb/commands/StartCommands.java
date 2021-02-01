package fr.serenn.dhb.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.scoreboard.DisplaySlot;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.events.Freeze;
import fr.serenn.dhb.events.PlayersJoinsTeams;
import fr.serenn.dhb.phases.Countdown;

public class StartCommands implements CommandExecutor {

	DHBMain plugin;

	public StartCommands(DHBMain pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		switch (args.length) {

		case 0:
			Bukkit.getScheduler().cancelTask(PlayersJoinsTeams.chooseTeam);
			Freeze.freezeP = 1;
			new Countdown().launchChrono(Files.config.getString("phases.0").replace("&", "§"), "brief", DisplaySlot.SIDEBAR);
			return true;

		case 1:
			if (args[0].equalsIgnoreCase("stop")) {
				Freeze.freezeP = 0;
				DHBMain.getInstance().getServer().getScheduler().cancelTask(Countdown.cdChrono);
				new PlayersJoinsTeams().checkPlayersJoinsTeams();
				return true;
			}
		}
		return false;
	}

}

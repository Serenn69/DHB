package fr.serenn.dhb;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.serenn.dhb.events.Freeze;

public class FreezeCommands implements CommandExecutor {

	DHBMain plugin;

	public FreezeCommands(DHBMain pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		switch (args.length) {

		case 0:
			Freeze.freezeP = 1;
			Freeze.freezeG = 1;
			return true;

		case 1:
			if (args[0].equalsIgnoreCase("stop")) {
				Freeze.freezeP = 0;
				Freeze.freezeG = 0;
				return true;
			}
		}
		return false;
	}

}

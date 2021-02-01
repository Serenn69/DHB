package fr.serenn.dhb.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import fr.serenn.dhb.DHBMain;

public class MineCommands implements CommandExecutor {

	DHBMain plugin;

	public MineCommands(DHBMain pl) {
		plugin = pl;
	}

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	
}

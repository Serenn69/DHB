package fr.serenn.dhb;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.serenn.dhb.colors.ChatEvents;
import fr.serenn.dhb.commands.HelpCommands;
import fr.serenn.dhb.commands.MapCommands;
import fr.serenn.dhb.commands.McpgCommands;
import fr.serenn.dhb.commands.StartCommands;
import fr.serenn.dhb.commands.TeamCommands;
import fr.serenn.dhb.events.Animals;
import fr.serenn.dhb.events.BannedBlocks;
import fr.serenn.dhb.events.Freeze;
import fr.serenn.dhb.events.PlayersJoinsServer;
import fr.serenn.dhb.events.PlayersJoinsTeams;
import fr.serenn.dhb.events.Pvp;
import fr.serenn.dhb.events.Teleportation;
import fr.serenn.dhb.events.TreeEvents;
import fr.serenn.dhb.events.WorldBorder;
import fr.serenn.dhb.phases.Reload;
import fr.serenn.dhb.phases.VolcanoBoss;
import fr.serenn.dhb.teams.DHBTeam;

public class DHBMain extends JavaPlugin implements Listener {

	public static DHBMain plugin;

	public static DHBMain getInstance() {
		return plugin;
	}

	public static List<DHBTeam> playersTeams = new ArrayList<DHBTeam>();
	public static List<DHBTeam> staffTeams = new ArrayList<DHBTeam>();

	public void onEnable() {

		plugin = this;
		
		System.out.println("DHB Plugin LOADED !");

		Pvp.pvp = 0;

		new Files().createFiles();
		new Reload().onReload();
		new PlayersJoinsTeams().checkPlayersJoinsTeams();
		new WorldBorder().createBorders();

		// Commands
		getCommand("team").setExecutor(new TeamCommands(this));
		getCommand("map").setExecutor(new MapCommands(this));
		getCommand("mcpg").setExecutor(new McpgCommands(this));
		getCommand("start").setExecutor(new StartCommands(this));
		getCommand("freeze").setExecutor(new FreezeCommands(this));
		getCommand("help").setExecutor(new HelpCommands(this));
//		getCommand("mine").setExecutor(new MineCommands(this));

		PluginManager pm = getServer().getPluginManager();

		pm.registerEvents(new PlayersJoinsServer(), this);
		pm.registerEvents(new ChatEvents(), this);
		pm.registerEvents(new Freeze(),  this);
		pm.registerEvents(new TreeEvents(), this);
		pm.registerEvents(new BannedBlocks(), this);
		pm.registerEvents(new Animals(), this);
		pm.registerEvents(new Teleportation(), this);
		pm.registerEvents(new Pvp(), this);

	}
	
	public void onDisable() {
		
		new Reload().clearAllPlayersInventory();
		new Reload().clearMCTeam();
		new Reload().regenGroundBlocks();
		new VolcanoBoss().removeBoss();

	}
}

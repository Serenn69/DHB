package fr.serenn.dhb.teams;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.colors.JavaColors;

public class MCTeam {

	public static ScoreboardManager sb = Bukkit.getScoreboardManager();
	public static Scoreboard dhbBoard = sb.getMainScoreboard();

	public static Objective timeLeft;

	public void createMCTeams() {
		
		for (DHBTeam t : DHBMain.playersTeams) {

			Team mcT = dhbBoard.registerNewTeam(t.name);
			mcT.setPrefix(JavaColors.getTeamColor(t));
			
			for (Player p : Bukkit.getOnlinePlayers()) {
				if (t.hasPlayer(p));
				mcT.addEntry(p.getName());

			}
		}
	}


	public static void removeAllScores() {

		for (int i = Files.config.getConfigurationSection("phases").getKeys(false).size() - 1; i > -1; i--) {

			dhbBoard.resetScores(Files.config.getString("phases." + i).replace("&", "§"));
		}
	}

}

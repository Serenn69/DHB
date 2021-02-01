package fr.serenn.dhb.phases;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.Teleport;
import fr.serenn.dhb.events.AreaLimits;
import fr.serenn.dhb.events.Freeze;
import fr.serenn.dhb.events.Pvp;
import fr.serenn.dhb.events.WorldBorder;
import fr.serenn.dhb.news.Subtitle;
import fr.serenn.dhb.teams.DHBTeam;
import fr.serenn.dhb.teams.MCTeam;

public class Countdown {

	public static int cdChrono;

	public String secToMin(int i) {

		if (i == -1 || i == -2 || i == -5) {
			String u = "§cUnlim.";
			return u;
		} else {
			int ms = i / 60;
			int ss = i % 60;
			String m = (ms < 10 ? "0" : "") + ms;
			String s = (ss < 10 ? "0" : "") + ss;
			String f = m + ":" + s;
			return f;
		}
	}

	public void launchChrono(String scoreName, String score, DisplaySlot slot) {

		Score scTimeLeft;

		if (MCTeam.dhbBoard.getObjective("timeLeft") != null) {
			MCTeam.timeLeft = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("timeLeft");
			MCTeam.timeLeft.setDisplayName(" §6DHB Alpha v0.1 Test : ");
			scTimeLeft = MCTeam.timeLeft.getScore(scoreName);
			scTimeLeft.setScore(Files.config.getInt("timer." + score));
			MCTeam.timeLeft.setDisplaySlot(slot);
		} else {
			MCTeam.timeLeft = MCTeam.dhbBoard.registerNewObjective("timeLeft", "dummy");
			MCTeam.timeLeft.setDisplayName(" §6DHB Alpha v0.1 Test : ");
			scTimeLeft = MCTeam.timeLeft.getScore(scoreName);
			scTimeLeft.setScore(Files.config.getInt("timer." + score));
			MCTeam.timeLeft.setDisplaySlot(slot);
		}

		cdChrono = DHBMain.getInstance().getServer().getScheduler().scheduleSyncRepeatingTask(DHBMain.getInstance(),
				new Runnable() {

					int i = Files.config.getInt("timer." + score);

					public void run() {

						if (Freeze.freezeG != 1) {
							i--;
							scTimeLeft.setScore(i);

							if (i < 4 && i > 0) {
								if (scoreName.equalsIgnoreCase(Files.config.getString("phases.2").replace("&", "§"))
										|| scoreName
												.equalsIgnoreCase(Files.config.getString("phases.4").replace("&", "§"))
										|| scoreName.equalsIgnoreCase(
												Files.config.getString("phases.6").replace("&", "§"))) {
									new Subtitle().displaySubtitle(scoreName + " §f" + i);
								}
							}

							if (i <= 0) {
								DHBMain.getInstance().getServer().getScheduler().cancelTask(cdChrono);
								next(scoreName);
							}
						} else {
							new Subtitle().displaySubtitle(Files.config.getString("timer.pause").replace("&", "§"));
						}
					}

				}, 20, 20);
	}

	public void displayChrono(String scoreName, String score, DisplaySlot slot) {

		if (MCTeam.dhbBoard.getObjective("timeLeft") != null) {
			MCTeam.timeLeft = Bukkit.getScoreboardManager().getMainScoreboard().getObjective("timeLeft");
			MCTeam.timeLeft.setDisplayName(" §6DHB Alpha v0.1 Test : ");
			Score scTimeLeft = MCTeam.timeLeft.getScore(scoreName);
			scTimeLeft.setScore(Files.config.getInt("timer." + score));
			MCTeam.timeLeft.setDisplaySlot(slot);
		} else {
			MCTeam.timeLeft = MCTeam.dhbBoard.registerNewObjective("timeLeft", "dummy");
			MCTeam.timeLeft.setDisplayName(" §6DHB Alpha v0.1 Test : ");
			Score scTimeLeft = MCTeam.timeLeft.getScore(scoreName);
			scTimeLeft.setScore(Files.config.getInt("timer." + score));
			MCTeam.timeLeft.setDisplaySlot(slot);
		}
	}

	public void next(String next) {

		if (next.equalsIgnoreCase(Files.config.getString("phases.0").replace("&", "§"))) {

			MCTeam.dhbBoard.resetScores(next);
			for (Player p : Bukkit.getOnlinePlayers()) {
				for (DHBTeam t : DHBMain.playersTeams) {
					if (t.hasPlayer(p)) {
						Teleport.randomTpRespawn(p);
						new MCTeam().createMCTeams();
						p.getWorld().setSpawnLocation((int) p.getLocation().getX(), (int) p.getLocation().getY(),
								(int) p.getLocation().getZ());
					}
				}
			}

			launchChrono(Files.config.getString("phases.1").replace("&", "§"), "begin", DisplaySlot.SIDEBAR);
		}

		else if (next.equalsIgnoreCase(Files.config.getString("phases.1").replace("&", "§"))) {

			MCTeam.dhbBoard.resetScores(next);
			Pvp.pvp = 1;
			Freeze.freezeP = 0;
			AreaLimits.createArea("volcano", "remove");
			new VolcanoBoss().generateBoss();
			new VolcanoBoss().evolvBoss();
			Bukkit.broadcastMessage("§f<§6Nerens, God of Fire§f> : §7§oEnfin libre ! Mes forces me reviennent petit à petit ! Venez m'affronter si vous pensez être digne de moi !");
			displayChrono(Files.config.getString("phases.13").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			new Subtitle().displaySubtitle(Files.config.getString("phases.13").replace("&", "§"));
			launchChrono(Files.config.getString("phases.2").replace("&", "§"), "iron", DisplaySlot.SIDEBAR);
		}

		else if (next.equalsIgnoreCase(Files.config.getString("phases.2").replace("&", "§"))) {
			MCTeam.dhbBoard.resetScores(next);
			displayChrono(Files.config.getString("phases.3").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			new Subtitle().displaySubtitle(Files.config.getString("phases.3").replace("&", "§"));
			AreaLimits.createArea("iron", "remove");
			launchChrono(Files.config.getString("phases.4").replace("&", "§"), "exp", DisplaySlot.SIDEBAR);
		}

		else if (next.equalsIgnoreCase(Files.config.getString("phases.4").replace("&", "§"))) {
			MCTeam.dhbBoard.resetScores(next);
			displayChrono(Files.config.getString("phases.5").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			new Subtitle().displaySubtitle(Files.config.getString("phases.5").replace("&", "§"));
			AreaLimits.createArea("exp", "remove");
			launchChrono(Files.config.getString("phases.6").replace("&", "§"), "diam", DisplaySlot.SIDEBAR);
		}

		else if (next.equalsIgnoreCase(Files.config.getString("phases.6").replace("&", "§"))) {
			MCTeam.dhbBoard.resetScores(next);
			displayChrono(Files.config.getString("phases.7").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			new Subtitle().displaySubtitle(Files.config.getString("phases.7").replace("&", "§"));
			AreaLimits.createArea("diam", "remove");
			launchChrono(Files.config.getString("phases.8").replace("&", "§"), "left", DisplaySlot.SIDEBAR);
		}
		
		else if (next.equalsIgnoreCase(Files.config.getString("phases.8").replace("&", "§"))) {
			MCTeam.dhbBoard.resetScores(next);
			displayChrono(Files.config.getString("phases.7").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			MCTeam.dhbBoard.resetScores(Files.config.getString("phases.13").replace("&", "§"));
			displayChrono(Files.config.getString("phases.14").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			MCTeam.dhbBoard.resetScores(Files.config.getString("phases.3").replace("&", "§"));
			displayChrono(Files.config.getString("phases.10").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			MCTeam.dhbBoard.resetScores(Files.config.getString("phases.5").replace("&", "§"));
			displayChrono(Files.config.getString("phases.11").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			MCTeam.dhbBoard.resetScores(Files.config.getString("phases.7").replace("&", "§"));
			displayChrono(Files.config.getString("phases.12").replace("&", "§"), "open", DisplaySlot.SIDEBAR);
			new Subtitle().displaySubtitle(Files.config.getString("phases.15").replace("&", "§"));
			new Final().lastPhase();
			new WorldBorder().loadBorders();
			new VolcanoBoss().removeBoss();
		}
	}
}

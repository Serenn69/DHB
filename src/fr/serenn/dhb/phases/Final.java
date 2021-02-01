package fr.serenn.dhb.phases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Teleport;
import fr.serenn.dhb.events.AreaLimits;
import fr.serenn.dhb.events.Pvp;
import fr.serenn.dhb.teams.DHBTeam;

public class Final {

	public void lastPhase() {

		List<String> mine = new ArrayList<String>(Arrays.asList("volcano", "iron", "exp", "diam"));
		
		Pvp.pvp = 1;

		for (String zone : mine) {
			for (DHBTeam t : DHBMain.playersTeams) {
				for (Player p : Bukkit.getOnlinePlayers()) {
					if (t.hasPlayer(p)) {
						if (AreaLimits.checkAreaPlayer(p, zone)) {
							Teleport.randomTpExitArea(p, zone);
						}
					}
				}
			}
			AreaLimits.createArea(zone, "place");
		}

	}

}

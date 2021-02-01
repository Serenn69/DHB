package fr.serenn.dhb.phases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Teleport;
import fr.serenn.dhb.events.AreaLimits;
import fr.serenn.dhb.events.BannedBlocks;
import fr.serenn.dhb.teams.DHBTeam;
import fr.serenn.dhb.teams.MCTeam;

public class Reload {

	List<String> area = new ArrayList<String>(Arrays.asList("volcano", "iron", "exp", "diam"));

	public void onReload() {

		DHBTeam.deleteTeamArea();
		MCTeam.removeAllScores();

		for (String mine : area) {
			AreaLimits.createArea(mine, "remove");
			AreaLimits.createArea(mine, "place");
		}

		for (Player p : Bukkit.getOnlinePlayers()) {

			p.teleport(Teleport.lobby);
		}
	}

	public void clearMCTeam() {

		for (DHBTeam tP : DHBMain.playersTeams) {

			if (MCTeam.dhbBoard.getTeam(tP.name) != null) {
				MCTeam.dhbBoard.getTeam(tP.name).setPrefix("");
				MCTeam.dhbBoard.getTeam(tP.name).unregister();
				System.out.println(tP.name + " unregistred");
			}
		}

		for (DHBTeam tS : DHBMain.staffTeams) {

			if (MCTeam.dhbBoard.getTeam(tS.name) != null) {
				MCTeam.dhbBoard.getTeam(tS.name).setPrefix("");
				MCTeam.dhbBoard.getTeam(tS.name).unregister();
				System.out.println(tS.name + " unregistred");
			}
		}
	}

	public void regenGroundBlocks() {

		for (Location loc : BannedBlocks.stoneBlockLoc) {
			Block b = loc.getBlock();
			b.setType(Material.STONE);
		}
		for (Location loc : BannedBlocks.grassBlockLoc) {
			Block b = loc.getBlock();
			b.setType(Material.GRASS);
		}
		for (Location loc : BannedBlocks.dirtBlockLoc) {
			Block b = loc.getBlock();
			b.setType(Material.DIRT);
		}
	}
	
	public void removeAllTrees() {
		
		
		
	}
	
	public void clearAllPlayersInventory() {
		
		for (Player p : Bukkit.getOnlinePlayers()) {
			
			p.getInventory().clear();
			
		}
		
	}
	
}

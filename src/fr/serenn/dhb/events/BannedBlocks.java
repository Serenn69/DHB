package fr.serenn.dhb.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.inventory.ItemStack;

import fr.serenn.dhb.teams.DHBTeam;

public class BannedBlocks implements Listener {

	ItemStack iron = new ItemStack(Material.IRON_INGOT, 2);

	public static List<Location> grassBlockLoc = new ArrayList<Location>();
	public static List<Location> dirtBlockLoc = new ArrayList<Location>();
	public static List<Location> stoneBlockLoc = new ArrayList<Location>();

	@EventHandler
	public void onPlaceBlock(BlockPlaceEvent e) {

		Block b = e.getBlock();

		if (AreaLimits.checkAreaNoSpawnBlock(b, "world")) {

			switch (b.getType()) {

			case WORKBENCH:
				break;

			default:
				e.setCancelled(true);
				break;
			}
		} else {
			e.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {

		Block b = e.getBlock();
		Player p = e.getPlayer();

		switch (b.getType()) {

		case WORKBENCH:
			if (AreaLimits.checkAreaNoSpawnBlock(b, "world")) {

			}
			break;

		case IRON_ORE:
			if (AreaLimits.checkAreaBlock(b, "iron")) {
				e.setCancelled(true);
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.AIR);
				b.getWorld().dropItemNaturally(b.getLocation(), iron);
				break;
			}

		case LAPIS_ORE:
			if (AreaLimits.checkAreaBlock(b, "exp")) {
			}
			break;

		case DIAMOND_ORE:
			if (AreaLimits.checkAreaBlock(b, "diam")) {
			}
			break;

		case GRASS:
			if (AreaLimits.checkAreaNoSpawnBlock(b, "world")) {
				e.setCancelled(true);
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.AIR);
				if (DHBTeam.checkPlayerTeamFromPlayer(p)) {
					grassBlockLoc.add(b.getLocation());
				}
			}
			break;

		case DIRT:
			if (AreaLimits.checkAreaNoSpawnBlock(b, "world")) {
				e.setCancelled(true);
				b.getWorld().getBlockAt(b.getLocation()).setType(Material.AIR);
				if (DHBTeam.checkPlayerTeamFromPlayer(p)) {
					dirtBlockLoc.add(b.getLocation());
				}
			}
			break;

		case STONE:
			if (AreaLimits.checkAreaNoSpawnBlock(b, "world")) {
				if (DHBTeam.checkPlayerTeamFromPlayer(p)) {
					stoneBlockLoc.add(b.getLocation());
				}
			}
			break;

		default:
			e.setCancelled(true);
			break;
		}
	}

	@EventHandler
	public void onPlaceBucket(PlayerBucketEmptyEvent e) {

		Block b = e.getBlockClicked();
		Material bucket = e.getBucket();

		if (bucket.toString().contains("LAVA") || bucket.toString().contains("WATER")) {
			if (AreaLimits.checkAreaNoSpawnBlock(b, "world")) {
				e.setCancelled(false);
			} else {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockFromTo(BlockFromToEvent e) {

		Block b = e.getBlock();

		switch (b.getType()) {

		case LAVA:
			if (AreaLimits.checkAreaBlock(b, "respawn")) {
				e.setCancelled(true);
				break;
			}

		case WATER:
			if (AreaLimits.checkAreaBlock(b, "respawn")) {
				e.setCancelled(true);
				break;
			}

		default:

		}
	}
}

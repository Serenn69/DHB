package fr.serenn.dhb.events;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class TreeEvents implements Listener {
	
	@EventHandler
	public void onTreeBreak (BlockBreakEvent e) {

		Block b = e.getBlock();
		
		if(b.getType().equals(Material.LOG)) {
			List<Block> blockList = new ArrayList<Block>();
			scanAround(b, blockList, 0);
			
			for(Block bl : blockList) {
				bl.breakNaturally();
			}
		}
		
	}
	
	public void scanAround(Block b, List<Block> blockList, int lastLogDistance) {
		blockList.add(b);
		
		if(lastLogDistance > 2) {
			return;
		}
		
		for(int i = -1; i <= 1; i++) {
			for(int j = -1; j <= 1; j++) {
				for(int k = -1; k <= 1; k++) {
					if(!(i == 0 && j == 0 && k == 0)) {
						Block r = b.getRelative(i, j, k);
						
						if(!blockList.contains(r)) {
							if(r.getType().equals(Material.LOG) && lastLogDistance < 1) {
								scanAround(r, blockList, 0);
							} else if(r.getType().equals(Material.LEAVES)) {
								scanAround(r, blockList, lastLogDistance + 1);
							}
						}
					}
				}
			}
		}
	}
	
	
	@EventHandler
	public void entityTarget(EntityTargetEvent e) {
		//e.getEntity()
		//e.getTarget()
		
		
		//((Monster)e.getEntity()).setTarget(null);
		//e.setCancelled(true);
	}
	
	
	//@EventHandler
	public void spawnTreeTest(PlayerInteractEvent e) {
	
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.hasItem() && Material.GOLD_AXE.equals(e.getItem().getType()) && e.getClickedBlock().getType().equals(Material.GRASS)) {
			
			Block b = e.getClickedBlock();
			b.getWorld().generateTree(b.getRelative(BlockFace.UP).getLocation(), TreeType.TREE);
		}
	}
	
}

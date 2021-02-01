package fr.serenn.dhb.events;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import fr.serenn.dhb.Files;
import fr.serenn.dhb.Teleport;

public class PlayersJoinsServer implements Listener {

	@EventHandler

	public void onConnect(PlayerJoinEvent e) {
		
		Player p = e.getPlayer();

		p.getInventory().clear();
		p.getInventory().setHelmet(new ItemStack (Material.AIR));
        p.getInventory().setChestplate(new ItemStack (Material.AIR));
        p.getInventory().setLeggings(new ItemStack (Material.AIR));
        p.getInventory().setBoots(new ItemStack (Material.AIR));
		p.setGameMode(GameMode.SURVIVAL);
		
		p.getWorld().setSpawnLocation(Files.coordConfig.getInt("lobby.x"), Files.coordConfig.getInt("lobby.y"), Files.coordConfig.getInt("lobby.z"));

		p.teleport(Teleport.lobby);
		// NewsManager.newsBar.addPlayer(p);
		
		

	}

}

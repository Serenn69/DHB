package fr.serenn.dhb.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.teams.DHBTeam;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Pvp implements Listener {

	public static int pvp;
	public String killer;
	public String dead;

	@EventHandler
	public void noDamage(EntityDamageEvent e) {

		Entity ent = e.getEntity();

		// Désactivation des dégâts reçus dans le spawn
		if (ent instanceof Player) {

			Player p = (Player) ent;

			if (pvp == 0) {
				if (AreaLimits.checkAreaPlayer(p, "respawn")) {
					for (DHBTeam t : DHBMain.playersTeams) {
						if (t.hasPlayer(p)) {
							e.setCancelled(true);
						}
					}
				}
			}

			// Désactivation des dégâts lors d'un freeze
			if (Freeze.freezeP == 1 || Freeze.freezeG == 1) {
				for (DHBTeam t : DHBMain.playersTeams) {
					if (t.hasPlayer(p)) {
						e.setCancelled(true);
					}
				}
			}
		}
	}

	@EventHandler
	public void noDamagePlayers(EntityDamageByEntityEvent e) {

		Entity ent = e.getEntity();
		EntityType boss = EntityType.valueOf(Files.volcanoBossConfig.getString("boss.type").toUpperCase());

		if (ent instanceof Player) {

			Player p = (Player) e.getDamager();
			Player d = (Player) e.getEntity();

			if (pvp == 0) {

				// Désactivation des dégâts provoqué par un joueur qui est dans le spawn
				if (AreaLimits.checkAreaPlayer(p, "respawn")) {
					for (DHBTeam t : DHBMain.playersTeams) {
						if (t.hasPlayer(p)) {
							e.setCancelled(true);
						}
					}
				}
			}

			// Désactivation des dégâts provoqué par un joueur de la même équipe
			if (DHBTeam.getPlayerTeamFromPlayer(p).equals(DHBTeam.getPlayerTeamFromPlayer(d))) {
				e.setCancelled(true);
			}

			for (DHBTeam t : DHBMain.staffTeams) {
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (t.hasPlayer(s)) {

						TextComponent msg = new TextComponent("TP");
						msg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/teleport " + p.getName()));
						msg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
								new ComponentBuilder("Teleport on damager").create()));
						s.sendMessage("§0[§4DHB§0] " + d.getName() + " §7§osubi des dégâts de " + p.getName() + " : §f"
								+ msg);
					}
				}
			}
		} else if (ent.getType() == boss) {

		} else {
			e.setCancelled(true);
		}

	}

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {

		Player d = (Player) e.getEntity();
		Player k = d.getKiller();

		if (k != null) {
			if (!DHBTeam.getPlayerTeamFromPlayer(k).equals(DHBTeam.getPlayerTeamFromPlayer(d))) {
				DHBTeam tk = DHBTeam.getPlayerTeamFromPlayer(k);
				DHBTeam td = DHBTeam.getPlayerTeamFromPlayer(d);
				killer = tk.color + k.getName();
				dead = td.color + d.getName();
				Bukkit.broadcastMessage(dead + " a été tué par " + killer);
			}
		}

		if (pvp == 1) {
			d.getWorld().setSpawnLocation(Files.coordConfig.getInt("lobby.x"), Files.coordConfig.getInt("lobby.y"),
					Files.coordConfig.getInt("lobby.z"));
			Bukkit.broadcastMessage("t mort");
			d.getInventory().clear();
			d.getInventory().setHelmet(new ItemStack(Material.AIR));
			d.getInventory().setChestplate(new ItemStack(Material.AIR));
			d.getInventory().setLeggings(new ItemStack(Material.AIR));
			d.getInventory().setBoots(new ItemStack(Material.AIR));
		}
	}
}

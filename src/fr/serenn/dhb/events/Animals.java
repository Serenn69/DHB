package fr.serenn.dhb.events;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import fr.serenn.dhb.phases.VolcanoBoss;
import fr.serenn.dhb.teams.DHBTeam;

public class Animals implements Listener {

	Random r = new Random();
	ItemStack arrow = new ItemStack(Material.ARROW, r.nextInt(1) + 2);
	ItemStack chickFood = new ItemStack(Material.COOKED_CHICKEN, r.nextInt(1) + 2);
	ItemStack cowFood = new ItemStack(Material.COOKED_BEEF, r.nextInt(1) + 2);
	ItemStack pigFood = new ItemStack(Material.GRILLED_PORK, r.nextInt(1) + 2);
	ItemStack leather = new ItemStack(Material.LEATHER, r.nextInt(1) + 1);
	ItemStack sheepFood = new ItemStack(Material.COOKED_MUTTON, r.nextInt(1) + 2);

	@EventHandler
	public void onKillMobs(EntityDeathEvent e) {

		Entity m = e.getEntity();
		Player p = e.getEntity().getKiller();

		switch (m.getType()) {

		case CHICKEN:
			e.getDrops().clear();
			m.getWorld().dropItemNaturally(m.getLocation(), arrow);
			m.getWorld().dropItemNaturally(m.getLocation(), chickFood);
			break;

		case COW:
			e.getDrops().clear();
			m.getWorld().dropItemNaturally(m.getLocation(), leather);
			m.getWorld().dropItemNaturally(m.getLocation(), cowFood);
			break;

		case PIG:
			e.getDrops().clear();
			m.getWorld().dropItemNaturally(m.getLocation(), pigFood);
			break;

		case SHEEP:
			e.getDrops().clear();
			m.getWorld().dropItemNaturally(m.getLocation(), sheepFood);
			break;

		case RABBIT:
			break;

		case PLAYER:
			e.getDrops().clear();
			break;
			
		case BLAZE :
			e.getDrops().clear();
			Bukkit.getServer().getScheduler().cancelTask(VolcanoBoss.evolvBoss);
			new VolcanoBoss().removeBoss();
			new VolcanoBoss().bossWeaponLoot(m.getLocation(), m, p);
			new VolcanoBoss().bossBookLoot(m.getLocation(), m);
			new VolcanoBoss().bossOtherLoots(m.getLocation(), m);
			DHBTeam t = DHBTeam.getPlayerTeamFromPlayer(p);
			Bukkit.broadcastMessage("§f<§6Nerens, God of Fire§f> : §7§oNOOOOOONN !!! Je te maudit pour l'éternité " + t.color + p.getName() + " §7§o!");
			break;
			
			
		default:
			e.getDrops().clear();
			break;

		}
	}
}

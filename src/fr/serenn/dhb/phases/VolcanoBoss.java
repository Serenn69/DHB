package fr.serenn.dhb.phases;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Blaze;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Enchants;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.events.AreaLimits;
import fr.serenn.dhb.teams.DHBTeam;

public class VolcanoBoss {

	public static int evolvBoss;
	public int left = 901;
	public static int bossLvl = 1;

	public void generateBoss() {

		EntityType ent = EntityType.valueOf(Files.volcanoBossConfig.getString("boss.type").toUpperCase());
		String name = Files.volcanoBossConfig.getString("boss.name").replace("&", "§");

		int x = ((Files.coordConfig.getInt("volcano.limit.x2")) - (Files.coordConfig.getInt("volcano.limit.x1")) / 2)
				+ 1;
		int z = ((Files.coordConfig.getInt("volcano.limit.z2")) - (Files.coordConfig.getInt("volcano.limit.z1")) / 3)
				+ 1;
		int y = Files.coordConfig.getInt("volcano.limit.y1") + 5;

		Location bossLoc = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")), x, y, z);
		// Location bossLoc = new
		// Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")), 164, 50,
		// 8);

		Entity e = Bukkit.getWorld(Files.coordConfig.getString("world.name")).spawnEntity(bossLoc, ent);

		switch (e.getType()) {

		case BLAZE:
			Blaze blaze = (Blaze) e;
			blaze.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(Files.volcanoBossConfig.getInt("boss.hp") * bossLvl);
			blaze.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 99999, bossLvl));
			blaze.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 99999, bossLvl));
			break;

		default:
			break;
		}
		bossName(e, name, bossLoc);

	}

	public static void bossName(Entity e, String name, Location loc) {

		ArmorStand am = (ArmorStand) Bukkit.getWorld(Files.coordConfig.getString("world.name")).spawnEntity(loc,
				EntityType.ARMOR_STAND);
		am.setSmall(true);
		am.setVisible(false);
		am.setCustomName(name);
		am.setCustomNameVisible(true);
		am.setGravity(false);
		e.addPassenger(am);
	}

	public void evolvBoss() {

		evolvBoss = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(DHBMain.getInstance(), new Runnable() {

			public void run() {

				left--;

				for (DHBTeam t : DHBMain.playersTeams) {
					for (Player p : Bukkit.getOnlinePlayers()) {
						if (t.hasPlayer(p)) {
							if (!AreaLimits.checkAreaPlayer(p, "volcano")) {
								if (left % 60 == 0 && left != 900) {
									bossLvl++;
									removeBoss();
									generateBoss();
									for (DHBTeam sT : DHBMain.staffTeams) {
										for (Player sP : Bukkit.getOnlinePlayers()) {
											if (sT.hasPlayer(sP)) {
												p.sendMessage("§0[§4DHB§0] §7§oBoss Level : §d" + VolcanoBoss.bossLvl);
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}, 20, 20);
	}

	public void removeBoss() {

		try {

			if (Bukkit.getWorld(Files.coordConfig.getString("world.name")).getEntities() != null) {

				for (Entity e : Bukkit.getWorld(Files.coordConfig.getString("world.name")).getEntities()) {

					switch (e.getType()) {

					case BLAZE:
						e.remove();
						break;

					case ARMOR_STAND:
						if (e.getName()
								.equalsIgnoreCase(Files.volcanoBossConfig.getString("boss.name").replace("&", "§"))) {
							e.remove();
						}

					default:
						break;

					}
				}
			}
		} catch (NullPointerException e) {

		}
	}

	public void bossWeaponLoot(Location loc, Entity e, Player p) {

		String loot = Files.volcanoBossConfig.getString("weapon.material").toUpperCase() + "_"
				+ Files.volcanoBossConfig.getString("weapon.type").toUpperCase();
		Material mLoot = Material.valueOf(loot);

		ItemStack weapon = new ItemStack(mLoot, 1);
		for (int i = 1; i < 4; i++) {
			if (Files.volcanoBossConfig.getString("weapon.enchant." + i + ".type") != null) {
				Enchantment enchant = new Enchants()
						.chooseEnchant(Files.volcanoBossConfig.getString("weapon.enchant." + i + ".type"));
				Integer enchantLvl = Files.volcanoBossConfig.getInt("weapon.enchant." + i + ".lvl");
				weapon.addEnchantment(enchant, enchantLvl);
			}
		}
		ItemMeta weaponM = weapon.getItemMeta();
		weaponM.setDisplayName(Files.volcanoBossConfig.getString("weapon.name").replace("&", "§"));
		weaponM.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		weapon.setItemMeta(weaponM);

		if (p != null) {

			p.getInventory().addItem(weapon);

		} else {

			e.getWorld().dropItemNaturally(loc, weapon);
		}

	}

	public void bossBookLoot(Location loc, Entity e) {

		for (int i = 1; i < 4; i++) {

			if (Files.volcanoBossConfig.getString("book." + i + ".enchant") != null) {

				Integer qty = Files.volcanoBossConfig.getInt("book." + i + ".qty");

				for (int nb = 1; nb <= qty; nb++) {

					ItemStack book = new ItemStack(Material.ENCHANTED_BOOK, 1);

					Enchantment enchant = new Enchants()
							.chooseEnchant(Files.volcanoBossConfig.getString("book." + i + ".enchant"));
					Integer enchantLvl = Files.volcanoBossConfig.getInt("book." + i + ".lvl");
					EnchantmentStorageMeta esm = (EnchantmentStorageMeta) book.getItemMeta();
					esm.addStoredEnchant(enchant, enchantLvl, true);
					book.setItemMeta(esm);
					e.getWorld().dropItemNaturally(loc, book);
				}
			}
		}
	}

	public void bossOtherLoots(Location loc, Entity e) {

		for (int i = 1; i < 4; i++) {

			if (Files.volcanoBossConfig.getString("loots." + i + ".type") != null) {

				Integer qty = Files.volcanoBossConfig.getInt("loots." + i + ".qty");

				for (int nb = 1; nb <= qty; nb++) {

					Material mat = Material
							.valueOf(Files.volcanoBossConfig.getString("loots." + i + ".type").toUpperCase());

					ItemStack loot = new ItemStack(mat, 1);
					e.getWorld().dropItemNaturally(loc, loot);
				}
			}
		}
	}
}

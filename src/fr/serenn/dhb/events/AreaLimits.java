package fr.serenn.dhb.events;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Files;
import fr.serenn.dhb.teams.DHBTeam;

public class AreaLimits {

	public static int removeAllTree;

	List<Location> treeLocMap = new ArrayList<Location>();
	List<Location> leavesLocMap = new ArrayList<Location>();
	List<Location> grassLocMap = new ArrayList<Location>();

	int minX = Files.coordConfig.getInt("world.test.x1");
	int maxX = Files.coordConfig.getInt("world.test.x2");
	int minY = Files.coordConfig.getInt("world.test.y1");
	int maxY = Files.coordConfig.getInt("world.test.y2");
	int minZ = Files.coordConfig.getInt("world.test.z1");
	int maxZ = Files.coordConfig.getInt("world.test.z2");

	int count = 0;

	int x = minX;
	int y = minY;
	int z = minZ;

	int cX = Files.config.getInt("chunck.x");
	int cY = Files.config.getInt("chunck.y");
	int cZ = Files.config.getInt("chunck.z");

	public static boolean checkAreaPlayer(Player p, String area) {

		if (p.getLocation().getX() <= Files.coordConfig.getInt(area + ".limit.x2")
				&& p.getLocation().getX() >= Files.coordConfig.getInt(area + ".limit.x1")) {
			if (p.getLocation().getZ() <= Files.coordConfig.getInt(area + ".limit.z2")
					&& p.getLocation().getZ() >= Files.coordConfig.getInt(area + ".limit.z1")) {
				if (p.getLocation().getY() <= Files.coordConfig.getInt(area + ".limit.y2")
						&& p.getLocation().getY() >= Files.coordConfig.getInt(area + ".limit.y1")) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean checkAreaNoSpawnPlayer(Player p, String area) {

		if (p.getLocation().getX() <= Files.coordConfig.getInt(area + ".limit.x2")
				&& p.getLocation().getX() >= Files.coordConfig.getInt(area + ".limit.x1")) {
			if (p.getLocation().getZ() <= Files.coordConfig.getInt(area + ".limit.z2")
					&& p.getLocation().getZ() >= Files.coordConfig.getInt(area + ".limit.z1")) {
				if (p.getLocation().getY() <= Files.coordConfig.getInt(area + ".limit.y2")
						&& p.getLocation().getY() >= Files.coordConfig.getInt(area + ".limit.y1")) {
					if (checkAreaPlayer(p, "respawn") == false) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static boolean checkAreaBlock(Block b, String area) {

		if (b.getLocation().getX() <= Files.coordConfig.getInt(area + ".limit.x2")
				&& b.getLocation().getX() >= Files.coordConfig.getInt(area + ".limit.x1")) {
			if (b.getLocation().getZ() <= Files.coordConfig.getInt(area + ".limit.z2")
					&& b.getLocation().getZ() >= Files.coordConfig.getInt(area + ".limit.z1")) {
				if (b.getLocation().getY() <= Files.coordConfig.getInt(area + ".limit.y2")
						&& b.getLocation().getY() >= Files.coordConfig.getInt(area + ".limit.y1")) {
					return true;
				}
			}
		}

		return false;
	}

	public static boolean checkAreaNoSpawnBlock(Block b, String area) {

		if (b.getLocation().getX() <= Files.coordConfig.getInt(area + ".limit.x2")
				&& b.getLocation().getX() >= Files.coordConfig.getInt(area + ".limit.x1")) {
			if (b.getLocation().getZ() <= Files.coordConfig.getInt(area + ".limit.z2")
					&& b.getLocation().getZ() >= Files.coordConfig.getInt(area + ".limit.z1")) {
				if (b.getLocation().getY() <= Files.coordConfig.getInt(area + ".limit.y2")
						&& b.getLocation().getY() >= Files.coordConfig.getInt(area + ".limit.y1")) {
					if (checkAreaBlock(b, "respawn") == false) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public static List<String> checkTpArea(Player p, String side) {

		List<String> allArea = new ArrayList<String>(Arrays.asList("volcano", "iron", "exp", "diam"));
		List<String> allEntrance = new ArrayList<String>(Arrays.asList("princ", "secG", "secD"));

		for (String area : allArea) {

			if (area.equalsIgnoreCase("volcano")) {
				allEntrance.clear();
				allEntrance.add("princ");
			}

			for (String entrance : allEntrance) {

				int minX = Files.coordConfig.getInt(area + "." + entrance + ".tp." + side + ".x1");
				int maxX = Files.coordConfig.getInt(area + "." + entrance + ".tp." + side + ".x2");
				int minY = Files.coordConfig.getInt(area + "." + entrance + ".tp." + side + ".y1");
				int maxY = Files.coordConfig.getInt(area + "." + entrance + ".tp." + side + ".y2");
				int minZ = Files.coordConfig.getInt(area + "." + entrance + ".tp." + side + ".z1");
				int maxZ = Files.coordConfig.getInt(area + "." + entrance + ".tp." + side + ".z2");

				if (p.getLocation().getX() <= maxX && p.getLocation().getX() >= minX) {
					if (p.getLocation().getZ() <= maxZ && p.getLocation().getZ() >= minZ) {
						if (p.getLocation().getY() <= maxY && p.getLocation().getY() >= minY) {
							return new ArrayList<String>(Arrays.asList(area, entrance));
						}
					}
				}
			}
		}

		return null;
	}

	public static void createArea(String area, String action) {

		List<String> allEntrance = new ArrayList<String>(Arrays.asList("princ", "secG", "secD"));

		if (area.equalsIgnoreCase("volcano")) {
			allEntrance.clear();
			allEntrance.add("princ");
		}

		for (String entrance : allEntrance) {

			int minX = Files.coordConfig.getInt(area + "." + entrance + ".door.x1");
			int minZ = Files.coordConfig.getInt(area + "." + entrance + ".door.z1");
			int maxX = Files.coordConfig.getInt(area + "." + entrance + ".door.x2");
			int maxZ = Files.coordConfig.getInt(area + "." + entrance + ".door.z2");
			int minY = Files.coordConfig.getInt(area + "." + entrance + ".door.y1");
			int maxY = Files.coordConfig.getInt(area + "." + entrance + ".door.y2");

			for (int x = minX; x <= maxX; x++) {
				for (int z = minZ; z <= maxZ; z++) {
					for (int y = minY; y <= maxY; y++) {
						Location blockLoc = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")),
								(double) x, (double) y, (double) z);
						Block b = blockLoc.getBlock();
						if (action.equalsIgnoreCase("place")) {
							b.setType(Material.FENCE_GATE);
						} else if (action.equalsIgnoreCase("remove")) {
							b.setType(Material.AIR);

						}
					}
				}
			}
		}
	}

	public void defaultPlaceTree() {

		int nbT = Files.blocksConfig.getConfigurationSection("tree").getKeys(true).size();
		int nbL = Files.blocksConfig.getConfigurationSection("leaves").getKeys(true).size();

		for (int i = 0; i < nbT; i++) {
			Location bLoc = (Location) Files.blocksConfig.get("tree." + i);
			Block bTree = bLoc.getBlock();

			bTree.setType(Material.LOG);
		}

		for (int i = 0; i < nbL; i++) {
			Location bLoc = (Location) Files.blocksConfig.get("leaves." + i);
			Block bTree = bLoc.getBlock();

			bTree.setType(Material.LEAVES);
		}

	}

	public void randomTree(int nb) {

		int max = Files.blocksConfig.getConfigurationSection("grass").getKeys(true).size();
		List<Integer> randInt = new ArrayList<Integer>();

		for (int i = 0; i <= nb; i++) {
			Random rand = new Random();
			int r = rand.nextInt(max);
			if (!randInt.contains(r)) {
				randInt.add(r);
			} else {
				i--;
			}
		}

		for (Integer x : randInt) {
			Location grassLoc = (Location) Files.blocksConfig.get("grass." + x);
			Location bLocY = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")), grassLoc.getX(),
					grassLoc.getY() + 1, grassLoc.getZ());
			Block bTree = bLocY.getBlock();
			Block bGrass = grassLoc.getBlock();

			if (bGrass.getType() == Material.GRASS || bGrass.getType() == Material.LONG_GRASS) {
				if (bTree.getType() == Material.AIR) {
					bTree.getWorld().generateTree(bLocY, TreeType.TREE);
				}
			}
		}
	}

	public void clearTree(Player p) {

		if (Files.blocksConfig.getConfigurationSection("tree").getKeys(true) != null) {
			int nbT = Files.blocksConfig.getConfigurationSection("tree").getKeys(true).size();

			for (int i = 0; i < nbT; i++) {
				Location bLoc = (Location) Files.blocksConfig.get("tree." + i);
				Block bTree = bLoc.getBlock();

				bTree.setType(Material.AIR);
			}
			p.sendMessage("§0[§4DHB§0] §7§oEffacement des arbres : §dOK §7§o!");
		}
		if (Files.blocksConfig.getConfigurationSection("leaves").getKeys(true) != null) {
			int nbL = Files.blocksConfig.getConfigurationSection("leaves").getKeys(true).size();

			for (int i = 0; i < nbL; i++) {
				Location bLoc = (Location) Files.blocksConfig.get("leaves." + i);
				Block bTree = bLoc.getBlock();

				bTree.setType(Material.AIR);
			}
			p.sendMessage("§0[§4DHB§0] §7§oEffacement des feuilles : §dOK §7§o!");
		}	
	}

	public void saveBlocksList() {

		int iT = 0;
		int iL = 0;
		int iG = 0;

		clearConfig(Files.blocksConfig, Files.blocksFile);

		for (Location loc : treeLocMap) {

			Files.blocksConfig.set("tree." + iT, loc);
			iT++;
		}
		for (Location loc : leavesLocMap) {

			Files.blocksConfig.set("leaves." + iL, loc);
			iL++;

		}
		for (Location loc : grassLocMap) {

			Files.blocksConfig.set("grass." + iG, loc);
			iG++;

		}
		saveConfig(Files.blocksConfig, Files.blocksFile);
	}

	public void addBlocksToList(int iX, int iY, int iZ) {

		Location checkTree = new Location(Bukkit.getWorld(Files.coordConfig.getString("world.name")), minX + iX,
				minY + iY, minZ + iZ);
		Block b = checkTree.getBlock();

		if (b.getType() == Material.LOG || b.getType() == Material.LOG_2) {
			treeLocMap.add(b.getLocation());
		}
		if (b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2) {
			leavesLocMap.add(b.getLocation());
		}
		if (b.getType() == Material.GRASS || b.getType() == Material.LONG_GRASS) {
			grassLocMap.add(b.getLocation());
		}
	}

	public void clearConfig(FileConfiguration config, File file) {

		for (String key : config.getKeys(false)) {
			config.set(key, null);
		}
		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveConfig(FileConfiguration config, File file) {

		try {
			config.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void scanMap() {

		removeAllTree = DHBMain.getInstance().getServer().getScheduler()
				.scheduleSyncRepeatingTask(DHBMain.getInstance(), new Runnable() {

					public void run() {

						for (int iY = 0; iY < cY; iY++) {
							for (int iX = 0; iX < cX; iX++) {
								for (int iZ = 0; iZ < cZ; iZ++) {

									count++;
									addBlocksToList(iX, iY, iZ);

								}
							}
						}

						y = y + cY;

						if (y == maxY && x >= maxX - cX && z >= maxZ - cZ) {

							for (Player p : Bukkit.getOnlinePlayers()) {
								if (DHBTeam.checkStaffTeamFromPlayer(p)) {

									p.sendMessage("§0[§4DHB§0] §7§oScan done ! Nombre de blocs scannés : §d" + count);

									saveBlocksList();
									p.sendMessage("§0[§4DHB§0] §7§oSauvegarde des arbres : §dOK §7§o!");
									p.sendMessage("§0[§4DHB§0] §7§oSauvegarde des blocks d'herbe : §dOK §7§o!");

								}
							}

							Bukkit.getServer().getScheduler().cancelTask(removeAllTree);

						} else if (y == maxY && x >= maxX - cX) {
							y = minY;
							x = minX;
							z = z + cZ;
						} else if (y == maxY) {
							y = minY;
							x = x + cX;
						}
					}
				}, 20, 20);
	}
}

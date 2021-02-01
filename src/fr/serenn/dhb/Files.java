package fr.serenn.dhb;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.serenn.dhb.colors.JavaColors;

public class Files {

	public static File confFile, coordFile, volcanoBossFile, blocksFile;
	public static FileConfiguration config, coordConfig, volcanoBossConfig, blocksConfig;

	public void createFiles() {

		confFile = new File(DHBMain.getInstance().getDataFolder(), "config.yml");
		coordFile = new File(DHBMain.getInstance().getDataFolder(), "coordinates.yml");
		File pathVolcanoBoss = new File(DHBMain.getInstance().getDataFolder(), File.separator + "BossDatabase");
		volcanoBossFile = new File(pathVolcanoBoss, File.separator + "volcanoBoss.yml");
		File pathBlocks = new File(DHBMain.getInstance().getDataFolder(), File.separator + "MapDatabase");
		blocksFile = new File(pathBlocks, File.separator + "blocksSave.yml");

		if (!confFile.exists()) {
			confFile.getParentFile().mkdirs();
			DHBMain.getInstance().saveResource("config.yml", false);
		} else
			DHBMain.getInstance().saveResource("config.yml", true);

		if (!coordFile.exists()) {
			coordFile.getParentFile().mkdirs();
			DHBMain.getInstance().saveResource("coordinates.yml", false);
		} else
			DHBMain.getInstance().saveResource("coordinates.yml", true);
		
		if (!volcanoBossFile.exists()) {
			volcanoBossFile.getParentFile().mkdirs();
			DHBMain.getInstance().saveResource("BossDatabase/volcanoBoss.yml", false);
		}
		
		if (!blocksFile.exists()) {
			blocksFile.getParentFile().mkdirs();
			DHBMain.getInstance().saveResource("MapDatabase/blocksSave.yml", false);
		}

		config = new YamlConfiguration();
		coordConfig = new YamlConfiguration();
		volcanoBossConfig = new YamlConfiguration();
		blocksConfig = new YamlConfiguration();

		try {
			config.load(confFile);
			System.out.println(
					JavaColors.Bright + JavaColors.White + "-----------------------------------" + JavaColors.Reset);
			System.out.println(JavaColors.Bright + JavaColors.White + "| " + JavaColors.Green + "OK	"
					+ JavaColors.Magenta + "Default Config            " + JavaColors.White + " |" + JavaColors.Reset);
			System.out.println(
					JavaColors.Bright + JavaColors.White + "-----------------------------------" + JavaColors.Reset);
			coordConfig.load(coordFile);
			System.out.println(JavaColors.Bright + JavaColors.White + "| " + JavaColors.Green + "OK	"
					+ JavaColors.Magenta + "Tp & Blocks Coordinates   " + JavaColors.White + " |" + JavaColors.Reset);
			System.out.println(
					JavaColors.Bright + JavaColors.White + "-----------------------------------" + JavaColors.Reset);
			blocksConfig.load(blocksFile);
			System.out.println(JavaColors.Bright + JavaColors.White + "| " + JavaColors.Green + "OK	"
					+ JavaColors.Magenta + "Blocks Locations          " + JavaColors.White + " |" + JavaColors.Reset);
			System.out.println(
					JavaColors.Bright + JavaColors.White + "-----------------------------------" + JavaColors.Reset);
			volcanoBossConfig.load(volcanoBossFile);
			System.out.println(JavaColors.Bright + JavaColors.White + "| " + JavaColors.Green + "OK	"
					+ JavaColors.Magenta + "Volcano Boss              " + JavaColors.White + " |" + JavaColors.Reset);
			System.out.println(
					JavaColors.Bright + JavaColors.White + "-----------------------------------" + JavaColors.Reset);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidConfigurationException e) {
			e.printStackTrace();
		}

	}
}

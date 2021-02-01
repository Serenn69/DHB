package fr.serenn.dhb.events;

import org.bukkit.Bukkit;
import org.bukkit.World;

import fr.serenn.dhb.Files;

public class WorldBorder {
	
	World w = Bukkit.getWorld(Files.coordConfig.getString("world.name"));
	org.bukkit.WorldBorder wb = w.getWorldBorder();
	
	public void createBorders() {
		
		wb.setCenter(Files.coordConfig.getDouble("lobby.x"), Files.coordConfig.getDouble("lobby.z"));
		wb.setCenter(164, 6);
		wb.setSize(499);
		
	}

	public void loadBorders() {
		
		wb.setSize(1, 360);
		wb.setWarningTime(5);
		wb.setDamageBuffer(0);
		wb.setDamageAmount(0.5);
		
	}	
	
	public void deleteBorders() {
		
		wb.setSize(499, 360);
		wb.setWarningTime(5);
		wb.setDamageBuffer(0);
		wb.setDamageAmount(0.5);
		
	}
}

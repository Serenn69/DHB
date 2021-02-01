package fr.serenn.dhb;

import org.bukkit.enchantments.Enchantment;

public class Enchants {

	public Enchantment chooseEnchant(String choice) {
		
		Enchantment enchant;
		
		switch (choice) {
		
		case "power" :
			enchant = Enchantment.ARROW_DAMAGE;
			return enchant;
			
		case "flame" :
			enchant = Enchantment.ARROW_FIRE;
			return enchant;
			
		case "infinity" :
			enchant = Enchantment.ARROW_INFINITE;
			return enchant;
			
		case "punch" :
			enchant = Enchantment.ARROW_KNOCKBACK;
			return enchant;
			
		case "fireaspect" :
			enchant = Enchantment.FIRE_ASPECT;
			return enchant;
			
		case "knockback" :
			enchant = Enchantment.KNOCKBACK;
			return enchant;
			
		case "sharpness" :
			enchant = Enchantment.DAMAGE_ALL;
			return enchant;
			
		case "protection" :
			enchant = Enchantment.PROTECTION_ENVIRONMENTAL;
			return enchant;
			
		case "explosionprotection" :
			enchant = Enchantment.PROTECTION_EXPLOSIONS;
			return enchant;
			
		case "fireprotectionfire" :
			enchant = Enchantment.PROTECTION_FIRE;
			return enchant;
			
		case "projectileprotection" :
			enchant = Enchantment.PROTECTION_PROJECTILE;
			return enchant;
			
		case "thorns" :
			enchant = Enchantment.THORNS;
			return enchant;
		}
		return null;
	}
}

package fr.serenn.dhb.events;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.serenn.dhb.DHBMain;
import fr.serenn.dhb.Teleport;
import fr.serenn.dhb.teams.DHBTeam;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class Teleportation implements Listener {

	@EventHandler
	public void onEntranceMine(PlayerMoveEvent e) {
		
		Player p = e.getPlayer();
		
		if (AreaLimits.checkTpArea(p, "in") != null) {
			
			Teleport.randomTpArea(p, "out", AreaLimits.checkTpArea(p, "in"));
			
		}
		if (AreaLimits.checkTpArea(p, "out") != null) {
			
			List<String> result = AreaLimits.checkTpArea(p, "out");
			
			Teleport.randomTpArea(p, "in", result);
			
			for (DHBTeam t : DHBMain.staffTeams) {
				for (Player s : Bukkit.getOnlinePlayers()) {
					if (t.hasPlayer(s)) {
						
						switch (result.get(0)) {
						
						case "volcano" :
							TextComponent msgVolc = new TextComponent( "§dVOLCAN" );
							msgVolc.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/teleport "+p.getName() ) );
							msgVolc.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Teleport on player in this area").create() ) );
							s.sendMessage("§0[§4DHB§0] "+p.getName()+" §7§ovient d'entrer dans la zone : "+msgVolc);
							break;
						
						case "iron" :
							TextComponent msgIron = new TextComponent( "§7MINE DE FER" );
							msgIron.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/teleport "+p.getName() ) );
							msgIron.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Teleport on player in this area").create() ) );
							s.sendMessage("§0[§4DHB§0] "+p.getName()+" §7§ovient d'entrer dans la zone : "+msgIron);
							break;
							
						case "exp" :
							TextComponent msgExp = new TextComponent( "§aMINE D'EXP" );
							msgExp.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/teleport "+p.getName() ) );
							msgExp.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Teleport on player in this area").create() ) );
							s.sendMessage("§0[§4DHB§0] "+p.getName()+" §7§ovient d'entrer dans la zone : "+msgExp);
							break;
							
						case "diam" :
							TextComponent msgDiam = new TextComponent( "§bMINE DE DIAMANT" );
							msgDiam.setClickEvent( new ClickEvent( ClickEvent.Action.RUN_COMMAND, "/teleport "+p.getName() ) );
							msgDiam.setHoverEvent( new HoverEvent( HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Teleport on player in this area").create() ) );
							s.sendMessage("§0[§4DHB§0] "+p.getName()+" §7§ovient d'entrer dans la zone : "+msgDiam);
							break;
						
						}
					}
				}
			}
		}
	}
}

package fr.serenn.dhb.news;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_14_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_14_R1.IChatBaseComponent;
import net.minecraft.server.v1_14_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_14_R1.PacketPlayOutChat;

public class Subtitle {

	public void displaySubtitle(String sub) {

		for (Player p : Bukkit.getOnlinePlayers()) {
			
			IChatBaseComponent barmsg = ChatSerializer
					.a("{\"text\":\"" + ChatColor.translateAlternateColorCodes('&', sub) + "\"}");
			PacketPlayOutChat bar = new PacketPlayOutChat(barmsg);
			//PacketPlayOutChat bar = new PacketPlayOutChat(barmsg, (byte) 2);
			((CraftPlayer) p).getHandle().playerConnection.sendPacket(bar);
		}
	}

}

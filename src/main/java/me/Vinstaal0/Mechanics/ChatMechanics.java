package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Minigame;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class ChatMechanics implements Listener {

    public ChatMechanics(Minigame plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void normalChat(AsyncPlayerChatEvent event) {

        event.setCancelled(true);

        Player player = event.getPlayer();

        String rawMessage = event.getMessage();

        // TODO Add prefix support
        String prefix = "";

        String message = ChatColor.GRAY + prefix + player.getDisplayName() + ": " + ChatColor.WHITE + rawMessage;

        Location loc = player.getLocation();

        for (Player p : event.getRecipients()) {

            int i = 0;

            if (p.getLocation().distanceSquared(loc) < 400) {
                p.sendMessage(message);
                i++;
            }

            if (i == 0) {
                player.sendMessage("Nobody can hear you ...");
            }
        }
    }

}
package me.Vinstaal0.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Mechanics.HealthMechanics;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class PlayerListener {

    public PlayerListener(Minigame plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onLogin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        HealthMechanics.updateHealth(player);
    }

    @EventHandler
    public void onLogout(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        HealthMechanics.updateHealth(player);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        HealthMechanics.updateHealth(player);
    }

    @EventHandler
    public void onInteract(InventoryClickEvent event) {
        Player player;

        if (event.getWhoClicked() instanceof Player) {
            player = (Player) event.getWhoClicked();

            HealthMechanics.updateHealth(player);

            HealthMechanics.updateHealth(player);
        }
    }

    @EventHandler
    public void onRegen(EntityRegainHealthEvent event) {
        Player player;

        event.setCancelled(true);

        if (event.getEntity() instanceof Player) {
            player = (Player) event.getEntity();

            HealthMechanics.updateHealth(player);
        }
    }

}

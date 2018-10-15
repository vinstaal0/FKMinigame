package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Minigame;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class EnergyMechanics implements Listener {

    public EnergyMechanics(Minigame plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    /**
     * Safety check, cancels mobs/players
     * dropping MC exp
     *
     * @param event
     * @since 1.0
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeathDropExp(EntityDeathEvent event) {
        event.setDroppedExp(0);
    }

    /**
     * Safety check, cancels players
     * picking up items
     *
     * @param event
     * @since 1.0
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemPickup(PlayerPickupItemEvent event) {
        if (!(event.getItem() instanceof ExperienceOrb) && (!(event.getItem() instanceof ExperienceOrb))) return;
        event.setCancelled(true);
    }

}
package me.Vinstaal0.Player;

import me.Vinstaal0.Mechanics.ItemMechanics.Durability;
import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Mechanics.HealthMechanics;
import org.bukkit.inventory.ItemStack;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class PlayerListener implements Listener {

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

    @EventHandler
    public void onPlayerRightClickEquip(PlayerInteractEvent event) {
        if(event.hasItem() && (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            ItemStack item = event.getItem();
            if(item.getType() == Material.POTION) { return; }
            Player p = event.getPlayer();

            if(item.getType() == Material.DIAMOND_HELMET || item.getType() == Material.DIAMOND_CHESTPLATE || item.getType() == Material.DIAMOND_LEGGINGS || item.getType() == Material.DIAMOND_BOOTS || item.getType() == Material.IRON_HELMET || item.getType() == Material.IRON_CHESTPLATE || item.getType() == Material.IRON_LEGGINGS || item.getType() == Material.IRON_BOOTS || item.getType() == Material.CHAINMAIL_HELMET || item.getType() == Material.CHAINMAIL_CHESTPLATE || item.getType() == Material.CHAINMAIL_LEGGINGS || item.getType() == Material.CHAINMAIL_BOOTS || item.getType() == Material.GOLDEN_HELMET || item.getType() == Material.GOLDEN_CHESTPLATE || item.getType() == Material.GOLDEN_LEGGINGS || item.getType() == Material.GOLDEN_BOOTS || item.getType() == Material.LEATHER_HELMET || item.getType() == Material.LEATHER_CHESTPLATE || item.getType() == Material.LEATHER_LEGGINGS || item.getType() == Material.LEATHER_BOOTS) {
                if(!ItemMechanics.isArmor(item)) {
                    event.setCancelled(true);
                    event.setUseItemInHand(Event.Result.DENY);
                    p.updateInventory();
                    return;
                }
            } else {
                return;
            }

            if(p.getInventory().getItem(ItemMechanics.getRespectiveArmorSlot(item)) != null) {
                return;
            }
            event.setCancelled(true);
            event.setUseItemInHand(Event.Result.DENY);
            p.updateInventory();
        }
    }

    @EventHandler
    public void onItemDamageEvent(PlayerItemDamageEvent event) {

        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        player.sendMessage(ChatColor.DARK_PURPLE + "TRIGGER");

        if (!Durability.hasCustomDurability(item)) {
            return;
        }

        try {
            Durability.damageItem(player, item, 1);
        } catch (IndexOutOfBoundsException e) {
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            player.sendMessage("Your " + item.getItemMeta().getDisplayName() + " broke!");
            event.setDamage(item.getType().getMaxDurability());
        }

        HealthMechanics.updateHealth(player);

    }
}

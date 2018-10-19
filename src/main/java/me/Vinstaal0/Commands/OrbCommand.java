package me.Vinstaal0.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Vinstaal0.Mechanics.ItemMechanics.Items.GeneralItem;
import org.bukkit.inventory.PlayerInventory;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class OrbCommand implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;

        if (sender instanceof Player) {
            player = (Player) sender;
        }

        ItemStack item = player.getItemInHand();

        GeneralItem g = new GeneralItem();

        ItemStack newItem = g.rerollStats(item);

        PlayerInventory inventory = player.getInventory();

        try {
            for (int i = 1; i <= 35; i++) {

                boolean match = false;

                if(inventory.getItem(i).getType() != null) {
                    if(inventory.getItem(i).getType() == item.getType()) {
                        if(inventory.getItem(i).getItemMeta().getDisplayName().equalsIgnoreCase(item.getItemMeta().getDisplayName())) {
                            if(inventory.getItem(i).getItemMeta().getLore().get(0).equalsIgnoreCase(item.getItemMeta().getLore().get(0))) {
                                match = true;
                            }
                        }
                    }
                }

                if (match) {
                    player.getInventory().setItem(i, newItem);
                }
            }
        } catch (NullPointerException ignored) {}

        player.updateInventory();

        return true;
    }

}
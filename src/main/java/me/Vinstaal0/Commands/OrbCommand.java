package me.Vinstaal0.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Vinstaal0.Mechanics.Items.GeneralItem;

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

        System.out.println("rerolled item = " + newItem);

        player.getInventory().remove(item);

        player.getInventory().addItem(newItem);

        player.updateInventory();

        return true;
    }

}
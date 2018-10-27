package me.Vinstaal0.Commands;


import me.Vinstaal0.Mechanics.ItemMechanics.Durability;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import org.bukkit.inventory.ItemStack;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;

        if (sender instanceof Player) {
            player = (Player) sender;
        }

        int damage = 10;

        if (args.length > 0) {
            damage = Integer.valueOf(args[0]);
        }

        ItemStack item = player.getInventory().getItemInMainHand();

        try {
            Durability.damageItem(player, item, damage);
        } catch (IndexOutOfBoundsException e) {
            player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
            player.playSound(player.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
            player.sendMessage(item.getItemMeta().getLore().toString());
        }

        return true;
    }
}
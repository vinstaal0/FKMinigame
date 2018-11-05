package me.Vinstaal0.Mechanics.ItemMechanics.Commands;

import me.Vinstaal0.Mechanics.ItemMechanics.Items.GenericItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;

/**
 * Created by Vinstaal0 on 29-10-2018.
 */
public class CreateOrbCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player player = (Player) sender;

        int amount = 1;

        if (args.length > 0) {
            amount = Integer.parseInt(args[0]);
        }

        ItemStack item = GenericItem.createOrbOfAlt(amount);

        HashMap<Integer, ItemStack> overflow = player.getInventory().addItem(item);

        if (!overflow.isEmpty()) {
            player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
        }

        player.updateInventory();

        return false;
    }
}

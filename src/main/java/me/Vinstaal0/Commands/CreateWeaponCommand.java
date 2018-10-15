package me.Vinstaal0.Commands;

import java.util.HashMap;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Mechanics.Items.Weapon;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class CreateWeaponCommand implements CommandExecutor {

    @SuppressWarnings("unused")
    private Plugin plugin = Minigame.getPlugin();

    // CreateItem tier rarity slot
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("createweapon")) {
            if (args.length == 0) {

                ItemStack item = new Weapon().getItem();

                HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                overflow = player.getInventory().addItem(item);

                player.updateInventory();

                if (!overflow.isEmpty()) {
                    player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                }

                return true;
            } else if (args.length == 1) {
                try {
                    Tier tier = Tier.getTier(args[0]);

                    ItemStack item = new Weapon(tier).getItem();

                    HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                    overflow = player.getInventory().addItem(item);

                    if (!overflow.isEmpty()) {
                        player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                    }

                    player.updateInventory();

                } catch (IllegalArgumentException e) {
                    player.sendMessage("Invalid arguments");
                }
                return true;
            } else if (args.length == 2) {
                try {
                    Tier tier = Tier.getTier(args[0]);

                    Rarity rarity = Rarity.getRarity(args[1]);

                    ItemStack item = new Weapon(tier, rarity).getItem();

                    HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                    overflow = player.getInventory().addItem(item);

                    if (!overflow.isEmpty()) {
                        player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                    }

                    player.updateInventory();

                } catch (IllegalArgumentException e) {
                    player.sendMessage("Invalid arguments");
                }
                return true;
            } else if (args.length == 3) {
                try {

                    Tier tier = Tier.getTier(args[0]);

                    Rarity rarity = Rarity.getRarity(args[1]);

                    int slot = 0;

                    try {
                        slot = Integer.parseInt(args[2]);
                    } catch (NumberFormatException e) {
                        player.sendMessage("Invalid arguments");
                    }

                    ItemStack item = new Weapon(tier, rarity, slot).getItem();

                    HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                    overflow = player.getInventory().addItem(item);

                    if (!overflow.isEmpty()) {
                        player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                    }

                    player.updateInventory();

                } catch (IllegalArgumentException e) {
                    player.sendMessage("Invalid arguments");
                }
                return true;
            } else if (args.length > 3) {
                player.sendMessage("Invalid arguments");
            }

        }

        return false;
    }

}
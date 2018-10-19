package me.Vinstaal0.Commands;


import java.util.HashMap;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Mechanics.ItemMechanics.Items.Armour;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class CreateArmourCommand implements CommandExecutor {

    private Plugin plugin = Minigame.getPlugin();

    // CreateItem tier rarity slot
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("createarmour")) {
            if (args.length == 0) {

                Armour armour = new Armour();

                ItemStack item = armour.getItem();

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

                    Armour armour = new Armour(tier);

                    ItemStack item = armour.getItem();

                    HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                    overflow = player.getInventory().addItem(item);

                    player.updateInventory();

                    if (!overflow.isEmpty()) {
                        player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                    }
                } catch (IllegalArgumentException e) {
                    player.sendMessage("Invalid arguments");
                }
                return true;
            } else if (args.length == 2) {
                try {
                    Tier tier = Tier.getTier(args[0]);

                    Rarity rarity = Rarity.getRarity(args[1]);

                    Armour armour = new Armour(tier, rarity);

                    ItemStack item = armour.getItem();

                    HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                    overflow = player.getInventory().addItem(item);

                    player.updateInventory();

                    if (!overflow.isEmpty()) {
                        player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                    }

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

                    Armour armour = new Armour(tier, rarity, slot);

                    ItemStack item = armour.getItem();

                    HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

                    overflow = player.getInventory().addItem(item);

                    player.updateInventory();

                    if (!overflow.isEmpty()) {
                        player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
                    }

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


    /**
     * @return the plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * @param plugin the plugin to set
     */
    public void setPlugin(Plugin plugin) {
        this.plugin = plugin;
    }

}
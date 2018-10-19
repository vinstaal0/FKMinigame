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
import me.Vinstaal0.Mechanics.ItemMechanics.Items.Weapon;
import me.Vinstaal0.Utility.EnumHelp;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class CreateSetCommand implements CommandExecutor {

    private Plugin plugin = Minigame.getPlugin();

    // CreateItem tier rarity slot
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player;

        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            sender.sendMessage("You need to be a player to issue this command");
            return false;
        }

        Tier tier = null;

        if (args.length == 0) {
            tier = EnumHelp.randomEnum(Tier.class);
        } else if (args[0] == "T1") {
            tier = Tier.ONE;
        } else if (args[0] == "T2") {
            tier = Tier.TWO;
        } else if (args[0] == "T3") {
            tier = Tier.TREE;
        } else if (args[0] == "T4") {
            tier = Tier.FOUR;
        } else if (args[0] == "T5") {
            tier = Tier.FIVE;
        } else {
            tier = EnumHelp.randomEnum(Tier.class);
        }

        ItemStack helmet = new Armour(tier, 1).getItem();
        ItemStack chestplate = new Armour(tier, 2).getItem();
        ItemStack leggings = new Armour(tier, 3).getItem();
        ItemStack boots = new Armour(tier, 4).getItem();

        ItemStack weapon = new Weapon(tier).getItem();

        HashMap<Integer, ItemStack> overflow = new HashMap <Integer, ItemStack>();

        overflow = player.getInventory().addItem(helmet, chestplate, leggings, boots, weapon);

        if (!overflow.isEmpty()) {
            player.sendMessage("Cannot add " + overflow.toString() + " to the inventory!");
        }

        return true;

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
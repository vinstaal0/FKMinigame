package me.Vinstaal0.Mechanics.Items;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Currency {

    public static ItemStack createGem() {
        ItemStack item = new ItemStack(Material.EMERALD);
        ItemMeta im = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        im.setDisplayName(ChatColor.GREEN + "Gem");
        lore.add(ChatColor.GRAY + "The currency of FallingKingdom");
        im.setLore(lore);

        item.setItemMeta(im);

        return item;
    }

    public static ItemStack createDiamond() {
        ItemStack item = new ItemStack(Material.DIAMOND);
        ItemMeta im = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        im.setDisplayName(ChatColor.AQUA + "Diamond");
        lore.add(ChatColor.GRAY + "The premium currency of [TBD]");
        im.setLore(lore);

        item.setItemMeta(im);

        return item;
    }

    public static ItemStack createBankNote(int value) {
        ItemStack item = new ItemStack(Material.PAPER);
        ItemMeta im = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        im.setDisplayName(ChatColor.WHITE + "Bank note");
        lore.add(ChatColor.GRAY + "Value: " + value);
        im.setLore(lore);

        item.setItemMeta(im);

        return item;
    }

}
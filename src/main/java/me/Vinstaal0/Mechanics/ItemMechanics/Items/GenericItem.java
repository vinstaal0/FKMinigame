package me.Vinstaal0.Mechanics.ItemMechanics.Items;

import me.Vinstaal0.Utility.Tier;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class GenericItem {

    public static ItemStack createOrbOfAlt() {
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta im = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        im.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Orb of alteration");
        lore.add(ChatColor.GRAY + "Randomizes bonus stats of selected equipment");
        im.setLore(lore);

        item.setItemMeta(im);

        return item;
    }

    public static ItemStack createEnchant(Tier tier) {

        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.GRAY + "");
        im.setLore(lore);

        String name;

        switch(tier) {
            case ONE :
                name = ChatColor.GRAY + "Enchant T1 equipment";
                break;

            case TWO :
                name = ChatColor.GREEN + "Enchant T2 equipment";
                break;

            case TREE :
                name = ChatColor.AQUA + "Enchant T3 equipment";
                break;

            case FOUR :
                name = ChatColor.DARK_PURPLE + "Enchant T4 equipment";
                break;

            case FIVE :
                name = ChatColor.YELLOW + "Enchant T5 equipment";
                break;

            default : return null;
        }

        im.setDisplayName(name);

        item.setItemMeta(im);

        return item;
    }
}
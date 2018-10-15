package me.Vinstaal0.Mechanics.Items;

import me.Vinstaal0.Utility.Tier;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class GenericItem {

    public static ItemStack createOrbOfAlt() {
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta im = item.getItemMeta();

        im.setDisplayName(ChatColor.BOLD + ChatColor.LIGHT_PURPLE + "Orb of alteration");
        im.setLore(ChatColor.GRAY + "Randomizes bonus stats of selected equipment");

        item.setItemMeta(im);

        return item;
    }

    public static ItemStack createEnchant(Tier tier) {

        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta im = item.getItemMeta();

        String name;

        switch(tier) {
            case ONE :
                name = ChatColor.GRAY + "Enchant T1 equipment";

            case TWO :
                name = ChatColor.GREEN + "Enchant T2 equipment";

            case TREE :
                name = ChatColor.AQUA + "Enchant T3 equipment";

            case FOUR :
                name = ChatColor.DARK_PURPLE + "Enchant T4 equipment";

            case FIVE :
                name = ChatColor.YELLOW + "Enchant T5 equipment";

            default : return null;
        }

        im.setDisplayName(name);
        im.setLore(ChatColor.GRAY + "");
    }
}
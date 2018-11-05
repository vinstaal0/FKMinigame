package me.Vinstaal0.Mechanics.ItemMechanics.Items;

import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
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

    public static ItemStack createOrbOfAlt(int amount) {
        ItemStack item = new ItemStack(Material.MAGMA_CREAM);
        ItemMeta im = item.getItemMeta();

        List<String> lore = new ArrayList<String>();

        im.setDisplayName(ChatColor.BOLD + "" + ChatColor.LIGHT_PURPLE + "Orb of alteration");
        lore.add(ChatColor.GRAY + "Randomizes bonus stats of selected equipment");
        lore.add(ChatColor.GRAY + "Click on gear to use");
        im.setLore(lore);

        item.setItemMeta(im);

        item.setAmount(amount);

        return item;
    }

    public static ItemStack createEnchant(Tier tier, int amount) {

        ItemStack item = new ItemStack(Material.MAP);
        ItemMeta im = item.getItemMeta();
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.RED + "+5% DMG " + ChatColor.GRAY + "- OR - " + ChatColor.RED + "+5% HP " + ChatColor.GRAY + "- AND -");
        lore.add(ChatColor.RED + "+10% HP REGEN " + ChatColor.GRAY + "- OR -");
        lore.add(ChatColor.RED + "+1% ENERGY REGEN");
        lore.add(ChatColor.ITALIC + "" + ChatColor.GRAY + "Item will VANISH if enchant above +3 FAILS");
        im.setLore(lore);

        String name;

        switch(tier) {
            case ONE :
                name = ChatColor.GRAY + "Enchant Wood/Leather equipment";
                break;

            case TWO :
                name = ChatColor.GREEN + "Enchant Stone/Chainmail equipment";
                break;

            case TREE :
                name = ChatColor.AQUA + "Enchant Steel equipment";
                break;

            case FOUR :
                name = ChatColor.DARK_PURPLE + "Enchant Ivory equipment";
                break;

            case FIVE :
                name = ChatColor.YELLOW + "Enchant Gold equipment";
                break;

            default : return null;
        }

        im.setDisplayName(name);

        item.setItemMeta(im);

        item.setAmount(amount);

        return item;
    }

    public static ItemStack createScrap(Tier tier, int amount) throws NullPointerException {

        Material mat = null;
        String name = null;
        List<String> lore = null;

        switch(tier) {

            case ONE:
                name = ChatColor.GRAY + "Leather Scrap";
                mat = Material.LEATHER;
                lore.add(ChatColor.GRAY + "Recovers 3% Durability of Wooden/Leather Gear");
                break;
            case TWO:
                name = ChatColor.GREEN + "Chainmail Scrap";
                mat = Material.IRON_BARS;
                lore.add(ChatColor.GRAY + "Recovers 3% Durability of Stone/Chainmail Gear");
                break;
            case TREE:
                name = ChatColor.AQUA + "Steel Scrap";
                mat = Material.LIGHT_GRAY_DYE;
                lore.add(ChatColor.GRAY + "Recovers 3% Durability of Steel Gear");
                break;
            case FOUR:
                name = ChatColor.DARK_PURPLE + "Ivory Scrap";
                mat = Material.LIGHT_BLUE_DYE;
                lore.add(ChatColor.GRAY + "Recovers 3% Durability of Ivory Gear");
                break;
            case FIVE:
                name = ChatColor.YELLOW + "Gold Scrap";
                mat = Material.DANDELION_YELLOW;
                lore.add(ChatColor.GRAY + "Recovers 3% Durability of Gold Gear");
                break;
        }

        if (mat == null || name.isEmpty() || lore.isEmpty()) {
            throw new NullPointerException();
        }

        ItemStack item = new ItemStack(mat);
        ItemMeta im = item.getItemMeta();

        im.setDisplayName(name);
        im.setLore(lore);

        item.setItemMeta(im);

        item.setAmount(amount);

        return item;
    }
}
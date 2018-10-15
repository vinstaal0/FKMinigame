package me.Vinstaal0.Mechanics.Items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Vinstaal0.Mechanics.ItemMechanics;
import me.Vinstaal0.Utility.Rarity;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class GeneralItem {

    public boolean addRarity(List<String> lore, Rarity rarity) {

        try {
            switch(rarity) {
                case COMMON :
                    lore.add(ChatColor.GRAY + "" + ChatColor.ITALIC + "Common");
                    break;
                case UNCOMMON :
                    lore.add(ChatColor.GREEN + "" + ChatColor.ITALIC + "Uncommon");
                    break;
                case RARE :
                    lore.add(ChatColor.AQUA + "" + ChatColor.ITALIC + "Rare");
                    break;
                case UNIQUE :
                    lore.add(ChatColor.DARK_PURPLE + "" + ChatColor.ITALIC + "Unique");
                    break;
                case ANCIENT :
                    lore.add(ChatColor.YELLOW + "" + ChatColor.ITALIC + "Ancient");
                    break;
                default :
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public ItemStack rerollStats(ItemStack item) {

        if (ItemMechanics.isArmor(item) || ItemMechanics.isWep(item)) {

            if (ItemMechanics.isArmor(item)) {

                ItemMeta im = item.getItemMeta();

                List<String> lore = rerollArmourStats(item, im.getLore())
                        ;
                im.setLore(null);
                im.setLore(lore);

                item.setItemMeta(im);

                return item;

            } else if (ItemMechanics.isWep(item)) {

                ItemMeta im = item.getItemMeta();

                List<String> lore = rerollWeaponStats(item, im.getLore());

                im.setLore(null);
                im.setLore(lore);

                item.setItemMeta(im);

                return item;
            }

        } else {
            return item;
        }
        return item;
    }

    public List<String> rerollWeaponStats(ItemStack item, List<String> lore) {

        String dmg = lore.get(0);

        String rarity = lore.get(lore.size() - 1);

        lore.removeAll(lore);

        lore.add(dmg);

        Weapon.addRandomStats(lore, ItemMechanics.getTier(item));

        lore.add(rarity);

        return lore;

    }

    public List<String> rerollArmourStats(ItemStack item, List<String> lore) {

        String hp = lore.get(0);
        String armor = lore.get(1);
        String energy = lore.get(2);

        String rarity = lore.get(lore.size() - 1);

        lore.removeAll(lore);

        lore.add(hp);
        lore.add(armor);
        lore.add(energy);

        Armour.addRandomStats(lore, ItemMechanics.getTier(item));

        lore.add(rarity);

        return lore;

    }


    public ItemStack enchant(Player player, ItemStack item) {

        if (ItemMechanics.isArmor(item) || ItemMechanics.isWep(item)) {

            if (ItemMechanics.isArmor(item)) {

                ItemMeta im = item.getItemMeta();

                List<String> lore = enchantArmour(item, im.getLore());

                im.setLore(null);
                im.setLore(lore);

                item.setItemMeta(im);

                ItemStack newItem = enchantName(player, item);

                return newItem;

            } else if (ItemMechanics.isWep(item)) {

                ItemMeta im = item.getItemMeta();

                List<String> lore = enchantWeapon(item, im.getLore());

                im.setLore(null);
                im.setLore(lore);

                item.setItemMeta(im);

                ItemStack newItem = enchantName(player, item);

                return newItem;
            }

        } else {
            return item;
        }
        return item;
    }

    private List<String> enchantArmour(ItemStack item, List<String> lore) {

        String name = item.getItemMeta().getDisplayName();

        String hp = lore.get(0);
        String armour = lore.get(1);
        String energy = lore.get(2);

        Double hpv = ItemMechanics.getStat(hp) * 1.05;

        int energyv = 0;

        if (energy.contains("ENERGY")) {
            energyv = ItemMechanics.getStat(energy) + 1;
        } else if (energy.contains("REGEN")){
            Double x = ItemMechanics.getStat(energy) * 1.05;
            energyv = x.intValue();
        }

        List<String> tempLore = new ArrayList<String>();

        tempLore.add(ChatColor.RED + "HP: +" + Integer.valueOf(hpv.intValue()) + "");
        tempLore.add(armour);
        if (energy.contains("ENERGY")) {
            tempLore.add(ChatColor.RED + "ENERGY: +" + energyv);
        } else if (energy.contains("REGEN")){
            tempLore.add(ChatColor.RED + "HP REGEN: +" + energyv);
        }

        for (int x = 3; x < lore.size() - 1 + 10; x++) {
            try {
                tempLore.add(lore.get(x));
            } catch (IndexOutOfBoundsException ignored) {}

        }

        ItemMeta im = item.getItemMeta();

        im.setDisplayName(name.toString());

        lore.removeAll(lore);
        lore.addAll(tempLore);

        item.setItemMeta(im);

        return lore;
    }

    private List<String> enchantWeapon(ItemStack item, List<String> lore) {

        String name = item.getItemMeta().getDisplayName();

        Double lowDMG = ItemMechanics.getFromLineDamage(item, 0)[0] * 1.05;
        Double highDMG = ItemMechanics.getFromLineDamage(item, 0)[1] * 1.05;

        List<String> tempLore = new ArrayList<String>();

        tempLore.add(ChatColor.RED + "DMG: " + Integer.valueOf(lowDMG.intValue()) + " - " + Integer.valueOf(highDMG.intValue()));

        for (int x = 1; x < lore.size() - 1 + 10; x++) {
            try {
                tempLore.add(lore.get(x));
            } catch (IndexOutOfBoundsException ignored) {}

        }
        ItemMeta im = item.getItemMeta();

        im.setDisplayName(name);

        lore.removeAll(lore);
        lore.addAll(tempLore);

        item.setItemMeta(im);

        return lore;
    }

    private ItemStack enchantName(Player player, ItemStack item) {

        ItemMeta im = item.getItemMeta();

        String name = im.getDisplayName();

//		StringBuilder sb = new StringBuilder(name);

        StringBuilder test = new StringBuilder();

        if (name.contains("[") && name.contains("]")) {
            String sub = name.substring(name.indexOf("[") + 1, name.indexOf("]"));

            int level = Integer.parseInt(sub);

//			sb.setLength(name.indexOf("[") - 1);

            String subString = "";

            if (level < 10) {
                subString = name.substring(6);
            } else {
                subString = name.substring(7);
            }



            int display = level + 1;

            test.append(ChatColor.RED + "[" + display + "] ");

            test.append(subString);

//			sb.append(" [" + display + "]");

        } else {

            test.append(ChatColor.RED + "[1] ");

            test.append(name);
//			sb.append(" [1]");
        }

//		im.setDisplayName(sb.toString());
        im.setDisplayName(test.toString());

        item.setItemMeta(im);

        return item;
    }

}
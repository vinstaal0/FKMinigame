package me.Vinstaal0.Mechanics.ItemMechanics;

import me.Vinstaal0.Mechanics.ItemMechanics.Items.GeneralItem;
import me.Vinstaal0.Minigame;
import me.Vinstaal0.Utility.Tier;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;

/**
 * Created by Vinstaal0 on 19-10-2018.
 */
public class Durability {

    final Plugin plugin;

    private static final int durabilityT1 = 1000;
    private static final int durabilityT2 = 1250;
    private static final int durabilityT3 = 1500;
    private static final int durabilityT4 = 1750;
    private static final int durabilityT5 = 2000;

    public Durability(Minigame plugin) {
        this.plugin = plugin;
    }

    public static int getMaxDurability(ItemStack item) {
        Tier tier = ItemMechanics.getTier(item);

        return getMaxDurability(tier);
    }

    public static int getMaxDurability(Tier tier) {
        switch(tier) {

            case ONE:
                return durabilityT1;
            case TWO:
                return durabilityT2;
            case TREE:
                return durabilityT3;
            case FOUR:
                return durabilityT4;
            case FIVE:
                return durabilityT5;
        }

        return 1000; // Standard durability
    }

    public static boolean hasCustomDurability(ItemStack item) {

        ItemMeta im = item.getItemMeta();
        List<String> lore = im.getLore();

        for (String line : lore) {
            if (line.contains("Durability")) {
                return true;
            }
        }

        return false;

    }

    public static void removeCustomDurability(Player player, ItemStack item, int durabilityReduction) {

        ItemMeta im = item.getItemMeta();
        List<String> oldLore = im.getLore();

        if (!hasCustomDurability(item)) {
            GeneralItem g = new GeneralItem();

            g.addCustomDurability(oldLore, ItemMechanics.getTier(item));
        }

        int currentDurability = getDurability(oldLore);
        int newDurability = currentDurability - durabilityReduction;

        List<String> newLore = oldLore.subList(0, (oldLore.size() - 1));

        newLore.add(ChatColor.GRAY + "Durability: " + newDurability + "/" + getMaxDurability(ItemMechanics.getTier(item)));

        im.setLore(newLore);

        item.setItemMeta(im);

        player.updateInventory();
    }

    public static int getDurability(ItemStack item) {

        return getDurability(item.getItemMeta().getLore());
    }

    public static int getDurability(List<String> lore) {

        for (String s : lore) {
            if (s.contains("Durability")) {
                return getDurability(s);
            }

        }

        return 500;
    }

    public static int getDurability(String line) {

        String oldString = ChatColor.stripColor(line);
        String newString = oldString.substring(oldString.indexOf(":") + 2);

        String durability = newString.substring(0, newString.indexOf("/"));

        return Integer.valueOf(durability);
    }

    public static void damageItem(Player player, ItemStack item, int damage) throws IndexOutOfBoundsException {

        float durability = getDurability(item);

        double percentage = durability / (float) getMaxDurability(item);

//        player.sendMessage("Durability = " + durability + " -" + percentage);

        short newDura = (short) (item.getType().getMaxDurability() - (item.getType().getMaxDurability() * percentage));

//        player.sendMessage("newDura = " + newDura);

        if (newDura > item.getType().getMaxDurability()) {
            throw new IndexOutOfBoundsException("Durability exceeds max possible durability of item");
        }

        removeCustomDurability(player, item, damage);

        Damageable d = (Damageable) item.getItemMeta();

//        player.sendMessage("new Damage? " + d.getDamage());

        d.setDamage(newDura - 1);

//        player.sendMessage("new Damage 2 " + d.getDamage());

        ItemMeta nim = (ItemMeta) d;

        item.setItemMeta(nim);

        player.updateInventory();
    }
}

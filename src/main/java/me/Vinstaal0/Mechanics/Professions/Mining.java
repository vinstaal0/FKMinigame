package me.Vinstaal0.Mechanics.Professions;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

import static org.bukkit.Material.EMERALD_ORE;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Mining {

    /**
     * Gets the amount of EXP given when an ore is mined
     *
     */
    public static int getOreEXP(ItemStack item) {
        Material m = item.getType();

        if (m == Material.COAL_ORE) {
            return 90 + new Random().nextInt(35);
        }
        if (m == Material.EMERALD_ORE) {
            return 275 + new Random().nextInt(35);
        }
        if (m == Material.IRON_ORE) {
            return 460 + new Random().nextInt(80);
        }
        if (m == Material.DIAMOND_ORE) {
            return 820 + new Random().nextInt(40);
        }
        if (m == Material.GOLD_ORE) {
            return 1025 + new Random().nextInt(55);
        }
        return 1;
    }

    public static int getEXPNeeded(int level) {
        if (level == 1) {
            return 176; // Formula break with 1
        }
        if (level == 0) {
            return 0;
        }

        int previous_level = level - 1;
        return (int) (Math.pow((previous_level), 2) + ((previous_level) * 20) + 150 + ((previous_level) * 4)
                + getEXPNeeded((previous_level)));
    }
}
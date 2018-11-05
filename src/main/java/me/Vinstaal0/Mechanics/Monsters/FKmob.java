package me.Vinstaal0.Mechanics.Monsters;

import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
import me.Vinstaal0.Mechanics.ItemMechanics.Items.Weapon;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public interface FKmob {

    Zombie zombie = null;

    ItemStack helmet = null;
    ItemStack chestPlate = null;
    ItemStack leggings = null;
    ItemStack boots = null;

    Tier tier = null;
    Rarity rarity = null;

    ItemStack weapon = null;

    void giveArmour();

    void giveArmour(Tier tier);

    void giveArmour(Rarity rarity);

    /*
     * 1 = Nothing
     * 2 = chestplate
     * 3 = leggings
     * 4 = boots
     * 5 = chest and legs
     * 6 = legs and boots
     * 7 = chest and boots
     * 8 = chest, legs and boots
     */
    void giveArmour(int type);

    void giveArmour(Tier tier, Rarity rarity);

    void giveArmour(Tier tier, Rarity rarity, int type);

    public void giveArmourRandom(Tier tier, int type);

    public void giveWeapon();

    public void giveWeapon(Tier tier);

    public void giveWeapon(Rarity rarity);

    public void giveWeapon(Weapon.Type type);

    public void giveWeapon(Tier tier, Rarity rarity);

    public void giveWeapon(Tier tier, Rarity rarity, Weapon.Type type);

    public void setName(String name);

    public void setName(Tier tier, Rarity rarity);

    void setHealth();

    public void setHealth(Double hp);

}
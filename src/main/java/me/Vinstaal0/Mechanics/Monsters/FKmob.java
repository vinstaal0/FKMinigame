package me.Vinstaal0.Mechanics.Monsters;

import me.Vinstaal0.Mechanics.Items.Weapon;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public interface FKmob {

    public void giveArmour();

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
    public void giveArmour(int type);

    public void giveWeapon();

    public void giveWeapon(Weapon.Type type);

    public void setName(String name);

}
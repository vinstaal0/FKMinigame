package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Mechanics.ItemMechanics.Items.Weapon;
import me.Vinstaal0.Mechanics.Monsters.Base.FKZombie;
import me.Vinstaal0.Minigame;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class MonsterMechanics {

    Plugin plugin;
    World world;

    public MonsterMechanics(Minigame plugin) {
        this.plugin = plugin;
        this.world = plugin.getServer().getWorlds().get(0);

        this.world.setMonsterSpawnLimit(0);

    }

    public boolean spawnZombie(Location loc) {
        try {
            new FKZombie(world, loc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean spawnZombie(Location loc, Tier tier, Rarity rarity) {
        try {
            new FKZombie(world, loc, tier, rarity);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean spawnZombie(Location loc, Tier tier, Rarity rarity, int armourType, Weapon.Type weaponType) {
        try {
            new FKZombie(world, loc, tier, rarity, armourType, weaponType);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
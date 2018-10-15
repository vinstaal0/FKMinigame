package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Minigame;
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

    public boolean spawnZombie(Location loc, Tier tier) {
        try {
            new FKZombie(world, loc, tier);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
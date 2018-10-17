package me.Vinstaal0;

import me.Vinstaal0.Commands.*;
import me.Vinstaal0.Mechanics.*;
import me.Vinstaal0.Player.PlayerListener;
import me.Vinstaal0.Player.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Minigame extends JavaPlugin {

    private static Plugin plugin;
    public static MonsterMechanics monsterMechanics;

    @Override
    public void onEnable() {

        this.getCommand("createarmour").setExecutor(new CreateArmourCommand());
        this.getCommand("createweapon").setExecutor(new CreateWeaponCommand());
        this.getCommand("test").setExecutor(new Test());
        this.getCommand("gm").setExecutor(new GMCommand());
        this.getCommand("getstats").setExecutor(new GetSatsCommand());
        this.getCommand("spawnmob").setExecutor(new SpawnMobCommand());
        this.getCommand("createset").setExecutor(new CreateSetCommand());
        this.getCommand("gl").setExecutor(new GLCommand());
        this.getCommand("orb").setExecutor(new OrbCommand());
        this.getCommand("enchant").setExecutor(new EnchantCommand());

        new PlayerStats(this);
        new PlayerListener(this);
        new EnergyMechanics(this);
        new DamageMechanics(this);
        new ChatMechanics(this);
        new HealthMechanics(this);

        monsterMechanics = new MonsterMechanics(this);

    }

    @Override
    public void onDisable() {
        HealthMechanics.updateHealth();

        Iterator it = HealthMechanics.bbstore.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();

            BossBar bb = (BossBar) pair.getValue();
            bb.removePlayer(Bukkit.getPlayer((UUID)pair.getKey()));

        }

    }

    /**
     * @return the plugin
     */
    public static Plugin getPlugin() {
        return plugin;
    }

    /**
     * @param plugin the plugin to set
     */
    public static void setPlugin(Plugin plugin) {
        Minigame.plugin = plugin;
    }
}

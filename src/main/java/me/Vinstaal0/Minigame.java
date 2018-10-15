package me.Vinstaal0;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

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
        this.getCommand("getstats").setExecutor(new GetStatsCommand());
        this.getCommand("spawnmob").setExecutor(new SpawnMobCommand());
        this.getCommand("createset").setExecutor(new CreateSetCommand());
        this.getCommand("gl").setExecutor(new GlCommand());
        this.getCommand("orb").setExecutor(new OrbCommand());
        this.getCommand("enchant").setExecutor(new EnchantCommand());

        new PlayerStats(this);
        new PlayerListener(this);
        new EnergyMechanics(this);
        new DamageMechanics(this);
        new ChatMechanics(this);
        new HealthMechanics(this);

        monsterMechanics = new MonsterMechanics(this);

        BukkitScheduler scheduler = getServer().getScheduler();

        scheduler.scheduleSyncDelayedTask(this, new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers() != null) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
//						try {
//							PlayerStats.getInCombat(player.getUniqueId());
//						} catch (NullPointerException e) {
//							PlayerStats.setInCombat(player.getUniqueId(), false);
//						}

                        if (PlayerStats.getInCombat(player.getUniqueId()) == false) {
                            HealthMechanics.regenPlayer(player);
                        }
                        HealthMechanics.updateHealth(player);
                    }
                }
            }
        }, 30L);

        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {

            public void run() {

                if (Bukkit.getOnlinePlayers() != null) {
                    for (Player player : Bukkit.getOnlinePlayers()) {

                        if (PlayerStats.getInCombat(player.getUniqueId()) == false) {
                            HealthMechanics.regenPlayer(player);
                        }

                        HealthMechanics.updateHealth(player);
                    }
                }
            }
        }, 10, 30);

    }

    @Override
    public void onDisable() {
        HealthMechanics.updateHealth();
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

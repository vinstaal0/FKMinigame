package me.Vinstaal0;

import me.Vinstaal0.Commands.*;
import me.Vinstaal0.Mechanics.*;
import me.Vinstaal0.Mechanics.ItemMechanics.ArmourEquip.ArmourListener;
import me.Vinstaal0.Mechanics.ItemMechanics.Commands.CreateArmourCommand;
import me.Vinstaal0.Mechanics.ItemMechanics.Commands.CreateSetCommand;
import me.Vinstaal0.Mechanics.ItemMechanics.Commands.CreateWeaponCommand;
import me.Vinstaal0.Mechanics.ItemMechanics.Durability;
import me.Vinstaal0.Mechanics.ItemMechanics.Items.Enchantment.Glow;
import me.Vinstaal0.Mechanics.ItemMechanics.Items.GeneralItem;
import me.Vinstaal0.Player.PlayerListener;
import me.Vinstaal0.Player.PlayerStats;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BossBar;
import org.bukkit.command.PluginCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Minigame extends JavaPlugin {

    private static Plugin plugin;
    public static MonsterMechanics monsterMechanics;

    @Override
    public void onEnable() {

        getServer().getPluginManager().registerEvents(new ArmourListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerListener(this), this);

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
//        new PlayerListener(this);
        new EnergyMechanics(this);
        new DamageMechanics(this);
        new ChatMechanics(this);
        new HealthMechanics(this);
        new GeneralItem(this);
        new Durability(this);

        monsterMechanics = new MonsterMechanics(this);

        registerGlow();

    }

    @Override
    public void onDisable() {
        HealthMechanics.updateHealth();

        try {
            Iterator it = HealthMechanics.bbstore.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();

                BossBar bb = (BossBar) pair.getValue();
                bb.removePlayer(Bukkit.getPlayer((UUID)pair.getKey()));

            }
        } catch (NullPointerException ignored) {}

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

    public void registerGlow() {
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Glow glow = new Glow(new NamespacedKey(this, "fallingkingdom"));
            Enchantment.registerEnchantment(glow);
        } catch (IllegalArgumentException e) {
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

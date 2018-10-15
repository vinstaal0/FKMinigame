package me.Vinstaal0.Player;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.plugin.Plugin;
import me.Vinstaal0.Minigame;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class PlayerStats {

    static Plugin plugin = null;

    private static HashMap<UUID, Double> maxHP = new HashMap<UUID, Double>();
    @Deprecated
    private static HashMap<UUID, Integer> currentHP = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxEnergy = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxHPs = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxArmor = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxDPs = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxInt = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxVit = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxDex = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxStr = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxDodge = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxBlock = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxReflect = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxResistance = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxThorns = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxGemFind = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxItemFind = new HashMap<UUID, Integer>();

    private static HashMap<UUID, Boolean> fullHealth = new HashMap<UUID, Boolean>();

    private static HashMap<UUID, Integer> maxLowerDMG = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxUpperDMG = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxPoison = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxIce = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxFire = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxPure = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxLifeSteal = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxVsPlayers = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxVsMonsters = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxCritHit = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxBluntHit = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxAccuracy = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxSlowness = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxArmorPen = new HashMap<UUID, Integer>();
    private static HashMap<UUID, Integer> maxBlind = new HashMap<UUID, Integer>();

    private static HashMap<UUID, Boolean> inCombat = new HashMap<UUID, Boolean>();

    public PlayerStats(Minigame plugin) {
        PlayerStats.plugin = plugin;
    }

    public static double getMaxHP(UUID uuid) {
        return maxHP.get(uuid);
    }

    public static void setMaxHP(UUID uuid, double maxHP) {
        PlayerStats.maxHP.put(uuid, maxHP);
    }

    @Deprecated
    public static int getCurrentHP(UUID uuid) {
        return currentHP.get(uuid);
    }

    @Deprecated
    public static void setCurrentHP(UUID uuid, int currentHP) {
        PlayerStats.currentHP.put(uuid, currentHP);
    }

    public static int getMaxEnergy(UUID uuid) {
        return maxEnergy.get(uuid);
    }

    public static void setMaxEnergy(UUID uuid, int maxEnergy) {
        PlayerStats.maxEnergy.put(uuid, maxEnergy);
    }

    public static int getMaxHPs(UUID uuid) {
        return maxHPs.get(uuid);
    }

    public static void setMaxHPs(UUID uuid, int maxHPs) {
        PlayerStats.maxHPs.put(uuid, maxHPs);
    }

    public static int getMaxArmor(UUID uuid) {
        return maxArmor.get(uuid);
    }

    public static void setMaxArmor(UUID uuid, int maxArmor) {
        PlayerStats.maxArmor.put(uuid, maxArmor);
    }

    public static int getMaxDPs(UUID uuid) {
        return maxDPs.get(uuid);
    }

    public static void setMaxDPs(UUID uuid, int maxDPs) {
        PlayerStats.maxDPs.put(uuid, maxDPs);
    }

    public static int getMaxInt(UUID uuid) {
        return maxInt.get(uuid);
    }

    public static void setMaxInt(UUID uuid, int maxInt) {
        PlayerStats.maxInt.put(uuid, maxInt);
    }

    public static int getMaxVit(UUID uuid) {
        return maxVit.get(uuid);
    }

    public static void setMaxVit(UUID uuid, int maxVit) {
        PlayerStats.maxVit.put(uuid, maxVit);
    }

    public static int getMaxDex(UUID uuid) {
        return maxDex.get(uuid);
    }

    public static void setMaxDex(UUID uuid, int maxDex) {
        PlayerStats.maxDex.put(uuid, maxDex);
    }

    public static int getMaxStr(UUID uuid) {
        return maxStr.get(uuid);
    }

    public static void setMaxStr(UUID uuid, int maxStr) {
        PlayerStats.maxStr.put(uuid, maxStr);
    }

    public static int getMaxDodge(UUID uuid) {
        return maxDodge.get(uuid);
    }

    public static void setMaxDodge(UUID uuid, int maxDodge) {
        PlayerStats.maxDodge.put(uuid, maxDodge);
    }

    public static int getMaxBlock(UUID uuid) {
        return maxBlock.get(uuid);
    }

    public static void setMaxBlock(UUID uuid, int maxBlock) {
        PlayerStats.maxBlock.put(uuid, maxBlock);
    }

    public static int getMaxReflect(UUID uuid) {
        return maxReflect.get(uuid);
    }

    public static void setMaxReflect(UUID uuid, int maxReflect) {
        PlayerStats.maxReflect.put(uuid, maxReflect);
    }

    public static int getMaxResistance(UUID uuid) {
        return maxResistance.get(uuid);
    }

    public static void setMaxResistance(UUID uuid, int maxResistance) {
        PlayerStats.maxResistance.put(uuid, maxResistance);
    }

    public static int getMaxThorns(UUID uuid) {
        return maxThorns.get(uuid);
    }

    public static void setMaxThorns(UUID uuid, int maxThorns) {
        PlayerStats.maxThorns.put(uuid, maxThorns);
    }

    public static int getMaxGemFind(UUID uuid) {
        return maxGemFind.get(uuid);
    }

    public static void setMaxGemFind(UUID uuid, int maxGemFind) {
        PlayerStats.maxGemFind.put(uuid, maxGemFind);
    }

    public static int getMaxItemFind(UUID uuid) {
        return maxItemFind.get(uuid);
    }

    public static void setMaxItemFind(UUID uuid, int maxItemFind) {
        PlayerStats.maxItemFind.put(uuid, maxItemFind);
    }

    public static boolean getFullHealth(UUID uuid) {
        return fullHealth.get(uuid);
    }

    public static void setFullHealth(UUID uuid, boolean fullHealth) {
        PlayerStats.fullHealth.put(uuid, fullHealth);
    }

    public static int getMaxLowerDMG(UUID uuid) {
        return maxLowerDMG.get(uuid);
    }

    public static void setMaxLowerDMG(UUID uuid, int maxLowerDMG) {
        PlayerStats.maxLowerDMG.put(uuid, maxLowerDMG);
    }

    public static int getMaxUpperDMG(UUID uuid) {
        return maxUpperDMG.get(uuid);
    }

    public static void setMaxUpperDMG(UUID uuid, int maxUpperDMG) {
        PlayerStats.maxUpperDMG.put(uuid, maxUpperDMG);
    }

    public static int getMaxPoison(UUID uuid) {
        return maxPoison.get(uuid);
    }

    public static void setMaxPoison(UUID uuid, int maxPoison) {
        PlayerStats.maxPoison.put(uuid, maxPoison);
    }

    public static int getMaxIce(UUID uuid) {
        return maxIce.get(uuid);
    }

    public static void setMaxIce(UUID uuid, int maxIce) {
        PlayerStats.maxIce.put(uuid, maxIce);
    }

    public static int getMaxFire(UUID uuid) {
        return maxFire.get(uuid);
    }

    public static void setMaxFire(UUID uuid, int maxFire) {
        PlayerStats.maxFire.put(uuid, maxFire);
    }

    public static int getMaxPure(UUID uuid) {
        return maxPure.get(uuid);
    }

    public static void setMaxPure(UUID uuid, int maxPure) {
        PlayerStats.maxPure.put(uuid, maxPure);
    }

    public static int getMaxLifeSteal(UUID uuid) {
        return maxLifeSteal.get(uuid);
    }

    public static void setMaxLifeSteal(UUID uuid, int maxLifeSteal) {
        PlayerStats.maxLifeSteal.put(uuid, maxLifeSteal);
    }

    public static int getMaxVsPlayers(UUID uuid) {
        return maxVsPlayers.get(uuid);
    }

    public static void setMaxVsPlayers(UUID uuid, int maxVsPlayers) {
        PlayerStats.maxVsPlayers.put(uuid, maxVsPlayers);
    }

    public static int getMaxVsMonsters(UUID uuid) {
        return maxVsMonsters.get(uuid);
    }

    public static void setMaxVsMonsters(UUID uuid, int maxVsMonsters) {
        PlayerStats.maxVsMonsters.put(uuid, maxVsMonsters);
    }

    public static int getMaxCritHit(UUID uuid) {
        return maxCritHit.get(uuid);
    }

    public static void setMaxCritHit(UUID uuid, int maxCritHit) {
        PlayerStats.maxCritHit.put(uuid, maxCritHit);
    }

    public static int getMaxBluntHit(UUID uuid) {
        return maxBluntHit.get(uuid);
    }

    public static void setMaxBluntHit(UUID uuid, int maxBluntHit) {
        PlayerStats.maxBluntHit.put(uuid, maxBluntHit);
    }

    public static int getMaxAccuracy(UUID uuid) {
        return maxAccuracy.get(uuid);
    }

    public static void setMaxAccuracy(UUID uuid, int maxAccuracy) {
        PlayerStats.maxAccuracy.put(uuid, maxAccuracy);
    }

    public static int getMaxSlowness(UUID uuid) {
        return maxSlowness.get(uuid);
    }

    public static void setMaxSlowness(UUID uuid, int maxSlowness) {
        PlayerStats.maxSlowness.put(uuid, maxSlowness);
    }

    public static int getMaxArmorPen(UUID uuid) {
        return maxArmorPen.get(uuid);
    }

    public static void setMaxArmorPen(UUID uuid, int maxArmorPen) {
        PlayerStats.maxArmorPen.put(uuid, maxArmorPen);
    }

    public static int getMaxBlind(UUID uuid) {
        return maxBlind.get(uuid);
    }

    public static void setMaxBlind(UUID uuid, int maxBlind) {
        PlayerStats.maxBlind.put(uuid, maxBlind);
    }

    public static boolean getInCombat(UUID uuid) {

        try {
            return inCombat.get(uuid);
        } catch (NullPointerException e) {
            PlayerStats.setInCombat(uuid, false);
            return false;
        }

    }

    public static void setInCombat(UUID uuid, boolean inCombat) {
        PlayerStats.inCombat.put(uuid, inCombat);
    }

    //TODO rework inCombat

    static int id = 99999;

    public static void inCombat(UUID uuid) {

        final UUID u = uuid;

        System.out.println("in combat");

        PlayerStats.setInCombat(u, true);

        if (id != 99999) {
            plugin.getServer().getScheduler().cancelTask(id);
        }

        id = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

            @Override
            public void run() {

                PlayerStats.setInCombat(u, false);

                System.out.println("out of combat");

            }
        }, 20L * 30L);

        System.out.println("ID = " + id);

    }

}

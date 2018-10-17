package me.Vinstaal0.Mechanics;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import me.Vinstaal0.Minigame;
import me.Vinstaal0.Player.PlayerStats;
import me.Vinstaal0.Utility.Tier;
import org.bukkit.plugin.Plugin;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class HealthMechanics {

    public static ConcurrentHashMap<String, Long> in_combat = new ConcurrentHashMap<>();
    // A list of all players in combat and the last time they were in a combat situation.

    public static int HealthRegenCombatDelay = 10;
    // Interval (in seconds) between last instance of a combat acivity and the start of auto HP regen.

    static Plugin plugin = null;

    public HealthMechanics(Minigame minigame) {

        updateHealth();
        plugin = minigame;

        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            @Override
            public void run() {

                for(Player player : Bukkit.getServer().getOnlinePlayers()) {

                    if (!in_combat.containsKey(player.getName())) {
                        regenPlayer(player);
                    }
                }

            }
        },40L, 20L);

        // "in combat" Handler, removes players from the in_combat list after 'HealthRegenCombatDelay' is over.
        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(plugin, new Runnable() {
            public void run() {
                if(!in_combat.isEmpty()) {
                    Map<String, Long> hmash = new HashMap<String, Long>(in_combat);
                    for(Map.Entry<String, Long> entry : hmash.entrySet()) {
                        String p_name = entry.getKey();
                        if(cooldownOver(p_name)) {
                            in_combat.remove(p_name);

                            System.out.println(p_name + " is now out of combat!");
                        }
                    }
                }
            }
        }, 40L, 20L);

    }

    public static boolean cooldownOver(String p_name) {
        if(!in_combat.containsKey(p_name)) {
            return true;
        }

        long oldTime = getOldTime(p_name);
        long currentTime = System.currentTimeMillis();
        if(currentTime - oldTime >= (HealthRegenCombatDelay * 1000)) {

            return true;

        } else {

            return false;
        }
    }

    public static long getOldTime(String p_name) {
        return in_combat.get(p_name);
    }

    //TODO Update
    public static void regenPlayer(Player player) {

        if (PlayerStats.getMaxHP(player.getUniqueId()) != player.getHealth()) {
            if (!player.isDead()) {

                PlayerStats.setFullHealth(player.getUniqueId(), false);

                int regen = PlayerStats.getMaxHPs(player.getUniqueId());
                Double currentHP = player.getHealth() + regen;

                player.sendMessage(ChatColor.GREEN + "Regen +" + ChatColor.BOLD + "" + regen);

                System.out.println("Player " + player.getName() + " GENERIC MAX HEALTH = " + player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());

                try {
                    if (currentHP > player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) {
                        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                    } else {
                        player.setHealth(currentHP);
                    }

                } catch (IllegalArgumentException e) {
                    // Full heal
                    e.printStackTrace();
                    player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
                }

                updateHealth(player);
            }
        } else {
            PlayerStats.setFullHealth(player.getUniqueId(), true);
        }
    }

    public static void updateHealth() {

        Collection<? extends Player> onlinePlayers = Bukkit.getOnlinePlayers();

        for (Player player : onlinePlayers) {
            updateHealth(player);
        }
    }

    public static void updateHealth(Player player) {

        ArrayList<Tier> tier = getStats(player);

        Double maxHP = PlayerStats.getMaxHP(player.getUniqueId());

        Double currentHP;

        try {
            currentHP = player.getHealth();
        } catch (NullPointerException e) {
            currentHP = maxHP;
        }

        int actualCurrentHP = currentHP.intValue();

        if (actualCurrentHP > maxHP.intValue()) {
            actualCurrentHP = maxHP.intValue();
        }

        refreshHealth(player);

//		PlayerStats.setCurrentHP(player.getUniqueId(), actualCurrentHP);

    }

    public static void refreshHealth(Player player) {

        if (player != null) {
            double maxHP = PlayerStats.getMaxHP(player.getUniqueId());

            player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(maxHP);

            player.setHealthScale(20.0);

            updateOverHeadBar(player);
        }
    }

    public static HashMap<UUID, BossBar> bbstore = new HashMap<>();

    @SuppressWarnings("deprecation")
    public static void updateOverHeadBar(Player player) {

        if (player != null) {
            Double maxHP = PlayerStats.getMaxHP(player.getUniqueId());
            Double currentHP = player.getHealth();

            player.sendMessage(ChatColor.BOLD + "" + ChatColor.GREEN + "HP: " + currentHP.intValue() + "/" + maxHP.intValue());

            if (!bbstore.containsKey(player.getUniqueId())) {
                BossBar bar = Bukkit.createBossBar(ChatColor.BOLD + "" + ChatColor.GREEN + "HP: " + currentHP.intValue() + "/" + maxHP.intValue(), BarColor.RED, BarStyle.SEGMENTED_10);
                bar.addPlayer(player);
                bbstore.put(player.getUniqueId(), bar);
            } else {
                BossBar bar = bbstore.get(player.getUniqueId());
                bar.setTitle(ChatColor.BOLD + "" + ChatColor.GREEN + "HP: " + currentHP.intValue() + "/" + maxHP.intValue());

                Double percentage = currentHP / maxHP;

                try {
                    bar.setProgress(percentage);
                } catch (IllegalArgumentException ignored) {
                    //TODO check later after fixing order of health update
                    bar.setProgress(0.99);
                }
            }

        }

    }

    public static ArrayList<Tier> getStats(Player player) {
        // TODO check why below line throwns a non important nullpointer exception
        ItemStack[] armorPieces = null;

        try {
            armorPieces = player.getInventory().getArmorContents();
        } catch (NullPointerException ignored) {}

        ArrayList<Tier> pieceTiers = new ArrayList<Tier>();

        int maxHP = 100;
        int maxEnergy = 1;
        int maxHPs = 5;
        int maxArmor = 0;
        int maxDPs = 0;
        int maxInt = 0;
        int maxVit = 0;
        int maxDex = 0;
        int maxStr = 0;
        int maxDodge = 0;
        int maxBlock = 0;
        int maxReflect = 0;
        int maxResistance = 0;
        int maxThorns = 0;
        int maxGemFind = 0;
        int maxItemFind = 0;

        // checks for changes in armor stats
        for (ItemStack armorPiece : armorPieces) {

            if (ItemMechanics.isArmor(armorPiece)) {
                List<String> lore = armorPiece.getItemMeta().getLore();
                pieceTiers.add(ItemMechanics.getTier(armorPiece));

                for (String line : lore) {
                    // get hps
                    if (line.contains("HPs") || line.contains("HP REGEN"))
                        maxHPs += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                        // keep after hps
                    else if (line.contains("HP"))
                        maxHP += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("ENERGY"))
                        maxEnergy += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("ARMOR"))
                        maxArmor += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("DPs"))
                        maxDPs += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("INT"))
                        maxInt += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("VIT"))
                        maxVit += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("DEX"))
                        maxDex += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("STR"))
                        maxStr += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("DODGE"))
                        maxDodge += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("BLOCK"))
                        maxBlock += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("REFLECT"))
                        maxReflect += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("RESISTANCE"))
                        maxResistance += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("THORNS"))
                        maxThorns += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("GEM FIND"))
                        maxGemFind += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("ITEM FIND"))
                        maxItemFind += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else {}
                }
            }
        }

        if (maxInt > 0)
            maxThorns = (int) Math.ceil(maxThorns * (1 + (maxInt / 1600.0)));

        if (maxVit > 0)
        {
            maxHP = (int) Math.ceil(maxHP * (1 + maxVit / 1600.0));
            maxReflect = (int) Math.ceil(maxReflect * (1 + (maxVit / 1600.0)));
        }

        if (maxDex > 0)
        {
            maxDPs = (int) Math.ceil(maxDPs * (1 + (maxDex / 1600.0)));
            maxDodge = (int) Math.ceil(maxDodge * (1 + (maxDex / 1600.0)));
        }

        if (maxStr > 0)
        {
            maxArmor = (int) Math.ceil(maxArmor * (1 + (maxStr / 1600.0)));
            maxBlock = (int) Math.ceil(maxBlock * (1 + (maxStr / 1600.0)));
        }

        PlayerStats.setMaxHP(player.getUniqueId(), maxHP);
        PlayerStats.setMaxEnergy(player.getUniqueId(), maxEnergy);
        PlayerStats.setMaxHPs(player.getUniqueId(), maxHPs);
        PlayerStats.setMaxArmor(player.getUniqueId(), maxArmor);
        PlayerStats.setMaxDPs(player.getUniqueId(), maxDPs);
        PlayerStats.setMaxInt(player.getUniqueId(), maxInt);
        PlayerStats.setMaxVit(player.getUniqueId(), maxVit);
        PlayerStats.setMaxDex(player.getUniqueId(), maxDex);
        PlayerStats.setMaxStr(player.getUniqueId(), maxStr);
        PlayerStats.setMaxDodge(player.getUniqueId(), maxDodge);
        PlayerStats.setMaxBlock(player.getUniqueId(), maxBlock);
        PlayerStats.setMaxReflect(player.getUniqueId(), maxReflect);
        PlayerStats.setMaxResistance(player.getUniqueId(), maxResistance);
        PlayerStats.setMaxThorns(player.getUniqueId(), maxThorns);
        PlayerStats.setMaxGemFind(player.getUniqueId(), maxGemFind);
        PlayerStats.setMaxItemFind(player.getUniqueId(), maxItemFind);

//        try {
//        	PlayerStats.getCurrentHP(player.getUniqueId());
//        } catch (Exception e) {
//        	e.printStackTrace();
//        	PlayerStats.setCurrentHP(player.getUniqueId(), 100);
//        }

        return pieceTiers;
    }

}
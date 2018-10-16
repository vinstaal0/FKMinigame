package me.Vinstaal0.Mechanics;

import org.bukkit.ChatColor;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Player.PlayerStats;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class DamageMechanics implements Listener {

    public DamageMechanics(Minigame plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // TODO rework calculating damage, doesn't calculate correctly (DMG and Display is not the same)

    @SuppressWarnings("deprecation")
    @EventHandler
    public void playerDMG(EntityDamageByEntityEvent event) {

        Player defender = null;

        if (event.getEntity() instanceof Player) {
            defender = (Player) event.getEntity();

            HealthMechanics.in_combat.put(defender.getName(), System.currentTimeMillis());

            System.out.println(defender.getName() + " is now in combat!");
        }
        Creature attacker = null;

        if (event.getDamager() instanceof Creature) {
            attacker = (Creature) event.getDamager();
        }

        if (defender != null || attacker != null) {
            ItemStack weapon = null;

            if (attacker.getEquipment().getItemInHand() != null) {
                weapon = attacker.getEquipment().getItemInHand();
            }

            int[] stat = ItemMechanics.getDamage(weapon);

            Double rawDmg = stat[0] + Math.random() * (stat[1] - stat[0]);

            Double armor = 1 + Math.random() * (PlayerStats.getMaxArmor(defender.getUniqueId()) - 1 );

            Double dmg = rawDmg * ((100 - armor) / 100);

            Double difference = rawDmg - dmg;

            if (dmg == 0) {
                dmg = 1.0;
                difference = 1.0;
            }

            HealthMechanics.updateHealth(defender);

            if ((PlayerStats.getMaxHP(defender.getUniqueId()) - dmg) < 0) {
                defender.damage(defender.getHealth());
            } else {
                defender.damage(dmg);
            }

            HealthMechanics.updateHealth(defender);

            Double hp = Double.valueOf(defender.getHealth());

            if (true) {
                defender.sendMessage(ChatColor.RED + "     -" + dmg.intValue() + ChatColor.BOLD + "HP" +
                        ChatColor.RED + "-> " + ChatColor.GRAY + "[-" + armor.intValue() + "%Armor -> -" + difference.intValue() +
                        " DMG] " + ChatColor.GREEN + "[" + hp.intValue() + ChatColor.BOLD + "HP" + ChatColor.GREEN + "]");
            }

        }

    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void mobDMG(EntityDamageByEntityEvent event) {

        Creature defender = null;

        if (event.getEntity() instanceof Creature) {
            defender = (Creature) event.getEntity();
        }
        Player attacker = null;

        if (event.getDamager() instanceof Player) {
            attacker = (Player) event.getDamager();

            HealthMechanics.in_combat.put(attacker.getName(), System.currentTimeMillis());

            System.out.println(attacker.getName() + " is now in combat!");

//            PlayerStats.inCombat(attacker.getUniqueId());
        }

        if (defender != null || attacker != null) {
            ItemStack weapon = null;

            if (attacker.getEquipment().getItemInHand() != null) {
                weapon = attacker.getEquipment().getItemInHand();
            }

            int[] stat = ItemMechanics.getDamage(weapon);

            Double rawDmg = stat[0] + Math.random() * (stat[1] - stat[0]);

            Double dps = 1 + Math.random() * (PlayerStats.getMaxDPs(attacker.getUniqueId()) - 1);

            Double dmg = rawDmg * ((dps / 100) + 1);

            Double difference = dmg - rawDmg;

            if (dmg == 0) {
                dmg = 1.0;
                difference = 1.0;
            }

            HealthMechanics.updateHealth(attacker);

            if ((defender.getMaxHealth() - dmg) < 0) {
                defender.damage(defender.getHealth());
            } else {
                defender.damage(dmg);
            }

            HealthMechanics.updateHealth(attacker);

            Double hp = Double.valueOf(defender.getHealth());

            if (true) {
                attacker.sendMessage(ChatColor.RED + "     " + dmg.intValue() + ChatColor.BOLD + " DMG" +
                        ChatColor.RED + "-> " + ChatColor.GRAY + "[+" + dps.intValue() + "%DPs -> +" + difference.intValue() +
                        " DMG] " + ChatColor.WHITE + defender.getCustomName() + ChatColor.WHITE + " [" + hp.intValue() + ChatColor.BOLD + "HP" + ChatColor.WHITE + "]");
            }
        }
    }
}
package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Player.PlayerStats;

import java.util.Random;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class DamageMechanics implements Listener {

    public DamageMechanics(Minigame plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void playerDMG(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Player && event.getDamager() instanceof Creature) {
            Player defender = (Player) event.getEntity();
            Creature attacker = (Creature) event.getDamager();

            HealthMechanics.in_combat.put(defender.getName(), System.currentTimeMillis());

            System.out.println(defender.getName() + " is now in combat!");

            if (defender != null || attacker != null) {
                ItemStack weapon = null;

                if (attacker.getEquipment().getItemInHand() != null) {
                    weapon = attacker.getEquipment().getItemInHand();
                }

                int[] stat = ItemMechanics.getDamage(weapon);

                int min_dmg = stat[0];
                int max_dmg = stat[1];

                int rawDmg = 1;

                try {
                    rawDmg = new Random().nextInt(max_dmg - min_dmg) + min_dmg;
                } catch (IllegalArgumentException ignored) {}

                Double armor = 1 + Math.random() * (PlayerStats.getMaxArmor(defender.getUniqueId()) - 1 );

                int dmg = (int) Math.round(rawDmg * ((100 - armor) / 100));

                int difference = rawDmg - dmg;

                HealthMechanics.updateHealth(defender);

                defender.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);

                if ((PlayerStats.getMaxHP(defender.getUniqueId()) - dmg) < 0) {
                    defender.damage(defender.getHealth());
                } else {

                    try {
                        defender.setHealth((defender.getHealth() - dmg));
                    } catch (IllegalArgumentException ignored) {
                        ignored.printStackTrace();
                        defender.damage(defender.getHealth());
                    }

                    event.setDamage(0);
                }

                HealthMechanics.updateHealth(defender);

                Double hp = Double.valueOf(defender.getHealth());

                if (true) {
                    defender.sendMessage(ChatColor.RED + "     -" + dmg + ChatColor.BOLD + "HP" +
                            ChatColor.RED + "-> " + ChatColor.GRAY + "[-" + armor.intValue() + "%Armor -> -" + difference +
                            " DMG] " + ChatColor.GREEN + "[" + hp.intValue() + ChatColor.BOLD + "HP" + ChatColor.GREEN + "]");
                }
            }
        }
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void mobDMG(EntityDamageByEntityEvent event) {

        if (event.getEntity() instanceof Creature && event.getDamager() instanceof Player) {
           Creature defender = (Creature) event.getEntity();
           Player attacker = (Player) event.getDamager();

            HealthMechanics.in_combat.put(attacker.getName(), System.currentTimeMillis());

            System.out.println(attacker.getName() + " is now in combat!");

            ItemStack weapon = null;

            if (attacker.getEquipment().getItemInHand() != null) {
                weapon = attacker.getEquipment().getItemInHand();
            }

            int[] stat = ItemMechanics.getDamage(weapon);

            int min_dmg = stat[0];
            int max_dmg = stat[1];

            int rawDmg = 1;

            try {
                rawDmg = new Random().nextInt(max_dmg - min_dmg) + min_dmg;
            } catch (IllegalArgumentException ignored) {}

            Double dps = 1 + Math.random() * (PlayerStats.getMaxDPs(attacker.getUniqueId()) - 1);

            attacker.sendMessage(ChatColor.GREEN + "" + rawDmg);

            int dmg = (int) Math.round(rawDmg * ((dps / 100) + 1));

            attacker.sendMessage(ChatColor.GREEN + "After " + dmg);

            int difference = dmg - rawDmg;

            attacker.sendMessage(ChatColor.GREEN + "Difference " + difference);

            HealthMechanics.updateHealth(attacker);

            defender.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).setBaseValue(0);

            if ((defender.getMaxHealth() - dmg) < 0) {
                defender.damage(defender.getHealth());
            } else {
//                event.setDamage(EntityDamageEvent.DamageModifier.BASE, finalDmg.intValue());
                try {
                    defender.setHealth((defender.getHealth() - dmg + event.getFinalDamage()));
                } catch (IllegalArgumentException ignored) {
                    // Defender is dead
                    defender.damage(defender.getHealth());
                }
                event.setDamage(0);
                System.out.println("------------------------");
                System.out.println("Bukkit final DMG = " + event.getFinalDamage());
            }

            HealthMechanics.updateHealth(attacker);

            Double hp = Double.valueOf(defender.getHealth());

            if (true) {
                attacker.sendMessage(ChatColor.RED + "     " + dmg + ChatColor.BOLD + " DMG" +
                        ChatColor.RED + "-> " + ChatColor.GRAY + "[+" + dps.intValue() + "%DPs -> +" + difference +
                        " DMG] " + ChatColor.WHITE + defender.getCustomName() + ChatColor.WHITE + " [" + hp.intValue() + ChatColor.BOLD + "HP" + ChatColor.WHITE + "]");
            }

            try {
                if (defender.isDead()) {

                    Zombie zombie = null;

                    if (defender instanceof Zombie) {
                        zombie = (Zombie) defender;
                    }

                    ItemStack[] armour = zombie.getEquipment().getArmorContents();
                    ItemStack wep = zombie.getEquipment().getItemInMainHand();

                    // Boots
                    Location loc = zombie.getLocation();
                    if (armour[0] != null && armour[0].getType() != Material.AIR) {
                        loc.getWorld().dropItem(loc, armour[0]);
                    }
                    // Leggings
                    if (armour[1] != null && armour[1].getType() != Material.AIR) {
                        loc.getWorld().dropItem(loc, armour[1]);
                    }
                    // Chestplate
                    if (armour[2] != null && armour[2].getType() != Material.AIR) {
                        loc.getWorld().dropItem(loc, armour[2]);
                    }

                    // Helmet
                    if (armour[3] != null && armour[3].getType() != Material.AIR) {
                        loc.getWorld().dropItem(loc, armour[3]);
                    }

                    if (wep != null && wep.getType() != Material.AIR) {
                        loc.getWorld().dropItem(loc, wep);
                    }

                }
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
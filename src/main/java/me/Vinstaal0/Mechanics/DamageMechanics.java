package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Creature;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
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

            long dmg = Math.round(rawDmg * ((100 - armor) / 100));

            long difference = Math.round(rawDmg) - dmg;

            Long finalDmg = 1L;

            if (dmg == 0) {
                dmg = 1L;
            } else {
                finalDmg = dmg;
            }

            System.out.println("Final DMG = " + finalDmg + " DMG = " + dmg + " int value = " + finalDmg.intValue());
            System.out.println("Defender HP = " + defender.getHealth());
            System.out.println("Bukkit final DMG = " + event.getFinalDamage());

            Long finalDifference = difference;

            HealthMechanics.updateHealth(defender);

            if ((PlayerStats.getMaxHP(defender.getUniqueId()) - dmg) < 0) {
                defender.damage(defender.getHealth());
            } else {

                try {
                    defender.setHealth((defender.getHealth() - finalDmg));
                } catch (IllegalArgumentException ignored) {
                    ignored.printStackTrace();
                    defender.damage(defender.getHealth());
                }
                event.setDamage(1.0);
                System.out.println("------------------------");
                System.out.println("Bukkit final DMG = " + event.getFinalDamage());
            }

            HealthMechanics.updateHealth(defender);

            Double hp = Double.valueOf(defender.getHealth());

            if (true) {
                defender.sendMessage(ChatColor.RED + "     -" + finalDmg.intValue() + ChatColor.BOLD + "HP" +
                        ChatColor.RED + "-> " + ChatColor.GRAY + "[-" + armor.intValue() + "%Armor -> -" + finalDifference.intValue() +
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

        }

        if (defender != null || attacker != null) {
            ItemStack weapon = null;

            if (attacker.getEquipment().getItemInHand() != null) {
                weapon = attacker.getEquipment().getItemInHand();
            }

            int[] stat = ItemMechanics.getDamage(weapon);

            Double rawDmg = stat[0] + Math.random() * (stat[1] - stat[0]);

            Double dps = 1 + Math.random() * (PlayerStats.getMaxDPs(attacker.getUniqueId()) - 1);

            long dmg = Math.round(rawDmg * ((dps / 100) + 1));

            long difference = dmg - Math.round(rawDmg);

            HealthMechanics.updateHealth(attacker);

            Long finalDmg = 1L;

            if (dmg == 0) {
                finalDmg = 1L;
            } else {
                finalDmg = dmg;
            }

            System.out.println("Final DMG = " + finalDmg + " DMG = " + dmg + " int value = " + finalDmg.intValue());
            System.out.println("Defender HP = " + defender.getHealth());
            System.out.println("Bukkit final DMG = " + event.getFinalDamage());

            Long finalDifference = difference;

            if ((defender.getMaxHealth() - dmg) < 0) {
                defender.damage(defender.getHealth());
            } else {
//                event.setDamage(EntityDamageEvent.DamageModifier.BASE, finalDmg.intValue());
                try {
                    defender.setHealth((defender.getHealth() - finalDmg + event.getFinalDamage()));
                } catch (IllegalArgumentException ignored) {
                    // Defender is dead
                    defender.damage(defender.getHealth());
                }
                event.setDamage(1.0);
                System.out.println("------------------------");
                System.out.println("Bukkit final DMG = " + event.getFinalDamage());
            }

            HealthMechanics.updateHealth(attacker);

            Double hp = Double.valueOf(defender.getHealth());

            if (true) {
                attacker.sendMessage(ChatColor.RED + "     " + finalDmg.intValue() + ChatColor.BOLD + " DMG" +
                        ChatColor.RED + "-> " + ChatColor.GRAY + "[+" + dps.intValue() + "%DPs -> +" + finalDifference.intValue() +
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
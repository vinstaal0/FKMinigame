package me.Vinstaal0.Mechanics;

import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
import me.Vinstaal0.Minigame;
import me.Vinstaal0.Player.PlayerStats;
import me.Vinstaal0.Utility.Tier;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class EnergyMechanics implements Listener {

    private static Plugin plugin = null;
    private static ConcurrentHashMap<Player, Integer> fatigueEffect = new ConcurrentHashMap<Player, Integer>();
    private static HashMap<UUID, Float> energyRegenData = new HashMap<UUID, Float>();
    private static HashMap<UUID, Float> oldEnergy = new HashMap<UUID, Float>();

    private static HashMap<UUID, Long> lastAttack = new HashMap<UUID, Long>();

    private static CopyOnWriteArrayList<Player> sprinting = new CopyOnWriteArrayList<Player>();
    private static CopyOnWriteArrayList<Player> starving = new CopyOnWriteArrayList<Player>();

    public EnergyMechanics(Minigame plugin) {

        EnergyMechanics.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Nasty hack to prevent sprinting while starving.
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Player p : fatigueEffect.keySet()) {
                    p.setSprinting(false);
                }
            }
        }.runTaskTimerAsynchronously(plugin, 2 * 20L, 5L);

        // Handles energy regen event.
        new BukkitRunnable() {
            @Override
            public void run() {
                replenishEnergy();
            }
        }.runTaskTimerAsynchronously(plugin, 2 * 20L, 2L);

        // Handles the 2 second 'delay' when you run out of energy.
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
            public void run() {
                ClearFatiguePlayers();
            }
        }, 2 * 20L, 25L);

        // Remove energy for sprinting and hunger
        new BukkitRunnable() {

            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (player.isSprinting()) {
                        removeEnergy(player, 0.15F);
                    }
                }

                for (Player player : starving) {
                    player.removePotionEffect(PotionEffectType.HUNGER);

                    if (player.getFoodLevel() <= 0) {
                        player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 0));
                    } else {
                        starving.remove(player.getName());
                    }
                }
            }
        }.runTaskTimer(plugin, 2 * 20L, 10L);
    }

    /**
     * Safety check, cancels mobs/players
     * dropping MC exp
     *
     * @param event
     * @since 1.0
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDeathDropExp(EntityDeathEvent event) {
        event.setDroppedExp(0);
    }

    /**
     * Safety check, cancels players
     * picking up items
     *
     * @param event
     * @since 1.0
     */
    @EventHandler(priority = EventPriority.LOWEST)
    public void onItemPickup(PlayerPickupItemEvent event) {
        if (!(event.getItem() instanceof ExperienceOrb) && (!(event.getItem() instanceof ExperienceOrb))) return;
        event.setCancelled(true);
    }

    public void ClearFatiguePlayers() {
        HashMap<Player, Integer> fatigueEffectMirror = new HashMap<>(fatigueEffect);

        for (Map.Entry<Player, Integer> entry : fatigueEffectMirror.entrySet()) {
            Player player = entry.getKey();
            int i = entry.getValue();

            if (i >= 1) {
                player.removePotionEffect(PotionEffectType.SLOW_DIGGING);
                fatigueEffect.remove(player);
                player.setExp(0.10F);
                updatePlayerLevel(player);
                continue;
            }

            i++;

            fatigueEffect.put(player, i);
        }
    }

    public static void updatePlayerLevel(Player player) {
        float exp = player.getExp();
        double percent = exp * 100.0D;

        if (percent > 100) {
            percent = 100;
        }
        if (percent < 0) {
            percent = 0;
        }

        player.setLevel((int) percent);
    }

    public static float getEnergyPercent(Player player) {
        return player.getExp();
    }

    public void addEnergy(Player player, float add) {
        float currentXP = getEnergyPercent(player);

        if (currentXP == 1) {
            return;
        }
        try {
            player.setExp(getEnergyPercent(player) + add);
        } catch (Exception ignored) {
            player.setExp(1F);
        }

        updatePlayerLevel(player);
    }

    public static void removeEnergy(Player player, float remove) {
        float currentXP = getEnergyPercent(player);
        oldEnergy.put(player.getUniqueId(), currentXP);
        if (currentXP <= 0) {
            return;
        }
        if((getEnergyPercent(player) - remove) <= 0) {
            fatigueEffect.put(player, 0);
            player.setExp(0.0F);
            updatePlayerLevel(player);

            player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 50, 4));

            return;
        }

        player.setExp(getEnergyPercent(player) - remove);
        updatePlayerLevel(player);
    }

    public void replenishEnergy() {
        for (Player player : Bukkit.getServer().getOnlinePlayers()) {

            if (getEnergyPercent(player) == 1.0F) {
                continue;
            }

            if (getEnergyPercent(player) > 1.0F) {
                player.setExp(1.0F);
                updatePlayerLevel(player);
                continue;
            }

            if (!fatigueEffect.containsKey(player)) {
                float resAmount = getEnergyRegainPercent(player);

                if (starving.contains(player)) {
                    resAmount = 0.05F;
                }

                resAmount = resAmount / 6.3F;

                addEnergy(player, resAmount);
            }
        }
    }

    public float getEnergyRegainPercent(Player player) {
        if (!energyRegenData.containsKey(player.getUniqueId())) {
            energyRegenData.put(player.getUniqueId(), 0.10F);
        }

        float energyRegen = energyRegenData.get(player.getUniqueId());

        double intMod = 0;

        HealthMechanics.getStats(player);

        if (PlayerStats.getMaxInt(player.getUniqueId()) > 0) {
            intMod = PlayerStats.getMaxInt(player.getUniqueId());
        }

        energyRegen += ((intMod * 0.015F) / 100.0F);

        return energyRegen;
    }

    public static float getEnergyCost(ItemStack item) {
        Material m = item.getType();

        if(m == Material.AIR) { return 0.05F; }

        if(m == Material.WOODEN_SWORD) { return 0.06F; }
        if(m == Material.STONE_SWORD) { return 0.071F; }
        if(m == Material.IRON_SWORD) { return 0.0833F; }
        if(m == Material.DIAMOND_SWORD) { return 0.125F; }
        if(m == Material.GOLDEN_SWORD) { return 0.135F; }

        if(m == Material.WOODEN_AXE) { return 0.0721F * 1.1F; }
        if(m == Material.STONE_AXE) { return 0.0833F * 1.1F; }
        if(m == Material.IRON_AXE) { return 0.10F * 1.1F; }
        if(m == Material.DIAMOND_AXE) { return 0.125F * 1.1F; }
        if(m == Material.GOLDEN_AXE) { return 0.135F * 1.1F; }

        if(m == Material.WOODEN_SHOVEL) { return 0.0721F; }
        if(m == Material.STONE_SHOVEL) { return 0.0833F; }
        if(m == Material.IRON_SHOVEL) { return 0.10F; }
        if(m == Material.DIAMOND_SHOVEL) { return 0.125F; }
        if(m == Material.GOLDEN_SHOVEL) { return 0.135F; }

        if(m == Material.WOODEN_HOE) { return 0.10F / 1.1F; }
        if(m == Material.STONE_HOE) { return 0.12F / 1.1F; }
        if(m == Material.IRON_HOE) { return 0.13F / 1.1F; }
        if(m == Material.DIAMOND_HOE) { return 0.14F / 1.1F; }
        if(m == Material.GOLDEN_HOE) { return 0.15F / 1.1F; }

        if(m == Material.BOW) { // Arrow shooting. Bow punch will be addressed at event level.
            Tier tier = ItemMechanics.getTier(item);

            switch (tier) {
                case ONE:
                    return 0.08F;
                case TWO:
                    return 0.10F;
                case TREE:
                    return 0.11F;
                case FOUR:
                    return 0.13F;
                case FIVE:
                    return 0.15F;
            }
        }

        return 0.10F;
    }

    public void disableSprint(final Player player) {
        Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                player.setSprinting(false);
            }
        }, 1L);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = false)
    public void onPlayerAnimation(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack weapon = event.getItem();

        if (weapon == null || !event.hasItem()) {
            weapon = new ItemStack(Material.AIR);
        }

        // TODO change world name
        if (player.getWorld().getName().equalsIgnoreCase("dungeonrealms") && event.hasBlock() && (event.getClickedBlock().getType() == Material.TALL_GRASS)) {
            event.setCancelled(true);
            event.setUseItemInHand(Event.Result.DENY);
            return;
        }

        if (!ItemMechanics.isWep(weapon)) {
            return;
        }

        float energyCost = getEnergyCost(weapon);

        if (weapon.getType() == Material.BOW) {
            energyCost =+ 0.15F;
        }

        if (fatigueEffect.containsKey(player)) {
            event.setUseItemInHand(Event.Result.DENY);
            event.setCancelled(true);
            // TODO change world name
            if (player.getWorld().getName().equalsIgnoreCase("dungeonrealms")) {
                player.playSound(player.getLocation(), Sound.ENTITY_WOLF_PANT, 10F, 1.5F);
            }

            return;
        }

        if (lastAttack.containsKey(player.getUniqueId()) && (System.currentTimeMillis() - lastAttack.get(player.getUniqueId())) < 100) {
            event.setUseItemInHand(Event.Result.DENY);
            event.setCancelled(true);
            return;
        }

        removeEnergy(player, energyCost);
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {

        Player attacker = null;

        if (event.getDamager() instanceof Player) {
            attacker = (Player) event.getDamager();
        }

        ItemStack weapon;

        try {
            weapon = attacker.getEquipment().getItemInMainHand();
        } catch (NullPointerException e) {
            weapon = new ItemStack(Material.AIR);
        }


        float energyCost = getEnergyCost(weapon);

        if (weapon.getType() == Material.BOW) {
            energyCost =+ 0.15F;
        }

        if (fatigueEffect.containsKey(attacker)) {
            event.setCancelled(true);

            // TODO fix world name
            if (attacker.getWorld().getName().equalsIgnoreCase("dungeonrealms")) {
                attacker.playSound(attacker.getLocation(), Sound.ENTITY_WOLF_PANT, 12F, 1.5F);
            }

            return;
        }

        lastAttack.put(attacker.getUniqueId(), System.currentTimeMillis());

        removeEnergy(attacker, energyCost);

    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        starving.remove(player);
        sprinting.remove(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onEntityDamage(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.STARVATION) {
            event.setCancelled(true);
            event.setDamage(0);

            Player player = (Player) event.getEntity();

            if (!(player.hasPotionEffect(PotionEffectType.HUNGER))) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 40, 0));

                if (!(starving.contains(player))) {
                    starving.add(player);
                    player.sendMessage(ChatColor.DARK_GREEN + "" + ChatColor.BOLD + "                        *STARVING*");
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void FoodLevelChange(FoodLevelChangeEvent event) {
        if(!(event.getEntity() instanceof Player)) { return; }
        Player player = (Player) event.getEntity();
        if(event.getFoodLevel() < player.getFoodLevel()) { // Make sure they're loosing food level.
            int r = new Random().nextInt(4); // 0, 1, 2, 3
            if(r >= 1) { // Cancel 75% of the time.
                event.setCancelled(true);
                return;
            }
        }
        if(event.getFoodLevel() > 0 && starving.contains(player)) {
            starving.remove(player);
            player.removePotionEffect(PotionEffectType.HUNGER);
        }
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerToggleSprintEvent event) {

        Player player = event.getPlayer();

        if (player.getExp() <= 0.0F) {
            disableSprint(player);
            sprinting.remove(player);
        } else if (event.isSprinting()) {
            sprinting.add(player);
            removeEnergy(player, 0.15F);
        } else {
            sprinting.remove(player);
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        player.removePotionEffect(PotionEffectType.HUNGER);
        sprinting.remove(player);
        fatigueEffect.remove(player);
        starving.remove(player);
    }
}
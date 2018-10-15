package me.Vinstaal0.Mechanics;

import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Vinstaal0.Player.PlayerStats;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class ItemMechanics {

    public static Tier getWeaponStats(Player player) {
        UUID uuid = player.getUniqueId();
        ItemStack weapon = player.getItemInHand();
        int lowerDMG = 0;
        int upperDMG = 0;
        int maxPoison = 0;
        int maxIce = 0;
        int maxFire = 0;
        int maxPure = 0;
        int maxLifeSteal = 0;
        int maxVsPlayers = 0;
        int maxVsMonsters = 0;
        int maxCritHit = 0;
        int maxBluntHit = 0;
        int maxAccuracy = 0;
        int maxSlowness = 0;
        int maxArmorPen = 0;
        int maxBlind = 0;

        if (ItemMechanics.isWep(weapon)) {
            List<String> lore = weapon.getItemMeta().getLore();

            for (String line : lore) {
                if (line.contains("POISON"))
                    maxPoison += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("ICE"))
                    maxIce += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("FIRE"))
                    maxFire += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("PURE"))
                    maxPure += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("LIFESTEAL"))
                    maxLifeSteal += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("vs. PLAYERS"))
                    maxVsPlayers += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("vs. MONSTERS"))
                    maxVsMonsters += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("CRIT HIT"))
                    maxCritHit += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("BLUNT HIT"))
                    maxBluntHit += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("ACCURACY"))
                    maxAccuracy += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("SLOWNESS"))
                    maxSlowness += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("ARMOR PEN"))
                    maxArmorPen += ItemMechanics.getStat(weapon, lore.indexOf(line));

                else if (line.contains("BLIND"))
                    maxBlind += ItemMechanics.getStat(weapon, lore.indexOf(line));

                    // keep at bottom
                else if (line.contains("DMG")) {
                    lowerDMG += ItemMechanics.getFromLineDamage(weapon, lore.indexOf(line))[0];
                    upperDMG += ItemMechanics.getFromLineDamage(weapon, lore.indexOf(line))[1];
                }
            }

            // save stats to map
            PlayerStats.setMaxLowerDMG(uuid, lowerDMG);
            PlayerStats.setMaxUpperDMG(uuid, upperDMG);
            PlayerStats.setMaxPoison(uuid, maxPoison);
            PlayerStats.setMaxIce(uuid, maxIce);
            PlayerStats.setMaxFire(uuid, maxFire);
            PlayerStats.setMaxPure(uuid, maxPure);
            PlayerStats.setMaxLifeSteal(uuid, maxLifeSteal);
            PlayerStats.setMaxVsPlayers(uuid, maxVsPlayers);
            PlayerStats.setMaxVsMonsters(uuid, maxVsMonsters);
            PlayerStats.setMaxCritHit(uuid, maxCritHit);
            PlayerStats.setMaxBluntHit(uuid, maxBluntHit);
            PlayerStats.setMaxAccuracy(uuid, maxAccuracy);
            PlayerStats.setMaxSlowness(uuid, maxSlowness);
            PlayerStats.setMaxArmorPen(uuid, maxArmorPen);
            PlayerStats.setMaxBlind(uuid, maxBlind);

            return ItemMechanics.getTier(weapon);
        }

        return null;
    }

    public static int[] getFromLineDamage(ItemStack item, int line) {
        int[] error = {0, 0};

        if (item.hasItemMeta()) {
            List<String> lore = item.getItemMeta().getLore();

            String oldString = lore.get(line);
            oldString = ChatColor.stripColor(oldString);
            String newString = oldString.substring(oldString.indexOf(":") + 2);
            if (newString.contains("%"))
                newString = newString.replace("%", "");
            if (newString.contains("+"))
                newString = newString.replace("+", "");

            String lowerDMGString = newString.substring(0, newString.indexOf("-") - 1);
            String upperDMGString = newString.substring(newString.indexOf("-") + 2);

            int[] damages = {Integer.parseInt(lowerDMGString), Integer.parseInt(upperDMGString)};

            return damages;
        }

        return error;
    }

    public static int[] getDamage(ItemStack item) {
        int[] error = {0, 0};

        if (!item.hasItemMeta()) {
            return error;
        }

        List<String> lore = item.getItemMeta().getLore();

        for (String line : lore) {
            if (line.contains("POISON") || line.contains("ICE") || line.contains("FIRE") || line.contains("PURE"));

            else if (line.contains("DMG"))
                return getFromLineDamage(item, lore.indexOf(line));
        }

        return error;
    }

    public static int getStat(ItemStack item, int line) {
        int stat = 0;

        if (item.hasItemMeta()) {
            List<String> lore = item.getItemMeta().getLore();

            String oldString = lore.get(line);
            oldString = ChatColor.stripColor(oldString);
            String newString = oldString.substring(oldString.indexOf(":") + 2);

            if (newString.contains("%"))
                newString = newString.replace("%", "");
            if (newString.contains("+"))
                newString = newString.replace("+", "");

            switch (newString.toLowerCase()) {
                case "common":
                    stat = 1;
                    break;
                case "uncommon":
                    stat = 2;
                    break;
                case "rare":
                    stat = 3;
                    break;
                case "unique":
                    stat = 4;
                    break;
                case "ancient":
                    stat = 5;
                    break;
                default:
                    stat = Integer.parseInt(newString);
                    break;
            }
        }

        return stat;
    }

    public static int getStat(List<String> lore, int line) {
        int stat = 0;

        String oldString = lore.get(line);
        oldString = ChatColor.stripColor(oldString);
        String newString = oldString.substring(oldString.indexOf(":") + 2);

        if (newString.contains("%"))
            newString = newString.replace("%", "");
        if (newString.contains("+"))
            newString = newString.replace("+", "");

        switch (newString.toLowerCase()) {
            case "common":
                stat = 1;
                break;
            case "uncommon":
                stat = 2;
                break;
            case "rare":
                stat = 3;
                break;
            case "unique":
                stat = 4;
                break;
            case "ancient":
                stat = 5;
                break;
            default:
                stat = Integer.parseInt(newString);
                break;
        }

        return stat;
    }

    public static int getStat(String line) {
        int stat = 0;

        String oldString = ChatColor.stripColor(line);
        String newString = oldString.substring(oldString.indexOf(":") + 2);

        if (newString.contains("%"))
            newString = newString.replace("%", "");
        if (newString.contains("+"))
            newString = newString.replace("+", "");

        switch (newString.toLowerCase()) {
            case "common":
                stat = 1;
                break;
            case "uncommon":
                stat = 2;
                break;
            case "rare":
                stat = 3;
                break;
            case "unique":
                stat = 4;
                break;
            case "ancient":
                stat = 5;
                break;
            default:
                stat = Integer.parseInt(newString);
                break;
        }

        return stat;
    }

    public static int getHP(ItemStack item) {

        int hp = 0;

        hp = getStat(item, 1);

        return hp;
    }

    public static boolean hasEnergy(ItemStack item) {

        boolean hasEnergy = false;

        if (item.hasItemMeta()) {

            List<String> lore = item.getItemMeta().getLore();

            for (String line : lore) {
                if (line.contains("energy")) {
                    hasEnergy = true;
                }
            }

        }

        return hasEnergy;
    }

    public static boolean isArmor(ItemStack item) {

        return (item != null && (item.getType().toString().contains("HELMET") || item.getType().toString().contains("CHESTPLATE") || item.getType().toString().contains("LEGGINGS") || item.getType().toString().contains("BOOTS"))) && item.hasItemMeta() && item.getItemMeta().hasLore();
    }

    public static boolean isWep(ItemStack item){
        return (item != null && (item.getType().toString().contains("SWORD") || (item.getType().toString().contains("AXE")) || item.getType().toString().contains("SPADE") || item.getType().toString().contains("HOE") || item.getType().toString().contains("BOW"))) && item.hasItemMeta() && item.getItemMeta().hasLore();
    }

    public static Tier getTier(ItemStack item) {

        Tier tier = null;

        if (item.getItemMeta() != null && item.getItemMeta().hasDisplayName() && (item.getType().equals(Material.BOW) || item.getType().equals(Material.FISHING_ROD))) {
            String name = item.getItemMeta().getDisplayName();
            if (name.contains("Wooden") || name.contains(ChatColor.GRAY.toString()))
                tier = Tier.ONE;
            else if (name.contains("Stone") || name.contains(ChatColor.DARK_GREEN.toString()))
                tier = Tier.TWO;
            else if (name.contains("Steel") || name.contains(ChatColor.AQUA.toString()))
                tier = Tier.TREE;
            else if (name.contains("Ivory") || name.contains(ChatColor.DARK_PURPLE.toString()))
                tier = Tier.FOUR;
            else if (name.contains("Gold") || name.contains(ChatColor.YELLOW.toString()))
                tier = Tier.FIVE;
        }
        else if (item.getType().toString().contains("HELMET") || item.getType().toString().contains("CHESTPLATE") || item.getType().toString().contains("LEGGINGS") || item.getType().toString().contains("BOOTS")) {
            String type = item.getType().toString();
            if (type.contains("LEATHER"))
                tier = Tier.ONE;
            else if (type.contains("CHAIN"))
                tier = Tier.TWO;
            else if (type.contains("IRON"))
                tier = Tier.TREE;
            else if (type.contains("DIAMOND"))
                tier = Tier.FOUR;
            else if (type.contains("GOLD"))
                tier = Tier.FIVE;
        }
        else if (item.getItemMeta() != null && item.getType().equals(Material.SADDLE)) {
            String name = item.getItemMeta().getDisplayName();
            if (name.equals(ChatColor.GREEN + "Old Horse Mount"))
                tier = Tier.TWO;
            else if (name.equals(ChatColor.AQUA + "Traveler's Horse Mount"))
                tier = Tier.TREE;
            else if (name.equals(ChatColor.DARK_PURPLE + "Knight's Horse Mount"))
                tier = Tier.FOUR;
            else if (name.equals(ChatColor.YELLOW + "War Stallion Mount"))
                tier = Tier.FIVE;
        }
        else if (item.getItemMeta() != null && item.getItemMeta().hasDisplayName()) {
            String name = item.getItemMeta().getDisplayName();

            if (name.contains(ChatColor.GRAY.toString()))
                tier = Tier.ONE;
            else if (name.contains(ChatColor.DARK_GREEN.toString()))
                tier = Tier.TWO;
            else if (name.contains(ChatColor.AQUA.toString()))
                tier = Tier.TREE;
            else if (name.contains(ChatColor.DARK_PURPLE.toString()))
                tier = Tier.FOUR;
            else if (name.contains(ChatColor.YELLOW.toString()))
                tier = Tier.FIVE;
        }
        else {
            String type = item.getType().toString();
            if (type.contains("WOOD"))
                tier = Tier.ONE;
            else if (type.contains("STONE"))
                tier = Tier.TWO;
            else if (type.contains("IRON"))
                tier = Tier.TREE;
            else if (type.contains("DIAMOND"))
                tier = Tier.FOUR;
            else if (type.contains("GOLD"))
                tier = Tier.FIVE;
        }

        return tier;
    }

}
package me.Vinstaal0.Mechanics.Items;

import me.Vinstaal0.Mechanics.ItemMechanics;
import me.Vinstaal0.Utility.EnumHelp;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Armour implements implItem {

    public enum Type {
        Helmet,
        Chestplate,
        Leggings,
        Boots;
    }

    private ItemStack item;
    private Material material = null;
    private String name = null;
    private ItemMeta im = null;
    private List<String> lore = new ArrayList<String>();
    private Tier tier;
    private Rarity rarity;
    private int slot;

    public Armour() {
        this.tier = EnumHelp.randomEnum(Tier.class);
        this.rarity = EnumHelp.randomEnum(Rarity.class);
        this.slot = ThreadLocalRandom.current().nextInt(1, 4 + 1);

        this.getArmour();
    }

    public Armour(int slot) {
        this.tier = EnumHelp.randomEnum(Tier.class);
        this.rarity = EnumHelp.randomEnum(Rarity.class);
        this.slot = slot;

        this.getArmour();
    }

    public Armour(Tier tier) {
        this.tier = tier;
        this.rarity = EnumHelp.randomEnum(Rarity.class);
        this.slot = ThreadLocalRandom.current().nextInt(1, 4 + 1);

        this.getArmour();
    }

    public Armour(Tier tier, int slot) {
        this.tier = tier;
        this.rarity = EnumHelp.randomEnum(Rarity.class);
        this.slot = slot;

        this.getArmour();
    }

    public Armour(Tier tier, Rarity rarity) {
        this.tier = tier;
        this.rarity = rarity;
        this.slot = ThreadLocalRandom.current().nextInt(1, 4 + 1);

        this.getArmour();
    }

    public Armour(Tier tier, Rarity rarity, int slot) {
        this.tier = tier;
        this.rarity = rarity;
        this.slot = slot;

        this.getArmour();
    }

    public void getArmour() {

        Tier tier = this.getTier();
        Rarity rarity = this.getRarity();
        int slot = this.slot;

        System.out.println("Tier = " + tier + " Rarity = " + rarity + " Slot = " + slot);

        if (tier == Tier.ONE && slot == 1) {
            material = Material.LEATHER_HELMET;
            name = ChatColor.GRAY + "Leather Helmet";
        } else if (tier == Tier.ONE && slot == 2) {
            material = Material.LEATHER_CHESTPLATE;
            name = ChatColor.GRAY + "Leather Chestplate";
        } else if (tier == Tier.ONE && slot == 3) {
            material = Material.LEATHER_LEGGINGS;
            name = ChatColor.GRAY + "Leather Leggings";
        } else if (tier == Tier.ONE && slot == 4) {
            material = Material.LEATHER_BOOTS;
            name = ChatColor.GRAY + "Leather Boots";

        } else if (tier == Tier.TWO && slot == 1) {
            material = Material.CHAINMAIL_HELMET;
            name = ChatColor.GREEN + "Chain Helmet";
        } else if (tier == Tier.TWO && slot == 2) {
            material = Material.CHAINMAIL_CHESTPLATE;
            name = ChatColor.GREEN + "Chain Chestplate";
        } else if (tier == Tier.TWO && slot == 3) {
            material = Material.CHAINMAIL_LEGGINGS;
            name = ChatColor.GREEN + "Chain Leggings";
        } else if (tier == Tier.TWO && slot == 4) {
            material = Material.CHAINMAIL_BOOTS;
            name = ChatColor.GREEN + "Chain Boots";

        } else if (tier == Tier.TREE && slot == 1) {
            material = Material.IRON_HELMET;
            name = ChatColor.AQUA + "Steel Helmet";
        } else if (tier == Tier.TREE && slot == 2) {
            material = Material.IRON_CHESTPLATE;
            name = ChatColor.AQUA + "Steel Chestplate";
        } else if (tier == Tier.TREE && slot == 3) {
            material = Material.IRON_LEGGINGS;
            name = ChatColor.AQUA + "Steel Leggings";
        } else if (tier == Tier.TREE && slot == 4) {
            material = Material.IRON_BOOTS;
            name = ChatColor.AQUA + "Steel Boots";

        } else if (tier == Tier.FOUR && slot == 1) {
            material = Material.DIAMOND_HELMET;
            name = ChatColor.DARK_PURPLE + "Ivory Helmet";
        } else if (tier == Tier.FOUR && slot == 2) {
            material = Material.DIAMOND_CHESTPLATE;
            name = ChatColor.DARK_PURPLE + "Ivory Chestplate";
        } else if (tier == Tier.FOUR && slot == 3) {
            material = Material.DIAMOND_LEGGINGS;
            name = ChatColor.DARK_PURPLE + "Ivory Leggings";
        } else if (tier == Tier.FOUR && slot == 4) {
            material = Material.DIAMOND_BOOTS;
            name = ChatColor.DARK_PURPLE + "Ivory Boots";

        } else if (tier == Tier.FIVE && slot == 1) {
            material = Material.GOLDEN_HELMET;
            name = ChatColor.YELLOW + "Gold Helmet";
        } else if (tier == Tier.FIVE && slot == 2) {
            material = Material.GOLDEN_CHESTPLATE;
            name = ChatColor.YELLOW + "Gold Chestplate";
        } else if (tier == Tier.FIVE && slot == 3) {
            material = Material.GOLDEN_LEGGINGS;
            name = ChatColor.YELLOW + "Gold Leggings";
        } else if (tier == Tier.FIVE && slot == 4) {
            material = Material.GOLDEN_BOOTS;
            name = ChatColor.YELLOW + "Gold Boots";
        } else {
            material = Material.LADDER;
            name = ChatColor.BLACK + "ERROR 404";
        }

        this.item = new ItemStack(material);

        this.im = item.getItemMeta();

        Boolean accepted = false;

        accepted = addHp(lore, tier, rarity, slot);
        if (accepted == false) {System.out.println("Item error");}

        accepted = addArmorDPs(lore, tier);
        if (accepted == false) {System.out.println("Item error");}

        accepted = addEnergy(item, lore, tier);
        if (accepted == false) {System.out.println("Item error");}

        accepted = addRandomStats(lore, tier);
        if (accepted == false) {System.out.println("Item error");}

        GeneralItem g = new GeneralItem();

        accepted = g.addRarity(lore, rarity);
        if (accepted == false) {System.out.println("Item error");}

        this.im.setDisplayName(this.name);

        this.im.setLore(this.lore);

        this.item.setItemMeta(im);
    }

    public static boolean addHp(List<String> lore, Tier tier, Rarity rarity, int slot) {

        try {

            int[] helmboots = {25, 67, 175, 470, 1275};
            int[] chestlegs = {35, 100, 315, 890, 2600};

            double[] multiRarity = {2.4, 3.2, 3.8, 5.0, 8.0};
            double[] multiTier = {0.6, 0.6, 0.6, 0.2, 0.15};

            int t = tier.toInt();
            int r = rarity.toInt();

            Double hp;

            {
                double x = 0;
                double y = multiRarity[(r-1)];

                if (slot == 1 || slot == 4) {
                    x = helmboots[(t-1)];
                } else {
                    x = chestlegs[(t-1)];
                }

                double lowHp = Math.ceil(Math.ceil(x * y) - Math.ceil(x * multiTier[(t-1)]));
                double highHp = Math.ceil(Math.ceil(x * y));

                hp = lowHp + Math.random() * (highHp - lowHp);

                lore.add(ChatColor.RED + "HP: +" + Integer.valueOf(hp.intValue()) + "");
            }
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addEnergy(ItemStack item, List<String> lore, Tier tier) {

        try {

            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            if (d == 0) {
                int energyL[] = {1, 2, 2, 3, 3};
                int energyH[] = {3, 4, 5, 6, 6};

                int low = energyL[(t-1)];
                int high = energyH[(t-1)];

                Double energy = low + Math.random() * (high - low);

                this.lore.add(ChatColor.RED + "ENERGY: +" + energy.intValue());
            } else {
                Double hps = 0.0;

                int hp = 0;

                String oldString = null;
                for (String line : lore) {
                    if (line.contains("HP")) {
                        oldString = line;
                    }
                }

                oldString = ChatColor.stripColor(oldString);

                String newString = oldString.substring(oldString.indexOf(":") + 2);

                if (newString.contains("%"))
                    newString = newString.replace("%", "");
                if (newString.contains("+"))
                    newString = newString.replace("+", "");

                hp = Integer.parseInt(newString);;

                hps = (double) (hp * 0.27);

                this.lore.add(ChatColor.RED + "HP REGEN: +" + hps.intValue());
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean addArmorDPs(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            int low[] = {3, 4, 8, 10, 12};
            int high[] = {6, 8, 12, 16, 20};

            if (d == 0) {
                int lowArmor = low[(t-1)];
                int highArmor = high[(t-1)];

                Double armor = lowArmor + Math.random() * (highArmor - lowArmor);

                lore.add(ChatColor.RED + "ARMOR: +" + armor.intValue() +"%");
            } else {
                int lowDps = low[(t-1)];
                int highDps = high[(t-1)];

                Double dps = lowDps + Math.random() * (highDps - lowDps);

                lore.add(ChatColor.RED + "DPs: +" + dps.intValue() + "%");

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean addRandomStats(List<String> lore, Tier tier) {

        boolean accepted = false;

        Random ran = new Random();
        int d = ran.nextInt(2);

        if (d == 1) {
            accepted = loreInt(lore, tier);
        } else if (d == 2) {
            accepted = loreVit(lore, tier);
        } else if (d == 3) {
            accepted = loreDex(lore, tier);
        } else {
            accepted = loreStr(lore, tier);
        }

        accepted = loreDodge(lore, tier);
        accepted = loreBlock(lore, tier);
        accepted = loreReflect(lore, tier);
        accepted = loreResistance(lore, tier);
        accepted = loreThorns(lore, tier);
        accepted = loreGemFind(lore, tier);
        accepted = loreItemFind(lore, tier);

        return accepted;
    }

    public static boolean loreInt(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {10, 20, 30, 40, 50};
            int high[] = {200, 250, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "INT: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreVit(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {10, 20, 30, 40, 50};
            int high[] = {200, 250, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "VIT: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreDex(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {10, 20, 30, 40, 50};
            int high[] = {200, 250, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "DEX: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreStr(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {10, 20, 30, 40, 50};
            int high[] = {200, 250, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "STR: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreDodge(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {2, 4, 4, 6, 6};
            int high[] = {6, 8, 10, 12, 14};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "DODGE: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreBlock(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {2, 4, 4, 6, 6};
            int high[] = {6, 8, 10, 12, 14};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "BLOCK: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreReflect(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {2, 4, 4, 6, 6};
            int high[] = {6, 8, 10, 12, 14};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "REFLECT: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreResistance(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {2, 4, 4, 6, 6};
            int high[] = {6, 8, 10, 12, 14};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "RESISTANCE: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreThorns(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {2, 4, 4, 6, 6};
            int high[] = {6, 8, 10, 12, 14};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "THORNS: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean loreGemFind(List<String> lore, Tier tier) {

        try {

            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {1, 1, 1, 1, 1};
            int high[] = {5, 5, 5, 5, 5};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "GEM FIND: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static boolean loreItemFind(List<String> lore, Tier tier) {

        try {

            Random ran = new Random();
            int d = ran.nextInt(2);

            int t = tier.toInt();

            String line = null;

            int low[] = {1, 1, 1, 1, 1};
            int high[] = {5, 5, 5, 5, 5};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = (ChatColor.RED + "ITEM FIND: +" + stat.intValue() + "%");

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public static ItemStack rerollStats(ItemStack item) {

        List<String> oldLore = item.getItemMeta().getLore();

        List<String> newLore = new ArrayList<String>();

        Tier tier = ItemMechanics.getTier(item);

        for (String line : oldLore) {
            // get hps
            if (line.contains("HPs") || line.contains("HP REGEN"))
                newLore.add(1, line);

                // keep after hps
            else if (line.contains("HP"))
                newLore.add(0, line);

            else if (line.contains("ENERGY"))
                newLore.add(1, line);

            else if (line.contains("ARMOR"))
                newLore.add(2, line);

            else {}
        }

        boolean accepted = false;

        accepted = addRandomStats(newLore, tier);
        if (accepted == false) {System.out.println("Item error");}

        ItemStack newItem = new ItemStack(item.getType());

        ItemMeta im = newItem.getItemMeta();

        im.setLore(newLore);

        newItem.setItemMeta(im);

        return newItem;
    }

    /**
     * @return the item
     */
    public ItemStack getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(ItemStack item) {
        this.item = item;
    }

    /**
     * @return the tier
     */
    public Tier getTier() {
        return tier;
    }

    /**
     * @param tier the tier to set
     */
    public void setTier(Tier tier) {
        this.tier = tier;
    }

    /**
     * @return the rarity
     */
    public Rarity getRarity() {
        return rarity;
    }

    /**
     * @param rarity the rarity to set
     */
    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

}
package me.Vinstaal0.Mechanics.Items;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Vinstaal0.Mechanics.ItemMechanics;
import me.Vinstaal0.Utility.EnumHelp;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Weapon implements implItem {

    public enum Type {
        Sword,
        Axe,
        Polearm,
        Staff,
        Bow;
    }

    private ItemStack item;
    private Material material = null;
    private String name = null;
    private ItemMeta im = null;
    private List<String> lore = new ArrayList<String>();
    private Tier tier;
    private Rarity rarity;
    private int type;

    public Weapon() {
        this.setTier(EnumHelp.randomEnum(Tier.class));
        this.setRarity(EnumHelp.randomEnum(Rarity.class));
        this.type = ThreadLocalRandom.current().nextInt(1, 5 + 1);

        this.getWeapon();

    }

    public Weapon(int type) {
        this.setTier(EnumHelp.randomEnum(Tier.class));
        this.setRarity(EnumHelp.randomEnum(Rarity.class));
        this.type = type;

        this.getWeapon();

    }

    public Weapon(Tier tier) {
        this.setTier(tier);
        this.rarity = EnumHelp.randomEnum(Rarity.class);
        this.type = ThreadLocalRandom.current().nextInt(1, 5 + 1);

        this.getWeapon();
    }

    public Weapon(Tier tier, int type) {
        this.setTier(tier);
        this.rarity = EnumHelp.randomEnum(Rarity.class);
        this.type = type;

        this.getWeapon();
    }

    public Weapon(Tier tier, Rarity rarity) {
        this.setTier(tier);
        this.setRarity(rarity);
        this.type = ThreadLocalRandom.current().nextInt(1, 5 + 1);

        this.getWeapon();
    }

    public Weapon(Tier tier, Rarity rarity, int type) {
        this.setTier(tier);
        this.setRarity(rarity);
        this.type = type;

        this.getWeapon();
    }

    // Sword = 1, Axe = 2, Polearm = 3, Staff = 4, Bow = 5.
    public void getWeapon() {

        Tier tier = this.getTier();
        Rarity rarity = this.getRarity();
        int type = this.type;

        if (tier == Tier.ONE && type == 1) {
            material = Material.WOODEN_SWORD;
            name = ChatColor.GRAY + "Wooden Sword";
        } else if (tier == Tier.ONE && type == 2) {
            material = Material.WOODEN_AXE;
            name = ChatColor.GRAY + "Wooden Axe";
        } else if (tier == Tier.ONE && type == 3) {
            material = Material.WOODEN_SHOVEL;
            name = ChatColor.GRAY + "Wooden Polearm";
        } else if (tier == Tier.ONE && type == 4) {
            material = Material.WOODEN_HOE;
            name = ChatColor.GRAY + "Wooden Staff";
        } else if (tier == Tier.ONE && type == 5) {
            material = Material.BOW;
            name = ChatColor.GRAY + "Wooden Bow";

        } else if (tier == Tier.TWO && type == 1) {
            material = Material.STONE_SWORD;
            name = ChatColor.GREEN + "Stone Sword";
        } else if (tier == Tier.TWO && type == 2) {
            material = Material.STONE_AXE;
            name = ChatColor.GREEN + "Stone Axe";
        } else if (tier == Tier.TWO && type == 3) {
            material = Material.STONE_SHOVEL;
            name = ChatColor.GREEN + "Stone Polearm";
        } else if (tier == Tier.TWO && type == 4) {
            material = Material.STONE_HOE;
            name = ChatColor.GREEN + "Stone Staff";
        } else if (tier == Tier.TWO && type == 5) {
            material = Material.BOW;
            name = ChatColor.GREEN + "Stone Bow";
        }

        else if (tier == Tier.TREE && type == 1) {
            material = Material.IRON_SWORD;
            name = ChatColor.AQUA + "Steel Sword";
        } else if (tier == Tier.TREE && type == 2) {
            material = Material.IRON_AXE;
            name = ChatColor.AQUA + "Steel Axe";
        } else if (tier == Tier.TREE && type == 3) {
            material = Material.IRON_SHOVEL;
            name = ChatColor.AQUA + "Steel Polearm";
        } else if (tier == Tier.TREE && type == 4) {
            material = Material.IRON_HOE;
            name = ChatColor.AQUA + "Steel Staff";
        } else if (tier == Tier.TREE && type == 5) {
            material = Material.BOW;
            name = ChatColor.AQUA + "Steel Bow";
        }

        else if (tier == Tier.FOUR && type == 1) {
            material = Material.DIAMOND_SWORD;
            name = ChatColor.DARK_PURPLE + "Ivory Sword";
        } else if (tier == Tier.FOUR && type == 2) {
            material = Material.DIAMOND_AXE;
            name = ChatColor.DARK_PURPLE + "Ivory Axe";
        } else if (tier == Tier.FOUR && type == 3) {
            material = Material.DIAMOND_SHOVEL;
            name = ChatColor.DARK_PURPLE + "Ivory Polearm";
        } else if (tier == Tier.FOUR && type == 4) {
            material = Material.DIAMOND_HOE;
            name = ChatColor.DARK_PURPLE + "Ivory Staff";
        } else if (tier == Tier.FOUR && type == 5) {
            material = Material.BOW;
            name = ChatColor.DARK_PURPLE + "Ivory Bow";
        }

        else if (tier == Tier.FIVE && type == 1) {
            material = Material.GOLDEN_SWORD;
            name = ChatColor.YELLOW + "Golden Sword";
        } else if (tier == Tier.FIVE && type == 2) {
            material = Material.GOLDEN_AXE;
            name = ChatColor.YELLOW + "Golden Axe";
        } else if (tier == Tier.FIVE && type == 3) {
            material = Material.GOLDEN_SHOVEL;
            name = ChatColor.YELLOW + "Golden Polearm";
        } else if (tier == Tier.FIVE && type == 4) {
            material = Material.GOLDEN_HOE;
            name = ChatColor.YELLOW + "Golden Staff";
        } else if (tier == Tier.FIVE && type == 5) {
            material = Material.BOW;
            name = ChatColor.YELLOW + "Golden Bow";
        } else {
            material = Material.LADDER;
            name = ChatColor.BLACK + "ERROR 404";
        }

        this.item = new ItemStack(material);

        this.im = item.getItemMeta();

        Boolean accepted = false;

        accepted = addDmg(item, tier, rarity, type);
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

    private boolean addDmg(ItemStack item, Tier tier, Rarity rarity, int type) {

        try {
            // Base (Tiers)
            int[] swordL = {12, 70, 200, 800, 3250};
            int[] swordH = {22, 100, 239, 890, 3700};

            int[] axeL = {14, 71, 216, 865, 3400};
            int[] axeH = {24, 108, 251, 978, 3900};

            int[] staffL = {8, 59, 170, 690, 3100};
            int[] staffH = {17, 83, 207, 790, 3500};

            int[] bowL = {18, 90, 250, 890, 3825};
            int[] bowH = {28, 120, 290, 1040, 4200};

            double[] multiRarity = {1, 1.75, 2.5, 3.35, 4.5};

            double[] multiTier = {1, 1, 1.5, 1.5, 1.5};

            int t = tier.toInt();
            int r = rarity.toInt();

            Double rawLowDMG = 0.0;
            Double rawHighDMG = 0.0;

            Integer lowDMG = 0;
            Integer highDMG = 0;

            {
                double x = 0;
                double y = 0;
                double z = multiRarity[(r-1)];

                switch(type) {
                    case 1 :
                        x = swordL[(t-1)];
                        y = swordH[(t-1)];
                        break;
                    case 2 :
                        x = axeL[(t-1)];
                        y = axeH[(t-1)];
                        break;
                    case 3 : case 4 :
                        x = staffL[(t-1)];
                        y = staffH[(t-1)];
                        break;
                    case 5 :
                        x = bowL[(t-1)];
                        y = bowH[(t-1)];
                        break;
                }

                rawLowDMG = Math.ceil(Math.ceil(x * z) * multiTier[(t-1)]);
                rawHighDMG = Math.ceil(Math.ceil(y * z) * multiTier[(t-1)]);

                Double random1 = rawLowDMG + Math.random() * (rawHighDMG - rawLowDMG);
                Double random2 = rawLowDMG + Math.random() * (rawHighDMG - rawLowDMG);

                if (random1 > random2 || random1 == random2) {
                    lowDMG = Integer.valueOf(random2.intValue());
                    highDMG = Integer.valueOf(random1.intValue());
                } else {
                    lowDMG = Integer.valueOf(random1.intValue());
                    highDMG = Integer.valueOf(random2.intValue());
                }

            }

            this.lore.add(ChatColor.RED + "DMG: " + Integer.valueOf(lowDMG.intValue()) + " - " + Integer.valueOf(highDMG.intValue()));

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
            accepted = lorePoison(lore, tier);
        } else if (d == 2) {
            accepted = loreIce(lore, tier);
        } else if (d == 3) {
            accepted = loreFire(lore, tier);
        } else {
            accepted = lorePure(lore, tier);
        }

        accepted = loreLifeSteal(lore, tier);
        accepted = loreVsPlayers(lore, tier);
        accepted = loreVsMonsters(lore, tier);
        accepted = loreCritHit(lore, tier);
        accepted = loreBluntHit(lore, tier);
        accepted = loreAccuracy(lore, tier);
        accepted = loreSlowness(lore, tier);
        accepted = loreArmorPen(lore, tier);
        accepted = loreBlind(lore, tier);

        return accepted;
    }

    private static boolean lorePoison(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {5, 20, 30, 40, 50};
            int high[] = {50, 150, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "POISON DMG: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreIce(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {5, 20, 30, 40, 50};
            int high[] = {50, 150, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "ICE DMG: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreFire(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {5, 20, 30, 40, 50};
            int high[] = {50, 150, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "FIRE DMG: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean lorePure(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {5, 20, 30, 40, 50};
            int high[] = {50, 150, 300, 350, 400};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "FIRE DMG: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreLifeSteal(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "LIFESTEAL: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreVsPlayers(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "vs. PLAYERS: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreVsMonsters(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "vs. MONSTERS: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreCritHit(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "CRIT HIT: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreBluntHit(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "BLUND HIT: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreAccuracy(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "ACCURACY: +" + stat.intValue();

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreSlowness(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "SLOWNESS: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreArmorPen(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "ARMORPEN: +" + stat.intValue() + "%";

                lore.add(line);
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean loreBlind(List<String> lore, Tier tier) {

        try {
            Random ran = new Random();
            int d = ran.nextInt(4);

            int t = tier.toInt();

            String line = null;

            int low[] = {3, 3, 3, 3, 3};
            int high[] = {20, 20, 20, 20, 20};

            if (d == 0) {
                int lowStat = low[(t-1)];
                int highStat = high[(t-1)];

                Double stat = lowStat + Math.random() * (highStat - lowStat);

                line = ChatColor.RED + "BLIND: +" + stat.intValue() + "%";

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

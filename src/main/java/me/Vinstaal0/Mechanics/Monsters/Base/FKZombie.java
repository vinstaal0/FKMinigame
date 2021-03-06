package me.Vinstaal0.Mechanics.Monsters.Base;

import me.Vinstaal0.Mechanics.ItemMechanics.ItemMechanics;
import net.minecraft.server.v1_13_R2.EntityZombie;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;

import me.Vinstaal0.Mechanics.ItemMechanics.Items.Armour;
import me.Vinstaal0.Mechanics.ItemMechanics.Items.Weapon;
import me.Vinstaal0.Mechanics.ItemMechanics.Items.Weapon.Type;
import me.Vinstaal0.Mechanics.Monsters.FKmob;
import me.Vinstaal0.Utility.EnumHelp;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;
import org.bukkit.attribute.Attribute;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class FKZombie extends EntityZombie implements FKmob {

    public Zombie zombie = null;

    private ItemStack helmet = null;
    private ItemStack chestPlate = null;
    private ItemStack leggings = null;
    private ItemStack boots = null;

    private Tier tier = Tier.ONE;
    private Rarity rarity = Rarity.COMMON;

    private final ItemStack weapon = null;

    public FKZombie (World world, Location loc) {
        super(((CraftWorld)world).getHandle());

        Entity mob = world.spawnEntity(loc, EntityType.ZOMBIE);

        if (mob instanceof Zombie) {
            zombie = (Zombie) mob;
        }

        this.giveArmour();
        this.giveWeapon();
        this.setName(ChatColor.WHITE + "[-] Random.org");

        this.zombie.getEquipment().setHelmetDropChance(100.0F);
        this.zombie.getEquipment().setChestplateDropChance(100.0F);
        this.zombie.getEquipment().setLeggingsDropChance(100.0F);
        this.zombie.getEquipment().setBootsDropChance(100.0F);
        this.zombie.getEquipment().setItemInMainHandDropChance(100.0F);

        this.zombie.setBaby(false);

        try {
            this.setHealth();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public FKZombie (World world, Location loc, Tier tier, Rarity rarity) {
        super(((CraftWorld)world).getHandle());

        this.tier = tier;
        this.rarity = rarity;

        Entity mob = world.spawnEntity(loc, EntityType.ZOMBIE);

        if (mob instanceof Zombie) {
            zombie = (Zombie) mob;
        }

        this.giveArmour(this.tier, this.rarity);
        this.giveWeapon(this.tier, this.rarity);

        try {
            this.setHealth();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setName(this.tier, this.rarity);
        this.setBaby(false);
    }

    public FKZombie (World world, Location loc, Tier tier, Rarity rarity, int armourType, Weapon.Type weaponType) {
        super(((CraftWorld)world).getHandle());

        this.tier = tier;
        this.rarity = rarity;

        Entity mob = world.spawnEntity(loc, EntityType.ZOMBIE);

        if (mob instanceof Zombie) {
            zombie = (Zombie) mob;
        }

        this.giveArmour(this.tier, this.rarity, armourType);
        this.giveWeapon(this.tier, this.rarity, weaponType);

        try {
            this.setHealth();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.setName(this.tier, this.rarity);
        this.setBaby(false);
    }

    @Override
    public void giveArmour() {
        Tier tier = EnumHelp.randomEnum(Tier.class);
        Rarity rarity = EnumHelp.randomEnum(Rarity.class);
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
        giveArmour(tier, rarity, randomNum);
    }

    @Override
    public void giveArmour(Tier tier) {
        Rarity rarity = EnumHelp.randomEnum(Rarity.class);
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
        giveArmour(tier, rarity, randomNum);
    }

    @Override
    public void giveArmour(Rarity rarity) {
        Tier tier = EnumHelp.randomEnum(Tier.class);
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
        giveArmour(tier, rarity, randomNum);
    }

    @Override
    public void giveArmour(int type) {
        Tier tier = EnumHelp.randomEnum(Tier.class);
        Rarity rarity = EnumHelp.randomEnum(Rarity.class);
        giveArmour(tier, rarity, type);
    }

    @Override
    public void giveArmour(Tier tier, Rarity rarity) {
        int randomNum = ThreadLocalRandom.current().nextInt(1, 8 + 1);
        giveArmour(tier, rarity, randomNum);
    }

    @Override
    public void giveArmour(Tier tier, Rarity rarity, int type) {
        switch(type) {
            case 1 :
                break;
            case 2 :
                this.chestPlate = new Armour(tier, Rarity.getRandomRarityBelow(rarity),2).getItem();
                break;
            case 3 :
                this.leggings = new Armour(tier, Rarity.getRandomRarityBelow(rarity),3).getItem();
                break;
            case 4 :
                this.boots = new Armour(tier, Rarity.getRandomRarityBelow(rarity),4).getItem();
                break;
            case 5 :
                this.chestPlate = new Armour(tier, Rarity.getRandomRarityBelow(rarity),2).getItem();
                this.leggings = new Armour(tier, Rarity.getRandomRarityBelow(rarity),3).getItem();
                break;
            case 6 :
                this.leggings = new Armour(tier, Rarity.getRandomRarityBelow(rarity),3).getItem();
                this.boots = new Armour(tier, Rarity.getRandomRarityBelow(rarity),4).getItem();
                break;
            case 7 :
                this.chestPlate = new Armour(tier, Rarity.getRandomRarityBelow(rarity),2).getItem();
                this.boots = new Armour(tier, Rarity.getRandomRarityBelow(rarity),4).getItem();
                break;
            case 8 :
                this.chestPlate = new Armour(tier, Rarity.getRandomRarityBelow(rarity),2).getItem();
                this.leggings = new Armour(tier, Rarity.getRandomRarityBelow(rarity),3).getItem();
                this.boots = new Armour(tier, Rarity.getRandomRarityBelow(rarity),4).getItem();
                break;
            default :
                this.helmet = new Armour(tier, Rarity.getRandomRarityBelow(rarity),1).getItem();
                this.chestPlate = new Armour(tier, Rarity.getRandomRarityBelow(rarity),2).getItem();
                this.leggings = new Armour(tier, Rarity.getRandomRarityBelow(rarity),3).getItem();
                this.boots = new Armour(tier, Rarity.getRandomRarityBelow(rarity),4).getItem();
                break;
        }

        this.zombie.getEquipment().setHelmet(this.helmet);
        this.zombie.getEquipment().setChestplate(this.chestPlate);
        this.zombie.getEquipment().setLeggings(this.leggings);
        this.zombie.getEquipment().setBoots(this.boots);
    }

    @Override
    public void giveArmourRandom(Tier tier, int type) {
        switch(type) {
            case 1 :
                break;
            case 2 :
                this.chestPlate = new Armour(tier, EnumHelp.randomEnum(Rarity.class),2).getItem();
                break;
            case 3 :
                this.leggings = new Armour(tier, EnumHelp.randomEnum(Rarity.class),3).getItem();
                break;
            case 4 :
                this.boots = new Armour(tier, EnumHelp.randomEnum(Rarity.class),4).getItem();
                break;
            case 5 :
                this.chestPlate = new Armour(tier, EnumHelp.randomEnum(Rarity.class),2).getItem();
                this.leggings = new Armour(tier, EnumHelp.randomEnum(Rarity.class),3).getItem();
                break;
            case 6 :
                this.leggings = new Armour(tier, EnumHelp.randomEnum(Rarity.class),3).getItem();
                this.boots = new Armour(tier, EnumHelp.randomEnum(Rarity.class),4).getItem();
                break;
            case 7 :
                this.chestPlate = new Armour(tier, EnumHelp.randomEnum(Rarity.class),2).getItem();
                this.boots = new Armour(tier, EnumHelp.randomEnum(Rarity.class),4).getItem();
                break;
            case 8 :
                this.chestPlate = new Armour(tier, EnumHelp.randomEnum(Rarity.class),2).getItem();
                this.leggings = new Armour(tier, EnumHelp.randomEnum(Rarity.class),3).getItem();
                this.boots = new Armour(tier, EnumHelp.randomEnum(Rarity.class),4).getItem();
                break;
            default :
                this.helmet = new Armour(tier, EnumHelp.randomEnum(Rarity.class),1).getItem();
                this.chestPlate = new Armour(tier, EnumHelp.randomEnum(Rarity.class),2).getItem();
                this.leggings = new Armour(tier, EnumHelp.randomEnum(Rarity.class),3).getItem();
                this.boots = new Armour(tier, EnumHelp.randomEnum(Rarity.class),4).getItem();
                break;
        }

        this.zombie.getEquipment().setHelmet(this.helmet);
        this.zombie.getEquipment().setChestplate(this.chestPlate);
        this.zombie.getEquipment().setLeggings(this.leggings);
        this.zombie.getEquipment().setBoots(this.boots);
    }

    @Override
    public void giveWeapon() {
        Tier tier = EnumHelp.randomEnum(Tier.class);
        Rarity rarity = EnumHelp.randomEnum(Rarity.class);
        Weapon.Type type = EnumHelp.randomEnum(Weapon.Type.class);
        giveWeapon(tier, rarity, type);
    }

    @Override
    public void giveWeapon(Tier tier) {
        Rarity rarity = EnumHelp.randomEnum(Rarity.class);
        Weapon.Type type = EnumHelp.randomEnum(Weapon.Type.class);
        giveWeapon(tier, rarity, type);
    }

    @Override
    public void giveWeapon(Rarity rarity) {
        Tier tier = EnumHelp.randomEnum(Tier.class);
        Weapon.Type type = EnumHelp.randomEnum(Weapon.Type.class);
        giveWeapon(tier, rarity, type);
    }

    @Override
    public void giveWeapon(Type type) {
        Tier tier = EnumHelp.randomEnum(Tier.class);
        Rarity rarity = EnumHelp.randomEnum(Rarity.class);
        giveWeapon(tier, rarity, type);
    }

    @Override
    public void giveWeapon(Tier tier, Rarity rarity) {
        Weapon.Type type = EnumHelp.randomEnum(Weapon.Type.class);
        giveWeapon(tier, rarity, type);
    }

    @Override
    public void giveWeapon(Tier tier, Rarity rarity, Type type) {
        switch (type) {
            case Sword:
                this.zombie.getEquipment().setItemInMainHand(new Weapon(tier, rarity, 1).getItem());
                break;
            case Axe:
                this.zombie.getEquipment().setItemInMainHand(new Weapon(tier, rarity, 2).getItem());
                break;
            case Bow:
                this.zombie.getEquipment().setItemInMainHand(new Weapon(tier, rarity, 3).getItem());
                break;
            case Polearm:
                this.zombie.getEquipment().setItemInMainHand(new Weapon(tier, rarity, 4).getItem());
                break;
            case Staff:
                this.zombie.getEquipment().setItemInMainHand(new Weapon(tier, rarity, 5).getItem());
                break;
            default:
                this.zombie.getEquipment().setItemInMainHand(new Weapon(tier, rarity).getItem());

        }

    }

    @Override
    public void setName(String name) {

        this.zombie.setCustomName(name);

    }

    public void setName(Tier tier, Rarity rarity) {

        String prefix = "";
        ChatColor chatColor = ChatColor.WHITE;
        String name = "Zombie";

        switch (rarity) {
            case COMMON:
                prefix = "[-] ";
                break;
            case UNCOMMON:
                prefix = "[=] ";
                break;
            case RARE:
                prefix = "[☰] ";
                break;
            case UNIQUE:
                prefix = "[#] ";
                break;
            case ANCIENT:
                prefix = "[+] ";
                break;
            default:
                prefix = "[T] ";
        }

        switch (tier) {

            case ONE:
                chatColor = ChatColor.WHITE;
                name = "Zombie";
                break;
            case TWO:
                chatColor = ChatColor.GREEN;
                name = "Tough Zombie";
                break;
            case TREE:
                chatColor = ChatColor.AQUA;
                name = "White Walker";
                break;
            case FOUR:
                chatColor = ChatColor.DARK_PURPLE;
                name = "Zombie Naga";
                break;
            case FIVE:
                chatColor = ChatColor.YELLOW;
                name = "Solar Eclipse Zombie Naga";
                break;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(chatColor.toString());
        sb.append(prefix);
        sb.append(name);

        this.zombie.setCustomName(sb.toString());

    }

    public void setHealth() {
        Double hp = 100.0;

        System.out.println("Setting HP");

        ItemStack[] armour = {this.helmet, this.chestPlate, this.leggings, this.boots};

        int[] base = {5, 350, 4075, 16000, 75000};

        int[] low = {53, 280, 900, 3000, 7000};
        int[] high = {77, 450, 1425, 5000, 11000};

        double[] armStr = {1, 1.05, 1.1, 1.15, 1.25};

        double[] multiT5 = {1, 1.25, 1.5, 1.8, 2.15};

        int tier = this.tier.toInt();
        int rarity = this.rarity.toInt();

        double hpLow = Math.ceil((base[tier-1] + rarity * low[tier-1]) * armStr[tier-1]);
        double hpHigh = Math.ceil((base[tier-1] + rarity * high[tier-1]) * armStr[tier-1]);

        if (this.tier == Tier.FIVE) {
            hpLow *= multiT5[rarity-1];
            hpHigh *= multiT5[rarity-1];
        }

        for (ItemStack armourPiece : armour) {

            if (ItemMechanics.isArmor(armourPiece)) {
                List<String> lore = armourPiece.getItemMeta().getLore();

                for (String line : lore) {
                    // get hps
                    if (line.contains("HPs") || line.contains("HP REGEN")) {

                        // keep after hps
                    } else if (line.contains("HP")) {

                        hp += ItemMechanics.getStat(armourPiece, lore.indexOf(line));

                    } else if (line.contains("ENERGY")) {

                    } else if (line.contains("ARMOR")) {

                    } else if (line.contains("DPs")) {

                    } else if (line.contains("INT")) {

                    } else if (line.contains("VIT")) {

                    } else if (line.contains("DEX")) {

                    } else if (line.contains("STR")) {

                    } else if (line.contains("DODGE")) {

                    } else if (line.contains("BLOCK")) {

                    } else if (line.contains("REFLECT")) {

                    } else if (line.contains("RESISTANCE")) {

                    } else if (line.contains("THORNS")) {

                    } else if (line.contains("GEM FIND")) {

                    } else if (line.contains("ITEM FIND")) {

                    } else {}
                }
            }
        }

        double addition = hpLow + Math.random() * (hpHigh - hpLow);

        setHealth(hp + addition);
    }

//    public void setHealth() {
//        Double hp = 100.0;
//
//        System.out.println("Setting HP");
//
//        ItemStack[] armour = {this.helmet, this.chestPlate, this.leggings, this.boots};
//
//        for (ItemStack armourPiece : armour) {
//
//            System.out.println("armourpieces " + armourPiece);
//
//            if (ItemMechanics.isArmor(armourPiece)) {
//                List<String> lore = armourPiece.getItemMeta().getLore();
//
//                System.out.println("Lore " + lore);
//
//                for (String line : lore) {
//                    // get hps
//                    if (line.contains("HPs") || line.contains("HP REGEN")) {
//
//                        // keep after hps
//                    } else if (line.contains("HP")) {
//
//                        System.out.println("Piece = " + armourPiece);
//                        System.out.println("HP = " + hp.intValue());
//                        System.out.println("Adding = " + ItemMechanics.getStat(armourPiece, lore.indexOf(line)));
//                        hp += ItemMechanics.getStat(armourPiece, lore.indexOf(line));
//                        System.out.println("HP = " + hp.intValue());
//
//                    } else if (line.contains("ENERGY")) {
//
//                    } else if (line.contains("ARMOR")) {
//
//                    } else if (line.contains("DPs")) {
//
//                    } else if (line.contains("INT")) {
//
//                    } else if (line.contains("VIT")) {
//
//                    } else if (line.contains("DEX")) {
//
//                    } else if (line.contains("STR")) {
//
//                    } else if (line.contains("DODGE")) {
//
//                    } else if (line.contains("BLOCK")) {
//
//                    } else if (line.contains("REFLECT")) {
//
//                    } else if (line.contains("RESISTANCE")) {
//
//                    } else if (line.contains("THORNS")) {
//
//                    } else if (line.contains("GEM FIND")) {
//
//                    } else if (line.contains("ITEM FIND")) {
//
//                    } else {}
//                }
//            }
//        }
//
//        setHealth(hp);
//    }

    public void setHealth(Double hp) {
        zombie.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(hp);
        zombie.setHealth(hp);
    }

}
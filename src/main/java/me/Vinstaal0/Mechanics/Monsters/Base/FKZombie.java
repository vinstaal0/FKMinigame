package me.Vinstaal0.Mechanics.Monsters.Base;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;

import me.Vinstaal0.Mechanics.Items.Armour;
import me.Vinstaal0.Mechanics.Items.Weapon;
import me.Vinstaal0.Mechanics.Items.Weapon.Type;
import me.Vinstaal0.Mechanics.Monsters.FKmob;
import me.Vinstaal0.Utility.EnumHelp;
import me.Vinstaal0.Utility.Rarity;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class FKZombie extends EntityZombie implements FKmob{

    public Tier tier;
    public Zombie zombie = null;

    public FKZombie(World world, Location loc, Tier tier) {
        super(((CraftWorld)world).getHandle());

        Entity mob = world.spawnEntity(loc, EntityType.ZOMBIE);

        if (mob instanceof Zombie) {
            zombie = (Zombie) mob;
        }

//        zombie.setCustomName("[-] Testing");
//
//        zombie.getEquipment().setHelmet(new Armour(1).getItem());
//        zombie.getEquipment().setChestplate(new Armour(2).getItem());
//        zombie.getEquipment().setLeggings(new Armour(3).getItem());
//        zombie.getEquipment().setBoots(new Armour(4).getItem());
//
//        zombie.getEquipment().setItemInHand(new Weapon().getItem());
//
//        zombie.setCanPickupItems(false);
//        zombie.setBaby(false);
//
//        zombie.setMaxHealth(20000);
//        zombie.setHealth(20000);
    }

    @Override
    public void giveArmour() {
        giveArmour(0);
    }

    @Override
    public void giveArmour(int type) {

        switch(type) {
            case 1 :
                break;
            case 2 :
                this.zombie.getEquipment().setChestplate(new Armour(this.tier, 2).getItem());
                break;
            case 3 :
                this.zombie.getEquipment().setLeggings(new Armour(this.tier, 3).getItem());
                break;
            case 4 :
                this.zombie.getEquipment().setBoots(new Armour(this.tier, 4).getItem());
                break;
            case 5 :
                this.zombie.getEquipment().setChestplate(new Armour(this.tier, 2).getItem());
                this.zombie.getEquipment().setLeggings(new Armour(this.tier, 3).getItem());
                break;
            case 6 :
                this.zombie.getEquipment().setLeggings(new Armour(this.tier, 3).getItem());
                this.zombie.getEquipment().setBoots(new Armour(this.tier, 4).getItem());
                break;
            case 7 :
                this.zombie.getEquipment().setChestplate(new Armour(this.tier, 2).getItem());
                this.zombie.getEquipment().setBoots(new Armour(this.tier, 4).getItem());
                break;
            case 8 :
                this.zombie.getEquipment().setChestplate(new Armour(this.tier, 2).getItem());
                this.zombie.getEquipment().setLeggings(new Armour(this.tier, 3).getItem());
                this.zombie.getEquipment().setBoots(new Armour(this.tier, 4).getItem());
                break;
            default :
                this.zombie.getEquipment().setHelmet(new Armour(this.tier, 1).getItem());
                this.zombie.getEquipment().setChestplate(new Armour(this.tier, 2).getItem());
                this.zombie.getEquipment().setLeggings(new Armour(this.tier, 3).getItem());
                this.zombie.getEquipment().setBoots(new Armour(this.tier, 4).getItem());
                break;
        }

    }

    @Override
    public void giveWeapon() {
        giveWeapon(EnumHelp.randomEnum(Weapon.Type.class));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void giveWeapon(Type type) {
        switch(type) {
            case Sword :
                this.zombie.getEquipment().setItemInHand(new Weapon(this.tier, EnumHelp.randomEnum(Rarity.class),1).getItem());
                break;
            case Axe:
                this.zombie.getEquipment().setItemInHand(new Weapon(this.tier, EnumHelp.randomEnum(Rarity.class),2).getItem());
                break;
            case Bow:
                this.zombie.getEquipment().setItemInHand(new Weapon(this.tier, EnumHelp.randomEnum(Rarity.class),3).getItem());
                break;
            case Polearm:
                this.zombie.getEquipment().setItemInHand(new Weapon(this.tier, EnumHelp.randomEnum(Rarity.class),4).getItem());
                break;
            case Staff:
                this.zombie.getEquipment().setItemInHand(new Weapon(this.tier, EnumHelp.randomEnum(Rarity.class),5).getItem());
                break;
            default:
                this.zombie.getEquipment().setItemInHand(new Weapon(this.tier, EnumHelp.randomEnum(Rarity.class)).getItem());
                break;
        }

    }

    @Override
    public void setName(String name) {

        this.zombie.setCustomName(name);

    }

}
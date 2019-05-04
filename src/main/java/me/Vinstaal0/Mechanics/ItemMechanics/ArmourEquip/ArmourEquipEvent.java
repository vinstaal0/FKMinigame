package me.Vinstaal0.Mechanics.ItemMechanics.ArmourEquip;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @Author Borlea
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @since Jul 30, 2015
 */
public final class ArmourEquipEvent extends PlayerEvent implements Cancellable{

    private static final HandlerList handlers = new HandlerList();
    private boolean cancel = false;
    private final EquipMethod equipType;
    private final ArmourType type;
    private ItemStack oldArmourPiece, newArmourPiece;

    /**
     * Constructor for the ArmourEquipEvent.
     *
     * @param player The player who put on / removed the armour.
     * @param type The ArmourType of the armour added
     * @param oldArmourPiece The ItemStack of the armour removed.
     * @param newArmourPiece The ItemStack of the armour added.
     */
    public ArmourEquipEvent(final Player player, final EquipMethod equipType, final ArmourType type, final ItemStack oldArmourPiece, final ItemStack newArmourPiece){
        super(player);
        this.equipType = equipType;
        this.type = type;
        this.oldArmourPiece = oldArmourPiece;
        this.newArmourPiece = newArmourPiece;
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    public final static HandlerList getHandlerList(){
        return handlers;
    }

    /**
     * Gets a list of handlers handling this event.
     *
     * @return A list of handlers handling this event.
     */
    @Override
    public final HandlerList getHandlers(){
        return handlers;
    }

    /**
     * Sets if this event should be cancelled.
     *
     * @param cancel If this event should be cancelled.
     */
    public final void setCancelled(final boolean cancel){
        this.cancel = cancel;
    }

    /**
     * Gets if this event is cancelled.
     *
     * @return If this event is cancelled
     */
    public final boolean isCancelled(){
        return cancel;
    }

    public final ArmourType getType(){
        return type;
    }

    /**
     * Returns the last equipped armour piece, could be a piece of armour or null.
     */
    public final ItemStack getOldArmourPiece(){
        return oldArmourPiece;
    }

    public final void setOldArmourPiece(final ItemStack oldArmourPiece){
        this.oldArmourPiece = oldArmourPiece;
    }

    /**
     * Returns the newly equipped armour, could be a piece of armour or null.
     */
    public final ItemStack getNewArmourPiece(){
        return newArmourPiece;
    }

    public final void setNewArmourPiece(final ItemStack newArmourPiece){
        this.newArmourPiece = newArmourPiece;
    }

    /**
     * Gets the method used to either equip or unequip an armor piece.
     */
    public EquipMethod getMethod(){
        return equipType;
    }

    public enum EquipMethod{// These have got to be the worst documentations ever.
        /**
         * When you shift click an armor piece to equip or unequip
         */
        SHIFT_CLICK,
        /**
         * When you drag and drop the item to equip or unequip
         */
        DRAG,
        /**
         * When you manually equip or unequip the item. Use to be DRAG
         */
        PICK_DROP,
        /**
         * When you right click an armor piece in the hotbar without the inventory open to equip.
         */
        HOTBAR,
        /**
         * When you press the hotbar slot number while hovering over the armor slot to equip or unequip
         */
        HOTBAR_SWAP,
        /**
         * When in range of a dispenser that shoots an armor piece to equip.<br>
         * Requires the spigot version to have {org.bukkit.event.block.BlockDispenseArmorEvent} implemented.
         */
        DISPENSER,
        /**
         * When an armor piece is removed due to it losing all durability.
         */
        BROKE,
        /**
         * When you die causing all armor to unequip
         */
        DEATH,
        ;
    }
}
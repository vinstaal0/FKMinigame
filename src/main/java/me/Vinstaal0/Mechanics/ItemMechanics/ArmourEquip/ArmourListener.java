package me.Vinstaal0.Mechanics.ItemMechanics.ArmourEquip;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.inventory.ItemStack;

/**
 * @Author Borlea
 * @Github https://github.com/borlea/
 * @Website http://codingforcookies.com/
 * @since Jul 30, 2015 6:43:34 PM
 */
public class ArmourListener implements Listener{

    private final List<String> blockedMaterials;

    public ArmourListener(){
        List<String> blockedMaterials = new ArrayList<>();
        blockedMaterials.add("Dispenser");
        this.blockedMaterials = blockedMaterials;
    }

    @EventHandler
    public final void onInventoryClick(final InventoryClickEvent e){
        boolean shift = false, numberkey = false;
        if(e.isCancelled()) return;
        if(e.getAction() == InventoryAction.NOTHING) return;// Why does this get called if nothing happens??
        if(e.getClick().equals(ClickType.SHIFT_LEFT) || e.getClick().equals(ClickType.SHIFT_RIGHT)){
            shift = true;
        }
        if(e.getClick().equals(ClickType.NUMBER_KEY)){
            numberkey = true;
        }
        if(e.getSlotType() != SlotType.ARMOR && e.getSlotType() != SlotType.QUICKBAR && e.getSlotType() != SlotType.CONTAINER) return;
        if(e.getClickedInventory() != null && !e.getClickedInventory().getType().equals(InventoryType.PLAYER)) return;
        if (!e.getInventory().getType().equals(InventoryType.CRAFTING) && !e.getInventory().getType().equals(InventoryType.PLAYER)) return;
        if(!(e.getWhoClicked() instanceof Player)) return;
        ArmourType newArmourType = ArmourType.matchType(shift ? e.getCurrentItem() : e.getCursor());
        if(!shift && newArmourType != null && e.getRawSlot() != newArmourType.getSlot()){
            // Used for drag and drop checking to make sure you aren't trying to place a helmet in the boots slot.
            return;
        }
        if(shift){
            newArmourType = ArmourType.matchType(e.getCurrentItem());
            if(newArmourType != null){
                boolean equipping = true;
                if(e.getRawSlot() == newArmourType.getSlot()){
                    equipping = false;
                }
                if(newArmourType.equals(ArmourType.HELMET) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getHelmet()) : !isAirOrNull(e.getWhoClicked().getInventory().getHelmet()))
                        || newArmourType.equals(ArmourType.CHESTPLATE) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getChestplate()) :
                        !isAirOrNull(e.getWhoClicked().getInventory().getChestplate())) || newArmourType.equals(ArmourType.LEGGINGS) && (equipping ?
                        isAirOrNull(e.getWhoClicked().getInventory().getLeggings()) : !isAirOrNull(e.getWhoClicked().getInventory().getLeggings()))
                        || newArmourType.equals(ArmourType.BOOTS) && (equipping ? isAirOrNull(e.getWhoClicked().getInventory().getBoots()) :
                        !isAirOrNull(e.getWhoClicked().getInventory().getBoots()))){
                    ArmourEquipEvent armourEquipEvent = new ArmourEquipEvent((Player) e.getWhoClicked(), ArmourEquipEvent.EquipMethod.SHIFT_CLICK, newArmourType, equipping ? null : e.getCurrentItem(), equipping ? e.getCurrentItem() : null);
                    Bukkit.getServer().getPluginManager().callEvent(armourEquipEvent);
                    if(armourEquipEvent.isCancelled()){
                        e.setCancelled(true);
                    }
                }
            }
        }else{
            ItemStack newArmourPiece = e.getCursor();
            ItemStack oldArmourPiece = e.getCurrentItem();
            if(numberkey){
                if(e.getClickedInventory().getType().equals(InventoryType.PLAYER)){// Prevents shit in the 2by2 crafting
                    // e.getClickedInventory() == The players inventory
                    // e.getHotBarButton() == key people are pressing to equip or unequip the item to or from.
                    // e.getRawSlot() == The slot the item is going to.
                    // e.getSlot() == Armour slot, can't use e.getRawSlot() as that gives a hotbar slot ;-;
                    ItemStack hotbarItem = e.getClickedInventory().getItem(e.getHotbarButton());
                    if(!isAirOrNull(hotbarItem)){// Equipping
                        newArmourType = ArmourType.matchType(hotbarItem);
                        newArmourPiece = hotbarItem;
                        oldArmourPiece = e.getClickedInventory().getItem(e.getSlot());
                    }else{// Unequipping
                        newArmourType = ArmourType.matchType(!isAirOrNull(e.getCurrentItem()) ? e.getCurrentItem() : e.getCursor());
                    }
                }
            }else{
                if(isAirOrNull(e.getCursor()) && !isAirOrNull(e.getCurrentItem())){// unequip with no new item going into the slot.
                    newArmourType = ArmourType.matchType(e.getCurrentItem());
                }
                // e.getCurrentItem() == Unequip
                // e.getCursor() == Equip
                // newArmourType = ArmourType.matchType(!isAirOrNull(e.getCurrentItem()) ? e.getCurrentItem() : e.getCursor());
            }
            if(newArmourType != null && e.getRawSlot() == newArmourType.getSlot()){
                ArmourEquipEvent.EquipMethod method = ArmourEquipEvent.EquipMethod.PICK_DROP;
                if(e.getAction().equals(InventoryAction.HOTBAR_SWAP) || numberkey) method = ArmourEquipEvent.EquipMethod.HOTBAR_SWAP;
                ArmourEquipEvent armourEquipEvent = new ArmourEquipEvent((Player) e.getWhoClicked(), method, newArmourType, oldArmourPiece, newArmourPiece);
                Bukkit.getServer().getPluginManager().callEvent(armourEquipEvent);
                if(armourEquipEvent.isCancelled()){
                    e.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void playerInteractEvent(PlayerInteractEvent e){
        if(e.getAction() == Action.PHYSICAL) return;
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            final Player player = e.getPlayer();
            if(e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK){// Having both of these checks is useless, might as well do it though.
                // Some blocks have actions when you right click them which stops the client from equipping the armour in hand.
                Material mat = e.getClickedBlock().getType();
                for(String s : blockedMaterials){
                    if(mat.name().equalsIgnoreCase(s)) return;
                }
            }
            ArmourType newArmourType = ArmourType.matchType(e.getItem());
            if(newArmourType != null){
                if(newArmourType.equals(ArmourType.HELMET) && isAirOrNull(e.getPlayer().getInventory().getHelmet()) || newArmourType.equals(ArmourType.CHESTPLATE) && isAirOrNull(e.getPlayer().getInventory().getChestplate()) || newArmourType.equals(ArmourType.LEGGINGS) && isAirOrNull(e.getPlayer().getInventory().getLeggings()) || newArmourType.equals(ArmourType.BOOTS) && isAirOrNull(e.getPlayer().getInventory().getBoots())){
                    ArmourEquipEvent armourEquipEvent = new ArmourEquipEvent(e.getPlayer(), ArmourEquipEvent.EquipMethod.HOTBAR, ArmourType.matchType(e.getItem()), null, e.getItem());
                    Bukkit.getServer().getPluginManager().callEvent(armourEquipEvent);
                    if(armourEquipEvent.isCancelled()){
                        e.setCancelled(true);
                        player.updateInventory();
                    }
                }
            }
        }
    }

    @EventHandler
    public void inventoryDrag(InventoryDragEvent event){
        // getType() seems to always be even.
        // Old Cursor gives the item you are equipping
        // Raw slot is the ArmourType slot
        // Can't replace armour using this method making getCursor() useless.
        ArmourType type = ArmourType.matchType(event.getOldCursor());
        if(event.getRawSlots().isEmpty()) return;// Idk if this will ever happen
        if(type != null && type.getSlot() == event.getRawSlots().stream().findFirst().orElse(0)){
            ArmourEquipEvent armourEquipEvent = new ArmourEquipEvent((Player) event.getWhoClicked(), ArmourEquipEvent.EquipMethod.DRAG, type, null, event.getOldCursor());
            Bukkit.getServer().getPluginManager().callEvent(armourEquipEvent);
            if(armourEquipEvent.isCancelled()){
                event.setResult(Result.DENY);
                event.setCancelled(true);
            }
        }
        // Debug shit
		/*System.out.println("Slots: " + event.getInventorySlots().toString());
		System.out.println("Raw Slots: " + event.getRawSlots().toString());
		if(event.getCursor() != null){
			System.out.println("Cursor: " + event.getCursor().getType().name());
		}
		if(event.getOldCursor() != null){
			System.out.println("OldCursor: " + event.getOldCursor().getType().name());
		}
		System.out.println("Type: " + event.getType().name());*/
    }

    @EventHandler
    public void itemBreakEvent(PlayerItemBreakEvent e){
        ArmourType type = ArmourType.matchType(e.getBrokenItem());
        if(type != null){
            Player p = e.getPlayer();
            ArmourEquipEvent armourEquipEvent = new ArmourEquipEvent(p, ArmourEquipEvent.EquipMethod.BROKE, type, e.getBrokenItem(), null);
            Bukkit.getServer().getPluginManager().callEvent(armourEquipEvent);
            if(armourEquipEvent.isCancelled()){
                ItemStack i = e.getBrokenItem().clone();
                i.setAmount(1);
                i.setDurability((short) (i.getDurability() - 1));
                if(type.equals(ArmourType.HELMET)){
                    p.getInventory().setHelmet(i);
                }else if(type.equals(ArmourType.CHESTPLATE)){
                    p.getInventory().setChestplate(i);
                }else if(type.equals(ArmourType.LEGGINGS)){
                    p.getInventory().setLeggings(i);
                }else if(type.equals(ArmourType.BOOTS)){
                    p.getInventory().setBoots(i);
                }
            }
        }
    }

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent e){
        Player p = e.getEntity();
        for(ItemStack i : p.getInventory().getArmorContents()){
            if(!isAirOrNull(i)){
                Bukkit.getServer().getPluginManager().callEvent(new ArmourEquipEvent(p, ArmourEquipEvent.EquipMethod.DEATH, ArmourType.matchType(i), i, null));
                // No way to cancel a death event.
            }
        }
    }

    private Location shift(Location start, BlockFace direction, int multiplier){
        if(multiplier == 0) return start;
        return new Location(start.getWorld(), start.getX() + direction.getModX() * multiplier, start.getY() + direction.getModY() * multiplier, start.getZ() + direction.getModZ() * multiplier);
    }

    /**
     * A utility method to support versions that use null or air ItemStacks.
     */
    private boolean isAirOrNull(ItemStack item){
        return item == null || item.getType().equals(Material.AIR);
    }
}
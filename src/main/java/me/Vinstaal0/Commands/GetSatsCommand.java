package me.Vinstaal0.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.Vinstaal0.Mechanics.ItemMechanics;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class GetSatsCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = (Player) sender;

        // TODO check why below line throwns a non important nullpointer exception
        ItemStack[] armorPieces = null;

        try {
            armorPieces = player.getInventory().getArmorContents();
        } catch (NullPointerException ignored) {}

        ArrayList<Tier> pieceTiers = new ArrayList<Tier>();

        int maxHP = 100;
        int maxEnergy = 1;
        int maxHPs = 5;
        int maxArmor = 0;
        int maxDPs = 0;
        int maxInt = 0;
        int maxVit = 0;
        int maxDex = 0;
        int maxStr = 0;
        int maxDodge = 0;
        int maxBlock = 0;
        int maxReflect = 0;
        int maxResistance = 0;
        int maxThorns = 0;
        int maxGemFind = 0;
        int maxItemFind = 0;

        // checks for changes in armor stats
        for (ItemStack armorPiece : armorPieces) {

            if (ItemMechanics.isArmor(armorPiece)) {

                List<String> lore = armorPiece.getItemMeta().getLore();
                pieceTiers.add(ItemMechanics.getTier(armorPiece));

                for (String line : lore) {
                    // get hps
                    if (line.contains("HPs") || line.contains("HP REGEN"))
                        maxHPs += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                        // keep after hps
                    else if (line.contains("HP"))
                        maxHP += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("ENERGY"))
                        maxEnergy += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("ARMOR"))
                        maxArmor += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("DPs"))
                        maxDPs += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("INT"))
                        maxInt += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("VIT"))
                        maxVit += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("DEX"))
                        maxDex += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("STR"))
                        maxStr += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("DODGE"))
                        maxDodge += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("BLOCK"))
                        maxBlock += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("REFLECT"))
                        maxReflect += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("RESISTANCE"))
                        maxResistance += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("THORNS"))
                        maxThorns += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("GEM FIND"))
                        maxGemFind += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else if (line.contains("ITEM FIND"))
                        maxItemFind += ItemMechanics.getStat(armorPiece, lore.indexOf(line));

                    else {}
                }
            }
        }

        if (maxInt > 0)
            maxThorns = (int) Math.ceil(maxThorns * (1 + (maxInt / 1600.0)));

        if (maxVit > 0) {
            maxHP = (int) Math.ceil(maxHP * (1 + maxVit / 1600.0));
            maxReflect = (int) Math.ceil(maxReflect * (1 + (maxVit / 1600.0)));
        }

        if (maxDex > 0) {
            maxDPs = (int) Math.ceil(maxDPs * (1 + (maxDex / 1600.0)));
            maxDodge = (int) Math.ceil(maxDodge * (1 + (maxDex / 1600.0)));
        }

        if (maxStr > 0) {
            maxArmor = (int) Math.ceil(maxArmor * (1 + (maxStr / 1600.0)));
            maxBlock = (int) Math.ceil(maxBlock * (1 + (maxStr / 1600.0)));
        }

        player.sendMessage("MaxHP: " + maxHP);
        player.sendMessage("MaxEnergy: " + maxEnergy);
        player.sendMessage("MaxHPs: " + maxHPs);
        player.sendMessage("MaxArmor: " + maxArmor);
        player.sendMessage("MaxDPs: " + maxDPs);
        player.sendMessage("MaxInt: " + maxInt);
        player.sendMessage("MaxVit: " + maxVit);
        player.sendMessage("MaxDex: " + maxDex);
        player.sendMessage("MaxStr: " + maxStr);
        player.sendMessage("MaxDodge: " + maxDodge);
        player.sendMessage("MaxBlock: " + maxBlock);
        player.sendMessage("MaxReflect: " + maxReflect);
        player.sendMessage("MaxResistance: " + maxResistance);
        player.sendMessage("MaxThorns: " + maxThorns);
        player.sendMessage("MaxGemFind: " + maxGemFind);
        player.sendMessage("MaxItemFind: " + maxItemFind);

        return false;
    }

}

package me.Vinstaal0.Commands;

import me.Vinstaal0.Utility.Rarity;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Vinstaal0.Minigame;
import me.Vinstaal0.Utility.Tier;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class SpawnMobCommand implements CommandExecutor {

    @SuppressWarnings("deprecation")
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            sender.sendMessage("You need to be a player to issue this command");
            return false;
        }

        Location loc = player.getTargetBlock(null, 200).getLocation();
        Location loc_ = loc.add(0, 1, 0);

        if (args.length == 0) {
            Minigame.monsterMechanics.spawnZombie(loc_);
        } else if (args.length == 2){
            try {
                Tier tier = Tier.getTier(args[0]);
                Rarity rarity = Rarity.getRarity(args[1]);

                Minigame.monsterMechanics.spawnZombie(loc_, tier, rarity);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return true;
    }

}
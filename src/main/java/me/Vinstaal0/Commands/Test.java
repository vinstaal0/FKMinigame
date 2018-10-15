package me.Vinstaal0.Commands;


import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.Vinstaal0.Mechanics.HealthMechanics;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Test implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player = null;

        if (sender instanceof Player) {
            player = (Player) sender;
        }

        int dmg = 0;

        try {
            dmg = Integer.parseInt(args[0]);
        } catch (Exception ignored) {
        }

        if (dmg > 0) {
            player.damage(dmg);
        }

        HealthMechanics.updateHealth(player);

        return true;
    }
}
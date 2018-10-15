package me.Vinstaal0.Commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class GMCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player player;

        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            sender.sendMessage("You need to be a player to issue this command");
            return false;
        }

        GameMode gamemode = player.getGameMode();

        if (gamemode == GameMode.CREATIVE) {
            player.setGameMode(GameMode.SURVIVAL);
            return true;
        } else if (gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE) {
            player.setGameMode(GameMode.CREATIVE);
            return true;
        } else if (gamemode != GameMode.SURVIVAL && gamemode != GameMode.CREATIVE) {
            return false;
        }
        return false;
    }

}
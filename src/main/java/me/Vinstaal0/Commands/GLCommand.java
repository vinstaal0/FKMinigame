package me.Vinstaal0.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class GLCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player player;

        if (sender instanceof Player) {
            player = (Player) sender;
        } else {
            sender.sendMessage("You need to be a player to issue this command");
            return false;
        }

        // TODO add prefix support
        String prefix = "";

        if(args.length <= 0) {
            player.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Incorrect Syntax. You must supply a message! " + ChatColor.RED + "/gl <MESSAGE>");
            return true;
        }

        String msg = "";

        for(String s : args) {
            msg += s + " ";
        }

        player.sendMessage(ChatColor.AQUA + "<" + ChatColor.BOLD + "G" + ChatColor.AQUA + "> " + ChatColor.GRAY + prefix + ChatColor.GRAY + player.getDisplayName() + ChatColor.GRAY + ": " + ChatColor.WHITE + msg);

        return true;
    }

}
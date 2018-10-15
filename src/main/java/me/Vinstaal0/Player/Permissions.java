package me.Vinstaal0.Player;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Vinstaal0 on 15-10-2018.
 */
public class Permissions {

    private static HashMap<UUID, ArrayList<Rank>> rank = new HashMap<UUID, ArrayList<Rank>>();

    enum Rank {
        BLD(ChatColor.GREEN + "BLD"),
        PMOD(ChatColor.WHITE + "PMOD"),
        GM(ChatColor.AQUA + "GM"),
        DEV(ChatColor.GREEN + "DEV");

        private String prefix;

        Rank(String prefix) {
            this.prefix = prefix;
        }

        public String getPrefix() {
            return this.prefix;
        }

    }

    public static boolean hasPermission(Player player, Rank rank) {

        boolean t = false;

        ArrayList<Rank> ranks = Permissions.rank.get(player.getUniqueId());

        for (Rank r : ranks) {

            if (r == rank) {

                t = true;

            }

        }
        return t;

    }

    public static void setPermission(Player player, Rank rank) {

        ArrayList<Rank> ranks = Permissions.rank.get(player.getUniqueId());

        ranks.add(rank);

        Permissions.rank.put(player.getUniqueId(), ranks);
    }

    public static ArrayList<Rank> getPermissions(Player player) {

        return Permissions.rank.get(player.getUniqueId());

    }
}

package net.waterraid.WarpRandom;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            if (commandSender instanceof Player) {
                WarpManager wm = WarpManager.getWarpManager(strings[0]);
                if (wm == null) {
                    commandSender.sendMessage(ChatColor.RED + "Warp " + strings[0] + " was not found!");
                    return true;
                }
                wm.randWarp((Player)commandSender);
                commandSender.sendMessage(ChatColor.GOLD + "Sending... Please Wait");
            } else {
                commandSender.sendMessage(ChatColor.RED + "You may only run this command if you are a player");
            }
        } else if (strings.length == 0) {
            commandSender.sendMessage(ChatColor.GOLD + "Listing Warps...");
            for (String keys : WarpManager.WarpManagers.keySet()) {
                commandSender.sendMessage(ChatColor.YELLOW + "- " + keys);
            }
        } else if (strings.length == 2) {
            Player player = Bukkit.getPlayer(strings[1]);
            if (player == null) {
                commandSender.sendMessage(ChatColor.RED + "Cannot Find Player " + player.getName());
                return true;
            }
            WarpManager wm = WarpManager.getWarpManager(strings[0]);
            if (wm == null) {
                commandSender.sendMessage(ChatColor.RED + "Warp " + strings[0] + " was not found!");
                return true;
            }
            wm.randWarp(player);
            commandSender.sendMessage(ChatColor.GOLD + "Sending... Please Wait");
        } else {
            commandSender.sendMessage(ChatColor.RED + "Usage: /warp <name> or /warp or /warp <name> <PlayerName>");
        }
        return true;
    }
}

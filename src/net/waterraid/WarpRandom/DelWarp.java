package net.waterraid.WarpRandom;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DelWarp implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender.isOp()) {
            if (strings.length == 1) {
                WarpManager.removeWarpManager(strings[0]);
                commandSender.sendMessage(ChatColor.GOLD + "Remove Warp " + strings[0] + " if it existed (Note: its case sensitive)");
            } else {
                commandSender.sendMessage(ChatColor.RED + "Usage: /delrtp <name>");
            }
        }
        return true;
    }
}

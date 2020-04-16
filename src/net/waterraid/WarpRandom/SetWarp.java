package net.waterraid.WarpRandom;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1 && commandSender instanceof Player){
            WarpManager wm = WarpManager.getWarpManager(strings[0]);
            if (wm != null) {
                wm.addTarget(((Player) commandSender).getLocation());
                commandSender.sendMessage(ChatColor.GOLD+"Added Location to Warp "+strings[0]);
            }else {
                new WarpManager(strings[0], ((Player) commandSender).getLocation());
                commandSender.sendMessage(ChatColor.GOLD+"Created New Warp "+strings[0]);
            }
        }else{
            commandSender.sendMessage(ChatColor.RED + "Usage (must be run by player): /setwarp <warpname>");
        }
        return true;
    }
}

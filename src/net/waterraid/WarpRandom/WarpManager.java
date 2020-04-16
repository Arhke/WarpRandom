package net.waterraid.WarpRandom;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WarpManager {
    public static HashMap<String,WarpManager> WarpManagers = new HashMap<String, WarpManager>();
    private List<Location> _targets = new ArrayList<Location>();
    public WarpManager(String name, Location target) {
        _targets.add(target);
        WarpManagers.put(name, this);
    }
    public WarpManager(String name) {
        WarpManagers.put(name, this);
    }
    public void addTarget(Location target) {
        _targets.add(target);
    }
    public List<Location> getTargets(){
        return _targets;
    }
    public void randWarp(Player player) {
        player.teleport(_targets.get((int)(Math.random()*_targets.size())));
    }
    @Nullable
    public static WarpManager getWarpManager(String s){
        return WarpManagers.get(s);
    }
    public static void removeWarpManager(String s) {
        WarpManagers.remove(s);
    }
}

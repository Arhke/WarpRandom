package net.waterraid.WarpRandom;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("warp").setExecutor(new Warp());
        getCommand("setwarp").setExecutor(new SetWarp());
        getCommand("delwarp").setExecutor(new DelWarp());
        saveResource("config.yml", false);
        loadWarps();
    }

    @Override
    public void onDisable() {
        saveWarps();
    }

    public void loadWarps() {
        File file = new File(this.getDataFolder() + File.separator + "config.yml");
        YamlConfiguration config = new YamlConfiguration();
        if (!file.exists()) {
            this.fatalDisable("Config.yml Does not Exist, Disabling...");
        }
        try {
            config.load(file);
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String warps : config.getKeys(false)) {
            WarpManager wm = new WarpManager(warps);
            for (String targets : config.getConfigurationSection(warps).getKeys(false)) {
                ConfigurationSection cs = config.getConfigurationSection(warps + "." + targets);
                World world = Bukkit.getWorld(cs.getString("world"));
                if (world == null) {
                    continue;
                }
                double x = cs.getDouble("x"),
                        y = cs.getDouble("y"),
                        z = cs.getDouble("z");
                wm.addTarget(new Location(world, x, y, z));
            }
        }
    }

    public void saveWarps() {
        File file = new File(this.getDataFolder() + File.separator + "config.yml");
        YamlConfiguration config = new YamlConfiguration();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, WarpManager> entry : WarpManager.WarpManagers.entrySet()) {
            for (int i = 0; i < entry.getValue().getTargets().size(); i++) {
                Location loc = entry.getValue().getTargets().get(i);
                config.set(entry.getKey() + "." + i + "." + "world", loc.getWorld().getName());
                config.set(entry.getKey() + "." + i + "." + "x", loc.getX());
                config.set(entry.getKey() + "." + i + "." + "y", loc.getY());
                config.set(entry.getKey() + "." + i + "." + "z", loc.getZ());
            }
        }
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void fatalDisable(String s) {
        Bukkit.getLogger().severe(s);
        Bukkit.getPluginManager().disablePlugin(this);
    }

}

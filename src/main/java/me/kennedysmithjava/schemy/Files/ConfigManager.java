package me.kennedysmithjava.schemy.Files;

import me.kennedysmithjava.schemy.Schemy;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigManager {

    FileConfiguration config;

    public ConfigManager(){
        this.config = Schemy.getPlugin().getConfig();


    }

    public FileConfiguration getConfig(){
        return config;
    }

}

package me.kennedysmithjava.schemy;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import me.async.kuai.Kuai;
import me.async.kuai.api.KuaiAPI;
import me.kennedysmithjava.schemy.Commands.SchemyCommand;
import me.kennedysmithjava.schemy.Events.EventManager;
import me.kennedysmithjava.schemy.Files.ConfigManager;
import me.kennedysmithjava.schemy.World.SchemyWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.Objects;

public final class Schemy extends JavaPlugin {

    static JavaPlugin plugin;
    static ConfigManager configManager;
    static FileConfiguration config;
    static KuaiAPI kuaiAPI;
    static SchemyWorldManager worldManager;
    static EventManager eventManager;
    static String directory;

    @Override
    public void onEnable() {
        plugin = this;
        directory = Kuai.getInstance().getDataFolder().getPath() + "/schem/";
        configManager = new ConfigManager();
        kuaiAPI = Kuai.getInstance().getAPI();
        worldManager = new SchemyWorldManager();
        eventManager = new EventManager();
        Objects.requireNonNull(getCommand("schemy")).setExecutor(new SchemyCommand());
    }

    @Override
    public void onDisable() {

    }

    public static EventManager getEventManager() {
        return eventManager;
    }

    public static SchemyWorldManager getSchemyWorldManager(){
        return worldManager;
    }

    public static KuaiAPI getKuaiAPI(){
        return kuaiAPI;
    }

    public static JavaPlugin getPlugin(){
        return plugin;
    }

    public static String getDirectory(){
        return directory;
    }
}

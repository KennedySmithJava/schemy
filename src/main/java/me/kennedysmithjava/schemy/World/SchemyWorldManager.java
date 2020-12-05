package me.kennedysmithjava.schemy.World;

import com.sk89q.worldedit.math.BlockVector3;
import me.kennedysmithjava.schemy.Events.EventManager;
import me.kennedysmithjava.schemy.Schemy;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SchemyWorldManager {

    Generator generator;
    List<PlatformCoords> blocks = new ArrayList<PlatformCoords>();
    String worldName = "SCHEMATIC_EDITOR";

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id) {
        return generator;
    }


    @SuppressWarnings("ConstantConditions")
    public SchemyWorldManager(){
        File file = new File(Schemy.getPlugin().getServer().getWorldContainer(), worldName);
        if(!file.exists()) {
            Bukkit.createWorld(new WorldCreator(worldName).generator(new Generator(this)));
            Bukkit.broadcastMessage("World didn't exist, creating it!");
        }
        generator = new Generator(this);
    }

    public World getWorld(){
        File file = new File(Schemy.getPlugin().getServer().getWorldContainer(), worldName);
        if(!file.exists()) {
            Bukkit.broadcastMessage("World didn't exist, creating it!");
            return Bukkit.createWorld(new WorldCreator(worldName).generator(new Generator(this)));
        }
        return Bukkit.getWorld(worldName);
    }

    public BlockVector3 getUniqueLocation(){
        EventManager manager = Schemy.getEventManager();
        ArrayList<Player> playersEditing = manager.getPlayersEditing();
        int size = playersEditing.size();
        return BlockVector3.at(96, 50, 95);
    }

}

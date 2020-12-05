package me.kennedysmithjava.schemy.Events;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.WorldEditException;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.extent.clipboard.Clipboard;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormat;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardFormats;
import com.sk89q.worldedit.extent.clipboard.io.ClipboardReader;
import com.sk89q.worldedit.function.operation.Operation;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.session.ClipboardHolder;
import me.async.kuai.Kuai;
import me.async.kuai.api.KuaiAPI;
import me.async.kuai.api.KuaiChunkInfo;
import me.kennedysmithjava.schemy.Schemy;
import me.kennedysmithjava.schemy.World.SchemyWorldManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.beans.EventHandler;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;

public class Edit implements SchemyEvent {

    EventManager eventManager;
    KuaiAPI api;
    String schematicName;
    String directory;
    Player player;
    SchemyWorldManager worldManager;
    BlockVector3 uniqueLocation;

    BlockVector3 minimum;
    BlockVector3 maximum;

    public Edit(Player player, String schematicName, String directory){
        this.eventManager           = Schemy.getEventManager();
        this.api                    = Schemy.getKuaiAPI();
        this.schematicName          = schematicName;
        this.directory              = directory;
        this.player                 = player;
        this.worldManager           = Schemy.getSchemyWorldManager();
        editMode();
    }

    public Edit(Player player, String directory){
        this.worldManager = Schemy.getSchemyWorldManager();
        this.eventManager = Schemy.getEventManager();
        this.api = Schemy.getKuaiAPI();
        World world = player.getWorld();
        if(!api.isKuaiWorld(world)){ return; }
        Bukkit.broadcastMessage("Detected standing in schematic.");
        int chunkX = player.getLocation().getChunk().getX(); int chunkZ = player.getLocation().getChunk().getZ();
        KuaiChunkInfo info = api.getKuaiChunkInfo(world, chunkX, chunkZ);
        Bukkit.broadcastMessage("Debug 1");
        this.schematicName = info.getSchematic();
        this.directory = directory;
        this.player = player;
        this.uniqueLocation = worldManager.getUniqueLocation();
        Bukkit.broadcastMessage("Debug 2");

        eventManager.registerEvent(this);

        if(editMode()){
            Bukkit.broadcastMessage("Debug 3");
            Block block = Objects.requireNonNull(Bukkit.getWorld(worldManager.getWorld().getName())).getBlockAt(uniqueLocation.getBlockX(), uniqueLocation.getBlockY(), uniqueLocation.getBlockY());
            player.teleport(block.getLocation());
            Bukkit.broadcastMessage("Debug 4");
        }
        Bukkit.broadcastMessage("Debug 12");
    }

    public Boolean editMode(){
        String schemPath = Schemy.getDirectory();
        File file = new File(schemPath + schematicName);

        if(!file.exists()){
            Bukkit.broadcastMessage("File doesn't exist! Canceling command.");
            return false;
        }

        ClipboardFormat format = ClipboardFormats.findByFile(file);
        if(null == format){
            Bukkit.broadcastMessage("Format null");
            return false; }

        try (ClipboardReader reader = format.getReader(new FileInputStream(file))) {

            Clipboard clipboard = reader.read();

            try (EditSession editSession =
                         WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(worldManager.getWorld()), -1)) {

                Operation operation = new ClipboardHolder(clipboard).createPaste(editSession).to(uniqueLocation).ignoreAirBlocks(false).build();
                Bukkit.broadcastMessage("Dimensions: " + clipboard.getDimensions().toString());
                clipboard.getDimensions().

                Bukkit.broadcastMessage("Orgin: " + "x" + clipboard.getOrigin().getBlockX() + "y" + clipboard.getOrigin().getBlockY() + "z" + clipboard.getOrigin().getBlockZ());

                Bukkit.broadcastMessage("Region: " + "x" + clipboard.getRegion().getMinimumPoint().getBlockX() + "y" + clipboard.getRegion().getMinimumPoint().getBlockY() + "z" + clipboard.getRegion().getMinimumPoint().getBlockZ());

                Operations.complete(operation);
            }

        } catch(IOException | WorldEditException e){
            Bukkit.broadcastMessage("Issue found");
            e.printStackTrace();
            return false;
        }

        Bukkit.broadcastMessage("Debug 11");
        return true;
    }

    public BlockVector3 getMaximum() {
        return maximum;
    }

    public BlockVector3 getMinimum() {
        return minimum;
    }

    public BlockVector3 getUniqueLocation() {
        return uniqueLocation;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void cancel() {
        eventManager.unregisterEvent(this);
    }
}

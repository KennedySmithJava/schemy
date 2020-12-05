package me.kennedysmithjava.schemy.Events;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.extent.clipboard.BlockArrayClipboard;
import com.sk89q.worldedit.function.operation.ForwardExtentCopy;
import com.sk89q.worldedit.function.operation.Operations;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import me.async.kuai.api.KuaiAPI;
import me.kennedysmithjava.schemy.Schemy;
import me.kennedysmithjava.schemy.World.SchemyWorldManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Save implements SchemyEvent {

    EventManager eventManager;
    KuaiAPI api;
    String schematicName;
    String directory;
    Player player;
    SchemyWorldManager worldManager;
    BlockVector3 uniqueLocation;

    BlockVector3 minimum;
    BlockVector3 maximum;

    Save(Player player){
        this.eventManager = Schemy.getEventManager();
        this.api = Schemy.getKuaiAPI();
        this.schematicName = schematicName;
        this.directory = directory;
        this.player = player;
        this.worldManager = Schemy.getSchemyWorldManager();
    }

    public boolean saveExecute(){

        ///START LOCATION = UNIQUE LOCATION
        ///ADD DIMENSION VALUES TO UNIQUE LOCATION TO GET OTHER CORNER OF CUBOID REGION
        //CuboidRegion region = new CuboidRegion(, min, max);
        /*BlockArrayClipboard clipboard = new BlockArrayClipboard(region);

        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(world, -1)) {
            ForwardExtentCopy forwardExtentCopy = new ForwardExtentCopy(
                    editSession, region, clipboard, region.getMinimumPoint()
            );
            // configure here
            Operations.complete(forwardExtentCopy);
        }*/
        return true;
    }


    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public void cancel() {

    }
}

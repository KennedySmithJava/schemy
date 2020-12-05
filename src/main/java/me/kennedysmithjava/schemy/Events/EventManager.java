package me.kennedysmithjava.schemy.Events;

import com.sk89q.worldedit.math.BlockVector3;
import me.kennedysmithjava.schemy.Events.Edit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.ArrayList;
import java.util.HashMap;

public class EventManager {

    ArrayList<Edit> editEvents;
    //ArrayList<Player> playersEditing;
    HashMap<Player, String> playersEditing;
    ArrayList<SchemyEvent> activeEvents;


    public EventManager(){
        this.editEvents = new ArrayList<>();
        this.playersEditing = new HashMap<>();
        this.activeEvents = new ArrayList<>();
    }

    public Boolean registerEditEvent(Edit event){
        editEvents.add(event);

        playersEditing.put(event.getPlayer(), )

        playersEditing.add(event.getPlayer());
        return true;
    }
    public Boolean unregisterEditEvent(Edit event){
        editEvents.remove(event);
        playersEditing.remove(event.getPlayer());
        return true;
    }

    public Boolean registerEvent(SchemyEvent event){
        if(event instanceof Edit){ registerEditEvent((Edit) event);}
        activeEvents.add(event);
        return true;
    }

    public Boolean unregisterEvent(SchemyEvent event){
        if(!activeEvents.contains(event)){return false;}
        if(event instanceof Edit){ unregisterEditEvent((Edit) event);}
        activeEvents.remove(event);
        return true;
    }

    public ArrayList<Player> getPlayersEditing(){
        return this.playersEditing;
    }

    public void cancelAllEvents(){
        for(SchemyEvent event : activeEvents){ event.cancel(); }
    }

}

package me.kennedysmithjava.schemy.Events;

import org.bukkit.entity.Player;

public interface SchemyEvent {

    Player getPlayer();
    void cancel();

}

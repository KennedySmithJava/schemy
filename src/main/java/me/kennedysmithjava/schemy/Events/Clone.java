package me.kennedysmithjava.schemy.Events;

import org.bukkit.entity.Player;

public class Clone implements SchemyEvent {

    @Override
    public Player getPlayer() {
        return null;
    }

    @Override
    public void cancel() {

    }
}

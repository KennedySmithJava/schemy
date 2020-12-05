package me.kennedysmithjava.schemy.Utility;

import org.bukkit.ChatColor;

public class Color {

    String text;

    public Color(String text){
        this.text = text;
    }

    public String get(){
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}

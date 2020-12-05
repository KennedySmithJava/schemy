package me.kennedysmithjava.schemy.Commands;

import me.kennedysmithjava.schemy.Events.Edit;
import me.kennedysmithjava.schemy.Events.EventManager;
import me.kennedysmithjava.schemy.Schemy;
import me.kennedysmithjava.schemy.Utility.Color;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SchemyCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        EventManager manager = Schemy.getEventManager();
        //command and permission
        if(cmd.getName().equalsIgnoreCase("schemy")) {
            if(!(sender instanceof Player)){ return true; }
            Player player = (Player) sender;
            if(sender.hasPermission("schemy.use")) {
            if (args.length == 0) {
                sender.sendMessage("You only typed schemy!");
                return true;
            }



                switch(args[0].toLowerCase()){
                    case "save":
                        Bukkit.broadcastMessage("Saved");
                        break;
                    case "world":
                        Block b = Schemy.getSchemyWorldManager().getWorld().getBlockAt(0, 0, 0);;
                        player.teleport(b.getLocation());
                        break;
                    case "edit":
                        if(manager.getPlayersEditing().contains(player)){
                            Bukkit.broadcastMessage("You're already editing a schematic.");
                            break;
                        }else{
                            Bukkit.broadcastMessage("Editing");
                        }
                        if(args.length == 2){
                            Edit edit = new Edit(player, args[1], Schemy.getDirectory());
                        } else if(args.length == 1){
                            Edit edit = new Edit(player, Schemy.getDirectory());
                        }
                        break;
                    case "new":
                        Bukkit.broadcastMessage("New Created");
                        break;
                    case "clone":
                        Bukkit.broadcastMessage("Cloned");
                        break;
                    case "rename":
                        Bukkit.broadcastMessage("Renamed");
                        break;
                    default:
                        sender.sendMessage(new Color("&8[&cSchemy&8] &7Invalid arguments. Use /schemy help to see possible commands.").get());
                        return true;
                }

            } else {

            }
        }
        return true;
    }

}

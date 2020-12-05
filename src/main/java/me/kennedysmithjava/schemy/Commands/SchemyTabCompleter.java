package me.kennedysmithjava.schemy.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchemyTabCompleter implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        List<String> completions = new ArrayList<>();
        List<String> commands = new ArrayList<>();

        if (args.length == 1) {
            commands.add("new");
            commands.add("edit");
            commands.add("save");
            commands.add("eee");
            StringUtil.copyPartialMatches(args[0], commands, completions);
        }
        else if (args.length == 2) {

            if (args[0].equals("arena")) {

            }
            StringUtil.copyPartialMatches(args[1], commands, completions);
        }

        else if (args.length == 3) {


            if (args[0].equals("arena")) {

                if (args[1].equals("info")) {
                    //commands.addAll(Arenas.getArenaList());
                }

            }
            StringUtil.copyPartialMatches(args[2], commands, completions);
        }


        else if (args.length == 4) {
            if (args[0].equals("gui")) {
                if (args[1].equals("setslot")) {
                    //commands.addAll(Util.getMaterialList());
                }
            }
            StringUtil.copyPartialMatches(args[3], commands, completions);
        }

        Collections.sort(completions);
        return completions;
    }
}

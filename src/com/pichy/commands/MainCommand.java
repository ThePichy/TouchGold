package com.pichy.commands;

import com.pichy.TouchGold;
import com.pichy.events.Walk;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainCommand implements TabCompleter, CommandExecutor {
    private final String[] auto = { "reload" };
    public MainCommand(JavaPlugin j){
        PluginCommand command = j.getCommand("touchgold");
        command.setExecutor(this);
        command.setTabCompleter(this);
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        return new ArrayList<>(Arrays.asList(auto));
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if(args.length > 0 && args[0].equalsIgnoreCase("reload")){
            JavaPlugin j = TouchGold.getInstance();
            j.reloadConfig();
            j.saveConfig();
            Walk.setExchanged(0);
            TouchGold.getProgressBar().load();
            sender.sendMessage(ChatColor.GREEN + "TouchGold reinicializado com sucesso!");
            return true;
        }
        sender.sendMessage(ChatColor.RED + "/touchgold reload");
        return false;
    }
}

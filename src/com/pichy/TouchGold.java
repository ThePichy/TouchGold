package com.pichy;

import com.pichy.commands.MainCommand;
import com.pichy.events.ProgressBar;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author https://thepichy.github.io
 * Date: 2021/12
 */
public class TouchGold extends JavaPlugin {
    @Getter static JavaPlugin instance;
    @Getter @Setter static boolean enable = false;
    @Getter @Setter static ProgressBar progressBar;
    @SneakyThrows
    public void onEnable(){
        instance = this;
        loadYmls();
        registerCommands();
        registerEvents();
    }
    private void registerCommands(){
        new MainCommand(this);
    }
    private void registerEvents() throws Exception {
        for(String s : new String[]{"Walk", "Hand", "MultiShot", "ProgressBar"})
            Bukkit.getPluginManager().registerEvents((org.bukkit.event.Listener) Class.forName("com.pichy.events."+s).newInstance(), this);
    }
    private void loadYmls(){
        getConfig().options().copyDefaults(true);
        saveConfig();
    }
    public void onDisable(){
        ProgressBar.remove();
        Bukkit.getScheduler().cancelTasks(this);
        org.bukkit.event.HandlerList.unregisterAll(this);
    }
}



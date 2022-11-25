package com.pichy.events;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class Walk implements org.bukkit.event.Listener {
    @Getter @Setter static int exchanged;
    @EventHandler
    public void onWalk(PlayerMoveEvent e){
        Location to = e.getFrom();
        Block down = to.subtract(0,1,0).getBlock();
        if(!(down.getType() == Material.GOLD_BLOCK || down.getType() == Material.AIR)) {
            down.setType(Material.GOLD_BLOCK);
            if(exchanged < ProgressBar.getMax())
                exchanged++;
        }
    }
}

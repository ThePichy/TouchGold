package com.pichy.events;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;

public class MultiShot implements org.bukkit.event.Listener {
    @EventHandler
    public void onShot(ProjectileLaunchEvent e){
        Entity projectile = e.getEntity();
        if(!(projectile instanceof Arrow) || !(e.getEntity().getShooter() instanceof Player))
            return;
        for(int i = 0 ; i < 2 ; i ++){
            int n = (i > 0) ? 1 : - 1;
            Arrow arrow = (Arrow) projectile.getWorld().spawnEntity(projectile.getLocation().add(n, 0,n), EntityType.ARROW);
            arrow.setVelocity(projectile.getVelocity());
        }
    }
}

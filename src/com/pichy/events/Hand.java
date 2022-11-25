package com.pichy.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Hand implements org.bukkit.event.Listener {
    @EventHandler
    public void onHeld(PlayerItemHeldEvent e){
        Inventory inv = e.getPlayer().getInventory();
        ItemStack itemStack = inv.getItem(e.getNewSlot());
        if(itemStack != null && !itemStack.getType().toString().contains("GOLD"))
            itemStack.setType(Material.GOLD_INGOT);
    }
}

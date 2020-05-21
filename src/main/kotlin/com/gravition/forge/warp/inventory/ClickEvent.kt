package com.gravition.forge.warp.inventory

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory


class ClickEvent(val guiInventory : Inventory, val action : (InventoryClickEvent) -> Unit) : Listener {

    @EventHandler
    fun clickEvent(event : InventoryClickEvent) {
        if(guiInventory.viewers.contains(event.whoClicked)) {
            action(event)
        }
        
        event.isCancelled = true
    }

}
package com.gravition.forge.warp.inventory

import com.gravition.forge.warp.WarpPlugin.Companion.mcServer
import com.gravition.forge.warp.WarpPlugin.Companion.plugin
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.ItemStack
import java.util.*


class GUI(
        private val name : String,
        private val amount : Int,
        private val items : List<InventorySlot> = emptyList(),
        private val fill : InventorySlot,
        private val onClick : (InventoryClickEvent) -> Unit = {}
) {

    val inv: Inventory  =  Bukkit.createInventory(null, amount, name)

    init {

        mcServer.pluginManager.registerEvents(ClickEvent(inv, onClick), plugin)
    }

    fun initialize() {
        repeat(amount) {
            if(items.any { itm -> itm.slot == it }) {
                inv.setItem(it, createGuiItem(items.find { itm -> itm.slot == it }!!))
            } else {
                inv.setItem(it,createGuiItem(fill))
            }
        }
    }

    private fun createGuiItem(data : InventorySlot): ItemStack {
        val item = data.material
        val meta = item.itemMeta
        meta.displayName = data.name
        if(data.lore.isNotEmpty()) {
            meta.lore = data.lore
            item.itemMeta = meta
        }
        return item
    }




}
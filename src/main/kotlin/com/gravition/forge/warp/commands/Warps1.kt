package com.gravition.forge.warp.commands

import com.gravition.forge.warp.MineCraftCommand
import com.gravition.forge.warp.database.DatabaseManager
import com.gravition.forge.warp.inventory.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


@MineCraftCommand("warps1")
class Warps1() : CommandExecutor {

    val filler = InventorySlot("Boons", ItemStack(Material.BARRIER,1), listOf(),0)

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player
        player.sendMessage("BOO, YOU WHORE")

        val items= DatabaseManager.getCollection("types").find().map{ WarpType.fromDocument(it) }.toList()

        val gui = GUI("fd",9, items.map { it.stack }, filler) {
            val type = items.find { itm -> itm.stack.name == it.currentItem.itemMeta.displayName }
            if(type != null) {
                openMainWarp(type.type, player)
            }
        }

        gui.initialize()
        player.openInventory(gui.inv)
        return true
    }


    fun openMainWarp(type : String, player : Player) {

        val warps  = DatabaseManager.getCollection("warps").find().map{ WarpData.fromDocument(it) }
                .filter {  it.type == type }
                .toList()

        if(warps.isEmpty()) {
            player.sendMessage("Empty")
            return
        }


        DatabaseManager.getCollection("warps").find().map{ WarpData.fromDocument(it) }
                .forEach { println("Found in the db $it") }

        println("Warps size ${warps.size}")

        val col = Math.floorDiv(warps.size, 9) + 1

        val gui = GUI(warps[0].type,col * 9 , warps.map { it.item }, filler) {
            val warp = warps.find { itm -> itm.item.name == it.currentItem.itemMeta.displayName }
            println("Got a warp boiiii - $warp - ${warp!!.position.x}")
        }

        gui.initialize()
        player.openInventory(gui.inv)
    }

}
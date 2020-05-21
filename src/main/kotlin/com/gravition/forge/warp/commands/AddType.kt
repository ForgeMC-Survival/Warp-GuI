package com.gravition.forge.warp.commands

import com.gravition.forge.warp.MineCraftCommand
import com.gravition.forge.warp.database.DatabaseManager
import com.gravition.forge.warp.inventory.*
import org.bukkit.Material
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack


@MineCraftCommand("test1")
class AddType() : CommandExecutor, TabCompleter {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player


            DatabaseManager.getCollection("types").insert(WarpType(args[0],InventorySlot(args[0],ItemStack(Material.valueOf(args[1]),1), listOf("Teleport to Staff Location",""),1)).asDocument())
            //player.sendMessage("Added Warp")
            return false

    }

    override fun onTabComplete(sender: CommandSender, command: Command, alias: String?, args: Array<String>): MutableList<String> {
        val items = emptyList<String>().toMutableList()
        if(args.size == 1) {
            Material.values().forEach {
                items.add(it.name)
            }
            return items
        }
        return items
    }

    private fun check(args: Array<String>) : Boolean = args.isEmpty() || args.size > 2



}
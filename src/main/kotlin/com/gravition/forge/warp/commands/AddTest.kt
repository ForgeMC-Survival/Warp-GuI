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


@MineCraftCommand("addtest")
class AddTest() : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<String>): Boolean {
        val player = sender as Player

        DatabaseManager.getCollection("warps").insert(WarpData("Staff Warps",InventorySlot("Staff", ItemStack(Material.ITEM_FRAME,1), listOf("Staff"),1),"test123", Location(0.0,0.0,0.0),true,"COOOLBOI69").asDocument())
        DatabaseManager.getCollection("warps").insert(WarpData("Player Warps",InventorySlot("Player", ItemStack(Material.ITEM_FRAME,1), listOf("Player"),1),"test123w", Location(0.0,0.0,0.0),true,"COOOLBOI696").asDocument())

        player.sendMessage("Added Warp")
        return true
    }



}
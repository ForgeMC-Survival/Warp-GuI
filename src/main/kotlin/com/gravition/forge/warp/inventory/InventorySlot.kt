package com.gravition.forge.warp.inventory

import com.gravition.forge.warp.database.*
import org.bukkit.inventory.ItemStack
import org.dizitart.no2.Document

data class InventorySlot(
        val name : String,
        val material : ItemStack,
        val lore : List<String>,
        val slot : Int
) : DatabaseSerialisable {
    companion object {

        fun fromDocument(doc: Document) =
                InventorySlot(
                        doc.getString("name"),
                        doc.get("material", Document::class.java).getItemStack(),
                        doc.getList("lore"),
                        doc.getInt("slot"))
    }

    override fun asDocument() =
            Document.createDocument("name", name)
                    .put("material", material.asDocument())!!
                    .put("lore", lore)!!
                    .put("slot", slot)!!
}




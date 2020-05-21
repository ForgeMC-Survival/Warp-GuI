package com.gravition.forge.warp.inventory

import com.gravition.forge.warp.database.*
import org.bukkit.inventory.ItemStack
import org.dizitart.no2.Document

data class WarpType(
        val type : String,
        val stack : InventorySlot
) : DatabaseSerialisable {
    companion object {

        fun fromDocument(doc: Document) =
                WarpType(
                        doc.getString("type"),
                        InventorySlot.fromDocument(doc.get("item", Document::class.java)))
    }

    override fun asDocument() =
            Document.createDocument("type", type)
                    .put("item", stack.asDocument())!!
}

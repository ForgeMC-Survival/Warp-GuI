package com.gravition.forge.warp.inventory

import com.gravition.forge.warp.database.*
import org.bukkit.Material
import org.dizitart.no2.Document



data class WarpData(val type : String, val item : InventorySlot,val name : String, val position: Location, val safe : Boolean, val owned: String) : DatabaseSerialisable {

    companion object {
        fun fromDocument(doc: Document) =
                WarpData(doc.getString("type"),
                        InventorySlot.fromDocument(doc.get("item", Document::class.java)),
                        doc.getString("name")!!,
                        Location.fromDocument(doc.get("position", Document::class.java)!!),
                        doc.getBoolean("safe")!!,
                        doc.getString("owned")!!)
    }

    override fun asDocument() =
            Document.createDocument("type", type)
                    .put("item", item.asDocument())!!
                    .put("name", name)!!
                    .put("position", position.asDocument())!!
                    .put("name", name)!!
                    .put("safe", safe)!!
                    .put("owned", owned)!!
}

data class Location(val x : Double, val y : Double, val z : Double) : DatabaseSerialisable {

    companion object {

        fun fromDocument(doc: Document) =
                Location(doc.getDouble("x"), doc.getDouble("y"), doc.getDouble("z"))
    }

    override fun asDocument() =
            Document.createDocument("x", x)
                .put("y", y)!!
                .put("z", z)!!

}
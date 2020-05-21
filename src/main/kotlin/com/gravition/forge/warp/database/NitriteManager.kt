package com.gravition.forge.warp.database

import mu.KotlinLogging
import org.bukkit.Material
import org.bukkit.inventory.ItemStack
import org.dizitart.no2.Document
import org.dizitart.no2.Nitrite

object DatabaseManager {

    val logger = KotlinLogging.logger {  }

    private lateinit var database : Nitrite

    /**
     *  Initialise the [Nitrite] database.
     */
    fun init(path : String) {
        logger.info { "Initialising Forge Warp datastore" }
        database = Nitrite.builder()
            .compressed()
            .filePath(path)
            .openOrCreate()
        logger.info { "Successfully initialised Forge Warp datastore" }

    }

    /**
     *  Close the database connection
     */
    fun close() {
        database.close()
    }

    fun getCollection(name : String) = database.getCollection(name)

}

fun Document.getString(name : String) = get(name, String::class.java)
fun Document.getInt(name : String) = get(name, Integer::class.java).toInt()
fun Document.getShort(name : String) = get(name, java.lang.Short::class.java).toShort()
fun Document.getDouble(name : String) = get(name, java.lang.Double::class.java).toDouble()
fun Document.getFloat(name : String) = get(name, java.lang.Float::class.java).toFloat()
fun Document.getBoolean(name : String) = get(name, java.lang.Boolean::class.java).booleanValue()
fun <K> Document.getList(name : String) = get(name, List::class.java) as List<K>
fun Document(name : String, value : Any) = Document.createDocument(name, value)

fun ItemStack.asDocument() : Document{
    return Document.createDocument("type", type.toString())
        .put("amount", amount)!!
        .put("durability", durability)!!
}

fun Document.getItemStack() : ItemStack {
    val type = Material.valueOf(getString("type"))
    val amount = getInt("amount")
    val dur = getShort("durability")
    return ItemStack(type, amount, dur)
}

interface DatabaseSerialisable {

    fun asDocument() : Document

}

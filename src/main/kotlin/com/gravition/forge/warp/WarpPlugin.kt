package com.gravition.forge.warp

import com.gravition.forge.warp.database.DatabaseManager
import mu.KotlinLogging
import net.milkbowl.vault.economy.Economy
import org.bukkit.Bukkit
import org.bukkit.Server
import org.bukkit.command.CommandExecutor
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.plugin.RegisteredServiceProvider
import org.bukkit.plugin.java.JavaPlugin
import org.dizitart.no2.NitriteCollection
import org.reflections.Reflections
import org.reflections.scanners.MethodAnnotationsScanner
import org.reflections.scanners.SubTypesScanner
import org.reflections.scanners.TypeAnnotationsScanner
import org.reflections.util.ConfigurationBuilder
import java.util.*

class WarpPlugin : JavaPlugin() {

    companion object {
        /**
         *  [KLogger] for this class
         */
        val log = KotlinLogging.logger {}

        /**
         *  [Economy] Economy for the server
         */
        lateinit var economy: Economy

        /**
         *  [Server] Server
         */
        lateinit var mcServer : Server

        lateinit var plugin : JavaPlugin
    }

    /**
     *  [onEnable] Loads when the plugin is called
     */

    override fun onEnable()  {
        log.info { "ForgeMc Warps Started" }
        if(!setupEconomy()) {
            log.info { "Disabled due to no Vault dependency found!${description.name}" }
            server.pluginManager.disablePlugin(this)
            return
        }

        mcServer = server
        plugin = this

        makeData()
        DatabaseManager.init("$dataFolder//data.db")

        val config = ConfigurationBuilder().addScanners(SubTypesScanner(false), TypeAnnotationsScanner(), MethodAnnotationsScanner()).addUrls(MineCraftCommand::class.java.getResource(""))
        val ref = Reflections(config)
        val commands = ref.getTypesAnnotatedWith(MineCraftCommand::class.java)

        commands.forEach {
            val name = it.getAnnotation(MineCraftCommand::class.java).command
            val instance = it.getDeclaredConstructor().newInstance() as CommandExecutor
            this.getCommand(name).executor = instance
        }

        log.info { "ForgeMc Warps Loaded" }
    }


    /**
     * Make the data folder if it does not exists
     */
    private fun makeData() {
        if(!dataFolder.exists()) {
            dataFolder.mkdirs()
        }
    }

    private fun setupEconomy(): Boolean {
        if (server.pluginManager.getPlugin("Vault") == null) {
            return false
        }
        val rsp: RegisteredServiceProvider<Economy> = server.servicesManager.getRegistration(Economy::class.java)  ?: return false
        economy = rsp.provider
        return true
    }

    /**
     *  [onEnable] Loads when the plugin is Shutdown
     */

    override fun onDisable() {
        DatabaseManager.close()
        log.info { "ForgeMc Warps Stopped" }
    }



}
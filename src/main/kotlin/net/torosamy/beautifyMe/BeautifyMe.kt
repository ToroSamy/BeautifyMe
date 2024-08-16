package net.torosamy.beautifyMe

import net.torosamy.beautifyMe.commands.AdminCommands
import net.torosamy.beautifyMe.commands.PlayerCommands
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.TorosamyCore
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BeautifyMe : JavaPlugin() {
    private var corePlugin: TorosamyCore = server.pluginManager.getPlugin("TorosamyCore") as TorosamyCore
    companion object {
        lateinit var plugin: BeautifyMe
    }
    override fun onEnable() {
        plugin = this
        corePlugin.getCommandManager().annotationParser.parse(AdminCommands())
        corePlugin.getCommandManager().annotationParser.parse(PlayerCommands())
        ConfigUtil.reloadConfig()
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a插件 &eBeautifyMe &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }
    override fun onDisable() {
        ConfigUtil.saveConfig()
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c插件 &eBeautifyMe &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&a[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}




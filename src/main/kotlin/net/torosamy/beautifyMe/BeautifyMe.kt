package net.torosamy.beautifyMe

import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.scheduler.NameTagTask
import net.torosamy.beautifyMe.utils.CommandUtil
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.beautifyMe.utils.ListenerUtil
import net.torosamy.beautifyMe.utils.SchedulerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class BeautifyMe : JavaPlugin() {
    companion object { lateinit var plugin: BeautifyMe }

    override fun onEnable() {
        plugin = this
        ConfigUtil.initConfig()
        ConfigUtil.reloadConfig()
        CommandUtil.registerCommand()
        SchedulerUtil.registerScheduler()
        ListenerUtil.registerListener()
        
        BeautifyMeAPI.loadUserdata()
        
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&a插件 &eBeautifyMe &a成功开启喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&a作者 &eTorosamy|yweiyang"))
    }

    override fun onDisable() {
        ConfigUtil.saveConfig()
        
        BeautifyMeAPI.saveUserdata()
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&c插件 &eBeautifyMe &c成功关闭喵~"))
        Bukkit.getConsoleSender().sendMessage(MessageUtil.format("&b[服务器娘]&c作者 &eTorosamy|yweiyang"))
    }
}




package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.listener.JoinQuitListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

object ListenerUtil {
    fun registerListener() {
        BeautifyMe.plugin.server.pluginManager.registerEvents(JoinQuitListener(),BeautifyMe.plugin)
    }
}
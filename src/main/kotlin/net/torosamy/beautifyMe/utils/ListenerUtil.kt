package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.listener.JoinBroadcastListener
import org.bukkit.Bukkit
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class ListenerUtil {
    companion object{
        private val joinBroadcastListener:Listener = JoinBroadcastListener()

        fun registerListener() {
            registerJoinBroadcastListener()
        }

        fun registerJoinBroadcastListener() {
            if (!ConfigUtil.getMainConfig().joinBroadcast.enabled) HandlerList.unregisterAll(joinBroadcastListener)
            else BeautifyMe.plugin.server.pluginManager.registerEvents(joinBroadcastListener,BeautifyMe.plugin)
        }
    }
}
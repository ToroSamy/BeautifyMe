package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.listener.JoinBroadcastListener
import org.bukkit.event.HandlerList
import org.bukkit.event.Listener

class ListenerUtil {
    companion object{
        private val joinBroadcastListener:Listener = JoinBroadcastListener()

        fun registerListener() {
            registerJoinBroadcastListener()
        }

        private fun registerJoinBroadcastListener() {
            HandlerList.unregisterAll(joinBroadcastListener)
            if (!ConfigUtil.mainConfig.joinBroadcast.enabled) return
            else BeautifyMe.plugin.server.pluginManager.registerEvents(joinBroadcastListener,BeautifyMe.plugin)
        }
    }
}
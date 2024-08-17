package net.torosamy.beautifyMe.listener

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent

class JoinBroadcastListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (!ConfigUtil.getMainConfig().joinBroadcast.enabled) return

        event.player.sendTitle(
            MessageUtil.text(
                PlaceholderAPI.setPlaceholders(
                    event.player,
                    ConfigUtil.getMainConfig().joinBroadcast.title
                )
            ),
            MessageUtil.text(
                PlaceholderAPI.setPlaceholders(
                    event.player,
                    ConfigUtil.getMainConfig().joinBroadcast.subTitle
                )
            )
        )
    }
}
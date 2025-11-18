package net.torosamy.beautifyMe.listener

import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.scheduler.BossbarTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import net.torosamy.beautifyMe.scheduler.TabListTask
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuitListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        if (ConfigUtil.mainConfig.joinBroadcast.enabled) {
            event.player.sendTitle(
                MessageUtil.format(
                        event.player,
                        ConfigUtil.mainConfig.joinBroadcast.title
                ),
                MessageUtil.format(
                    event.player,
                    ConfigUtil.mainConfig.joinBroadcast.subTitle
                )
            )
        }

        if (ConfigUtil.mainConfig.joinMotd.enabled) {
            ConfigUtil.mainConfig.joinMotd.messages.forEach {it ->
                event.player.sendMessage(MessageUtil.format(event.player, it))
            }
        }

        if (ConfigUtil.mainConfig.joinMessage.enabled) {
            event.joinMessage = MessageUtil.format(event.player, ConfigUtil.mainConfig.joinMessage.message)
        }
        
        if (ConfigUtil.mainConfig.firstJoinMessage.enabled && !event.player.hasPlayedBefore()) {
            event.joinMessage = MessageUtil.format(event.player, ConfigUtil.mainConfig.firstJoinMessage.message)
        }

        BeautifyMeAPI.bossbar(event.player)
        BeautifyMeAPI.scoreboard(event.player)
        BeautifyMeAPI.tabList(event.player)
        BeautifyMeAPI.nameList(event.player)
    }

    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        if (ConfigUtil.mainConfig.quitMessage.enabled) {
            event.quitMessage = MessageUtil.format(event.player, ConfigUtil.mainConfig.quitMessage.message)
        }
    }
    
}
package net.torosamy.beautifyMe.listener

import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent

class JoinQuitListener : Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        val player = event.player

        val userdata = BeautifyMeAPI.getUserdata(player.name)

        val joinMessage = getJoinMessage(player)
        if (joinMessage != null) {
            event.joinMessage = joinMessage
        }
        
        userdata.tabHeader(player)
        userdata.tabFooter(player)
        userdata.nameList(player)
        userdata.bossbar(player)
        userdata.joinBroadcast(player)
        userdata.joinMotd(player)
        userdata.reloadScoreBoard(player)
    }

    fun getJoinMessage(player: Player): String? {
        if (ConfigUtil.mainConfig.firstJoinMessage.enabled && !player.hasPlayedBefore()) {
            return MessageUtil.format(player, ConfigUtil.mainConfig.firstJoinMessage.message)
        }

        val userdata = BeautifyMeAPI.getUserdata(player.name)
        
        if (ConfigUtil.mainConfig.joinMessage.enabled && userdata.joinMessageEnable) {
            val message = if (!player.hasPermission(ConfigUtil.mainConfig.joinMessage.customPermission) || userdata.joinMessage == null)
                ConfigUtil.mainConfig.joinMessage.message else
                userdata.joinMessage
            
            return MessageUtil.format(player, message)
        }
        
        return null
    }
    
    @EventHandler
    fun onPlayerQuit(event: PlayerQuitEvent) {
        val userdata = BeautifyMeAPI.getUserdata(event.player.name)
        
        if (!ConfigUtil.mainConfig.quitMessage.enabled || !userdata.quitMessageEnable) {
            return
        }

        val message = if (!event.player.hasPermission(ConfigUtil.mainConfig.quitMessage.customPermission) || userdata.quitMessage == null)
            ConfigUtil.mainConfig.quitMessage.message else
            userdata.quitMessage

        event.quitMessage = MessageUtil.format(event.player, message)
    }
}
package net.torosamy.beautifyMe.api

import net.kyori.adventure.text.minimessage.MiniMessage
import net.kyori.adventure.text.serializer.gson.GsonComponentSerializer
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.a
import net.minecraft.server.level.EntityPlayer
import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.data.ScoreBoard
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.KeyedBossBar
import org.bukkit.craftbukkit.v1_21_R5.entity.CraftPlayer
import org.bukkit.craftbukkit.v1_21_R5.util.CraftChatMessage
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import java.util.*

object BeautifyMeAPI {
    private val bossBarNamespacedKey: NamespacedKey = NamespacedKey(BeautifyMe.plugin, BeautifyMe.plugin.name)

    private val UPDATE_DISPLAY_NAME_SET: EnumSet<a> = EnumSet.of(ClientboundPlayerInfoUpdatePacket.a.valueOf("UPDATE_DISPLAY_NAME"))

    private val MINI_MESSAGE = MiniMessage.miniMessage()
    
    private val scoreboards: HashMap<String, ScoreBoard> = HashMap()

    fun getBossbar(): KeyedBossBar {
        val bossBar = Bukkit.getBossBar(bossBarNamespacedKey)
        
        if (bossBar != null) {
            return bossBar
        }
        
        return Bukkit.createBossBar(
            bossBarNamespacedKey,
            MessageUtil.format(ConfigUtil.mainConfig.bossbar.info.text),
            BarColor.valueOf(ConfigUtil.mainConfig.bossbar.info.color),
            BarStyle.valueOf(ConfigUtil.mainConfig.bossbar.info.style)
        )
    }
    
    fun scoreboard(player: Player) {
        if (!ConfigUtil.mainConfig.scoreboard.enabled || !isEnableScoreBoard(player)) {
            stopScoreboard(player, false)
            return
        }

        val scoreboard = scoreboards[player.name] ?:
        ScoreBoard(player)

        if (!scoreboards.contains(player.name)) {
            scoreboards[player.name] = scoreboard
        }

        scoreboard.updateObjective(player)

        scoreboard.register(player)
    }


    fun stopScoreboard(player: Player, saveStatus: Boolean) {
        player.scoreboard.clearSlot(DisplaySlot.SIDEBAR)
        scoreboards.remove(player.name)
        
        if (!saveStatus) {
            return
        }

        if (ConfigUtil.mainConfig.scoreboard.defaultAllStart) {
            ConfigUtil.playerToggleConfig.scoreboard.add(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.scoreboard.remove(player.name)
    }
    
    fun enableScoreBoard(player: Player) {
        if (ConfigUtil.mainConfig.scoreboard.defaultAllStart) {
            ConfigUtil.playerToggleConfig.scoreboard.remove(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.scoreboard.add(player.name)
    }

    fun isEnableScoreBoard(player: Player): Boolean {
        if (ConfigUtil.mainConfig.scoreboard.defaultAllStart) {
            return !ConfigUtil.playerToggleConfig.scoreboard.contains(player.name)
        }

        return ConfigUtil.playerToggleConfig.scoreboard.contains(player.name)
    }

    fun updateScoreBoard(player: Player): Boolean {
        if (isEnableScoreBoard(player)) {
            stopScoreboard(player, true)
            scoreboard(player)
            return false
        }
        enableScoreBoard(player)
        scoreboard(player)
        return true
    }

    fun bossbar(player: Player) {
        if (!ConfigUtil.mainConfig.bossbar.enabled || !isEnableBossbar(player)) {
//            stopBossbar(player, false)
            return
        }

        getBossbar().style = BarStyle.valueOf(ConfigUtil.mainConfig.bossbar.info.style)
        
        getBossbar().color = BarColor.valueOf(ConfigUtil.mainConfig.bossbar.info.color)
        
        getBossbar().setTitle(MessageUtil.format(player, ConfigUtil.mainConfig.bossbar.info.text))

        getBossbar().addPlayer(player)
    }

    fun stopBossbar(player: Player, saveStatus: Boolean) {
        Bukkit.getBossBars().forEach { 
            it.removePlayer(player)
        }
        getBossbar().removePlayer(player)
        
        if (!saveStatus) {
            return
        }

        if (ConfigUtil.mainConfig.bossbar.defaultAllStart) {
            ConfigUtil.playerToggleConfig.bossbar.add(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.bossbar.remove(player.name)
    }
    
    fun enableBossbar(player: Player) {
        if (ConfigUtil.mainConfig.bossbar.defaultAllStart) {
            ConfigUtil.playerToggleConfig.bossbar.remove(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.bossbar.add(player.name)
    }

    fun isEnableBossbar(player: Player): Boolean {
        if (ConfigUtil.mainConfig.bossbar.defaultAllStart) {
            return !ConfigUtil.playerToggleConfig.bossbar.contains(player.name)
        }

        return ConfigUtil.playerToggleConfig.bossbar.contains(player.name)
    }

    fun updateBossbar(player: Player): Boolean {
        if (isEnableBossbar(player)) {
            stopBossbar(player, true)
            bossbar(player)
            return false
        }
        enableBossbar(player)
        bossbar(player)
        return true
    }
    
    fun tabList(player: Player) {
        if (!ConfigUtil.mainConfig.tabList.enabled || !isEnableTabList(player)) {
            stopTabList(player, false)
            return
        }

        val header = ConfigUtil.mainConfig.tabList.header.joinToString("\n&r")

        val footer = ConfigUtil.mainConfig.tabList.footer.joinToString("\n&r")

        player.setPlayerListHeaderFooter(
            MessageUtil.format(player, header),
            MessageUtil.format(player, footer)
        )
    }

    fun nameList(receiver: Player) {
        val entries: ArrayList<EntityPlayer> = ArrayList()

        for (targetPlayer in Bukkit.getOnlinePlayers()) {
            val displayName = if (!ConfigUtil.mainConfig.tabList.enabled ||!isEnableTabList(receiver)) {
                targetPlayer.name
            } else {
                MessageUtil.serialize(targetPlayer, ConfigUtil.mainConfig.tabList.nameList)
            }
            
            val targetEntityPlayer: EntityPlayer = (targetPlayer as CraftPlayer).handle

            val adventureComponent = MINI_MESSAGE.deserialize(displayName)
            val gson = GsonComponentSerializer.gson().serialize(adventureComponent)

            targetEntityPlayer.listName = CraftChatMessage.fromJSON(gson)

            entries.add(targetEntityPlayer)
        }

        val packet = ClientboundPlayerInfoUpdatePacket(UPDATE_DISPLAY_NAME_SET, entries)
        (receiver as CraftPlayer).handle.g.b(packet)
    }
    
    fun stopTabList(player: Player, saveStatus: Boolean) {
        player.setPlayerListHeaderFooter("", "")

        if (!saveStatus) {
            return
        }
        
        if (ConfigUtil.mainConfig.tabList.defaultAllStart) {
            ConfigUtil.playerToggleConfig.tabList.add(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.tabList.remove(player.name)
    }

    fun enableTabList(player: Player) {
        if (ConfigUtil.mainConfig.tabList.defaultAllStart) {
            ConfigUtil.playerToggleConfig.tabList.remove(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.tabList.add(player.name)
    }
    
    fun isEnableTabList(player: Player): Boolean {
        if (ConfigUtil.mainConfig.tabList.defaultAllStart) {
            return !ConfigUtil.playerToggleConfig.tabList.contains(player.name)
        }

        return ConfigUtil.playerToggleConfig.tabList.contains(player.name)
    }

    fun updateTabList(player: Player): Boolean {
        if (isEnableTabList(player)) {
            stopTabList(player, true)
            tabList(player)
            nameList(player)
            return false
        }
        
        enableTabList(player)
        tabList(player)
        nameList(player)
        return true
    }
    
    fun broadcast(player: Player, index: Int) {
        if (!ConfigUtil.mainConfig.broadcast.enabled ||!isEnableBroadcast(player)) {
            return
        }
        
        ConfigUtil.mainConfig.broadcast.messages[index].forEach{
            player.sendMessage(MessageUtil.format(player, it))
        }
    }
    
    fun stopBroadcast(player: Player, saveStatus: Boolean) {
        if (!saveStatus) {
            return
        }
        
        if (ConfigUtil.mainConfig.broadcast.defaultAllStart) {
            ConfigUtil.playerToggleConfig.broadcast.add(player.name)
            return
        }
        
        ConfigUtil.playerToggleConfig.broadcast.remove(player.name)
    }

    fun enableBroadcast(player: Player) {
        if (ConfigUtil.mainConfig.broadcast.defaultAllStart) {
            ConfigUtil.playerToggleConfig.broadcast.remove(player.name)
            return
        }

        ConfigUtil.playerToggleConfig.broadcast.add(player.name)
    }
    
    fun isEnableBroadcast(player: Player): Boolean {
        if (ConfigUtil.mainConfig.broadcast.defaultAllStart) {
            return !ConfigUtil.playerToggleConfig.broadcast.contains(player.name)
        }

        return ConfigUtil.playerToggleConfig.broadcast.contains(player.name)
    }

    fun updateBroadcast(player: Player): Boolean {
        if (isEnableBroadcast(player)) {
            stopBroadcast(player, true)
            return false
        }
        enableBroadcast(player)
        return true
    }
}
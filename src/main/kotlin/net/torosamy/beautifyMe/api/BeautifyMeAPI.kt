package net.torosamy.beautifyMe.api

import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket.a
import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.data.ScoreBoard
import net.torosamy.beautifyMe.data.Userdata
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.api.TorosamyCoreAPI
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.KeyedBossBar
import org.bukkit.entity.Player
import java.util.*
import java.util.concurrent.ConcurrentHashMap


object BeautifyMeAPI {
    private val bossBarNamespacedKey: NamespacedKey = NamespacedKey(BeautifyMe.plugin, BeautifyMe.plugin.name)

    public val UPDATE_DISPLAY_NAME_SET: EnumSet<a> = EnumSet.of(ClientboundPlayerInfoUpdatePacket.a.valueOf("UPDATE_DISPLAY_NAME"))

    private val userdatas: ConcurrentHashMap<String, Userdata> = ConcurrentHashMap()
    
    private val scoreboards: ConcurrentHashMap<String, ScoreBoard> = ConcurrentHashMap()
    
    public fun loadUserdata() {
        userdatas.clear()
        
        TorosamyCoreAPI.getConfigs(BeautifyMe.plugin, listOf("userdata")).values.forEach {
            val userdata = Userdata.generate(it)
            
            if (userdata != null) {
                userdatas[userdata.username] = userdata
            }
        }
    }
    
    public fun saveUserdata() {
        userdatas.values.forEach {
            it.saveConfig()
        }
    }

    public fun getScoreboard(username: String): ScoreBoard {
        return scoreboards.computeIfAbsent(username) {
            ScoreBoard(username)
        }
    }
    
    public fun getUserdata(username: String): Userdata {
        return userdatas.computeIfAbsent(username) {
            Userdata(username) 
        }
    }
    
    public fun getBossbar(): KeyedBossBar {
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
}
package net.torosamy.beautifyMe.scheduler

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.NamespacedKey
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class BossbarTask() : BukkitRunnable() {
    override fun run() {
        val defaultAllStart = ConfigUtil.getMainConfig().bossbar.defaultAllStart
        val flag: Int = if (defaultAllStart) 0 else 1
        Bukkit.getOnlinePlayers().forEach { player: Player ->
            var isContainers: Boolean = ConfigUtil.getPlayerToggleConfig().bossbar[flag].contains(player.name)
            if (!defaultAllStart) isContainers = !isContainers
            if (isContainers) return
            setBossbar(player)
        }
    }

    init {
        Bukkit.createBossBar(
            namespacedKey, "temp-title",
            BarColor.valueOf(ConfigUtil.getMainConfig().bossbar.info.color),
            BarStyle.valueOf(ConfigUtil.getMainConfig().bossbar.info.style)
        )
    }

    companion object {
        val namespacedKey: NamespacedKey = NamespacedKey(BeautifyMe.plugin, "bossbar")
        fun setBossbar(player: Player) {
            //更新Bossbar
            Bukkit.getBossBar(namespacedKey)?.setTitle(MessageUtil.text(PlaceholderAPI.setPlaceholders(player, ConfigUtil.getMainConfig().bossbar.info.text)))
            Bukkit.getBossBar(namespacedKey)?.style = BarStyle.valueOf(ConfigUtil.getMainConfig().bossbar.info.style)
            Bukkit.getBossBar(namespacedKey)?.color = BarColor.valueOf(ConfigUtil.getMainConfig().bossbar.info.color)

            Bukkit.getBossBar(namespacedKey)?.addPlayer(player)
        }

        fun clearBossbar(player: Player) {
            Bukkit.getBossBar(namespacedKey)?.removePlayer(player)
        }
    }
}
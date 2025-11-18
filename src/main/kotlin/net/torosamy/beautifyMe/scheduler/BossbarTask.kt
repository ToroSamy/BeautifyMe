package net.torosamy.beautifyMe.scheduler


import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class BossbarTask : BukkitRunnable() {
    override fun run() {
        if (!ConfigUtil.mainConfig.bossbar.enabled) {
            this.cancel()
            return
        }
        
        for (player in Bukkit.getOnlinePlayers()) {
            BeautifyMeAPI.bossbar(player)
        }
    }
}
package net.torosamy.beautifyMe.scheduler




import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable


class ScoreboardTask : BukkitRunnable() {
    override fun run() {
        if (!ConfigUtil.mainConfig.scoreboard.enabled) {
            Bukkit.getOnlinePlayers().forEach{
                BeautifyMeAPI.stopScoreboard(it, false)
            }

            this.cancel()
            return
        }

        for (player in Bukkit.getOnlinePlayers()) {
            BeautifyMeAPI.scoreboard(player)
        }
    }
}
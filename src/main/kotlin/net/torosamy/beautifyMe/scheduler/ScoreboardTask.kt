package net.torosamy.beautifyMe.scheduler




import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable


class ScoreboardTask : BukkitRunnable() {
    private var counts: Int = 0

    override fun run() {
        if (counts != ConfigUtil.mainConfig.scoreboard.time) {
            counts ++
            return
        }

        counts = 0

        for (player in Bukkit.getOnlinePlayers()) {
            BeautifyMeAPI.getUserdata(player.name).scoreboard(player)
        }
    }
}
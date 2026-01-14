package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.scheduler.*

object SchedulerUtil {
    fun registerScheduler() {
        BossbarTask().runTaskTimer(BeautifyMe.plugin, 0L, 20L)

        TabListTask().runTaskTimer(BeautifyMe.plugin, 0L, 20L)

        ScoreboardTask().runTaskTimer(BeautifyMe.plugin, 0L, 20L)

        BroadcastTask().runTaskTimer(BeautifyMe.plugin, 0L, 20L)
    
        NameTagTask().runTaskTimer(BeautifyMe.plugin, 0L, 20L)
    }
}
package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.scheduler.BossbarTask
import net.torosamy.beautifyMe.scheduler.BroadcastTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import net.torosamy.beautifyMe.scheduler.TabListTask
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

object SchedulerUtil {
    private val tasks: HashMap<String, BukkitTask> = HashMap()

    private val BOSSBAR_NAME = "BOSSBAR"
    
    private val TAB_NAME = "TAB"
    
    private val SCOREBOARD_NAME = "SCOREBOARD"
    
    private val BROADCAST_NAME = "BROADCAST"
    
    fun registerScheduler() {
        Bukkit.getOnlinePlayers().forEach{
            BeautifyMeAPI.stopBossbar(it, false)
            BeautifyMeAPI.stopBroadcast(it, false)
            BeautifyMeAPI.stopTabList(it, false)
            BeautifyMeAPI.stopScoreboard(it, false)
        }
        
        registerBroadcast()
        registerScoreboard()
        registerTabList()
        registerBossbar()
    }

    fun registerBossbar() {
        val task = tasks[BOSSBAR_NAME]

        if (task != null && !task.isCancelled) {
            task.cancel()
        }

        tasks[BOSSBAR_NAME] = BossbarTask().runTaskTimer(
            BeautifyMe.plugin, 0L,
            ConfigUtil.mainConfig.bossbar.time * 20L
        )
    }


    fun registerTabList() {
        val task = tasks[TAB_NAME]

        if (task != null && !task.isCancelled) {
            task.cancel()
        }

        tasks[TAB_NAME] = TabListTask().runTaskTimer(
            BeautifyMe.plugin, 0L,
            ConfigUtil.mainConfig.tabList.time * 20L
        )
    }

    fun registerScoreboard() {
        val task = tasks[SCOREBOARD_NAME]

        if (task != null && !task.isCancelled) {
            task.cancel()
        }

        tasks[SCOREBOARD_NAME] = ScoreboardTask().runTaskTimer(
            BeautifyMe.plugin, 0L,
            ConfigUtil.mainConfig.scoreboard.time * 20L
        )
    }


    fun registerBroadcast() {
        val task = tasks[BROADCAST_NAME]

        if (task != null && !task.isCancelled) {
            task.cancel()
        }

        tasks[BROADCAST_NAME] = BroadcastTask().runTaskTimer(
            BeautifyMe.plugin, 0L,
            ConfigUtil.mainConfig.broadcast.time * 20L
        )
    }
}
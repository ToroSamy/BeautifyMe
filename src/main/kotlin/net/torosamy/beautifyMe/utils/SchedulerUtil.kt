package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.scheduler.BroadcastTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask

//创建一个Task任务会被Bukkit任务调度器控制
//cancel之后 会在合适的时机移出任务调度器 但是对象依然存在
//重启用 只能在创建对象 无法run 会泄露但几乎可以忽略不计 一般也不会一直开着服务器还反复开关闭功能
class SchedulerUtil {
    companion object {
        private lateinit var broadcastTask: BukkitTask
        private lateinit var scoreboardTask: BukkitTask
        fun registerScheduler() {
            registerBroadcast()
            registerScoreboard()
        }


        fun registerScoreboard() {
            if (::scoreboardTask.isInitialized && !scoreboardTask.isCancelled) {
                scoreboardTask.cancel()
                Bukkit.getOnlinePlayers().forEach(ScoreboardTask::clearScoreboard)
            }
            //如果该功能不开启
            if (!ConfigUtil.getMainConfig().scoreboard.enabled) return

            scoreboardTask = ScoreboardTask().runTaskTimer(
                BeautifyMe.plugin,
                0L,
                ConfigUtil.getMainConfig().scoreboard.time * 20L
            )
        }


        fun registerBroadcast() {
            //先取消一下 防止开启多个
            //先检测是否为null 再检测是否已经取消
            if (::broadcastTask.isInitialized && !broadcastTask.isCancelled) broadcastTask.cancel()
            //如果该功能不开启
            if (!ConfigUtil.getMainConfig().broadcast.enabled) return
            broadcastTask = BroadcastTask().runTaskTimerAsynchronously(
                BeautifyMe.plugin,
                0L,
                ConfigUtil.getMainConfig().broadcast.time * 20L
            )
        }
    }
}
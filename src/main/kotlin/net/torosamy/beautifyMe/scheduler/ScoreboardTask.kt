package net.torosamy.beautifyMe.scheduler

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.scoreboard.DisplaySlot

class ScoreboardTask : BukkitRunnable() {
    override fun run() {
        val defaultAllStart = ConfigUtil.getMainConfig().scoreboard.defaultAllStart
        val flag:Int = if (defaultAllStart) 0 else 1

        Bukkit.getOnlinePlayers().forEach{player: Player ->
            var isContainers:Boolean = ConfigUtil.getPlayerToggleConfig().scoreboard[flag].contains(player.name)
            if(!defaultAllStart) isContainers = !isContainers
            if(isContainers) return
            setScoreboard(player)
        }
    }
    companion object{
        //根据一名玩家的信息生成对应的计分板
        fun setScoreboard(player: Player) {
            var scoreboard = Bukkit.getScoreboardManager().newScoreboard
            var objective = scoreboard.getObjective("side")
            if(objective == null) objective = scoreboard.registerNewObjective("side","dummy")
            objective.displaySlot = DisplaySlot.SIDEBAR
            var title: String = ConfigUtil.getMainConfig().scoreboard.board.title

            if(title.length >= 16) {
                Bukkit.getConsoleSender().sendMessage(MessageUtil.text("&b[服务器娘]&c错误 计分板的标题超过16个字符"))
                Bukkit.getPluginManager().getPlugin("BeautifyMe")?.let { Bukkit.getPluginManager().disablePlugin(it) }
                return
            }
            objective.displayName = MessageUtil.text(PlaceholderAPI.setPlaceholders(player, title))

            var rank = ConfigUtil.getMainConfig().scoreboard.board.lines.size
            for (line:String in ConfigUtil.getMainConfig().scoreboard.board.lines) {
                objective.getScore(MessageUtil.text(PlaceholderAPI.setPlaceholders(player,line))).score = rank
                rank--
            }
            player.scoreboard = scoreboard
        }
        //清除一名玩家的计分板
        fun clearScoreboard(player: Player){
            var scoreboard = Bukkit.getScoreboardManager().newScoreboard
            scoreboard.clearSlot(DisplaySlot.SIDEBAR)
            player.scoreboard = scoreboard
        }
    }
}
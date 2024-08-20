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
            //译注:CraftBukkit 使用弱引用技术管理计分板对象, 若插件或玩家均不再使用此计分板 (可以理解为完成使命了),
            //服务器会在 GC 时清理掉, 不需要开发者手动置空.
            val scoreboard = Bukkit.getScoreboardManager().newScoreboard
            var objective = scoreboard.getObjective("side")
            if(objective == null) objective = scoreboard.registerNewObjective("side","dummy")
            objective.displaySlot = DisplaySlot.SIDEBAR
            val title: String = ConfigUtil.getMainConfig().scoreboard.board.title

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
            val scoreboard = Bukkit.getScoreboardManager().newScoreboard
            scoreboard.clearSlot(DisplaySlot.SIDEBAR)
            player.scoreboard = scoreboard
        }
    }
}
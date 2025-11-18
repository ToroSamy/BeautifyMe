package net.torosamy.beautifyMe.data

import net.kyori.adventure.text.Component
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Objective
import org.bukkit.scoreboard.Scoreboard
import org.bukkit.scoreboard.Team

class ScoreBoard {
    private val lines: ArrayList<Team> = arrayListOf()

    private val scoreboard: Scoreboard
    
    private var objective: Objective

    public constructor(player: Player) {
        this.scoreboard = Bukkit.getScoreboardManager().newScoreboard

        val titleComponent = MessageUtil.component(player, ConfigUtil.mainConfig.scoreboard.board.title)
        
        this.objective = this.scoreboard.registerNewObjective(player.name, "dummy", titleComponent)
        
        objective.displaySlot = DisplaySlot.SIDEBAR
        
        val lines = ConfigUtil.mainConfig.scoreboard.board.lines

        for (i in 0 until lines.size) {
            val name = player.name + i

            val line = scoreboard.registerNewTeam(name)
            line.addEntry(name)

            this.lines.add(line)

            val score = this.objective.getScore(name)

            score.score = lines.size - i
            score.customName(Component.empty())
        }
    }
    
    public fun updateObjective(player: Player) {
        objective.displayName(MessageUtil.component(player, ConfigUtil.mainConfig.scoreboard.board.title))
        
        val lines = ConfigUtil.mainConfig.scoreboard.board.lines
        
        for (i in 0 until this.lines.size) {
            val line = this.lines[i]

            line.prefix = MessageUtil.format(player, lines[i])
        }
    }
    
    public fun register(player: Player) {
        player.scoreboard =  this.scoreboard
    }
}
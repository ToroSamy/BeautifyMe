package net.torosamy.beautifyMe.data

import net.minecraft.network.chat.IChatBaseComponent
import net.minecraft.network.protocol.game.ClientboundResetScorePacket
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardDisplayObjective
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardObjective
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardScore
import net.minecraft.world.scores.DisplaySlot
import net.minecraft.world.scores.Scoreboard
import net.minecraft.world.scores.ScoreboardObjective
import net.minecraft.world.scores.criteria.IScoreboardCriteria
import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.craftbukkit.v1_21_R5.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*


class ScoreBoard {
    private val empty: ScoreboardObjective

    private val scoreboard: Scoreboard

    private val objective: ScoreboardObjective

    private val username: String

    private val lines: ArrayList<String> = arrayListOf()

    private var inited: Boolean = false

    public constructor(username: String) {
        this.username = username

        this.scoreboard = Scoreboard()

        val title = BeautifyMeAPI.getUserdata(username).scoreBoardTitle ?: ConfigUtil.mainConfig.scoreboard.board.title

        this.objective = ScoreboardObjective(
            scoreboard,
            this.username,
            IScoreboardCriteria.c,
            MessageUtil.nmsComponent(title),
            IScoreboardCriteria.EnumScoreboardHealthDisplay.a,
            false,
            null
        )

        this.empty = ScoreboardObjective(
            Scoreboard(),
            this.username,
            IScoreboardCriteria.c,
            IChatBaseComponent.i(),
            IScoreboardCriteria.EnumScoreboardHealthDisplay.a,
            false,
            null
        );
    }

    private fun init(player: Player) {
        if (inited) {
            return
        }

        if (player.name != username || !player.isOnline) {
            return
        }

        val nms = (player as CraftPlayer).handle

        nms.g.b(PacketPlayOutScoreboardObjective(objective, 0))
        nms.g.b(PacketPlayOutScoreboardDisplayObjective(DisplaySlot.b, objective))

        this.lines.clear()
        
        val userdata = BeautifyMeAPI.getUserdata(username)
        
        if (!player.hasPermission(ConfigUtil.mainConfig.scoreboard.customPermission) || userdata.scoreBoardLines.isEmpty()) {
            this.lines.addAll(ConfigUtil.mainConfig.scoreboard.board.lines)
        }else {
            this.lines.addAll(userdata.scoreBoardLines)
        }

        inited = true
    }

    public fun update(player: Player) {
        init(player)
        
        if (player.name != username || !player.isOnline) {
            return
        }

        val nms = (player as CraftPlayer).handle

        for (i in 0 until lines.size) {
            val line = this.lines[i]

            val packet = PacketPlayOutScoreboardScore(
                line,
                player.name,
                this.lines.size - i,
                Optional.of(MessageUtil.nmsComponent(player, line)),
                Optional.empty()
            )

            nms.g.b(packet)
        }
    }

    public fun clear(player: Player) {
        if (!inited) {
            return
        }
        
        if (player.name != username || !player.isOnline) {
            return
        }

        (player as CraftPlayer).handle.g.b(PacketPlayOutScoreboardObjective(empty, 1))

        this.lines.forEach{
            player.handle.g.b(ClientboundResetScorePacket(it, player.name))
        }

        inited = false
    }
}
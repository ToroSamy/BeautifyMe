package net.torosamy.beautifyMe.commands

import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.scheduler.BossbarTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import net.torosamy.beautifyMe.scheduler.TabListTask
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.Argument
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class PlayerCommands {
    @Command("bm toggle broadcast")
    @Permission("beautifyme.toggle.broadcast.self")
    @CommandDescription("切换自己的broadcast的开关闭状态")
    fun playerToggleBroadcastSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.broadcast.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastDisabled))
            return
        }

        if (BeautifyMeAPI.updateBroadcast(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastToggleClose))
        return
    }

    @Command("bm toggle scoreboard")
    @Permission("beautifyme.toggle.scoreboard.self")
    @CommandDescription("切换自己的scoreboard的开关闭状态")
    fun playerToggleScoreboardSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.scoreboard.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardDisabled))
            return
        }

        if (BeautifyMeAPI.updateScoreBoard(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleOpen))
            return
        }
        
        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleClose))
        return
    }

    @Command("bm toggle tab")
    @Permission("beautifyme.toggle.tab.self")
    @CommandDescription("切换自己的tab-list的开关闭状态")
    fun playerToggleTabListSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.tabList.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListDisabled))
            return
        }

        if (BeautifyMeAPI.updateTabList(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListToggleClose))
        return
    }


    @Command("bm toggle bossbar")
    @Permission("beautifyme.toggle.bossbar.self")
    @CommandDescription("切换自己的bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: Player) {
        if(!ConfigUtil.mainConfig.bossbar.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarDisabled))
            return
        }

        if (BeautifyMeAPI.updateBossbar(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarToggleClose))
        return
    }
}
package net.torosamy.beautifyMe.commands

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

    @Command("bm toggle broadcast", requiredSender = Player::class)
    @Permission("beautifyme.toggle.broadcast.self")
    @CommandDescription("切换自己的broadcast的开关闭状态")
    fun playerToggleBroadcastSelf(sender: CommandSender) {
        if(!ConfigUtil.mainConfig.broadcast.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.broadcast.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.broadcast[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.broadcast[index].remove(sender.name) }
        else { ConfigUtil.playerToggleConfig.broadcast[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleOpen)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleClose))}
        }else {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleClose)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleOpen))}
        }
    }

    @Command("bm toggle scoreboard", requiredSender = Player::class)
    @Permission("beautifyme.toggle.scoreboard.self")
    @CommandDescription("切换自己的scoreboard的开关闭状态")
    fun playerToggleScoreboardSelf(sender: CommandSender) {
        if(!ConfigUtil.mainConfig.scoreboard.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.scoreboard.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.scoreboard[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.scoreboard[index].remove(sender.name) }
        else { ConfigUtil.playerToggleConfig.scoreboard[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                ScoreboardTask.setScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleOpen))
            } else {
                ScoreboardTask.clearScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleClose))
            }
        }else {
            if(isContains) {
                ScoreboardTask.clearScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleClose))
            } else {
                ScoreboardTask.setScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleOpen))
            }
        }
    }

    @Command("bm toggle tab", requiredSender = Player::class)
    @Permission("beautifyme.toggle.tab.self")
    @CommandDescription("切换自己的tab-list的开关闭状态")
    fun playerToggleTabListSelf(sender: CommandSender) {
        if(!ConfigUtil.mainConfig.tabList.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.tabList.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.tabList[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.tabList[index].remove(sender.name) }
        else { ConfigUtil.playerToggleConfig.tabList[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                TabListTask.setTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleOpen))
            } else {
                TabListTask.clearTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleClose))
            }
        }else {
            if(isContains) {
                TabListTask.clearTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleClose))
            } else {
                TabListTask.setTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleOpen))
            }
        }
    }


    @Command("bm toggle bossbar", requiredSender = Player::class)
    @Permission("beautifyme.toggle.bossbar.self")
    @CommandDescription("切换自己的bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: CommandSender) {
        if(!ConfigUtil.mainConfig.bossbar.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.bossbar.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.bossbar[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.bossbar[index].remove(sender.name) }
        else { ConfigUtil.playerToggleConfig.bossbar[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                BossbarTask.setBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleOpen))
            } else {
                BossbarTask.clearBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleClose))
            }
        }else {
            if(isContains) {
                BossbarTask.clearBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleClose))
            } else {
                BossbarTask.setBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleOpen))
            }
        }
    }
}
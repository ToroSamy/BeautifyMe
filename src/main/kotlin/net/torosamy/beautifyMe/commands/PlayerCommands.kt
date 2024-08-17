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
        if(!ConfigUtil.getMainConfig().broadcast.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().broadcast.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().broadcast[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().broadcast[index].remove(sender.name) }
        else { ConfigUtil.getPlayerToggleConfig().broadcast[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleOpen)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleClose))}
        }else {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleClose)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleOpen))}
        }
    }

    @Command("bm toggle scoreboard", requiredSender = Player::class)
    @Permission("beautifyme.toggle.scoreboard.self")
    @CommandDescription("切换自己的scoreboard的开关闭状态")
    fun playerToggleScoreboardSelf(sender: CommandSender) {
        if(!ConfigUtil.getMainConfig().scoreboard.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().scoreboard.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().scoreboard[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().scoreboard[index].remove(sender.name) }
        else { ConfigUtil.getPlayerToggleConfig().scoreboard[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                ScoreboardTask.setScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleOpen))
            } else {
                ScoreboardTask.clearScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleClose))
            }
        }else {
            if(isContains) {
                ScoreboardTask.clearScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleClose))
            } else {
                ScoreboardTask.setScoreboard(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleOpen))
            }
        }
    }

    @Command("bm toggle tab", requiredSender = Player::class)
    @Permission("beautifyme.toggle.tab.self")
    @CommandDescription("切换自己的tab-list的开关闭状态")
    fun playerToggleTabListSelf(sender: CommandSender) {
        if(!ConfigUtil.getMainConfig().tabList.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().tabList.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().tabList[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().tabList[index].remove(sender.name) }
        else { ConfigUtil.getPlayerToggleConfig().tabList[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                TabListTask.setTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleOpen))
            } else {
                TabListTask.clearTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleClose))
            }
        }else {
            if(isContains) {
                TabListTask.clearTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleClose))
            } else {
                TabListTask.setTabList(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleOpen))
            }
        }
    }


    @Command("bm toggle bossbar", requiredSender = Player::class)
    @Permission("beautifyme.toggle.bossbar.self")
    @CommandDescription("切换自己的bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: CommandSender) {
        if(!ConfigUtil.getMainConfig().bossbar.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().bossbar.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().bossbar[index].contains(sender.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().bossbar[index].remove(sender.name) }
        else { ConfigUtil.getPlayerToggleConfig().bossbar[index].add(sender.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                BossbarTask.setBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleOpen))
            } else {
                BossbarTask.clearBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleClose))
            }
        }else {
            if(isContains) {
                BossbarTask.clearBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleClose))
            } else {
                BossbarTask.setBossbar(sender as Player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleOpen))
            }
        }
    }
}
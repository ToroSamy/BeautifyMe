package net.torosamy.beautifyMe.commands

import net.torosamy.beautifyMe.scheduler.BossbarTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import net.torosamy.beautifyMe.scheduler.TabListTask
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.beautifyMe.utils.ListenerUtil
import net.torosamy.beautifyMe.utils.SchedulerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.Argument
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class AdminCommands {
    @Command(value = "bm reload")
    @Permission("beautifyme.reload")
    @CommandDescription("重载BeautifyMe配置文件")
    fun reloadConfig(sender: CommandSender) {
        ConfigUtil.reloadConfig()
        SchedulerUtil.registerScheduler()
        ListenerUtil.registerListener()
        sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().reloadMessage))
    }

    @Command("bm toggle broadcast <player>")
    @Permission("beautifyme.toggle.broadcast.other")
    @CommandDescription("切换其他玩家broadcast的开关闭状态")
    fun playerToggleBroadcastOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.getMainConfig().broadcast.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().broadcast.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().broadcast[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().broadcast[index].remove(player.name) }
        else { ConfigUtil.getPlayerToggleConfig().broadcast[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleOpen)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleClose))}
        }else {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleClose)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleOpen))}
        }
    }

    @Command("bm toggle scoreboard <player>")
    @Permission("beautifyme.toggle.scoreboard.other")
    @CommandDescription("切换其他玩家scoreboard的开关闭状态")
    fun playerToggleScoreboardOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.getMainConfig().scoreboard.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().scoreboard.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().scoreboard[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().scoreboard[index].remove(player.name) }
        else { ConfigUtil.getPlayerToggleConfig().scoreboard[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                ScoreboardTask.setScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleOpen))
            } else {
                ScoreboardTask.clearScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleClose))
            }
        }else {
            if(isContains) {
                ScoreboardTask.clearScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleClose))
            } else {
                ScoreboardTask.setScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().scoreboardToggleOpen))
            }
        }
    }

    @Command("bm toggle tab <player>")
    @Permission("beautifyme.toggle.tab.other")
    @CommandDescription("切换其他玩家tab-list的开关闭状态")
    fun playerToggleTabListOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.getMainConfig().tabList.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().tabList.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().tabList[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().tabList[index].remove(player.name) }
        else { ConfigUtil.getPlayerToggleConfig().tabList[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                TabListTask.setTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleOpen))
            } else {
                TabListTask.clearTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleClose))
            }
        }else {
            if(isContains) {
                TabListTask.clearTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleClose))
            } else {
                TabListTask.setTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().tabListToggleOpen))
            }
        }
    }



    @Command("bm toggle bossbar <player>")
    @Permission("beautifyme.toggle.bossbar.other")
    @CommandDescription("切换其他玩家bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.getMainConfig().bossbar.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.getMainConfig().bossbar.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.getPlayerToggleConfig().bossbar[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.getPlayerToggleConfig().bossbar[index].remove(player.name) }
        else { ConfigUtil.getPlayerToggleConfig().bossbar[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                BossbarTask.setBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleOpen))
            } else {
                BossbarTask.clearBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleClose))
            }
        }else {
            if(isContains) {
                BossbarTask.clearBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleClose))
            } else {
                BossbarTask.setBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleOpen))
            }
        }
    }
}
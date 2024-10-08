package net.torosamy.beautifyMe.commands
import net.torosamy.beautifyMe.BeautifyMe
import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.beautifyMe.scheduler.BossbarTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import net.torosamy.beautifyMe.scheduler.TabListTask
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.beautifyMe.utils.ListenerUtil
import net.torosamy.beautifyMe.utils.SchedulerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
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
        sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.reloadMessage))
    }

    @Command("bm toggle broadcast <player>")
    @Permission("beautifyme.toggle.broadcast.other")
    @CommandDescription("切换其他玩家broadcast的开关闭状态")
    fun playerToggleBroadcastOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.broadcast.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.broadcast.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.broadcast[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.broadcast[index].remove(player.name) }
        else { ConfigUtil.playerToggleConfig.broadcast[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleOpen)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleClose))}
        }else {
            if(isContains) { sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleClose)) }
            else {sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.broadcastToggleOpen))}
        }
    }

    @Command("bm toggle scoreboard <player>")
    @Permission("beautifyme.toggle.scoreboard.other")
    @CommandDescription("切换其他玩家scoreboard的开关闭状态")
    fun playerToggleScoreboardOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.scoreboard.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.scoreboard.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.scoreboard[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.scoreboard[index].remove(player.name) }
        else { ConfigUtil.playerToggleConfig.scoreboard[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                ScoreboardTask.setScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleOpen))
            } else {
                ScoreboardTask.clearScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleClose))
            }
        }else {
            if(isContains) {
                ScoreboardTask.clearScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleClose))
            } else {
                ScoreboardTask.setScoreboard(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.scoreboardToggleOpen))
            }
        }
    }

    @Command("bm toggle tab <player>")
    @Permission("beautifyme.toggle.tab.other")
    @CommandDescription("切换其他玩家tab-list的开关闭状态")
    fun playerToggleTabListOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.tabList.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.tabList.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.tabList[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.tabList[index].remove(player.name) }
        else { ConfigUtil.playerToggleConfig.tabList[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                TabListTask.setTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleOpen))
            } else {
                TabListTask.clearTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleClose))
            }
        }else {
            if(isContains) {
                TabListTask.clearTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleClose))
            } else {
                TabListTask.setTabList(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.tabListToggleOpen))
            }
        }
    }



    @Command("bm toggle bossbar <player>")
    @Permission("beautifyme.toggle.bossbar.other")
    @CommandDescription("切换其他玩家bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.bossbar.enabled) {
            sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarDisabled))
            return
        }

        //根据配置文件判断是白名单还是黑名单
        val isDefaultStart : Boolean =ConfigUtil.mainConfig.bossbar.defaultAllStart
        val index:Int = if (isDefaultStart) 0 else 1
        //判断是否包含
        val isContains : Boolean = ConfigUtil.playerToggleConfig.bossbar[index].contains(player.name)
        //内存操作
        if (isContains) { ConfigUtil.playerToggleConfig.bossbar[index].remove(player.name) }
        else { ConfigUtil.playerToggleConfig.bossbar[index].add(player.name) }
        //具体开关闭逻辑
        if(isDefaultStart) {
            if(isContains) {
                BossbarTask.setBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleOpen))
            } else {
                BossbarTask.clearBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleClose))
            }
        }else {
            if(isContains) {
                BossbarTask.clearBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleClose))
            } else {
                BossbarTask.setBossbar(player)
                sender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.bossbarToggleOpen))
            }
        }
    }


    @Command(value = "bm title <main> <sub>")
    @Permission("beautifyme.title.all")
    @CommandDescription("向全服玩家发送big-title")
    fun sendTitleToAll(sender: CommandSender, @Argument("main") mainTitle: String, @Argument("sub") subTitle: String) {
        Bukkit.getOnlinePlayers().forEach{ player: Player ->
            player.sendTitle(
                MessageUtil.text(PlaceholderAPI.setPlaceholders(player, mainTitle)),
                MessageUtil.text(PlaceholderAPI.setPlaceholders(player, subTitle))
            )
        }
        BeautifyMe.plugin.server.consoleSender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.sendSuccess))
    }

    @Command(value = "bm title <main> <sub> <player>")
    @Permission("beautifyme.title.single")
    @CommandDescription("向玩家单独发送big-title")
    fun sendTitleToSingle(sender: CommandSender, @Argument("main") mainTitle: String, @Argument("sub") subTitle: String,@Argument("player") player: Player) {
        player.sendTitle(
            MessageUtil.text(PlaceholderAPI.setPlaceholders(player, mainTitle)),
            MessageUtil.text(PlaceholderAPI.setPlaceholders(player, subTitle))
        )
        BeautifyMe.plugin.server.consoleSender.sendMessage(MessageUtil.text(ConfigUtil.langConfig.sendSuccess))
    }
}
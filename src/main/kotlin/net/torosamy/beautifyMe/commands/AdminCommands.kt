package net.torosamy.beautifyMe.commands
import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.scheduler.BossbarTask
import net.torosamy.beautifyMe.scheduler.ScoreboardTask
import net.torosamy.beautifyMe.scheduler.TabListTask
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.beautifyMe.utils.ListenerUtil
import net.torosamy.beautifyMe.utils.SchedulerUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.Sound
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
        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.reloadMessage))
    }
    

    @Command("bm toggle broadcast <player>")
    @Permission("beautifyme.toggle.broadcast.other")
    @CommandDescription("切换其他玩家broadcast的开关闭状态")
    fun playerToggleBroadcastOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.broadcast.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastDisabled))
            return
        }

        if (BeautifyMeAPI.updateBroadcast(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastToggleClose))
        return
    }

    @Command("bm toggle scoreboard <player>")
    @Permission("beautifyme.toggle.scoreboard.other")
    @CommandDescription("切换其他玩家scoreboard的开关闭状态")
    fun playerToggleScoreboardOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.scoreboard.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardDisabled))
            return
        }

        if (BeautifyMeAPI.updateScoreBoard(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleClose))
        return
    }

    @Command("bm toggle tab <player>")
    @Permission("beautifyme.toggle.tab.other")
    @CommandDescription("切换其他玩家tab-list的开关闭状态")
    fun playerToggleTabListOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.tabList.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListDisabled))
            return
        }

        if (BeautifyMeAPI.updateTabList(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListToggleClose))
        return
    }



    @Command("bm toggle bossbar <player>")
    @Permission("beautifyme.toggle.bossbar.other")
    @CommandDescription("切换其他玩家bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.bossbar.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarDisabled))
            return
        }
        
        if (BeautifyMeAPI.updateBossbar(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarToggleClose))
        return
    }


    @Command(value = "bm title <main> <sub>")
    @Permission("beautifyme.title.all")
    @CommandDescription("向全服玩家发送big-title")
    fun sendTitleToAll(sender: CommandSender, @Argument("main") mainTitle: String, @Argument("sub") subTitle: String) {
        Bukkit.getOnlinePlayers().forEach{ player: Player ->
            player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
            
            player.sendTitle(
                MessageUtil.format(player, ConfigUtil.mainConfig.titleBigPrefix + mainTitle),
                MessageUtil.format(player, ConfigUtil.mainConfig.titleSmallPrefix + subTitle)
            )
        }
        BeautifyMe.plugin.server.consoleSender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.sendSuccess))
    }

    @Command(value = "bm title <main> <sub> <player>")
    @Permission("beautifyme.title.single")
    @CommandDescription("向玩家单独发送big-title")
    fun sendTitleToSingle(sender: CommandSender, @Argument("main") mainTitle: String, @Argument("sub") subTitle: String,@Argument("player") player: Player) {
        player.sendTitle(
            MessageUtil.format(player, ConfigUtil.mainConfig.titleBigPrefix + mainTitle),
            MessageUtil.format(player, ConfigUtil.mainConfig.titleSmallPrefix + subTitle)
        )
        BeautifyMe.plugin.server.consoleSender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.sendSuccess))
    }
}
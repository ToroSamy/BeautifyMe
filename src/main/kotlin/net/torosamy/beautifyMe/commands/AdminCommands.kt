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
        BeautifyMeAPI.loadUserdata()
        
        Bukkit.getOnlinePlayers().forEach {
            BeautifyMeAPI.getUserdata(it.name).reloadScoreBoard(it)
        }
        
        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.reloadMessage))
    }

    @Command("bm toggle broadcast <player>")
    @Permission("beautifyme.toggle.broadcast.other")
    @CommandDescription("切换玩家的broadcast的开关闭状态")
    fun playerToggleBroadcastOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.broadcast.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleBroadcast()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.broadcastToggleClose))
        return
    }

    @Command("bm toggle scoreboard <player>")
    @Permission("beautifyme.toggle.scoreboard.other")
    @CommandDescription("切换玩家的scoreboard的开关闭状态")
    fun playerToggleScoreboardOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.scoreboard.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleScoreBoard(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleClose))
        return
    }

    @Command("bm toggle header <player>")
    @Permission("beautifyme.toggle.header.other")
    @CommandDescription("切换玩家的tab页眉的开关闭状态")
    fun playerToggleTabHeaderOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.tabList.headerEnabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleTabHeader(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListHeaderToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListHeaderToggleClose))
        return
    }


    @Command("bm toggle footer <player>")
    @Permission("beautifyme.toggle.footer.other")
    @CommandDescription("切换玩家的tab页脚的开关闭状态")
    fun playerToggleTabFooterOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.tabList.footerEnabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleTabFooter(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListFooterToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListFooterToggleClose))
        return
    }


    @Command("bm toggle name list <player>")
    @Permission("beautifyme.toggle.name.list.other")
    @CommandDescription("切换玩家的tab列表的开关闭状态")
    fun playerToggleNameListOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.tabList.nameListEnabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleNameList(player)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameListToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameListToggleClose))
        return
    }

    @Command("bm toggle join broadcast <player>")
    @Permission("beautifyme.toggle.join.broadcast.other")
    @CommandDescription("切换玩家的上线大标题的开关闭状态")
    fun playerToggleJoinBroadcastOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.joinBroadcast.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleJoinBroadcast()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinBroadcastToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinBroadcastToggleClose))
        return
    }

    @Command("bm toggle join motd <player>")
    @Permission("beautifyme.toggle.join.motd.other")
    @CommandDescription("切换玩家的上线公告的开关闭状态")
    fun playerToggleJoinMotdOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.joinMotd.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleJoinMotd()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMotdToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMotdToggleClose))
        return
    }

    @Command("bm toggle join message <player>")
    @Permission("beautifyme.toggle.join.message.other")
    @CommandDescription("切换玩家的上线消息的开关闭状态")
    fun playerToggleJoinMessageOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.joinMessage.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleJoinMessage()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMessageToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMessageToggleClose))
        return
    }


    @Command("bm toggle quit message <player>")
    @Permission("beautifyme.toggle.quit.message.other")
    @CommandDescription("切换玩家的下线消息的开关闭状态")
    fun playerToggleQuitMessageOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.quitMessage.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleQuitMessage()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.quitMessageToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.quitMessageToggleClose))
        return
    }


    @Command("bm toggle bossbar <player>")
    @Permission("beautifyme.toggle.bossbar.other")
    @CommandDescription("切换玩家的bossbar的开关闭状态")
    fun playerToggleBossbarOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.bossbar.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleBossbar(player)) {
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


    @Command("bm toggle name tag prefix <player>")
    @Permission("beautifyme.toggle.name.tag.prefix.other")
    @CommandDescription("切换玩家的NameTag前缀的开关闭状态")
    fun playerToggleNameTagPrefixOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.nameTag.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleNameTagPrefix()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagPrefixToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagPrefixToggleClose))
        return
    }


    @Command("bm toggle name tag suffix <player>")
    @Permission("beautifyme.toggle.name.tag.suffix.other")
    @CommandDescription("切换玩家的NameTag后缀的开关闭状态")
    fun playerToggleNameTagSuffixOther(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.nameTag.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleNameTagSuffix()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagSuffixToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagSuffixToggleClose))
        return
    }

    @Command("bm toggle name tag below <player>")
    @Permission("beautifyme.toggle.name.tag.below.other")
    @CommandDescription("切换玩家的NameTag下方信息的开关闭状态")
    fun playerToggleNameTagBelowSelf(sender: CommandSender, @Argument("player") player: Player) {
        if(!ConfigUtil.mainConfig.nameTag.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(player.name).toggleNameTagBelow()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagBelowToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagBelowToggleClose))
        return
    }
}
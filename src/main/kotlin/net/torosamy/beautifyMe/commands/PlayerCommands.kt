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
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }
        
        if (BeautifyMeAPI.getUserdata(sender.name).toggleBroadcast()) {
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
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleScoreBoard(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleOpen))
            return
        }
        
        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.scoreboardToggleClose))
        return
    }

    @Command("bm toggle header")
    @Permission("beautifyme.toggle.header.self")
    @CommandDescription("切换自己的tab页眉的开关闭状态")
    fun playerToggleTabHeaderSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.tabList.headerEnabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleTabHeader(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListHeaderToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListHeaderToggleClose))
        return
    }


    @Command("bm toggle footer")
    @Permission("beautifyme.toggle.footer.self")
    @CommandDescription("切换自己的tab页脚的开关闭状态")
    fun playerToggleTabFooterSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.tabList.footerEnabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleTabFooter(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListFooterToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.tabListFooterToggleClose))
        return
    }


    @Command("bm toggle name list")
    @Permission("beautifyme.toggle.name.list.self")
    @CommandDescription("切换自己的tab列表的开关闭状态")
    fun playerToggleNameListSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.tabList.nameListEnabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleNameList(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameListToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameListToggleClose))
        return
    }

    @Command("bm toggle join broadcast")
    @Permission("beautifyme.toggle.join.broadcast.self")
    @CommandDescription("切换自己上线大标题的开关闭状态")
    fun playerToggleJoinBroadcastSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.joinBroadcast.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleJoinBroadcast()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinBroadcastToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinBroadcastToggleClose))
        return
    }

    @Command("bm toggle join motd")
    @Permission("beautifyme.toggle.join.motd.self")
    @CommandDescription("切换自己上线公告的开关闭状态")
    fun playerToggleJoinMotdSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.joinMotd.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleJoinMotd()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMotdToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMotdToggleClose))
        return
    }

    @Command("bm toggle join message")
    @Permission("beautifyme.toggle.join.message.self")
    @CommandDescription("切换自己上线消息的开关闭状态")
    fun playerToggleJoinMessageSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.joinMessage.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleJoinMessage()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMessageToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.joinMessageToggleClose))
        return
    }


    @Command("bm toggle quit message")
    @Permission("beautifyme.toggle.quit.message.self")
    @CommandDescription("切换自己下线消息的开关闭状态")
    fun playerToggleQuitMessageSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.quitMessage.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleQuitMessage()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.quitMessageToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.quitMessageToggleClose))
        return
    }


    @Command("bm toggle bossbar")
    @Permission("beautifyme.toggle.bossbar.self")
    @CommandDescription("切换自己的bossbar的开关闭状态")
    fun playerToggleBossbarSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.bossbar.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleBossbar(sender)) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.bossbarToggleClose))
        return
    }

    @Command("bm toggle name tag prefix")
    @Permission("beautifyme.toggle.name.tag.prefix.self")
    @CommandDescription("切换自己的NameTag前缀的开关闭状态")
    fun playerToggleNameTagPrefixSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.nameTag.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleNameTagPrefix()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagPrefixToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagPrefixToggleClose))
        return
    }


    @Command("bm toggle name tag suffix")
    @Permission("beautifyme.toggle.name.tag.suffix.self")
    @CommandDescription("切换自己的NameTag后缀的开关闭状态")
    fun playerToggleNameTagSuffixSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.nameTag.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleNameTagSuffix()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagSuffixToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagSuffixToggleClose))
        return
    }

    @Command("bm toggle name tag below")
    @Permission("beautifyme.toggle.name.tag.below.self")
    @CommandDescription("切换自己的NameTag下方信息的开关闭状态")
    fun playerToggleNameTagBelowSelf(sender: Player) {
        if(!ConfigUtil.mainConfig.nameTag.enabled) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.functionDisabled))
            return
        }

        if (BeautifyMeAPI.getUserdata(sender.name).toggleNameTagBelow()) {
            sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagBelowToggleOpen))
            return
        }

        sender.sendMessage(MessageUtil.format(ConfigUtil.langConfig.nameTagBelowToggleClose))
        return
    }
}
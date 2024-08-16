package net.torosamy.beautifyMe.commands

import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.command.CommandSender
import org.bukkit.command.ConsoleCommandSender
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
        sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().reloadMessage))
    }

    @Command("bm toggle broadcast <player>")
    @Permission("beautifyme.toggle.broadcast.other")
    @CommandDescription("切换其他玩家broadcast的开关闭状态")
    fun playerToggleBroadcastOther(sender: CommandSender, @Argument("player") player: Player) {
        //如果内存中包含该玩家 则删除地址并开启玩家的broadcast
        if (ConfigUtil.getPlayerToggleConfig().broadcast.contains(player.name)) {
            //从内存的关闭列表中删除
            ConfigUtil.getPlayerToggleConfig().broadcast.remove(player.name)
            //发送开启成功的消息
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleOpen))
        }else {
            //添加到内存的关闭列表中
            ConfigUtil.getPlayerToggleConfig().broadcast.add(player.name)
            //发送关闭成功的消息
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().broadcastToggleClose))
        }
//        //使用指令不操作文件 提高性能
//        //是否开启文件记录状态的功能
//        if(!ConfigUtil.getMainConfig().broadcast.rememberToggleChoice) return
//        //检测被设置状态的玩家 是否允许在文件夹中记录自己的状态
//        if (!player.hasPermission("beautifyme.toggle.broadcast.self")) return
    }


}
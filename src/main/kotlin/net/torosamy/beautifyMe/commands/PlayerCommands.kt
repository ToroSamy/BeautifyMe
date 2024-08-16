package net.torosamy.beautifyMe.commands

import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.incendo.cloud.annotations.Command
import org.incendo.cloud.annotations.CommandDescription
import org.incendo.cloud.annotations.Permission

class PlayerCommands {
    @Command("bm toggle broadcast", requiredSender = Player::class)
    @Permission("beautifyme.toggle.broadcast.self")
    @CommandDescription("切换自己的broadcast的开关闭状态")
    fun playerToggleBroadcastSelf(sender: CommandSender) {
        if (ConfigUtil.getPlayerToggleConfig().broadcast.contains(sender.name)) {
            //从内存的关闭列表中删除
            ConfigUtil.getPlayerToggleConfig().broadcast.remove(sender.name)
            //发送开启成功的消息
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleOpen))
        }else {
            //添加到内存的关闭列表中
            ConfigUtil.getPlayerToggleConfig().broadcast.add(sender.name)
            //发送关闭成功的消息
            sender.sendMessage(MessageUtil.text(ConfigUtil.getLangConfig().bossbarToggleClose))
        }
//        //使用指令不操作文件 提高性能
//        //是否开启文件记录状态的功能
//        if(!ConfigUtil.getMainConfig().broadcast.rememberToggleChoice) return
//        //检测被设置状态的玩家 是否允许在文件夹中记录自己的状态
//        if (!sender.hasPermission("beautifyme.toggle.broadcast.self")) return
    }
}
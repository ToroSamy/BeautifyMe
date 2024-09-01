package net.torosamy.beautifyMe.scheduler
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable



class BroadcastTask : BukkitRunnable() {
    companion object{
        //当前要发送第几条List<String>
        private var index : Int = 0
    }

    override fun run() {
        val defaultAllStart = ConfigUtil.mainConfig.broadcast.defaultAllStart
        val flag:Int = if (defaultAllStart) 0 else 1

        //获取所有在线的玩家
        Bukkit.getOnlinePlayers().forEach playerLoop@{player:Player ->
            var isContainers:Boolean = ConfigUtil.playerToggleConfig.broadcast[flag].contains(player.name)
            if(!defaultAllStart) isContainers = !isContainers

            // 如果当前在线玩家在关闭列表中存在，则跳过
            if (isContainers) return@playerLoop
            //则将当前的List<String>逐行发送过去
            ConfigUtil.mainConfig.broadcast.messages.get(index).forEach{line:String->
                player.sendMessage(MessageUtil.text(line))
            }
        }
        //发送一条公告后 索引++
        index++
        //如果索引越界则重置索引
        if (index >= ConfigUtil.mainConfig.broadcast.messages.size) index = 0
    }
}
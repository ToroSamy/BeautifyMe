package net.torosamy.beautifyMe.scheduler

import me.clip.placeholderapi.PlaceholderAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TabListTask : BukkitRunnable() {
    override fun run() {
        val defaultAllStart = ConfigUtil.getMainConfig().tabList.defaultAllStart
        val flag:Int = if (defaultAllStart) 0 else 1

        Bukkit.getOnlinePlayers().forEach { player: Player ->
            var isContains:Boolean = ConfigUtil.getPlayerToggleConfig().tabList[flag].contains(player.name)
            if(!defaultAllStart) isContains = !isContains

            if (isContains) return
            setTabList(player)
        }
    }

    companion object {
        fun setTabList(player: Player) {
            //构建header
            var stringBuilder = StringBuilder()
            for (i in 0 until ConfigUtil.getMainConfig().tabList.header.size) {
                if (i == 0) {
                    stringBuilder.append(ConfigUtil.getMainConfig().tabList.header.get(i))
                    continue
                }

                stringBuilder.append("\n")
                stringBuilder.append(ConfigUtil.getMainConfig().tabList.header.get(i))
            }
            var header: String = MessageUtil.text(PlaceholderAPI.setPlaceholders(player, stringBuilder.toString()))

            //构建footer
            stringBuilder.clear()
            for (i in 0 until ConfigUtil.getMainConfig().tabList.footer.size) {
                if (i == 0) {
                    stringBuilder.append(ConfigUtil.getMainConfig().tabList.footer.get(i))
                    continue
                }

                stringBuilder.append("\n")
                stringBuilder.append(ConfigUtil.getMainConfig().tabList.footer.get(i))
            }
            var footer: String = MessageUtil.text(PlaceholderAPI.setPlaceholders(player, stringBuilder.toString()))

            //设置nameList
            player.setPlayerListName(
                MessageUtil.text(
                    PlaceholderAPI.setPlaceholders(
                        player,
                        ConfigUtil.getMainConfig().tabList.nameList
                    )
                )
            )
            //设置Header和Footer
            player.setPlayerListHeaderFooter(header, footer)
        }

        fun clearTabList(player: Player) {
            //设置nameList
            player.setPlayerListName(player.name)
            //设置Header和Footer
            player.setPlayerListHeaderFooter("", "")
        }
    }
}
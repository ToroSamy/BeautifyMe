package net.torosamy.beautifyMe.scheduler


import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.utils.ConfigUtil
import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitRunnable

class TabListTask : BukkitRunnable() {
    override fun run() {
        if (!ConfigUtil.mainConfig.tabList.enabled) {
            Bukkit.getOnlinePlayers().forEach{
                BeautifyMeAPI.stopTabList(it, false)
                BeautifyMeAPI.nameList(it)
            }

            this.cancel()
            return
        }

        for (player in Bukkit.getOnlinePlayers()) {
            BeautifyMeAPI.tabList(player)
            BeautifyMeAPI.nameList(player)
        }
    }
}
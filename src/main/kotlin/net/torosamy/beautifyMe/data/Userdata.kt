package net.torosamy.beautifyMe.data


import net.kyori.adventure.text.Component
import net.minecraft.network.chat.IChatBaseComponent
import net.minecraft.network.protocol.game.ClientboundPlayerInfoUpdatePacket
import net.minecraft.server.level.EntityPlayer
import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.api.BeautifyMeAPI
import net.torosamy.beautifyMe.api.BeautifyMeAPI.UPDATE_DISPLAY_NAME_SET
import net.torosamy.beautifyMe.api.BeautifyMeAPI.getBossbar
import net.torosamy.beautifyMe.utils.ConfigUtil
import net.torosamy.torosamyCore.utils.MessageUtil
import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.configuration.ConfigurationSection
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.craftbukkit.v1_21_R5.entity.CraftPlayer
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.bukkit.scoreboard.Team
import java.io.File
import java.io.IOException

class Userdata {
    val username: String

    var broadcastEnable: Boolean = true
    
    val broadcastMessages: ArrayList<List<String>> = arrayListOf()
    
    var broadcastMessagesIndex: Int = 0

    var scoreboardEnable: Boolean = true
    
    var scoreBoardTitle: String? = null
    
    val scoreBoardLines: ArrayList<String> = arrayListOf()

    var tabHeaderEnable: Boolean = true 
    
    val tabHeader: ArrayList<String> = arrayListOf()

    var tabFooterEnable: Boolean = true

    val tabFooter: ArrayList<String> = arrayListOf()

    var nameListForce: Boolean = false
    
    var nameListEnabled: Boolean = true
    
    var nameList: String? = null
    
    var joinBroadcastEnable: Boolean = true

    var joinBroadcastTitle: String? = null

    var joinBroadcastSubTitle: String? = null

    var joinMessageEnable: Boolean = true

    var joinMessage:String? = null
    
    var quitMessageEnable: Boolean = true

    var quitMessage:String? = null
    
    var joinMotdEnable:Boolean = true

    val joinMotdMessages: ArrayList<String> = arrayListOf()

    var namePrefix: String? = null

    var nameSuffix: String? = null
    
    var nameBelow: String? = null
    
    var namePrefixEnable: Boolean = true
    
    var nameSuffixEnable: Boolean = true
    
    var nameBelowEnable: Boolean = true
    
    var bossbarEnabled: Boolean = true
    
    val config: ConfigurationSection
    
    companion object {
        public fun generate(config: ConfigurationSection): Userdata? {
            config.getString("username", null) ?: return null
            
            return Userdata(config)
        }
    }
    
    public constructor(username: String) {
        this.username = username
        
        this.config = YamlConfiguration()
    }

    private constructor(config: ConfigurationSection) {
        this.username = config.getString("username")!!
        
        this.config = config
        
        this.broadcastEnable = config.getBoolean("broadcastEnable", true)

        val broadcastMessagesSection = config.getConfigurationSection("broadcastMessages")

        broadcastMessagesSection?.getKeys(false)?.forEach {
            this.broadcastMessages.add(broadcastMessagesSection.getStringList(it))
        }
        
        this.scoreboardEnable = config.getBoolean("scoreboardEnable", true)

        this.scoreBoardTitle = config.getString("scoreboardTitle", null)

        this.scoreBoardLines.addAll(config.getStringList("scoreboardLines"))
        
        this.tabHeaderEnable = config.getBoolean("tabHeaderEnable", true)
        
        this.tabHeader.addAll(config.getStringList("tabHeader"))
        
        this.tabFooterEnable = config.getBoolean("tabFooterEnable", true)
        
        this.tabFooter.addAll(config.getStringList("tabFooter"))
        
        this.nameListForce = config.getBoolean("nameListForce", false)
        
        this.nameListEnabled = config.getBoolean("nameListEnabled", true)
        
        this.nameList = config.getString("nameList", null)
        
        this.joinBroadcastEnable = config.getBoolean("joinBroadcastEnable", true)
        
        this.joinBroadcastTitle = config.getString("joinBroadcastTitle", null)
        
        this.joinBroadcastSubTitle = config.getString("joinBroadcastSubTitle", null)
    
        this.joinMessageEnable = config.getBoolean("joinMessageEnable", true)
        
        this.joinMessage = config.getString("joinMessage", null)
        
        this.quitMessageEnable = config.getBoolean("quitMessageEnable", true)
        
        this.quitMessage = config.getString("quitMessage", null)
        
        this.joinMotdEnable = config.getBoolean("joinMotdEnable", true)
        
        this.joinMotdMessages.addAll(config.getStringList("joinMotdMessages"))
        
        this.namePrefix = config.getString("namePrefix", null)
        
        this.nameSuffix = config.getString("nameSuffix", null)
        
        this.nameBelow = config.getString("nameBelow", null)
        
        this.namePrefixEnable = config.getBoolean("namePrefixEnable", true)
        
        this.nameSuffixEnable = config.getBoolean("nameSuffixEnable", true)
        
        this.nameBelowEnable = config.getBoolean("nameBelowEnable", true)
    }
    
    public fun saveConfig() {
        this.config.set("username", this.username)
        this.config.set("broadcastEnable", this.broadcastEnable)
        this.config.set("scoreboardEnable", this.scoreboardEnable)
        this.config.set("tabHeaderEnable", this.tabHeaderEnable)
        this.config.set("tabFooterEnable", this.tabFooterEnable)
        this.config.set("nameListForce", this.nameListForce)
        this.config.set("nameListEnabled", this.nameListEnabled)
        this.config.set("joinBroadcastEnable", this.joinBroadcastEnable)
        this.config.set("joinMessageEnable", this.joinMessageEnable)
        this.config.set("quitMessageEnable", this.quitMessageEnable)
        this.config.set("joinMotdEnable", this.joinMotdEnable)
        this.config.set("namePrefixEnable", this.namePrefixEnable)
        this.config.set("nameSuffixEnable", this.nameSuffixEnable)
        this.config.set("nameBelowEnable", this.nameBelowEnable)
        
        val userdataFolder = File(BeautifyMe.plugin.getDataFolder(), "userdata")

        if (!userdataFolder.exists()) {
            userdataFolder.mkdirs()
        }

        val rootConfig = this.config.root as YamlConfiguration
        try {
            rootConfig.save(File(userdataFolder, "$username.yml"))
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
    
    public fun broadcast(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }

        val default = ConfigUtil.mainConfig.broadcast

        if (!default.enabled || !this.broadcastEnable) {
            return true
        }

        val messages = if (!player.hasPermission(default.customPermission) || this.broadcastMessages.isEmpty())
            default.messages else
            this.broadcastMessages

        if (messages.isEmpty()) {
            return true
        }

        if (broadcastMessagesIndex > messages.size - 1) {
            broadcastMessagesIndex = 0
        }

        messages[broadcastMessagesIndex].forEach{
            player.sendMessage(MessageUtil.format(player, it))
        }
        
        return true
    }

    public fun toggleBroadcast(): Boolean {
        this.broadcastEnable = !this.broadcastEnable
        return this.broadcastEnable
    }

    public fun scoreboard(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }

        val default = ConfigUtil.mainConfig.scoreboard

        if (!default.enabled || !this.scoreboardEnable) {
            BeautifyMeAPI.getScoreboard(username).clear(player)
            return true
        }

        BeautifyMeAPI.getScoreboard(username).update(player)
        return true
    }


    public fun reloadScoreBoard(player: Player) {
        toggleScoreBoard(player)
        toggleScoreBoard(player)
    }

    
    public fun toggleScoreBoard(player: Player): Boolean {
        val oldValue = this.scoreboardEnable
        
        this.scoreboardEnable = !this.scoreboardEnable
        
        if (!scoreboard(player)) {
            this.scoreboardEnable = oldValue
        }

        return this.scoreboardEnable
    }

    public fun tabHeader(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }
        
        val default = ConfigUtil.mainConfig.tabList

        if (!default.headerEnabled || !this.tabHeaderEnable) {
            player.playerListHeader = ""
            return true
        }

        val header = if (!player.hasPermission(default.customHeaderPermission) || this.tabHeader.isEmpty())
            ConfigUtil.mainConfig.tabList.header.joinToString("\n&r") else
            this.tabHeader.joinToString("\n&r")

        player.playerListHeader = MessageUtil.format(player, header)
        return true
    }

    public fun toggleTabHeader(player: Player): Boolean {
        val oldValue = this.tabHeaderEnable
        
        this.tabHeaderEnable = !this.tabHeaderEnable

        if (!tabHeader(player)) {
            this.tabHeaderEnable = oldValue
        }

        return this.tabHeaderEnable
    }

    public fun tabFooter(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }
        
        val default = ConfigUtil.mainConfig.tabList

        if (!default.footerEnabled || !this.tabFooterEnable) {
            player.playerListFooter = ""
            return true
        }

        val footer = if (!player.hasPermission(default.customFooterPermission) || this.tabFooter.isEmpty())
            ConfigUtil.mainConfig.tabList.footer.joinToString("\n&r") else
            this.tabFooter.joinToString("\n&r")

        player.playerListFooter = MessageUtil.format(player, footer)
        return true
    }

    public fun toggleTabFooter(player: Player): Boolean {
        val oldValue = this.tabFooterEnable
        this.tabFooterEnable = !this.tabFooterEnable

        if (!tabFooter(player)) {
            this.tabFooterEnable = oldValue
        }

        return this.tabFooterEnable
    }
    public fun nameList(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }
        
        val entries: ArrayList<EntityPlayer> = ArrayList()

        for (targetPlayer in Bukkit.getOnlinePlayers()) {
            val targetEntityPlayer = (targetPlayer as CraftPlayer).handle
            
            targetEntityPlayer.listName = BeautifyMeAPI
                .getUserdata(targetPlayer.name)
                .getNameList(targetPlayer, username)

            entries.add(targetEntityPlayer)
        }

        val packet = ClientboundPlayerInfoUpdatePacket(UPDATE_DISPLAY_NAME_SET, entries)
        (player as CraftPlayer).handle.g.b(packet)
        
        return true
    }
    
    public fun getNameList(holder: Player, viewerName: String): IChatBaseComponent {
        val userdata = BeautifyMeAPI.getUserdata(viewerName)
        
        val enabled = 
            this.nameListForce ||
            (ConfigUtil.mainConfig.tabList.nameListEnabled && userdata.nameListEnabled)
        
        if (!enabled) {
            return MessageUtil.nmsComponent(this.username)
        }
        
        val nameList = if (!holder.hasPermission(ConfigUtil.mainConfig.tabList.customNameListPermission) || this.nameList == null)
            ConfigUtil.mainConfig.tabList.nameList else
            this.nameList
        
        return MessageUtil.nmsComponent(holder, nameList)
    }
    public fun toggleNameList(player: Player): Boolean {
        val oldValue = this.nameListEnabled
        
        this.nameListEnabled = !this.nameListEnabled

        if (!nameList(player)) {
            this.nameListEnabled = oldValue
        }

        return this.nameListEnabled
    }

    public fun toggleNameListForce(): Boolean {
        this.nameListForce = !this.nameListForce
        
        return this.nameListForce
    }
    
    public fun joinBroadcast(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }
        
        val default = ConfigUtil.mainConfig.joinBroadcast
        
        if (!default.enabled || !this.joinBroadcastEnable) {
            return true
        }
        
        val title = if (!player.hasPermission(default.customPermission) || this.joinBroadcastTitle == null)
            default.title else
            this.joinBroadcastTitle
        
        val subTitle = if (!player.hasPermission(default.customPermission) || this.joinBroadcastSubTitle == null)
            default.subTitle else
            this.joinBroadcastSubTitle
        
        player.sendTitle(
            MessageUtil.format(player, title),
            MessageUtil.format(player,subTitle)
        )
        return true
    }

    public fun toggleJoinBroadcast(): Boolean {
        this.joinBroadcastEnable = !this.joinBroadcastEnable

        return this.joinBroadcastEnable
    }

    
    public fun toggleJoinMessage(): Boolean {
        this.joinMessageEnable = !this.joinMessageEnable

        return this.joinMessageEnable
    }


    public fun toggleQuitMessage(): Boolean {
        this.quitMessageEnable = !this.quitMessageEnable

        return this.quitMessageEnable
    }


    public fun joinMotd(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }
        
        val default = ConfigUtil.mainConfig.joinMotd
        
        if (!default.enabled || !this.joinMotdEnable) {
            return true
        }

        val messages = if (!player.hasPermission(default.customPermission) || joinMotdMessages.isEmpty())
            default.messages else
            joinMotdMessages

        messages.forEach {
            player.sendMessage(MessageUtil.format(player, it))
        }
        
        return true
    }

    public fun toggleJoinMotd(): Boolean {
        this.joinMotdEnable = !this.joinMotdEnable

        return this.joinMotdEnable
    }


    public fun nameTag(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }

        val default = ConfigUtil.mainConfig.nameTag

        if (!default.enabled || !this.nameListEnabled) {
            return true
        }
        val scoreboard = Bukkit.getScoreboardManager().mainScoreboard
        //TODO        
//        val below = scoreboard.getObjective("BELOW_NAME_OBJ") ?:
//        
//        scoreboard.registerNewObjective("BELOW_NAME_OBJ", "dummy", MessageUtil.component(player, "<aqua>%player_name%测试信息"));
//        
//        below.displaySlot = DisplaySlot.BELOW_NAME
        
        var team = scoreboard.getTeam(player.name)

        if (team == null) {
            team = scoreboard.registerNewTeam(player.name)
        }

        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.ALWAYS)

        if (!team.hasEntry(player.name)) {
            team.addEntry(player.name)
        }
        
        team.prefix(nameTagPrefix(player))
        team.suffix(nameTagSuffix(player))
        
        player.scoreboard = scoreboard
        
        return true
    }
    
    public fun toggleNameTagPrefix(): Boolean {
        this.namePrefixEnable = !this.namePrefixEnable
        return this.namePrefixEnable
    }

    public fun toggleNameTagBelow(): Boolean {
        this.nameBelowEnable = !this.nameBelowEnable
        return this.nameBelowEnable
    }


    public fun toggleNameTagSuffix(): Boolean {
        this.nameSuffixEnable = !this.nameSuffixEnable
        return this.nameSuffixEnable
    }

    private fun nameTagBelow(player: Player): Component {
        val default = ConfigUtil.mainConfig.nameTag

        if (!default.enabled || !this.nameBelowEnable) {
            return Component.empty()
        }

        if (!player.hasPermission(default.customPermission) || this.nameBelow == null) {
            return MessageUtil.component(player, default.below)
        }

        return MessageUtil.component(player, this.nameBelow)
    }
    
    private fun nameTagPrefix(player: Player): Component {
        val default = ConfigUtil.mainConfig.nameTag
        
        if (!default.enabled || !this.namePrefixEnable) {
            return Component.empty()
        }
        
        if (!player.hasPermission(default.customPermission) || this.namePrefix == null) {
            return MessageUtil.component(player, default.prefix)
        }

        return MessageUtil.component(player, this.namePrefix)
    }
    
    private fun nameTagSuffix(player: Player): Component {
        val default = ConfigUtil.mainConfig.nameTag

        if (!default.enabled || !this.nameSuffixEnable) {
            return Component.empty()
        }

        if (!player.hasPermission(default.customPermission) || this.nameSuffix == null) {
            return MessageUtil.component(player, default.suffix)
        }

        return MessageUtil.component(player, this.nameSuffix)
    }

    public fun toggleBossbar(player: Player): Boolean {
        if (player.name != username || !player.isOnline) {
            return this.bossbarEnabled
        }

        val oldValue = this.bossbarEnabled
        
        this.bossbarEnabled = !this.bossbarEnabled

        Bukkit.getBossBars().forEach {
            it.removePlayer(player)
        }
        getBossbar().removePlayer(player)

        if (this.bossbarEnabled && !bossbar(player)) {
            this.bossbarEnabled = oldValue
        }
        
        return this.bossbarEnabled
    }
    
    public fun bossbar(player: Player): Boolean {
        if (player.name != this.username || !player.isOnline) {
            return false
        }
        
        if (!ConfigUtil.mainConfig.bossbar.enabled || !this.bossbarEnabled) {
            return true
        }

        getBossbar().style = BarStyle.valueOf(ConfigUtil.mainConfig.bossbar.info.style)

        getBossbar().color = BarColor.valueOf(ConfigUtil.mainConfig.bossbar.info.color)

        getBossbar().setTitle(MessageUtil.format(player, ConfigUtil.mainConfig.bossbar.info.text))

        getBossbar().addPlayer(player)
        
        return false
    }
}
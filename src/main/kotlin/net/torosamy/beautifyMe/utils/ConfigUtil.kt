package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe.Companion.plugin
import net.torosamy.beautifyMe.config.LangConfig
import net.torosamy.beautifyMe.config.MainConfig
import net.torosamy.beautifyMe.config.PlayerToggleConfig
import net.torosamy.torosamyCore.manager.ConfigManager

class ConfigUtil {
    companion object {
        private var mainConfig: MainConfig = MainConfig()
        private var mainConfigManager: ConfigManager = ConfigManager(mainConfig)
        private var langConfig: LangConfig = LangConfig()
        private var langConfigManager: ConfigManager = ConfigManager(langConfig)
        private var playerToggleConfig: PlayerToggleConfig = PlayerToggleConfig()
        private var playerToggleConfigManager: ConfigManager = ConfigManager(playerToggleConfig)

        fun getMainConfig(): MainConfig {return mainConfig}
        fun getLangConfig(): LangConfig {return langConfig}
        fun getPlayerToggleConfig(): PlayerToggleConfig {return playerToggleConfig}

        fun reloadConfig() {
            mainConfigManager.load(plugin, "config.yml")
            langConfigManager.load(plugin, "lang.yml")
            playerToggleConfigManager.load(plugin, "player-toggle.yml")
        }
        fun saveConfig() {
            mainConfigManager.saveFile()
            langConfigManager.saveFile()
            playerToggleConfigManager.saveFile()
        }
    }
}
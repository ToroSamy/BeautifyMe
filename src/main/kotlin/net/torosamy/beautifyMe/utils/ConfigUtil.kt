package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.config.LangConfig
import net.torosamy.beautifyMe.config.MainConfig
import net.torosamy.beautifyMe.config.PlayerToggleConfig
import net.torosamy.torosamyCore.manager.ConfigManager

class ConfigUtil {
    companion object {
        var mainConfig: MainConfig = MainConfig()
        var langConfig: LangConfig = LangConfig()
        var playerToggleConfig: PlayerToggleConfig = PlayerToggleConfig()

        private var mainConfigManager: ConfigManager = ConfigManager(mainConfig, BeautifyMe.plugin, "", "config.yml")
        private var langConfigManager: ConfigManager = ConfigManager(langConfig, BeautifyMe.plugin, "", "lang.yml")
        private var playerToggleConfigManager: ConfigManager = ConfigManager(playerToggleConfig, BeautifyMe.plugin, "", "player-toggle.yml")


        fun reloadConfig() {
            mainConfigManager.load()
            langConfigManager.load()
            playerToggleConfigManager.load()
        }

        fun saveConfig() {
            mainConfigManager.save()
            langConfigManager.save()
            playerToggleConfigManager.save()
        }
    }
}
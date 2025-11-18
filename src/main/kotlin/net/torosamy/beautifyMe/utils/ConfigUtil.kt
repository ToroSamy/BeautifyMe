package net.torosamy.beautifyMe.utils

import net.torosamy.beautifyMe.BeautifyMe
import net.torosamy.beautifyMe.config.LangConfig
import net.torosamy.beautifyMe.config.MainConfig
import net.torosamy.beautifyMe.config.PlayerToggleConfig
import net.torosamy.torosamyCore.config.Config
import net.torosamy.torosamyCore.config.ConfigFile


object ConfigUtil {
    private val configs: ArrayList<Config> = ArrayList()

    public var mainConfig: MainConfig = MainConfig()
    public var langConfig: LangConfig = LangConfig()
    public var playerToggleConfig: PlayerToggleConfig = PlayerToggleConfig()

    fun initConfig() {
        configs.clear()
        configs.add(Config(mainConfig, ConfigFile(BeautifyMe.plugin,"config.yml")))
        configs.add(Config(langConfig, ConfigFile(BeautifyMe.plugin,"lang.yml")))
        configs.add(Config(playerToggleConfig, ConfigFile(BeautifyMe.plugin, "player-toggle.yml")))
    }

    fun reloadConfig() {
        for (config in configs) {
            config.load()
        }
    }

    fun saveConfig() {
        for (config in configs) {
            config.save()
        }
    }
}
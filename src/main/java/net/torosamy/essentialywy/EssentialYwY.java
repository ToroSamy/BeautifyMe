package net.torosamy.essentialywy;

import java.util.*;


import net.torosamy.essentialywy.pojo.Plugin;

import net.torosamy.essentialywy.manager.DataManager;

import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.manager.PluginManager;
import net.torosamy.essentialywy.commands.reloadConfig;
import org.bukkit.Bukkit;


import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;



public final class EssentialYwY extends JavaPlugin {
    private static EssentialYwY mainPlugin;
    private static Map<String, Plugin> pluginList = new HashMap<>();
    private static Map<String, String> lang = new HashMap<>();
    public static Map<String, List<String>> getLangHelp() {
        return langHelp;
    }
    private static Map<String,List<String>> langHelp = new HashMap<>();
    private static Map<String, List<String>> playerData = new HashMap<>();
    private static YamlConfiguration playerDataFile;
    public void onEnable() {
        mainPlugin = this;

        if (!getServer().getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.text("&c[EssentialYwY]检测PlaceholderAPI未开启,关闭插件!"));
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("EssentialYwY")));
            return;
        }
        Objects.requireNonNull(Bukkit.getPluginCommand("essy")).setExecutor(new reloadConfig());

        saveDefaultConfig();


        DataManager.reloadConfig();
        PluginManager.initPlugin();
        PluginManager.reloadPlugin();


    }


    public void onDisable() {
    }

    public static EssentialYwY getMainPlugin() {
        return mainPlugin;
    }
    public static Map<String, Plugin> getPluginList() {
        return pluginList;
    }
    public static Map<String, String> getLang() {
        return lang;
    }
    public static Map<String, List<String>> getPlayerData() {
        return playerData;
    }

    public static YamlConfiguration getPlayerDataFile() {
        return playerDataFile;
    }
    public static void setPlayerDataFile(YamlConfiguration config) {
        EssentialYwY.playerDataFile = config;
    }
}


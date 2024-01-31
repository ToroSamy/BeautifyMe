package net.torosamy.essentialywy;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import net.torosamy.essentialywy.pojo.Plugin;

import net.torosamy.essentialywy.utils.DataManager;


import net.torosamy.essentialywy.utils.commands.reloadConfig;
import net.torosamy.essentialywy.welcome.WelcomeYwY;
import net.torosamy.essentialywy.welcome.WelcomeYwYCommands;
import net.torosamy.essentialywy.welcome.WelcomeYwYListener;

import org.bukkit.Bukkit;

import org.bukkit.plugin.java.JavaPlugin;


public final class EssentialYwY extends JavaPlugin {
    private static EssentialYwY mainPlugin;
    ;


    private static Map<String, Plugin> pluginList;


    private static Map<String, String> lang;

    public void onEnable() {
        mainPlugin = this;
        pluginList = new HashMap<>();

        lang = new HashMap<>();
        Objects.requireNonNull(Bukkit.getPluginCommand("essy")).setExecutor(new reloadConfig());
        Objects.requireNonNull(Bukkit.getPluginCommand("wcy")).setExecutor(new WelcomeYwYCommands());
        getServer().getPluginManager().registerEvents(new WelcomeYwYListener(),this);

        saveDefaultConfig();
        DataManager.initFuncList();
        DataManager.loadLang();

        WelcomeYwY.loadConfig();
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

    public static void setLang(Map<String, String> lang) {
        EssentialYwY.lang = lang;
    }
}


package net.torosamy.essentialywy.utils;

import java.io.File;
import java.util.*;


import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.color.ColorYwY;
import net.torosamy.essentialywy.pojo.Plugin;
import net.torosamy.essentialywy.welcome.WelcomeYwY;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.N;


public class DataManager {



    public static void initFunc(String plugin, HashMap<String, Boolean> funcList) {
        ConfigurationSection configurationSection = EssentialYwY.getMainPlugin().getConfig().getConfigurationSection(plugin + ".func");
        if (configurationSection != null) {
            configurationSection.getKeys(false).forEach(funcName -> {
                funcList.put(funcName, EssentialYwY.getMainPlugin().getConfig().getBoolean(plugin + ".func." + funcName));
                Bukkit.getConsoleSender().sendMessage(MessageUtils.text("&a"+funcName + "功能加载成功"));
            });
        }

    }

    public static void initFuncList() {
        EssentialYwY.getPluginList().clear();
        for (String pluginName : EssentialYwY.getMainPlugin().getConfig().getKeys(false)) {
            Plugin plugin = new Plugin();
            plugin.setPluginName(pluginName);
            HashMap<String, Boolean> func = new HashMap<>();
            initFunc(pluginName, func);
            plugin.setFunc(func);

            plugin.setConfig(loadConfigAsFile(pluginName));

            Bukkit.getConsoleSender().sendMessage(MessageUtils.text("&a"+pluginName + "配置加载成功"));

            if (EssentialYwY.getMainPlugin().getConfig().getBoolean(pluginName + ".enabled")) {
                plugin.setEnabled(true);
                Bukkit.getConsoleSender().sendMessage(MessageUtils.text("&a"+"[EssentialYwY]" + pluginName + " started successfully"));
            } else {
                plugin.setEnabled(false);
                Bukkit.getConsoleSender().sendMessage(MessageUtils.text("&c"+"[EssentialYwY]" + pluginName + " successfully closed"));
            }


            EssentialYwY.getPluginList().put(pluginName, plugin);
        }
    }

    public static YamlConfiguration loadConfigAsFile(String pluginName) {
        String path = EssentialYwY.getMainPlugin().getDataFolder().getPath();
        path += "/func";
        File file = new File(path, pluginName + ".yml");
        if (!file.exists()) {
            EssentialYwY.getMainPlugin().saveResource("func/" + pluginName + ".yml", false);
        }
        return YamlConfiguration.loadConfiguration(file);
    }


    public static void loadPlayerData(){
        String path = EssentialYwY.getMainPlugin().getDataFolder().getPath();
        File file = new File(path, "playerdata.yml");
        if (!file.exists()) {
            EssentialYwY.getMainPlugin().saveResource("playerdata.yml",false);
        }
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.getKeys(false).forEach(func -> EssentialYwY.getPlayerData().put(func,yamlConfiguration.getStringList(func)));
//        EssentialYwY.getPlayerData().forEach((func,playerList)->{
//            System.out.println(func.toString());
//            playerList.forEach(System.out::println);
//            System.out.println("---------------");
//        });
    }

    public static void loadLang() {
        File file = new File(EssentialYwY.getMainPlugin().getDataFolder(), "lang.yml");
        if (!file.exists()) {
            EssentialYwY.getMainPlugin().saveResource("lang.yml", false);
        }
        YamlConfiguration langFile = YamlConfiguration.loadConfiguration(file);
        langFile.getKeys(true).forEach(message -> {
            String[] split = message.split("\\.");
            EssentialYwY.getLang().put(split[split.length - 1], langFile.getString(message));
        });

    }
    public static void loadLangHelp(){
        File file = new File(EssentialYwY.getMainPlugin().getDataFolder(), "langHelp.yml");
        if (!file.exists()) {
            EssentialYwY.getMainPlugin().saveResource("langHelp.yml",false);
        }
        YamlConfiguration yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        yamlConfiguration.getKeys(false).forEach(message -> {
            EssentialYwY.getLangHelp().put(message, yamlConfiguration.getStringList(message));
        });
    }

    public static void reloadConfig() {
        //重载config.yml
        EssentialYwY.getMainPlugin().reloadConfig();
        initFuncList();
        loadLang();
        loadLangHelp();
        loadPlayerData();
        ColorYwY.loadConfig();
        WelcomeYwY.loadConfig();

    }
}

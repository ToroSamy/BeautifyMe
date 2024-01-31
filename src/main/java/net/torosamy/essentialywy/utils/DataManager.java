package net.torosamy.essentialywy.utils;

import java.io.File;
import java.util.*;


import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.pojo.Plugin;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;


public class DataManager {

    public static void initFunc(String plugin, HashMap<String, Boolean> funcList) {
        ConfigurationSection configurationSection = EssentialYwY.getMainPlugin().getConfig().getConfigurationSection(plugin + ".func");
        if (configurationSection != null) {
            configurationSection.getKeys(false).forEach(funcName -> {
                funcList.put(funcName, EssentialYwY.getMainPlugin().getConfig().getBoolean(plugin + ".func." + funcName));
                EssentialYwY.log.info(ColorUtils.text(funcName + "功能加载成功"));
            });
        }


    }

    public static void initFuncList() {
        for (String pluginName : EssentialYwY.getMainPlugin().getConfig().getKeys(false)) {
            Plugin plugin = new Plugin();
            plugin.setPluginName(pluginName);
            HashMap<String, Boolean> func = new HashMap<>();
            initFunc(pluginName, func);
            plugin.setFunc(func);

            plugin.setConfig(loadConfigAsFile(pluginName));
            EssentialYwY.log.info(ColorUtils.text(pluginName + "配置加载成功"));

            if (EssentialYwY.getMainPlugin().getConfig().getBoolean(pluginName + ".enabled")) {
                plugin.setEnabled(true);
                EssentialYwY.log.info(ColorUtils.text("[EssentialYwY]" + pluginName + " started successfully"));
            } else {
                plugin.setEnabled(false);
                EssentialYwY.log.info("[EssentialYwY]" + pluginName + " successfully closed");
            }


            EssentialYwY.getFuncStartList().put(pluginName, plugin);
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
}

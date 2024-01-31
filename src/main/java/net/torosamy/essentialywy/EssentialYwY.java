package net.torosamy.essentialywy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import net.torosamy.essentialywy.pojo.Plugin;

import net.torosamy.essentialywy.utils.DataManager;


import org.bukkit.plugin.java.JavaPlugin;


public final class EssentialYwY extends JavaPlugin {
    private static EssentialYwY mainPlugin;
    public static Logger log;


    private static Map<String, Plugin> funcStartList;




    public void onEnable() {
        mainPlugin = this;
        funcStartList = new HashMap<>();
        log = getLogger();

        saveDefaultConfig();
        DataManager.initFuncList();

    }



    public void onDisable() {
    }


    public static EssentialYwY getMainPlugin() {
        return mainPlugin;
    }

    public static Map<String, Plugin> getFuncStartList() {
        return funcStartList;
    }
}


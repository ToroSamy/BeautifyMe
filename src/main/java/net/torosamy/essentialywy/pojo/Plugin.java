package net.torosamy.essentialywy.pojo;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Map;
import java.util.PrimitiveIterator;

public class Plugin {
    //插件是否启用
    private boolean enabled;
    //插件功能
    private Map<String,Boolean> func;



    private String pluginName;

    public YamlConfiguration getConfig() {
        return config;
    }

    public void setConfig(YamlConfiguration config) {
        this.config = config;
    }

    private YamlConfiguration config;

    public Map<String, Boolean> getFunc() {
        return func;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Plugin() {
    }

    public void setFunc(Map<String, Boolean> func) {
        this.func = func;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getPluginName() {
        return pluginName;
    }

    public void setPluginName(String pluginName) {
        this.pluginName = pluginName;
    }
}

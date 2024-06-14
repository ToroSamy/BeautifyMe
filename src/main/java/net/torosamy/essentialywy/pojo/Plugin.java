package net.torosamy.essentialywy.pojo;

import org.bukkit.configuration.file.YamlConfiguration;

import java.util.Map;
import java.util.PrimitiveIterator;

public class Plugin {
    //插件是否启用
    private boolean enabled;
    //插件功能
    private Map<String,Boolean> func;
    private YamlConfiguration config;
    public void setFunc(Map<String, Boolean> func) {
        this.func = func;
    }
    public Map<String, Boolean> getFunc() {
        return func;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setConfig(YamlConfiguration config) {
        this.config = config;
    }
    public YamlConfiguration getConfig() {
        return config;
    }
}

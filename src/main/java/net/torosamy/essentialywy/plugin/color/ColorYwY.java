package net.torosamy.essentialywy.plugin.color;


import net.torosamy.essentialywy.EssentialYwY;


import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;


public class ColorYwY implements Listener {



    //计分板
    private static Boolean rememberScoreBoardToggle;
    private static String scoreBoardTitle;
    private static List<String> ScoreBoardLines;
    private static BukkitTask scoreBoardClockTask;
    private static Integer scoreBoardTime;


    //自动公告
    private static Boolean rememberBroadcastToggle;
    private static BukkitTask broadcastClockTask;
    private static Integer broadcastTime;
    private static List<List<String>> broadcastText;




    //玩家列表
    private static Integer tabListTime;
    private static Boolean rememberTabListToggle;
    private static List<String> tabListHeader;
    private static List<String> tabListFooter;
    private static String tabListNameList;
    private static BukkitTask tabListClockTask;


    //Bossbar
    private static String bossbarStyle;
    private static String bossbarColor;
    private static String bossbarText;
    private static Integer bossbarTime;
    private static Boolean rememberBossbarToggle;
    private static BukkitTask bossbarClockTask;
    private static NamespacedKey bossbarNameSpaceKey;




    public static void loadConfig() {
        YamlConfiguration colorYwYConfig = EssentialYwY.getPluginList().get("ColorYwY").getConfig();




        //自动公告
        ConfigurationSection sonConfig = colorYwYConfig.getConfigurationSection("broadcast.messages");
        List<List<String>> list = new ArrayList<>();
        for (String messageLine : sonConfig.getKeys(false)) {
            list.add(sonConfig.getStringList(messageLine));
        }
        broadcastText = list;
        broadcastTime = colorYwYConfig.getInt("broadcast.time");
        rememberBroadcastToggle = colorYwYConfig.getBoolean("broadcast.remember-toggle-choice");

        //计分板
        sonConfig = colorYwYConfig.getConfigurationSection("scoreboard.scoreboards");
        rememberScoreBoardToggle = colorYwYConfig.getBoolean("scoreboard.remember-toggle-choice");
        scoreBoardTime = colorYwYConfig.getInt("scoreboard.time");
        scoreBoardTitle = sonConfig.getString("title");
        ScoreBoardLines = sonConfig.getStringList("lines");


        //玩家列表
        rememberTabListToggle = colorYwYConfig.getBoolean("tabList.remember-toggle-choice");
        tabListTime = colorYwYConfig.getInt("tabList.time");
        tabListHeader = colorYwYConfig.getStringList("tabList.header");
        tabListFooter = colorYwYConfig.getStringList("tabList.footer");
        tabListNameList = colorYwYConfig.getString("tabList.nameList");

        //BossBar
        sonConfig = colorYwYConfig.getConfigurationSection("bossbar.info");
        bossbarTime = colorYwYConfig.getInt("bossbar.time");
        rememberBossbarToggle = colorYwYConfig.getBoolean("bossbar.remember-toggle-choice");
        bossbarStyle = sonConfig.getString("style");
        bossbarColor = sonConfig.getString("color");
        bossbarText = sonConfig.getString("text");
        bossbarNameSpaceKey = new NamespacedKey(EssentialYwY.getMainPlugin(),"bossbar");

    }
    //BossBar
    public static String getBossbarStyle() {
        return bossbarStyle;
    }
    public static String getBossbarColor() {
        return bossbarColor;
    }
    public static String getBossbarText() {
        return bossbarText;
    }
    public static Integer getBossbarTime() {
        return bossbarTime;
    }
    public static Boolean getRememberBossbarToggle() {
        return rememberBossbarToggle;
    }
    public static BukkitTask getBossbarClockTask() {
        return bossbarClockTask;
    }
    public static void setBossbarClockTask(BukkitTask bossbarClockTask) {
        ColorYwY.bossbarClockTask = bossbarClockTask;
    }
    public static NamespacedKey getBossbarNameSpaceKey() {
        return bossbarNameSpaceKey;
    }



    //玩家列表
    public static BukkitTask getTabListClockTask() {
        return tabListClockTask;
    }
    public static void setTabListClockTask(BukkitTask tabListClockTask) {
        ColorYwY.tabListClockTask = tabListClockTask;
    }
    public static Integer getTabListTime() {
        return tabListTime;
    }
    public static Boolean getRememberTabListToggle() {
        return rememberTabListToggle;
    }
    public static List<String> getTabListHeader() {
        return tabListHeader;
    }
    public static List<String> getTabListFooter() {
        return tabListFooter;
    }
    public static String getTabListNameList() {
        return tabListNameList;
    }


    //AutoBoardCast
    public static void setBroadcastClockTask(BukkitTask broadcastClockTask) {
        ColorYwY.broadcastClockTask = broadcastClockTask;
    }
    public static BukkitTask getBroadcastClockTask() {
        return broadcastClockTask;
    }
    public static Boolean getRememberBroadcastToggle() {
        return rememberBroadcastToggle;
    }
    public static List<List<String>> getBroadcastText() {
        return broadcastText;
    }
    public static Integer getBroadcastTime() {
        return broadcastTime;
    }


    //Scoreboard
    public static String getScoreBoardTitle() {
        return scoreBoardTitle;
    }
    public static List<String> getScoreBoardLines() {
        return ScoreBoardLines;
    }
    public static Boolean getRememberScoreBoardToggle() {
        return rememberScoreBoardToggle;
    }
    public static BukkitTask getScoreBoardClockTask() {
        return scoreBoardClockTask;
    }
    public static void setScoreBoardClockTask(BukkitTask scoreBoardClockTask) {
        ColorYwY.scoreBoardClockTask = scoreBoardClockTask;
    }
    public static Integer getScoreBoardTime() {
        return scoreBoardTime;
    }



}


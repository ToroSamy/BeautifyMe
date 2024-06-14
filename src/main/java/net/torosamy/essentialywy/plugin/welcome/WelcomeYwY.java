package net.torosamy.essentialywy.plugin.welcome;

import net.torosamy.essentialywy.EssentialYwY;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class WelcomeYwY {

    private static String titleBroadcast;
    private static String subTitleBroadcast;
    private static Listener welPlayerOnJoin;
    private static Listener welPlayerOnQuit;
    private static List<String> quitActionList;
    private static List<String> JoinActionList;
    private static List<String> firstJoinKey;
    private static Integer firstJoinTime;
    //发送key的player触发的Action
    private static List<String> firstJoinActionList;
    //已经触发过Key的Action的Player列表
    private static List<Player> fristJoinGetAction = new ArrayList<>();
    //第一次进入Server的player触发的Action
    private static List<String> firstJoinAction;
    private static BukkitTask firstJoinClockTask;
    private static Listener welPlayerOnChat;

    public static void loadConfig() {
        YamlConfiguration welcomeYwYConfig = EssentialYwY.getPluginList().get("WelcomeYwY").getConfig();

        titleBroadcast = welcomeYwYConfig.getString("join-broadcast.title");
        subTitleBroadcast = welcomeYwYConfig.getString("join-broadcast.subtitle");

        ConfigurationSection configurationSection = welcomeYwYConfig.getConfigurationSection("first-join-welcome");
        if (configurationSection != null) {
            firstJoinKey = configurationSection.getStringList("key");
            firstJoinTime = configurationSection.getInt("Time");
            firstJoinActionList = configurationSection.getStringList("Action");
            firstJoinAction = configurationSection.getStringList("FirstJoinAction");
        }


        JoinActionList = welcomeYwYConfig.getStringList("join-event");
        quitActionList = welcomeYwYConfig.getStringList("quit-event");
    }
    public static List<String> getJoinActionList() {
        return JoinActionList;
    }
    public static List<String> getQuitActionList() {
        return quitActionList;
    }


    public static String getTitleBroadcast() {
        return titleBroadcast;
    }

    public static String getSubTitleBroadcast() {
        return subTitleBroadcast;
    }
    public static Listener getWelPlayerOnJoin() {
        return welPlayerOnJoin;
    }

    public static void setWelPlayerOnJoin(Listener welPlayerOnJoin) {
        WelcomeYwY.welPlayerOnJoin = welPlayerOnJoin;
    }
    public static Listener getWelPlayerOnQuit() {
        return welPlayerOnQuit;
    }
    public static void setWelPlayerOnQuit(Listener welPlayerOnQuit) {
        WelcomeYwY.welPlayerOnQuit = welPlayerOnQuit;
    }
    public static List<String> getFirstJoinKey() {
        return firstJoinKey;
    }
    public static List<String> getFirstJoinActionList() {
        return firstJoinActionList;
    }
    public static int getFirstJoinTime() {
        return firstJoinTime;
    }
    public static List<String> getFirstJoinAction() {
        return firstJoinAction;
    }
    public static List<Player> getFristJoinGetAction() {
        return fristJoinGetAction;
    }
    public static Listener getWelPlayerOnChat() {
        return welPlayerOnChat;
    }
    public static void setWelPlayerOnChat(Listener welPlayerOnChat) {
        WelcomeYwY.welPlayerOnChat = welPlayerOnChat;
    }
    public static BukkitTask getFirstJoinClockTask() {
        return firstJoinClockTask;
    }
    public static void setFirstJoinClockTask(BukkitTask firstJoinClockTask) {
        WelcomeYwY.firstJoinClockTask = firstJoinClockTask;
    }
}


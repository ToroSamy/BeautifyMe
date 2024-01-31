package net.torosamy.essentialywy.welcome;

import net.torosamy.essentialywy.EssentialYwY;

public class WelcomeYwY {
    private static String joinMessage;
    private static String quitMessage;

    private static String titleBroadcast;
    private static String subTitleBroadcast;

    public static void loadConfig() {
//        EssentialYwY.getPluginList().get("WelcomeYwY").getConfig().getKeys(true).forEach(key -> {
//            String[] split = key.split("\\.");
//            String choice = split[split.length - 1];
//
//        });
        titleBroadcast = EssentialYwY.getPluginList().get("WelcomeYwY").getConfig().getString("join-broadcast.title");
        subTitleBroadcast = EssentialYwY.getPluginList().get("WelcomeYwY").getConfig().getString("join-broadcast.subtitle");
        joinMessage = EssentialYwY.getPluginList().get("WelcomeYwY").getConfig().getString("join-message");
        quitMessage = EssentialYwY.getPluginList().get("WelcomeYwY").getConfig().getString("quit-message");
    }

    public static String getJoinMessage() {
        return joinMessage;
    }

    public static String getQuitMessage() {
        return quitMessage;
    }

    public static String getTitleBroadcast() {
        return titleBroadcast;
    }

    public static String getSubTitleBroadcast() {
        return subTitleBroadcast;
    }
}


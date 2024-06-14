package net.torosamy.essentialywy.manager;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.plugin.color.commands.ColCommands;
import net.torosamy.essentialywy.plugin.color.clock.BossBarClock;
import net.torosamy.essentialywy.plugin.color.clock.BroadcastClock;
import net.torosamy.essentialywy.plugin.color.clock.ScoreBoardClock;
import net.torosamy.essentialywy.plugin.color.clock.TabListClock;
import net.torosamy.essentialywy.plugin.color.ColorYwY;
import net.torosamy.essentialywy.plugin.teleport.commands.TelCommands;
import net.torosamy.essentialywy.plugin.teleport.TeleportYwY;
import net.torosamy.essentialywy.plugin.teleport.listener.TelPlayerOnJoin;
import net.torosamy.essentialywy.plugin.teleport.listener.TelPlayerOnRespawn;
import net.torosamy.essentialywy.plugin.welcome.WelcomeYwY;
import net.torosamy.essentialywy.plugin.welcome.commands.WelCommands;
import net.torosamy.essentialywy.plugin.welcome.clock.FirstJoinClock;
import net.torosamy.essentialywy.plugin.welcome.listener.WelPlayerOnChat;
import net.torosamy.essentialywy.plugin.welcome.listener.WelPlayerOnJoin;
import net.torosamy.essentialywy.plugin.welcome.listener.WelPlayerOnQuit;
import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.event.HandlerList;

import java.util.Objects;

public class PluginManager {

    public static void initPlugin() {

        WelcomeYwY.setWelPlayerOnJoin(new WelPlayerOnJoin());
        WelcomeYwY.setWelPlayerOnQuit(new WelPlayerOnQuit());
        WelcomeYwY.setWelPlayerOnChat(new WelPlayerOnChat());
        Objects.requireNonNull(EssentialYwY.getMainPlugin().getCommand("wel")).setExecutor(new WelCommands());

        TeleportYwY.setTelPlayerOnJoin(new TelPlayerOnJoin());
        TeleportYwY.setTelPlayerOnRespawn(new TelPlayerOnRespawn());
        Objects.requireNonNull(EssentialYwY.getMainPlugin().getCommand("spawn")).setExecutor(new TelCommands());
        Objects.requireNonNull(EssentialYwY.getMainPlugin().getCommand("setspawn")).setExecutor(new TelCommands());

        Objects.requireNonNull(EssentialYwY.getMainPlugin().getCommand("col")).setExecutor(new ColCommands());
        Bukkit.createBossBar(ColorYwY.getBossbarNameSpaceKey(),"title", BarColor.valueOf("WHITE"), BarStyle.valueOf("SOLID"));
        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).setVisible(true);
    }

    public static void reloadPlugin() throws IllegalStateException {

        if (EssentialYwY.getPluginList().get("WelcomeYwY").isEnabled()) {
            EssentialYwY.getMainPlugin().getServer().getPluginManager().registerEvents(WelcomeYwY.getWelPlayerOnJoin(), EssentialYwY.getMainPlugin());
            EssentialYwY.getMainPlugin().getServer().getPluginManager().registerEvents(WelcomeYwY.getWelPlayerOnQuit(), EssentialYwY.getMainPlugin());
            EssentialYwY.getMainPlugin().getServer().getPluginManager().registerEvents(WelcomeYwY.getWelPlayerOnChat(),EssentialYwY.getMainPlugin());
            if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("first-join-welcome")) {
                WelcomeYwY.setFirstJoinClockTask(new FirstJoinClock().runTaskTimerAsynchronously(EssentialYwY.getMainPlugin(),0L,20L));
            }else if (WelcomeYwY.getFirstJoinClockTask() != null) {
                WelcomeYwY.getFirstJoinClockTask().cancel();
            }

        }
        else {
            HandlerList.unregisterAll(WelcomeYwY.getWelPlayerOnJoin());
            HandlerList.unregisterAll(WelcomeYwY.getWelPlayerOnQuit());
            HandlerList.unregisterAll(WelcomeYwY.getWelPlayerOnChat());
            if (WelcomeYwY.getFirstJoinClockTask() != null) {
                WelcomeYwY.getFirstJoinClockTask().cancel();
            }
        }

        if (EssentialYwY.getPluginList().get("TeleportYwY").isEnabled()) {
            EssentialYwY.getMainPlugin().getServer().getPluginManager().registerEvents(TeleportYwY.getTelPlayerOnJoin(),EssentialYwY.getMainPlugin());
            EssentialYwY.getMainPlugin().getServer().getPluginManager().registerEvents(TeleportYwY.getTelPlayerOnRespawn(),EssentialYwY.getMainPlugin());
        }
        else {
            HandlerList.unregisterAll(TeleportYwY.getTelPlayerOnJoin());
            HandlerList.unregisterAll(TeleportYwY.getTelPlayerOnRespawn());
        }

        if (EssentialYwY.getPluginList().get("ColorYwY").isEnabled()) {
            if (ColorYwY.getBroadcastClockTask() != null) ColorYwY.getBroadcastClockTask().cancel();
            if (EssentialYwY.getPluginList().get("ColorYwY").getFunc().get("broadcast"))
                ColorYwY.setBroadcastClockTask(new BroadcastClock().runTaskTimerAsynchronously(EssentialYwY.getMainPlugin(), 0L, ColorYwY.getBroadcastTime() * 20L));



            if (ColorYwY.getScoreBoardClockTask() != null) {
                ColorYwY.getScoreBoardClockTask().cancel();
                ScoreBoardClock.clearAllScoreBoard();
            }
            if (EssentialYwY.getPluginList().get("ColorYwY").getFunc().get("scoreboard"))
                ColorYwY.setScoreBoardClockTask(new ScoreBoardClock().runTaskTimer(EssentialYwY.getMainPlugin(), 0L, ColorYwY.getScoreBoardTime() * 20L));



            if (ColorYwY.getTabListClockTask() != null) {
                ColorYwY.getTabListClockTask().cancel();
                TabListClock.clearAllTabList();
            }
            if (EssentialYwY.getPluginList().get("ColorYwY").getFunc().get("tabList"))
                ColorYwY.setTabListClockTask(new TabListClock().runTaskTimer(EssentialYwY.getMainPlugin(), 0L, ColorYwY.getTabListTime() * 20L));



            if(ColorYwY.getBossbarClockTask() != null) {
                ColorYwY.getBossbarClockTask().cancel();
                Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).removeAll();
            }
            if (EssentialYwY.getPluginList().get("ColorYwY").getFunc().get("bossbar"))
                ColorYwY.setBossbarClockTask(new BossBarClock().runTaskTimer(EssentialYwY.getMainPlugin(),0L,ColorYwY.getBossbarTime()*20L));
        }
        else {
            if (ColorYwY.getBroadcastClockTask() != null) {
                ColorYwY.getBroadcastClockTask().cancel();
            }
            if (ColorYwY.getScoreBoardClockTask() != null) {
                ColorYwY.getScoreBoardClockTask().cancel();
                ScoreBoardClock.clearAllScoreBoard();
            }
            if (ColorYwY.getTabListClockTask() != null) {
                ColorYwY.getTabListClockTask().cancel();
                TabListClock.clearAllTabList();
            }
            if(ColorYwY.getBossbarClockTask() != null) {
                ColorYwY.getBossbarClockTask().cancel();
                Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).removeAll();
            }
        }
    }
}

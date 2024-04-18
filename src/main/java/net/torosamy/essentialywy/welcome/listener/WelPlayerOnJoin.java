package net.torosamy.essentialywy.welcome.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.color.ColorYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.welcome.WelcomeYwY;
import net.torosamy.essentialywy.welcome.clock.FirstJoinClock;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.TimeUnit;

public class WelPlayerOnJoin implements Listener {
    @EventHandler
    public void playerOnJoin(PlayerJoinEvent event) {
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("join-message")) {
            event.setJoinMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(), WelcomeYwY.getJoinMessage())));
        }



        //检测config里是否开启该插件的功能
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("join-broadcast")) {
            event.getPlayer().sendTitle(
                    MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(),
                            WelcomeYwY.getTitleBroadcast())), MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(), WelcomeYwY.getSubTitleBroadcast())));
        }



        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("join-event")) {
            for (String action : WelcomeYwY.getJoinActionList()) {

                if (action.startsWith("[message] ")) {
                    event.getPlayer().sendMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[message] ",""))));
                    continue;
                }
                if (action.startsWith("[allMessage] ")) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(player,action.replace("[allMessage] ",""))));
                    }
                    continue;
                }

                if (action.startsWith("[console] ")) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),action.replace("[console] ",""));
                    continue;
                }

                if (action.startsWith("[player] ")) {
                    Bukkit.dispatchCommand(event.getPlayer(),action.replace("[player] ",""));
                    continue;
                }
            }
        }


        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("first-join-welcome")) {
            long playedTime = System.currentTimeMillis() - event.getPlayer().getFirstPlayed();
            if (playedTime < 100) {
                WelcomeYwY.getFristJoinGetAction().clear();
                FirstJoinClock.time = WelcomeYwY.getFirstJoinTime();
                for (String action : WelcomeYwY.getFirstJoinAction()) {

                    if (action.startsWith("[message] ")) {
                        event.getPlayer().sendMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[message] ",""))));
                        continue;
                    }
                    if (action.startsWith("[allMessage] ")) {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(player,action.replace("[allMessage] ",""))));
                        }
                        continue;
                    }
                    if (action.startsWith("[console] ")) {
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),action.replace("[console] ",""));
                        continue;
                    }

                    if (action.startsWith("[player] ")) {
                        Bukkit.dispatchCommand(event.getPlayer(),action.replace("[player] ",""));
                        continue;
                    }
                }
            }
        }

    }
}

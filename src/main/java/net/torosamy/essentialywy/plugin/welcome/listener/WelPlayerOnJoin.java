package net.torosamy.essentialywy.plugin.welcome.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.plugin.welcome.WelcomeYwY;
import net.torosamy.essentialywy.plugin.welcome.clock.FirstJoinClock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class WelPlayerOnJoin implements Listener {
    @EventHandler
    public void playerOnJoin(PlayerJoinEvent event) {

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
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[console] ","")));
                    continue;
                }

                if (action.startsWith("[player] ")) {
                    Bukkit.dispatchCommand(event.getPlayer(),PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[player] ","")));
                    continue;
                }
            }
        }


        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("first-join-event")) {
            long playedTime = System.currentTimeMillis() - event.getPlayer().getFirstPlayed();
            if (playedTime < 500) {
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
                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[console] ","")));
                        continue;
                    }

                    if (action.startsWith("[player] ")) {
                        Bukkit.dispatchCommand(event.getPlayer(),PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[player] ","")));
                        continue;
                    }
                }
            }
        }

    }
}

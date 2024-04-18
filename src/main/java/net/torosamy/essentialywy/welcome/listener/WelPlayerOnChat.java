package net.torosamy.essentialywy.welcome.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.welcome.WelcomeYwY;
import net.torosamy.essentialywy.welcome.clock.FirstJoinClock;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class WelPlayerOnChat implements Listener {

    @EventHandler
    public static void doAction(AsyncPlayerChatEvent event){
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("first-Join-welcome")
                && FirstJoinClock.time >0
                && (!WelcomeYwY.getFristJoinGetAction().contains(event.getPlayer()))
        ) {
            for (String key : WelcomeYwY.getFirstJoinKey()) {
                if (event.getMessage().contains(key)) {
                    WelcomeYwY.getFristJoinGetAction().add(event.getPlayer());
                    for (String action : WelcomeYwY.getFirstJoinActionList()) {
                        if (action.startsWith("[message] ")) {
                            event.getPlayer().sendMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(),action.replace("[message] ",""))));
                            continue;
                        }
                        if (action.startsWith("[allMessage] ")) {
                            Bukkit.getOnlinePlayers().forEach(player -> {
                                MessageUtils.text(PlaceholderAPI.setPlaceholders(event.getPlayer(),MessageUtils.text(action.replace("[allMessage] ",""))));
                            });
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
}

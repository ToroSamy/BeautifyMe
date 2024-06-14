package net.torosamy.essentialywy.plugin.welcome.listener;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.plugin.welcome.WelcomeYwY;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class WelPlayerOnQuit implements Listener {
    @EventHandler
    public void playerOnQuit(PlayerQuitEvent event) {
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("quit-event")) {
            for (String action : WelcomeYwY.getQuitActionList()) {
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

            }
        }
    }
}

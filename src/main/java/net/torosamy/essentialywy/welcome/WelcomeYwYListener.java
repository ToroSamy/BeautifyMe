package net.torosamy.essentialywy.welcome;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class WelcomeYwYListener implements Listener {

    @EventHandler
    public boolean playerOnJoin(PlayerJoinEvent event) {
        if (!EssentialYwY.getPluginList().get("WelcomeYwY").isEnabled()) {
            return true;
        }
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("join-message")) {
            event.setJoinMessage(MessageUtils.text(WelcomeYwY.getJoinMessage()));
        }
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("join-broadcast")){
            Player player = event.getPlayer();
        }
        return true;
    }
    @EventHandler
    public boolean playerOnQuit(PlayerQuitEvent event) {
        if (!EssentialYwY.getPluginList().get("WelcomeYwY").isEnabled()) {
            return true;
        }
        if (EssentialYwY.getPluginList().get("WelcomeYwY").getFunc().get("quit-message")) {
            event.setQuitMessage(MessageUtils.text(WelcomeYwY.getQuitMessage()));
        }
        return true;
    }
}

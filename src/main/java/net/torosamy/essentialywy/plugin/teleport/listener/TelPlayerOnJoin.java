package net.torosamy.essentialywy.plugin.teleport.listener;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.plugin.teleport.TeleportYwY;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class TelPlayerOnJoin implements Listener {
    @EventHandler
    public static void doAction(PlayerJoinEvent event) {
        if (!EssentialYwY.getPluginList().get("TeleportYwY").getFunc().get("spawn")) return;
        if (!TeleportYwY.getIsForceLogin()) return;
        event.getPlayer().teleport(new Location(TeleportYwY.getWorld(),TeleportYwY.getX(),TeleportYwY.getY(),TeleportYwY.getZ(), TeleportYwY.getYaw().floatValue(),TeleportYwY.getPitch().floatValue()));
    }
}

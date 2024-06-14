package net.torosamy.essentialywy.plugin.teleport.commands;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.plugin.teleport.TeleportYwY;
import net.torosamy.essentialywy.manager.DataManager;
import net.torosamy.essentialywy.utils.MessageUtils;
import net.torosamy.essentialywy.manager.PluginManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

public class TelCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (!EssentialYwY.getPluginList().get("TeleportYwY").isEnabled()) {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("func-closed")));
            return true;
        }




        if ("spawn".equalsIgnoreCase(label)) {
            if(commandSender instanceof Player && strings.length == 0){
                Player player = (Player) commandSender;

                if (player.hasPermission("essentialywy.plugin.spawn.*")) {
                    player.teleport(new Location(TeleportYwY.getWorld(),TeleportYwY.getX(),TeleportYwY.getY(),TeleportYwY.getZ(), TeleportYwY.getYaw().floatValue(),TeleportYwY.getPitch().floatValue()));
                    player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("spawn-successful")));
                    return true;
                }

                if (!player.hasPermission("essentialywy.plugin.spawn.spawn")) {
                    player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                    return true;
                }else {
                    if (!player.hasPermission("essentials.teleport.cooldown.bypass")) {
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("spawn-cooldown")));
                        try {
                            Thread.sleep(TeleportYwY.getTime() * 1000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }

                    }
                    player.teleport(new Location(TeleportYwY.getWorld(),TeleportYwY.getX(),TeleportYwY.getY(),TeleportYwY.getZ(), TeleportYwY.getYaw().floatValue(),TeleportYwY.getPitch().floatValue()));
                    player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("spawn-successful")));
                    return true;
                }
            }
            EssentialYwY.getLangHelp().get("spawn-help").forEach(message -> commandSender.sendMessage(MessageUtils.text(message)));
            return true;
        }

        if ("setspawn".equalsIgnoreCase(label)) {
            if(commandSender instanceof Player && strings.length == 0){
                Player player = (Player) commandSender;
                if (player.hasPermission("essentialywy.plugin.spawn.*") || player.hasPermission("essentialywy.plugin.spawn.setspawn")) {
                    Location location = player.getLocation();
//                    String path = EssentialYwY.getMainPlugin().getDataFolder().getPath();
//                    path += "/func";
                    YamlConfiguration config = EssentialYwY.getPluginList().get("TeleportYwY").getConfig();
                    config.set("spawn.location.x",location.getX());
                    config.set("spawn.location.y",location.getY());
                    config.set("spawn.location.z",location.getZ());
                    config.set("spawn.location.world",location.getWorld().getName());
                    config.set("spawn.location.pitch",location.getPitch());
                    config.set("spawn.location.yaw",location.getYaw());
                    try {
                        config.save(new File(EssentialYwY.getMainPlugin().getDataFolder(), "func/TeleportYwY.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    DataManager.reloadConfig();
                    PluginManager.reloadPlugin();
                    player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("setspawn-successful")));
                }
                return true;
            }
        }

        return true;


    }


}

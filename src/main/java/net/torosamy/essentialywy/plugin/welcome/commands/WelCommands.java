package net.torosamy.essentialywy.plugin.welcome.commands;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class WelCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (!EssentialYwY.getPluginList().get("WelcomeYwY").isEnabled()) {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("func-closed")));
            return true;
        }
        if (!"wel".equalsIgnoreCase(label)) {
            return true;
        }

        if (strings.length == 4) {
            if ("title".equalsIgnoreCase(strings[0])) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (strings[3].equals(player.getName())) {
                        player.sendTitle(MessageUtils.text(PlaceholderAPI.setPlaceholders(player, strings[1])), MessageUtils.text(PlaceholderAPI.setPlaceholders(player, strings[2])));
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("send-successfully")));
                        return true;
                    }
                }
                commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("not-found-player")));
                return true;

            }
        }
        if (strings.length == 3) {
            if ("title".equalsIgnoreCase(strings[0])) {
                Bukkit.getOnlinePlayers().forEach(player -> player.sendTitle(MessageUtils.text(PlaceholderAPI.setPlaceholders(player, strings[1])), MessageUtils.text(PlaceholderAPI.setPlaceholders(player, strings[2]))));
                commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("send-successfully")));
                return true;
            }
        }

//        if(strings[0].equals("reload")){
//            WelcomeYwY.loadConfig();
//            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("welcome-reload-successfully")));
//            return true;
//        }
        EssentialYwY.getLangHelp().get("wel-help").forEach(message -> commandSender.sendMessage(MessageUtils.text(message)));
        return true;
    }
}

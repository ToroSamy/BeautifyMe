package net.torosamy.essentialywy.color;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.color.clock.ScoreBoardClock;
import net.torosamy.essentialywy.color.clock.TabListClock;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.NotNull;

public class ColCommands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, @NotNull String[] strings) {
        if (!EssentialYwY.getPluginList().get("ColorYwY").isEnabled()) {
            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("func-closed")));
            return true;
        }
        if (!label.equalsIgnoreCase("col")) {
            return true;
        }
//        if ("reload".equals(strings[0])) {
//            ColorYwY.loadConfig();
//            commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("color-reload-successfully")));
//            return true;
//        }
        if (commandSender instanceof Player) {
            if (strings.length != 2) {
                EssentialYwY.getLangHelp().get("col-help").forEach(message -> commandSender.sendMessage(MessageUtils.text(message)));
                return true;
            }
            if ("toggle".equalsIgnoreCase(strings[0])) {

                if ("broadcast".equalsIgnoreCase(strings[1])) {
                    if (!(commandSender.hasPermission("essentialywy.plugin.col.*") || commandSender.hasPermission("essentialywy.plugin.col.broadcast"))) {
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("broadcast").contains(commandSender.getName())) {
                        EssentialYwY.getPlayerData().get("broadcast").remove(commandSender.getName());
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("broadcast-toggle-open")));
                        return true;
                    } else {
                        EssentialYwY.getPlayerData().get("broadcast").add(commandSender.getName());
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("broadcast-toggle-close")));
                        return true;
                    }
                }
                if ("scoreboard".equalsIgnoreCase(strings[1])) {
                    if (!(commandSender.hasPermission("essentialywy.plugin.col.*") || commandSender.hasPermission("essentialywy.plugin.col.scoreboard"))) {
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("scoreboard").contains(commandSender.getName())) {
                        EssentialYwY.getPlayerData().get("scoreboard").remove(commandSender.getName());

                        ScoreBoardClock.setScoreBoard((Player) commandSender);
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("scoreboard-toggle-open")));
                        return true;
                    } else {
                        EssentialYwY.getPlayerData().get("scoreboard").add(commandSender.getName());

                        ScoreBoardClock.clearScoreBoard((Player) commandSender);
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("scoreboard-toggle-close")));
                        return true;
                    }
                }

                if ("tablist".equalsIgnoreCase(strings[1])) {
                    if (!(commandSender.hasPermission("essentialywy.plugin.col.*") || commandSender.hasPermission("essentialywy.plugin.col.tablist"))) {
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("tabList").contains(commandSender.getName())) {
                        EssentialYwY.getPlayerData().get("tabList").remove(commandSender.getName());

                        TabListClock.setTabList((Player) commandSender);
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("tabList-toggle-open")));
                        return true;
                    } else {
                        EssentialYwY.getPlayerData().get("tabList").add(commandSender.getName());

                        TabListClock.clearTabList((Player) commandSender);
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("tabList-toggle-close")));
                        return true;
                    }
                }

                if ("bossbar".equalsIgnoreCase(strings[1])) {
                    if (!(commandSender.hasPermission("essentialywy.plugin.col.*") || commandSender.hasPermission("essentialywy.plugin.col.bossbar"))) {
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("bossbar").contains(commandSender.getName())) {
                        EssentialYwY.getPlayerData().get("bossbar").remove(commandSender.getName());

                        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).addPlayer((Player) commandSender);
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("bossbar-toggle-open")));
                        return true;
                    } else {
                        EssentialYwY.getPlayerData().get("bossbar").add(commandSender.getName());

                        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).removePlayer((Player) commandSender);
                        commandSender.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("bossbar-toggle-close")));
                        return true;
                    }
                }
            }
        }

        EssentialYwY.getLangHelp().get("col-help").forEach(message -> commandSender.sendMessage(MessageUtils.text(message)));
        return true;
    }
}

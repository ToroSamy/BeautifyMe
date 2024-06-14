package net.torosamy.essentialywy.plugin.color.commands;

import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.plugin.color.ColorYwY;
import net.torosamy.essentialywy.plugin.color.clock.ScoreBoardClock;
import net.torosamy.essentialywy.plugin.color.clock.TabListClock;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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

        if (commandSender instanceof Player && strings.length == 2) {
            Player player = (Player) commandSender;


            if ("toggle".equalsIgnoreCase(strings[0])) {
                if ("broadcast".equalsIgnoreCase(strings[1])) {
                    if (!player.hasPermission("essentialywy.plugin.col.broadcast")) {
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }

                    if (EssentialYwY.getPlayerData().get("broadcast").contains(player.getName())) {
                        EssentialYwY.getPlayerData().get("broadcast").remove(player.getName());
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("broadcast-toggle-open")));
                    } else {
                        EssentialYwY.getPlayerData().get("broadcast").add(player.getName());
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("broadcast-toggle-close")));
                    }
                    if (!ColorYwY.getRememberBroadcastToggle()) return true;
                    if (!player.hasPermission("essentialywy.plugin.col.remember.broadcast")) return true;

                    YamlConfiguration config = EssentialYwY.getPlayerDataFile();
                    config.set("broadcast",EssentialYwY.getPlayerData().get("broadcast"));
                    try {
                        config.save(new File(EssentialYwY.getMainPlugin().getDataFolder(), "playerdata.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return true;
                }
                if ("scoreboard".equalsIgnoreCase(strings[1])) {
                    if (!player.hasPermission("essentialywy.plugin.col.scoreboard")) {
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("scoreboard").contains(player.getName())) {
                        EssentialYwY.getPlayerData().get("scoreboard").remove(player.getName());

                        ScoreBoardClock.setScoreBoard(player);
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("scoreboard-toggle-open")));
                    } else {
                        EssentialYwY.getPlayerData().get("scoreboard").add(player.getName());

                        ScoreBoardClock.clearScoreBoard(player);
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("scoreboard-toggle-close")));
                    }

                    if (!ColorYwY.getRememberScoreBoardToggle()) return true;
                    if (!player.hasPermission("essentialywy.plugin.col.remember.scoreboard")) return true;

                    YamlConfiguration config = EssentialYwY.getPlayerDataFile();
                    config.set("scoreboard",EssentialYwY.getPlayerData().get("scoreboard"));
                    try {
                        config.save(new File(EssentialYwY.getMainPlugin().getDataFolder(), "playerdata.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return true;


                }

                if ("tablist".equalsIgnoreCase(strings[1])) {
                    if (!player.hasPermission("essentialywy.plugin.col.tablist")) {
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("tabList").contains(player.getName())) {
                        EssentialYwY.getPlayerData().get("tabList").remove(player.getName());

                        TabListClock.setTabList(player);
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("tabList-toggle-open")));
                    } else {
                        EssentialYwY.getPlayerData().get("tabList").add(player.getName());

                        TabListClock.clearTabList(player);
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("tabList-toggle-close")));
                    }
                    if (!ColorYwY.getRememberTabListToggle()) return true;
                    if (!player.hasPermission("essentialywy.plugin.col.remember.tabList")) return true;

                    YamlConfiguration config = EssentialYwY.getPlayerDataFile();
                    config.set("tabList",EssentialYwY.getPlayerData().get("tabList"));
                    try {
                        config.save(new File(EssentialYwY.getMainPlugin().getDataFolder(), "playerdata.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return true;
                }

                if ("bossbar".equalsIgnoreCase(strings[1])) {
                    if (!player.hasPermission("essentialywy.plugin.col.bossbar")) {
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("no-permission")));
                        return true;
                    }
                    if (EssentialYwY.getPlayerData().get("bossbar").contains(player.getName())) {
                        EssentialYwY.getPlayerData().get("bossbar").remove(player.getName());

                        Objects.requireNonNull(Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey())).addPlayer(player);
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("bossbar-toggle-open")));
                    } else {
                        EssentialYwY.getPlayerData().get("bossbar").add(player.getName());

                        Objects.requireNonNull(Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey())).removePlayer(player);
                        player.sendMessage(MessageUtils.text(EssentialYwY.getLang().get("bossbar-toggle-close")));
                    }

                    if (!ColorYwY.getRememberBossbarToggle()) return true;
                    if (!player.hasPermission("essentialywy.plugin.col.remember.bossbar")) return true;

                    YamlConfiguration config = EssentialYwY.getPlayerDataFile();
                    config.set("bossbar",EssentialYwY.getPlayerData().get("bossbar"));
                    try {
                        config.save(new File(EssentialYwY.getMainPlugin().getDataFolder(), "playerdata.yml"));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    return true;
                }
            }
        }

        EssentialYwY.getLangHelp().get("col-help").forEach(message -> commandSender.sendMessage(MessageUtils.text(message)));
        return true;
    }
}

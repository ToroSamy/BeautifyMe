package net.torosamy.essentialywy.color.clock;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.color.ColorYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.Bukkit;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;


import java.text.SimpleDateFormat;
import java.time.temporal.ValueRange;
import java.util.*;

public class ScoreBoardClock extends BukkitRunnable {


    @Override
    public void run() {

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (EssentialYwY.getPlayerData().get("scoreboard").contains(player.getName())) {
                return;
            }
            setScoreBoard(player);
        });
    }


    public static void setScoreBoard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.getObjective("side");
        if (obj == null) {
            obj = scoreboard.registerNewObjective("side", "dummy");
        }
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        String title = ColorYwY.getScoreBoardTitle();
        if (title.length() >= 16) {
            Bukkit.getConsoleSender().sendMessage(MessageUtils.text(EssentialYwY.getLang().get("length-too-long")));
            Bukkit.getPluginManager().disablePlugin(Objects.requireNonNull(Bukkit.getPluginManager().getPlugin("EssentialYwy")));
            return;
        }
        obj.setDisplayName(MessageUtils.text(PlaceholderAPI.setPlaceholders(player,title)));

        Integer rank = ColorYwY.getScoreBoardLines().size();
        for (String line : ColorYwY.getScoreBoardLines()) {
            obj.getScore(MessageUtils.text(PlaceholderAPI.setPlaceholders(player,line))).setScore(rank);
            rank--;

        }
        player.setScoreboard(scoreboard);


    }

    public static void clearScoreBoard(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);
        player.setScoreboard(scoreboard);
    }

    public static void clearAllScoreBoard(){
        Bukkit.getOnlinePlayers().forEach(ScoreBoardClock::clearScoreBoard);
    }

}

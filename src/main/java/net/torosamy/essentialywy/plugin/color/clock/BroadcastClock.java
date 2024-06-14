package net.torosamy.essentialywy.plugin.color.clock;

import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.plugin.color.ColorYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.List;


public class BroadcastClock extends BukkitRunnable {
    private static Integer timesStatic;
    private static Integer times;

    public static Integer getTime() {
        return time;
    }

    private static Integer time;
    private static List<List<String>> textList;

    public BroadcastClock() {
        textList = ColorYwY.getBroadcastText();
        timesStatic = textList.size();
        times = timesStatic;
        time = ColorYwY.getBroadcastTime();
    }

    @Override
    public void run() {
        if (times <= 0) {
            times = timesStatic;
        }
        //发送第 静态的总条数 - 变化的条数(从最大开始) 条信息;
        List<String> lines = textList.get(timesStatic - times);

        Bukkit.getOnlinePlayers().forEach(player -> {
            if (!EssentialYwY.getPlayerData().get("broadcast").contains(player.getName())) {
                lines.forEach(line -> {
                    player.sendMessage(MessageUtils.text(PlaceholderAPI.setPlaceholders(player, line)));
                });
            }
        });
        times--;
        //减到最后一条则重置到最大的一条
    }


}

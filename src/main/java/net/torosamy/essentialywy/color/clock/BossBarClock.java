package net.torosamy.essentialywy.color.clock;



import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.color.ColorYwY;
import net.torosamy.essentialywy.utils.MessageUtils;

import org.bukkit.Bukkit;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class BossBarClock extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (EssentialYwY.getPlayerData().get("bossbar").contains(player.getName())) {
                return;
            }
            setBossbar(player);
        });

    }
    public static void setBossbar(Player player){
        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).setColor(BarColor.valueOf(ColorYwY.getBossbarColor()));
        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).setStyle(BarStyle.valueOf(ColorYwY.getBossbarStyle()));
        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).setTitle(MessageUtils.text(PlaceholderAPI.setPlaceholders(player,ColorYwY.getBossbarText())));
        Bukkit.getBossBar(ColorYwY.getBossbarNameSpaceKey()).addPlayer(player);
    }



}

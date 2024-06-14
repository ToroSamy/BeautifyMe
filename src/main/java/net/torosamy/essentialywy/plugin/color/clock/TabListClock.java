package net.torosamy.essentialywy.plugin.color.clock;


import me.clip.placeholderapi.PlaceholderAPI;
import net.torosamy.essentialywy.EssentialYwY;
import net.torosamy.essentialywy.plugin.color.ColorYwY;
import net.torosamy.essentialywy.utils.MessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;


public class TabListClock extends BukkitRunnable {
    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {
            if (EssentialYwY.getPlayerData().get("tabList").contains(player.getName())) {
                return;
            }
            setTabList(player);
        });
    }



    public static void setTabList(Player player) {
        StringBuilder Builder = new StringBuilder();
        for (int i = 0; i < ColorYwY.getTabListHeader().size(); i++) {
            if (i == 0) {
                Builder.append(ColorYwY.getTabListHeader().get(i));
                continue;
            }
            Builder.append("\n");
            Builder.append(ColorYwY.getTabListHeader().get(i));

        }
        String header = MessageUtils.text(PlaceholderAPI.setPlaceholders(player, Builder.toString()));


        Builder = new StringBuilder();
        for (int i = 0; i < ColorYwY.getTabListFooter().size(); i++) {
            if (i == 0) {
                Builder.append(ColorYwY.getTabListFooter().get(i));
                continue;
            }
            Builder.append("\n");
            Builder.append(ColorYwY.getTabListFooter().get(i));

        }


        String footer = MessageUtils.text(PlaceholderAPI.setPlaceholders(player, Builder.toString()));


        player.setPlayerListHeaderFooter(header, footer);
        player.setPlayerListName(MessageUtils.text(PlaceholderAPI.setPlaceholders(player, ColorYwY.getTabListNameList())));
    }

    public static void setAllTabList() {
        Bukkit.getOnlinePlayers().forEach(TabListClock::setTabList);
    }

    public static void clearTabList(Player player) {
        player.setPlayerListHeaderFooter("", "");
        player.setPlayerListName(player.getName());
    }

    public static void clearAllTabList() {
        Bukkit.getOnlinePlayers().forEach(TabListClock::clearTabList);
    }
}

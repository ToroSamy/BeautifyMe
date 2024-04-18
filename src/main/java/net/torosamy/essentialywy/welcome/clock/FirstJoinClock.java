package net.torosamy.essentialywy.welcome.clock;

import net.torosamy.essentialywy.welcome.WelcomeYwY;
import org.bukkit.scheduler.BukkitRunnable;

public class FirstJoinClock extends BukkitRunnable {
    public static Integer time;
    @Override
    public void run() {
        if (time>0) {
            time--;
        }
        checkTime(time);
    }
    private static void checkTime(Integer time){
        if (time <=0) {
            WelcomeYwY.getFirstJoinClockTask().cancel();
        }
    }
}

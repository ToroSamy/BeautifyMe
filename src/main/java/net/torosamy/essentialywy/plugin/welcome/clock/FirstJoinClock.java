package net.torosamy.essentialywy.plugin.welcome.clock;

import net.torosamy.essentialywy.plugin.welcome.WelcomeYwY;
import org.bukkit.scheduler.BukkitRunnable;

public class FirstJoinClock extends BukkitRunnable {
    public static Integer time;
    @Override
    public void run() {
        if (time.intValue() > 0) {
            time = Integer.valueOf(time.intValue() - 1);
        }
        checkTime(time);
    }
    public FirstJoinClock(){
        time = WelcomeYwY.getFirstJoinTime();
    }
    private static void checkTime(Integer time){
        if (time <=0) {
            WelcomeYwY.getFirstJoinClockTask().cancel();
        }
    }
}

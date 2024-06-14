package net.torosamy.essentialywy.plugin.teleport;

import net.torosamy.essentialywy.EssentialYwY;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;

import java.util.Objects;

public class TeleportYwY {
    private static Integer time;
    private static Double x;
    private static Double y;
    private static Double z;
    private static Double yaw;
    private static Double pitch;
    private static World world;

    private static Boolean isForceLogin;
    private static Boolean isRespawn;

    private static Listener telPlayerOnJoin;
    private static Listener telPlayerOnRespawn;

    public static void loadConfig() {
        YamlConfiguration teleportYwYConfig = EssentialYwY.getPluginList().get("TeleportYwY").getConfig();
        ConfigurationSection sonConfig = teleportYwYConfig.getConfigurationSection("spawn");
        if (sonConfig != null) {
            isForceLogin = sonConfig.getBoolean("forceLogin");
            isRespawn = sonConfig.getBoolean("respawn");
            time = sonConfig.getInt("time");

            x = sonConfig.getDouble("location.x");
            y = sonConfig.getDouble("location.y");
            z = sonConfig.getDouble("location.z");
            pitch = sonConfig.getDouble("location.pitch");
            yaw = sonConfig.getDouble("location.yaw");
            world = Bukkit.getWorld(Objects.requireNonNull(sonConfig.getString("location.world")));
        }
    }
    public static Integer getTime() {
        return time;
    }
    public static Double getX() {
        return x;
    }
    public static Double getY() {
        return y;
    }
    public static Double getZ() {
        return z;
    }
    public static World getWorld() {
        return world;
    }
    public static Double getYaw() {
        return yaw;
    }
    public static Double getPitch(){
        return pitch;
    }

    public static void setX(Double x) {
        TeleportYwY.x = x;
    }
    public static void setY(Double y) {
        TeleportYwY.y = y;
    }
    public static void setZ(Double z) {
        TeleportYwY.z = z;
    }
    public static void setWorld(World world) {
        TeleportYwY.world = world;
    }




    public static Boolean getIsForceLogin() {
        return isForceLogin;
    }


    public static Boolean getIsRespawn() {
        return isRespawn;
    }


    public static Listener getTelPlayerOnJoin() {
        return telPlayerOnJoin;
    }

    public static void setTelPlayerOnJoin(Listener telPlayerOnJoin) {
        TeleportYwY.telPlayerOnJoin = telPlayerOnJoin;
    }

    public static Listener getTelPlayerOnRespawn() {
        return telPlayerOnRespawn;
    }


    public static void setTelPlayerOnRespawn(Listener telPlayerOnRespawn) {
        TeleportYwY.telPlayerOnRespawn = telPlayerOnRespawn;
    }


}

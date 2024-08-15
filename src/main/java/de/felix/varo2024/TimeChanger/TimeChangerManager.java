package de.felix.varo2024.TimeChanger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class TimeChangerManager {
    private final Plugin plugin;

    public TimeChangerManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setDay(Player player) {
        player.getWorld().setTime(1000);
    }

    public void setNight(Player player) {
        player.getWorld().setTime(13000);
    }

    public void setMidNight(Player player) {
        player.getWorld().setTime(18000);
    }

    public void setNoon(Player player) {
        player.getWorld().setTime(6000);
    }
}

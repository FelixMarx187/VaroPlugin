package de.felix.varo2024;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class CountdownManager {
    private final Plugin plugin;
    private BukkitRunnable countdownTask;
    private int remainingTime;

    public CountdownManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void startCountdown(int time) {
        cancelCountdown();
        remainingTime = time;

        countdownTask = new BukkitRunnable() {
            @Override
            public void run() {
                if (remainingTime <= 0) {
                    Bukkit.broadcastMessage("Countdown finished!");
                    cancelCountdown();
                } else if (remainingTime <= 5) {
                    Bukkit.broadcastMessage("Time remaining: " + remainingTime + " seconds");
                } else if (remainingTime % 30 == 0) {
                    Bukkit.broadcastMessage("Time remaining: " + remainingTime + " seconds");
                }
                remainingTime--;
            }
        };

        countdownTask.runTaskTimer(plugin, 0L, 20L);
    }

    public void cancelCountdown() {
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }
    }
}
package de.felix.varo2024.Countdown;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
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
                    sendTitleToAllPlayers(ChatColor.BOLD + "Varo",ChatColor.LIGHT_PURPLE + "2024");

                    cancelCountdown();
                } else if (remainingTime <= 5) {
                    sendTitleToAllPlayers(ChatColor.AQUA + String.valueOf(remainingTime),"");
                } else if (remainingTime % 30 == 0) {
                    Bukkit.broadcastMessage(Color.AQUA + "Zeit Verbleibend: " + getFormattedTime());
                }
                remainingTime--;
            }
        };

        countdownTask.runTaskTimer(plugin, 0L, 20L);
    }

    public String getFormattedTime() {
        int minuten = (remainingTime % 3600) / 60;
        int sekunden = remainingTime % 60;

        return String.format("%02d:%02d", minuten,sekunden);
    }

    public void cancelCountdown() {
        if (countdownTask != null) {
            countdownTask.cancel();
            countdownTask = null;
        }
    }
    private void sendTitleToAllPlayers(String title, String subtitle) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(title, subtitle, 10, 70, 20);
        }
    }
}
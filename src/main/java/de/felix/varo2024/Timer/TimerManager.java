package de.felix.varo2024.Timer;


import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class TimerManager {
    private int time;
    private boolean running;
    private Plugin plugin;

    public TimerManager(Plugin plugin) {
        this.plugin = plugin;
    }


    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getFormattedTime() {
        int hours = (time % 86400) / 3600;
        int minutes = (time % 3600) / 60;
        int seconds = time % 60;

        if (hours > 0) {
            return String.format("%02d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%02d:%02d", minutes, seconds);
        }
    }

    public void sendToActionBar() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!isRunning()) {
                player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent("§c§lTimer wurde Pausiert"));
                continue;
            }

            player.sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(getFormattedTime()));
        }
    }

    public void run() {
        new BukkitRunnable() {
            @Override
            public void run() {
                sendToActionBar();

                if (!isRunning()) {
                    return;
                }
                setTime(getTime() + 1);
            }
        }.runTaskTimer(plugin,20,20);
    }
}

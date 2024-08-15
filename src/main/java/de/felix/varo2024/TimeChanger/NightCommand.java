package de.felix.varo2024.TimeChanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NightCommand implements CommandExecutor {
    private final TimeChangerManager timeChangerManager;
    private Player player;

    public NightCommand(TimeChangerManager timeChangerManager) {
        this.timeChangerManager = timeChangerManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        timeChangerManager.setNight(player);
        return false;
    }
}

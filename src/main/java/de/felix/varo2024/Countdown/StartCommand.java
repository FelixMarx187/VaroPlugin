package de.felix.varo2024;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {
    private final CountdownManager countdownManager;

    public StartCommand(CountdownManager countdownManager) {
        this.countdownManager = countdownManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Usage: /start <time in seconds>");
            return false;
        }

        try {
            int time = Integer.parseInt(args[0]);
            countdownManager.startCountdown(time);
            sender.sendMessage("Countdown started for " + time + " seconds.");
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage("Invalid time format. Please use a number.");
            return false;
        }
    }
}
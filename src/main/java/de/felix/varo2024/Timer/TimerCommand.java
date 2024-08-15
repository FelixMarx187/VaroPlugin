package de.felix.varo2024.Timer;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class TimerCommand implements CommandExecutor {
    private final TimerManager timerManager;

    public TimerCommand(TimerManager timerManager) {
        this.timerManager = timerManager;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 0) {
            sender.sendMessage("Usage: /timer set <Zeit> | /timer pause | /timer resume | /timer reset");
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "resume":
                if (timerManager.isRunning()) {
                    sender.sendMessage("§b§lDer Timer läuft bereits!");
                    break;
                }
                timerManager.setRunning(true);
                sender.sendMessage("&b&lTimer wurde fortgesetzt!");
                break;
            case "pause":
                if (!timerManager.isRunning()) {
                    sender.sendMessage("§c§lDer Timer läuft nicht!");
                    break;
                }
                timerManager.setRunning(true);
                sender.sendMessage("§c§lDer Timer wurde pausiert");
                break;
            case "set":
                if (args.length != 2) {
                    sender.sendMessage("Usage: /timer set <Zeit>");
                    return true;
                }
                try {
                    timerManager.setTime(Integer.parseInt(args[1]));
                    sender.sendMessage("Timer wurde gestartet");
                } catch (NumberFormatException e) {
                    sender.sendMessage("Falsches Zeitformat");
                }
                break;
            case "reset":
                timerManager.setRunning(false);
                timerManager.setTime(0);
                sender.sendMessage("Timer wurde zurückgesetzt");
                break;
            default:
                sender.sendMessage("Usage: /timer set <Zeit> | /timer pause | /timer resume | /timer reset");
                break;
        }
        return false;
    }
}

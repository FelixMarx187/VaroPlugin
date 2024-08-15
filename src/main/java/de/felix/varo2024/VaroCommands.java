package de.felix.varo2024;

import de.felix.varo2024.Countdown.CountdownManager;
import de.felix.varo2024.VaroKickOut.Playerlistener;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VaroCommands implements CommandExecutor {
    private final CountdownManager countdownManager;
    private final Playerlistener playerlistener;

    public VaroCommands(CountdownManager countdownManager, Playerlistener playerlistener) {
        this.countdownManager = countdownManager;
        this.playerlistener = playerlistener;

    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage("§5§lVaro §b§l24 §f| §rUsage: /varo start <Zeit in Sekunden> | /varo stop | /varo kick <Spieler>");
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "start":
                if (args.length != 2) {
                    sender.sendMessage("§5§lVaro §b§l24 §f| §rUsage: /varo start <Zeit in Sekunden> | /varo stop | /varo kick <Spieler>");
                    return false;
                }
                try {
                    int time = Integer.parseInt(args[1]);
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.setGameMode(GameMode.ADVENTURE);
                    }
                    countdownManager.startCountdown(time);
                    sender.sendMessage("§5§lVaro §b§l24 §f| §rCountdown wurde gestartet: §d§l" + countdownManager.getFormattedTime());
                    return true;
                } catch (NumberFormatException e) {
                    sender.sendMessage("§5§lVaro §b§l24 §f| §cFalsches Zeit Format.");
                    return false;
                }
            case "stop":
                if (args.length <= 2) {
                    countdownManager.cancelCountdown();
                    sender.sendMessage("§5§lVaro §b§l24 §f| §cCountdown wurde gestoppt");
                    return true;
                }
            case "kick":
                try {
                    playerlistener.kickPlayer(Bukkit.getPlayer(args[1]));
                    return true;
                } catch (ArrayIndexOutOfBoundsException e) {
                    sender.sendMessage("§5§lVaro §b§l24 §f| §rUsage: /varo kick <Spieler>");
                    return false;
                }
            default:
                sender.sendMessage("§5§lVaro §b§l24 §f| §rUsage: /varo start <seconds> | /varo stop | /varo kick <player>");
                return false;
        }
    }
}

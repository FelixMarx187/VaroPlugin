package de.felix.varo2024;

import de.felix.varo2024.Countdown.CountdownManager;
import de.felix.varo2024.VaroKickOut.Playerlistener;
import de.felix.varo2024.VaroTeam.VaroTeamManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class VaroCommands implements CommandExecutor {
    private final CountdownManager countdownManager;
    private final Playerlistener playerlistener;
    private final VaroTeamManager varoTeamManager;

    public VaroCommands(CountdownManager countdownManager, Playerlistener playerlistener, VaroTeamManager varoTeamManager) {
        this.countdownManager = countdownManager;
        this.playerlistener = playerlistener;
        this.varoTeamManager = varoTeamManager;

    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length < 1) {
            sender.sendMessage("Usage: /varo start <Zeit in Sekunden> | /varo stop | /varo kick <Spieler>");
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "start":
                if (args.length != 2) {
                    sender.sendMessage("Usage: /varo start <Zeit in Sekunden> | /varo stop | /varo kick <Spieler>");
                    return false;
                }
                try {
                    int time = Integer.parseInt(args[1]);
                    countdownManager.startCountdown(time);
                    sender.sendMessage("Countdown wurde gestartet: " + countdownManager.getFormattedTime());
                    return true;
                } catch (NumberFormatException e) {
                    sender.sendMessage("Falsches Zeit Format.");
                    return false;
                }
            case "stop":
                if (args.length <= 2) {
                    countdownManager.cancelCountdown();
                    sender.sendMessage("Countdown wurde gestoppt");
                    return true;
                }
            case "kick":
                playerlistener.kickPlayer(Bukkit.getPlayer(args[1]));
                return true;
            case "team":
                if (args.length < 3) {
                    sender.sendMessage("Usage: /varo team create <name> <color> | /varo team add <player> <team>");
                    return false;
                }
                switch (args[1].toLowerCase()) {
                    case "create":
                        if (args.length != 4) {
                            sender.sendMessage("Usage: /varo team create <name> <color>");
                            return false;
                        }
                        String teamName = args[2];
                        String Color = args[3].toLowerCase();
                        varoTeamManager.createTeam(teamName,Color);
                        return true;
                    case "join":
                        if (args.length != 4) {
                            sender.sendMessage("Usage: /varo team join <player> <team>");
                            return false;
                        }
                        String playerName = args[2];
                        String team = args[3];
                        Player player = Bukkit.getPlayer(playerName);
                        if (player != null && player.isOnline()) {
                            varoTeamManager.addPlayerToTeam(player,team);
                            sender.sendMessage("Spieler " + playerName + " wurde dem Team " + team + " hinzugefügt");
                        } else {
                            sender.sendMessage("Spieler " + playerName + " ist nicht Online.");
                        }
                        return true;
                    case "delete":
                        String teamNa = args[2];
                        varoTeamManager.deleteTeam(teamNa);
                    default:
                        sender.sendMessage("Unknown team command. Usage: /varo team create <name> <color> | /varo team add <player> <team>");
                        return false;
                }
            default:
                sender.sendMessage("Usage: /varo start <seconds> | /varo stop | /varo kick <player> | /varo team create <name> <color> | /varo team add <player> <team>");
                return false;
        }
    }
}

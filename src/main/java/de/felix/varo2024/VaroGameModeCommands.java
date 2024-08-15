package de.felix.varo2024;

import de.felix.varo2024.GameModeChanger.GamemodeChanger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class VaroGameModeCommands implements CommandExecutor {
    private final GamemodeChanger gamemodeChanger;
    private String PlayerName;

    public VaroGameModeCommands(GamemodeChanger gamemodeChanger) {
        this.gamemodeChanger = gamemodeChanger;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            PlayerName = player.getName();
        }
        if (args.length < 1) {
            sender.sendMessage("Usage: /gm <Gamemode>");
            return false;
        }
        switch (args[0].toLowerCase()) {
            case "0":
                gamemodeChanger.setGamemodeSurvival(PlayerName);
                return true;
            case "1":
                gamemodeChanger.setGamemodeCreative(PlayerName);
                return true;
            case "2":
                gamemodeChanger.setGamemodeAdventure(PlayerName);
                return true;
            case "3":
                gamemodeChanger.setGamemodeSpectator(PlayerName);
                return true;
            default:
                sender.sendMessage("Usage: /gm <Gamemode>");
                return false;
        }
    }
}

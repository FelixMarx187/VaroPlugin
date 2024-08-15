package de.felix.varo2024.GameModeChanger;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.plugin.Plugin;

public class GamemodeChanger {
    private final Plugin plugin;

    public GamemodeChanger(Plugin plugin) {
        this.plugin = plugin;
    }

    public void setGamemodeCreative(String pPlayerName) {
        Bukkit.getPlayer(pPlayerName).setGameMode(GameMode.CREATIVE);
    }

    public void setGamemodeSurvival(String pPlayerName) {
        Bukkit.getPlayer(pPlayerName).setGameMode(GameMode.SURVIVAL);
    }

    public void setGamemodeAdventure(String pPlayerName) {
        Bukkit.getPlayer(pPlayerName).setGameMode(GameMode.ADVENTURE);
    }

    public void setGamemodeSpectator(String pPlayerName) {
        Bukkit.getPlayer(pPlayerName).setGameMode(GameMode.SPECTATOR);
    }
}

package de.felix.varo2024.VaroTeam;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class VaroTeamManager implements Listener {
    private final Plugin plugin;

    public VaroTeamManager(Plugin plugin) {
        this.plugin = plugin;
    }

    public void createTeam(String pName, String pColor) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"team add " + pName.toLowerCase() + "{\"text\":\"" + pName + "\",\"color\":\""+ pColor+ "\"}");
        Bukkit.getConsoleSender().sendMessage("Team erstellt");
    }

    public void addPlayerToTeam(Player player, String pTeam) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"team join " + player.getName() + " " + pTeam.toLowerCase());
        Bukkit.getConsoleSender().sendMessage(player.getName() + " wurde dem Team " + pTeam + " hinzugefügt");
    }

    public void deleteTeam(String pTeam) {
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),"team remove " + pTeam.toLowerCase());
        Bukkit.getConsoleSender().sendMessage("Team " + pTeam + " wurde erfolgreich gelöscht");
    }
}

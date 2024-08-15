package de.felix.varo2024.VaroKickOut;

import de.felix.varo2024.Varo2024;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Playerlistener implements Listener {
    public Playerlistener(Varo2024 plugin) {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        event.getPlayer().setGameMode(GameMode.SPECTATOR);
        event.getPlayer().kickPlayer("Du bist gestorben, damit bist du vom dies Jährigen Varo ausgeschlossen" + "\n(Du kannst erneut auf dem Server joinen um die anderen Spieler zu beobachten)");
    }

    public void kickPlayer(Player player) {
        player.setGameMode(GameMode.SPECTATOR);
        player.kickPlayer("Du wurdest durch ein Verstoß der Regeln vom dies Jährigen Varo ausgeschlossen!" + "\n(Du kannst erneut auf dem Server joinen um die anderen Spieler zu beobachten)");
    }
}

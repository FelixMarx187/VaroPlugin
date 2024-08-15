package de.felix.varo2024.ServerPrefix;

import de.felix.varo2024.Varo2024;
import jdk.tools.jlink.plugin.Plugin;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class VaroPrefix implements Listener {

    public VaroPrefix(Varo2024 plugin) {
        plugin.getServer().getPluginManager().registerEvents(this,plugin);
    }

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        String prefix = "§5§lVaro §b§l24 §f| §r";
        String format = event.getFormat();
        format = format.replace("<","").replace(">","");
        event.setFormat(format);
        event.setFormat(prefix + event.getFormat());
    }
}

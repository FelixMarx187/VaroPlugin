package de.felix.varo2024;

import de.felix.varo2024.Countdown.CountdownManager;
import de.felix.varo2024.VaroKickOut.Playerlistener;
import de.felix.varo2024.VaroTeam.VaroTeamManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandMap;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;

public class Varo2024 extends JavaPlugin {
    private CountdownManager countdownManager;
    private Playerlistener playerlistener;
    private VaroTeamManager varoTeamManager;

    @Override
    public void onEnable() {
        getLogger().info("Varo2024 plugin wird gestartet...");

        countdownManager = new CountdownManager(this);
        playerlistener = new Playerlistener(this);

        if (registerCommand("varo")) {
            getLogger().info("'varo' Command wurde registriert.");
        } else {
            getLogger().severe("Command 'varo' konnte nicht registriert werden.");
        }

        getLogger().info("Varo2024 Plugin wurde aktiviert!");
    }

    @Override
    public void onDisable() {
        if (countdownManager != null) {
            countdownManager.cancelCountdown();
        }
        getLogger().info("Varo2024 Plugin wurde heruntergefahren!");
    }

    private boolean registerCommand(String name) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(getName(), new Command(name) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new VaroCommands(countdownManager, playerlistener,varoTeamManager).onCommand(sender, this, commandLabel, args);
                }
            });

            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            getLogger().severe("Command konnte nicht Registriert werden: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
package de.felix.varo2024;

import de.felix.varo2024.Countdown.CountdownManager;
import de.felix.varo2024.GameModeChanger.GamemodeChanger;
import de.felix.varo2024.ServerPrefix.VaroPrefix;
import de.felix.varo2024.TimeChanger.*;
import de.felix.varo2024.Timer.TimerCommand;
import de.felix.varo2024.Timer.TimerManager;
import de.felix.varo2024.VaroKickOut.Playerlistener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandMap;
import org.bukkit.Bukkit;

import java.lang.reflect.Field;

public class Varo2024 extends JavaPlugin {
    private CountdownManager countdownManager;
    private Playerlistener playerlistener;
    private GamemodeChanger gamemodeChanger;
    private TimeChangerManager timeChangerManager;
    private VaroPrefix varoPrefix;
    private TimerManager timerManager;

    @Override
    public void onEnable() {
        getLogger().info("Varo2024 plugin wird gestartet...");

        countdownManager = new CountdownManager(this);
        playerlistener = new Playerlistener(this);
        gamemodeChanger = new GamemodeChanger(this);
        timeChangerManager = new TimeChangerManager(this);
        varoPrefix = new VaroPrefix(this);
        timerManager = new TimerManager(this);


        //Varo Commands
        if (registerCommand("varo")) {
            getLogger().info("'varo' Command wurde registriert.");
        } else {
            getLogger().severe("Command 'varo' konnte nicht registriert werden.");
        }
        //GamemodeChanger Commands
        if (registerGameModeCommand("gm")) {
            getLogger().info("c");
        } else {
            getLogger().severe("Command 'gm' konnte nicht registriert werden.");
        }

        //TimeChanger Commands
        if (registerTimeChangerCommand("day","night","midnight","noon")) {
            getLogger().info("'day' / 'night' / 'midnight' / 'noon' Commands wurden registriert");
        } else {
            getLogger().severe("Commands 'day' / 'night' / 'midnight' / 'noon' konnte nicht registriert werden");
        }

        if (registerTimerCommand("timer")) {
            getLogger().info("'timer' Command wurde registriert.");
        } else {
            getLogger().severe("Command 'timer' konnte nicht registriert werden.");
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

    //Varo Commands
    private boolean registerCommand(String name) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(getName(), new Command(name) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new VaroCommands(countdownManager, playerlistener).onCommand(sender, this, commandLabel, args);
                }
            });

            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            getLogger().severe("Command konnte nicht Registriert werden: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //GamemodeChangerCommands
    private boolean registerGameModeCommand(String name) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(getName(), new Command(name) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new VaroGameModeCommands(gamemodeChanger).onCommand(sender, this, commandLabel, args);
                }
            });

            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            getLogger().severe("Command konnte nicht Registriert werden: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //TimeChangerCommands
    private boolean registerTimeChangerCommand(String pDay, String pNight, String pMidnight, String pNoon) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(getName(), new Command(pDay) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new DayCommand(timeChangerManager).onCommand(sender, this, commandLabel, args);
                }
            });

            commandMap.register(getName(), new Command(pNight) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new NightCommand(timeChangerManager).onCommand(sender, this, commandLabel, args);
                }
            });

            commandMap.register(getName(), new Command(pMidnight) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new MidnightCommand(timeChangerManager).onCommand(sender, this, commandLabel, args);
                }
            });

            commandMap.register(getName(), new Command(pNoon) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new NoonCommand(timeChangerManager).onCommand(sender, this, commandLabel, args);
                }
            });
            return true;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            getLogger().severe("Command konnte nicht Registriert werden: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    //timer Command
    private boolean registerTimerCommand(String name) {
        try {
            final Field bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            bukkitCommandMap.setAccessible(true);
            CommandMap commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());

            commandMap.register(getName(), new Command(name) {
                @Override
                public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                    return new TimerCommand(timerManager).onCommand(sender, this, commandLabel, args);
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
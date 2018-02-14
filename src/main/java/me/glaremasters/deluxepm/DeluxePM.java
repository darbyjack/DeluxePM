package me.glaremasters.deluxepm;

import java.util.stream.Stream;
import me.glaremasters.deluxepm.commands.CommandHelp;
import me.glaremasters.deluxepm.commands.CommandReload;
import me.glaremasters.deluxepm.commands.CommandToggle;
import me.glaremasters.deluxepm.commands.base.CommandHandler;
import me.glaremasters.deluxepm.events.CommandPreProcessEvent;
import me.glaremasters.deluxepm.events.PlayerLeaveEvent;
import me.glaremasters.deluxepm.updater.SpigotUpdater;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class DeluxePM extends JavaPlugin {

    private CommandHandler commandHandler;
    public static String prefix;
    private static DeluxePM i;

    public static DeluxePM getI() {
        return i;
    }

    @Override
    public void onEnable() {
        i = this;

        prefix = ChatColor.translateAlternateColorCodes('&', getConfig().getString("plugin-prefix"))
                + ChatColor.RESET + " ";

        saveDefaultConfig();

        commandHandler = new CommandHandler();
        commandHandler.enable();

        getCommand("deluxepm").setExecutor(commandHandler);

        Stream.of(
                new CommandHelp(), new CommandToggle(), new CommandReload()
        ).forEach(commandHandler::register);

        Stream.of(
                new CommandPreProcessEvent(), new PlayerLeaveEvent()
        ).forEach(l -> Bukkit.getPluginManager().registerEvents(l, this));

        if (getConfig().getBoolean("updater.check")) {
            SpigotUpdater updater = new SpigotUpdater(this, 52599);
            try {
                if (updater.checkForUpdates()) {
                    getLogger()
                            .warning(
                                    "You appear to be running a version other than our latest stable release."
                                            + " You can download our newest version at: " + updater
                                            .getResourceURL());
                } else {
                    getLogger().warning("You are currently the latest version of the plugin! - "
                            + getDescription().getVersion());
                }
            } catch (Exception e) {
                getLogger().info("Could not check for updates! Stacktrace:");
                e.printStackTrace();
            }
        }

        if (getServer().getPluginManager().isPluginEnabled("DeluxeChat")) {
            getLogger()
                    .warning("DeluxeChat has been detected! Make sure to enable it in the config!");
        }


    }

    @Override
    public void onDisable() {
        commandHandler.disable();
        if (CommandToggle.togglePM.size() > 0) {
            CommandToggle.togglePM.clear();
        }

    }

    public static String getPrefix() {
        return prefix;
    }

}

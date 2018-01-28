package me.glaremasters.deluxepm;

import java.util.stream.Stream;
import me.glaremasters.deluxepm.commands.CommandHelp;
import me.glaremasters.deluxepm.commands.CommandToggle;
import me.glaremasters.deluxepm.commands.base.CommandHandler;
import me.glaremasters.deluxepm.events.CommandPreProcessEvent;
import me.glaremasters.deluxepm.events.PlayerLeaveEvent;
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
                new CommandHelp(), new CommandToggle()
        ).forEach(commandHandler::register);

        Stream.of(
                new CommandPreProcessEvent(), new PlayerLeaveEvent()
        ).forEach(l -> Bukkit.getPluginManager().registerEvents(l, this));

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

package me.glaremasters.deluxepm.commands;

import me.glaremasters.deluxepm.DeluxePM;
import me.glaremasters.deluxepm.commands.base.CommandBase;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandReload extends CommandBase {

    public CommandReload() {
        super("reload", "Reload config",
                "deluxepm.reload", true, null,
                null, 0, 0);
    }

    public void execute(CommandSender sender, String[] args) {
        DeluxePM.getI().reloadConfig();

        DeluxePM.prefix = ChatColor.translateAlternateColorCodes('&', DeluxePM.getI().getConfig().getString("plugin-prefix"))
                + ChatColor.RESET + " ";

    }
}

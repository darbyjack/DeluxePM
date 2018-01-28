package me.glaremasters.deluxepm.commands;

import me.glaremasters.deluxepm.DeluxePM;
import me.glaremasters.deluxepm.commands.base.CommandBase;
import me.glaremasters.deluxepm.util.ColorUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class CommandHelp extends CommandBase {

    public CommandHelp() {
        super("help", "List all commands", "deluxepm.help", false, null, null, 0, 0);
    }

    public void execute(CommandSender sender, String[] args) {
        FileConfiguration config = DeluxePM.getI().getConfig();

        sender.sendMessage(ColorUtil.color(config.getString("messages.toggle")));
    }

}
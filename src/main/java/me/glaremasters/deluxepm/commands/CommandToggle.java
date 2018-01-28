package me.glaremasters.deluxepm.commands;

import java.util.ArrayList;
import me.glaremasters.deluxepm.DeluxePM;
import me.glaremasters.deluxepm.commands.base.CommandBase;
import me.glaremasters.deluxepm.util.ColorUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class CommandToggle extends CommandBase {

    public static ArrayList<String> togglePM = new ArrayList<>();

    public CommandToggle() {
        super("toggle", "Toggle PMs", "deluxepm.toggle", false, null, null, 0, 0);
    }

    public void execute(Player player, String[] args) {
        FileConfiguration config = DeluxePM.getI().getConfig();
        if (togglePM.contains(player.getName())) {
            togglePM.remove(player.getName());
            player.sendMessage(ColorUtil.color(config.getString("messages.enabled")));
            return;
        }
        togglePM.add(player.getName());
        player.sendMessage(ColorUtil.color(config.getString("messages.disabled")));

    }

}
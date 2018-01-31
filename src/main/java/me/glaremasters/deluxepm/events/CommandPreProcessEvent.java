package me.glaremasters.deluxepm.events;

import me.clip.deluxechat.DeluxeChat;
import me.glaremasters.deluxepm.DeluxePM;
import me.glaremasters.deluxepm.commands.CommandToggle;
import me.glaremasters.deluxepm.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class CommandPreProcessEvent implements Listener {

    List<String> commands = DeluxePM.getI().getConfig().getStringList("pm-commands");

    @EventHandler
    public void processEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        FileConfiguration config = DeluxePM.getI().getConfig();
        if (config.getBoolean("hooks.deluxechat")) {
            if (args[0].equalsIgnoreCase("/r")) {
                if (args.length < 0) {
                    String targetPlayer = DeluxeChat.getPmRecipient(player.getName());
                    if (targetPlayer == null) {
                        return;
                    }
                    if (CommandToggle.togglePM.contains(targetPlayer)) {
                        if (player.hasPermission("deluxepm.admin")) {
                            event.setCancelled(false);
                            return;
                        }
                        event.setCancelled(true);
                        player.sendMessage(ColorUtil.color(config.getString("messages.user-disabled")));
                    }
                }
            }
        }
        if (args.length > 1) {
            Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
            if (targetPlayer == null) {
                return;
            }
            if ((commands.contains(args[0])) && CommandToggle.togglePM
                    .contains(targetPlayer.getName())) {
                if (player.hasPermission("deluxepm.admin")) {
                    event.setCancelled(false);
                    return;
                }
                event.setCancelled(true);
                player.sendMessage(ColorUtil
                        .color(config.getString("messages.user-disabled")));
            }
        }

    }

}

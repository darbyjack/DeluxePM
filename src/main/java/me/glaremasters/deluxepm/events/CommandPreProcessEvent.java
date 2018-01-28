package me.glaremasters.deluxepm.events;

import java.util.List;
import me.glaremasters.deluxepm.DeluxePM;
import me.glaremasters.deluxepm.commands.CommandToggle;
import me.glaremasters.deluxepm.util.ColorUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class CommandPreProcessEvent implements Listener {

    List<String> commands = DeluxePM.getI().getConfig().getStringList("pm-commands");

    @EventHandler
    public void processEvent(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String[] args = event.getMessage().split(" ");
        if (args.length > 1) {
            Player targetPlayer = Bukkit.getServer().getPlayer(args[1]);
            if ((commands.contains(args[0])) && CommandToggle.togglePM
                    .contains(targetPlayer.getName())) {
                event.setCancelled(true);
                player.sendMessage(ColorUtil
                        .color(DeluxePM.getI().getConfig().getString("messages.user-disabled")));
            }
        }
    }

}

package me.glaremasters.deluxepm.events;

import me.glaremasters.deluxepm.commands.CommandToggle;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class PlayerLeaveEvent implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if (CommandToggle.togglePM.contains(player.getName())) {
            CommandToggle.togglePM.remove(player.getName());
        }
    }

}

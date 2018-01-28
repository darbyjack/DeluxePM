package me.glaremasters.deluxepm.util;

import me.glaremasters.deluxepm.DeluxePM;
import org.bukkit.ChatColor;

/**
 * Created by GlareMasters on 1/28/2018.
 */
public class ColorUtil {

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', DeluxePM.getPrefix() + string);
    }

}

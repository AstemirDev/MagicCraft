package org.astemir.magiccraft.magic.time;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.entity.Player;

public class SpellEquinox extends MagicSpell {


    public SpellEquinox() {
        super(SpellsConfiguration.EQUINOX);
    }

    @Override
    public void onUse(Player player) {
        if (player.getWorld().isDayTime()) {
            int nightTime = SpellsConfiguration.EQUINOX.getInt("night_time");
            player.getWorld().setTime(nightTime);
            player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_time_0", 1, 1);
        }else{
            int dayTime = SpellsConfiguration.EQUINOX.getInt("day_time");
            player.getWorld().setTime(dayTime);
            player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_time_1", 1, 1);
        }
    }
}

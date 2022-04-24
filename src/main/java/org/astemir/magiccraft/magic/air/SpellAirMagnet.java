package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellAirMagnet extends MasterSpell {


    public SpellAirMagnet() {
        super(SpellsConfiguration.AIR_MAGNET);
    }

    @Override
    public void onUse(Player player) {
        int radius = SpellsConfiguration.AIR_MAGNET.get("radius",Integer.class);
        player.getNearbyEntities(radius,radius,radius).forEach((e)->{
            if (e instanceof org.bukkit.entity.Item){
                org.bukkit.entity.Item item = (org.bukkit.entity.Item)e;
                item.setVelocity(player.getLocation().clone().subtract(item.getLocation().clone()).toVector().normalize());
            }
        });
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_wind", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }


    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.WIND_SPELL;
    }
}


package org.astemir.magiccraft.magic.space;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellVacuumMagnet extends MagicSpell {


    public SpellVacuumMagnet() {
        super(SpellsConfiguration.VACUUM_MAGNET);
    }

    @Override
    public void onUse(Player player) {
        int radius = SpellsConfiguration.VACUUM_MAGNET.getInt(DefaultVars.RADIUS);
        player.getNearbyEntities(radius,radius,radius).forEach((e)->{
            if (e instanceof org.bukkit.entity.Item){
                org.bukkit.entity.Item item = (org.bukkit.entity.Item)e;
                player.getInventory().addItem(item.getItemStack());
                item.remove();
            }
        });
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_void", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

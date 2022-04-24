package org.astemir.magiccraft.magic.water;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityWaterProjectile;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellWater extends MagicSpell {


    public SpellWater() {
        super(SpellsConfiguration.WATER);
    }


    @Override
    public void onUse(Player player) {
        EntityWaterProjectile projectile = EntityMagic.create(EntityWaterProjectile.class,player);
        projectile.own(player);
        projectile.join();

        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_SPLASH, 1, 1);
    }
}

package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityWindProjectile;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellWind extends MagicSpell {


    public SpellWind() {
        super(SpellsConfiguration.WIND);
    }


    @Override
    public void onUse(Player player) {
        EntityWindProjectile projectile = EntityMagic.create(EntityWindProjectile.class,player);
        projectile.launch(player,0,1f,1);
        projectile.join();
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_wind", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

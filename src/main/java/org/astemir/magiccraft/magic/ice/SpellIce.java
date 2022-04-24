package org.astemir.magiccraft.magic.ice;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityIceProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellIce extends MagicSpell {


    public SpellIce() {
        super(SpellsConfiguration.ICE);
    }


    @Override
    public void onUse(Player player) {
        EntityIceProjectile projectile = EntityMagic.create(EntityIceProjectile.class,player);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, 1);
        projectile.launch(player,0,1.25f,1f);
        projectile.own(player);
        projectile.join();
    }
}

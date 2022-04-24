package org.astemir.magiccraft.magic.earth;


import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityDirtProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellDirt extends MagicSpell {


    public SpellDirt() {
        super(SpellsConfiguration.DIRT);
    }


    @Override
    public void onUse(Player player) {
        EntityDirtProjectile projectile = EntityMagic.create(EntityDirtProjectile.class,player);
        projectile.launch(player,0,1.25f,1);
        projectile.join();
        player.playSound(player.getLocation(), Sound.BLOCK_GRAVEL_BREAK, 1, 0.8f);
    }
}

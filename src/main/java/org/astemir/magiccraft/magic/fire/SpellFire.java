package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityFireProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellFire extends MagicSpell {


    public SpellFire() {
        super(SpellsConfiguration.FIRE);
    }


    @Override
    public void onUse(Player player) {
        EntityFireProjectile projectile = EntityMagic.create(EntityFireProjectile.class,player);
        projectile.launch(player,0,1f,1);
        projectile.join();
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
    }
}

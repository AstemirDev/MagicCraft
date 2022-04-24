package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityArmagedonProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellArmagedon extends MasterSpell {


    public SpellArmagedon() {
        super(SpellsConfiguration.ARMAGEDON);
    }


    @Override
    public void onUse(Player player) {
        EntityArmagedonProjectile projectile = EntityMagic.create(EntityArmagedonProjectile.class,player);
        projectile.launch(player,0,1.5f,1);
        projectile.join();
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 0.5f);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 0.75f);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.FIRE_EXPLOSION_SPELL;
    }
}

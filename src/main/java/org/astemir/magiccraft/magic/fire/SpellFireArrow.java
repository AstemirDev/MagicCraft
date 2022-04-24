package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityFireArrowProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellFireArrow extends MasterSpell {


    public SpellFireArrow() {
        super(SpellsConfiguration.FIRE_ARROW);
    }


    @Override
    public void onUse(Player player) {
        EntityFireArrowProjectile projectile = EntityMagic.create(EntityFireArrowProjectile.class,player);
        projectile.launch(player,0,1.75f,1);
        projectile.join();
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.FIRE_SPELL;
    }
}

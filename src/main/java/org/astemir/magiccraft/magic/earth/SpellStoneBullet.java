package org.astemir.magiccraft.magic.earth;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityStoneBulletProjectile;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.entity.Player;

public class SpellStoneBullet extends MasterSpell {


    public SpellStoneBullet() {
        super(SpellsConfiguration.STONE_BULLET);
    }


    @Override
    public void onUse(Player player) {
        for (int i = 0;i<3;i++) {
            EntityStoneBulletProjectile projectile = EntityMagic.create(EntityStoneBulletProjectile.class, player);
            projectile.own(player);
            projectile.setTicksToLaunch(i*5);
            projectile.join();
        }
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.DIRT_SPELL;
    }
}

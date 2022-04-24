package org.astemir.magiccraft.magic.earth;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityBoulder;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityStoneBulletProjectile;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.entity.Player;

public class SpellBoulder extends MasterSpell {


    public SpellBoulder() {
        super(SpellsConfiguration.BOULDER);
    }


    @Override
    public void onUse(Player player) {
        EntityBoulder projectile = EntityMagic.create(EntityBoulder.class, player);
        projectile.launch(player,0,0.5f,1);
        projectile.join();
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.DIRT_SPELL;
    }
}

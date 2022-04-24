package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityWindBullet;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellWindBullet extends MasterSpell {


    public SpellWindBullet() {
        super(SpellsConfiguration.WIND_BULLET);
    }


    @Override
    public void onUse(Player player) {
        EntityWindBullet projectile = EntityMagic.create(EntityWindBullet.class,player);
        projectile.launch(player,0,2f,1);
        projectile.join();
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_wind", 0.25f, RandomUtils.randomFloat(1.9f,2f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.WIND_SPELL;
    }
}

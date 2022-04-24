package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityTornado;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellTornado extends MasterSpell {


    public SpellTornado() {
        super(SpellsConfiguration.TORNADO);
    }


    @Override
    public void onUse(Player player) {
        EntityTornado projectile = EntityMagic.create(EntityTornado.class,player);
        projectile.join();
        projectile.launch(player,0,0.5f,1);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_wind", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.WIND_SPELL;
    }
}

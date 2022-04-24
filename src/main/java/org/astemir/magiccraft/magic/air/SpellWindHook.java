package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.entity.EntityWindHookProjectile;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellWindHook extends MasterSpell {


    public SpellWindHook() {
        super(SpellsConfiguration.WIND_HOOK);
    }


    @Override
    public void onUse(Player player) {
        EntityWindHookProjectile projectile = EntityMagic.create(EntityWindHookProjectile.class,player);
        projectile.launch(player,0,1f,1);
        projectile.own(player);
        projectile.join();
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_wind", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.AIR_JUMP_SPELL;
    }
}

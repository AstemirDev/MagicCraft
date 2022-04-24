package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityAirJumpProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellAirJump extends MasterSpell {


    public SpellAirJump() {
        super(SpellsConfiguration.AIR_JUMP);
    }


    @Override
    public void onUse(Player player) {
        player.setFallDistance(0);
        EntityAirJumpProjectile projectile = EntityMagic.create(EntityAirJumpProjectile.class,player);
        projectile.launch(player,0,1f,1);
        projectile.join();
        player.setVelocity(player.getLocation().getDirection().multiply(-1f));
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1.5f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1.25f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 0.5f);
    }


    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.WIND_SPELL;
    }
}

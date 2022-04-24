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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellAirSlide extends MasterSpell {


    public SpellAirSlide() {
        super(SpellsConfiguration.AIR_SLIDE);
    }


    @Override
    public void onUse(Player player) {
        player.setFallDistance(0);
        EntityAirJumpProjectile projectile = EntityMagic.create(EntityAirJumpProjectile.class,player);
        projectile.launch(player,0,1f,1);
        projectile.join();
        player.setVelocity(player.getLocation().getDirection().multiply(1.5));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING,SpellsConfiguration.AIR_SLIDE.effect("slow_falling").duration(),SpellsConfiguration.AIR_SLIDE.effect("slow_falling").amplifier(),false,false));
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1.5f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1.25f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 1f);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_SAND_PLACE, 1, 0.5f);
    }


    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.AIR_JUMP_SPELL;
    }
}

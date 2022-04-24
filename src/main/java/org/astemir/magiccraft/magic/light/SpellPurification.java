package org.astemir.magiccraft.magic.light;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.magic.fire.SpellCook;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.PotionUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellPurification extends MasterSpell {


    public SpellPurification() {
        super(SpellsConfiguration.PURIFICATION);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(SpellsConfiguration.PURIFICATION.effect("regeneration").toBukkit());
        player.addPotionEffect(SpellsConfiguration.PURIFICATION.effect("heal").toBukkit());
        MagicUtils.healParticles(player.getLocation().clone().add(0,0.5f,0),5,0.5f);
        (player).getActivePotionEffects().forEach((potion)->{
            if (PotionUtils.isHarmful(potion.getType())){
                player.removePotionEffect(potion.getType());
            }
        });
        player.getWorld().spawnParticle(Particle.FLASH,player.getLocation().clone().add(0,1,0),0,0,0,0,0);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_light", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.LIGHT_HEAL_SPELL;
    }

}

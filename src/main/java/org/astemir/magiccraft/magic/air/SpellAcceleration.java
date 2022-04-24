package org.astemir.magiccraft.magic.air;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SpellAcceleration extends MasterSpell {


    public SpellAcceleration() {
        super(SpellsConfiguration.ACCELERATION);
    }


    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,SpellsConfiguration.ACCELERATION.effect("speed").duration(),SpellsConfiguration.ACCELERATION.effect("speed").amplifier(),false,false));
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_wind", 1, RandomUtils.randomFloat(1.2f,1.3f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.SWIFTNESS_SPELL;
    }
}

package org.astemir.magiccraft.magic.dark;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellCapeOfDarkness extends MagicSpell {


    public SpellCapeOfDarkness() {
        super(SpellsConfiguration.CAPE_OF_DARKNESS);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,SpellsConfiguration.CAPE_OF_DARKNESS.effect("invisibility").duration(),SpellsConfiguration.CAPE_OF_DARKNESS.effect("invisibility").amplifier(),false,false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,SpellsConfiguration.CAPE_OF_DARKNESS.effect("speed").duration(),SpellsConfiguration.CAPE_OF_DARKNESS.effect("speed").amplifier(),false,false));
        MagicUtils.darknessParticles(player.getLocation().clone().add(0,0.5f,0),20,1.5f);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_1", 1, RandomUtils.randomFloat(0.7f,0.8f));
    }


}

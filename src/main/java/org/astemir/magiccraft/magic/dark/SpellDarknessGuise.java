package org.astemir.magiccraft.magic.dark;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellDarknessGuise extends MasterSpell {


    public SpellDarknessGuise() {
        super(SpellsConfiguration.DARKNESS_GUISE);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY,SpellsConfiguration.DARKNESS_GUISE.effect("invisibility").duration(),SpellsConfiguration.DARKNESS_GUISE.effect("invisibility").amplifier(),false,false));
        player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,SpellsConfiguration.DARKNESS_GUISE.effect("speed").duration(),SpellsConfiguration.DARKNESS_GUISE.effect("speed").amplifier(),false,false));
        MagicUtils.darknessParticles(player.getLocation().clone().add(0.1,0.5f,0.1),20,1.5f);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_0", 1, RandomUtils.randomFloat(1.2f,1.3f));
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_1", 1, RandomUtils.randomFloat(0.7f,0.8f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.CAPE_OF_DARKNESS_SPELL;
    }
}

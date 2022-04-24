package org.astemir.magiccraft.magic.water;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.PotionUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellGills extends MagicSpell {


    public SpellGills() {
        super(SpellsConfiguration.GILLS);
    }


    @Override
    public void onUse(Player player) {
        player.setRemainingAir(player.getMaximumAir());
        player.addPotionEffect(SpellsConfiguration.GILLS.effect("water_breath").toBukkit());
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_DOLPHIN_SPLASH, 1, 2);
    }
}

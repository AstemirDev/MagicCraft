package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellFireShield extends MasterSpell {


    public SpellFireShield() {
        super(SpellsConfiguration.FIRE_SHIELD);
    }


    @Override
    public void onUse(Player player) {
        player.addPotionEffect(SpellsConfiguration.FIRE_SHIELD.effect("resistance").toBukkit());
        player.addPotionEffect(SpellsConfiguration.FIRE_SHIELD.effect("fire_resistance").toBukkit());
        player.setFireTicks(SpellsConfiguration.FIRE_SHIELD.getInt("fire_ticks"));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.FIRE_SPELL;
    }
}

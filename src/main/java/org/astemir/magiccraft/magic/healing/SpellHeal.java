package org.astemir.magiccraft.magic.healing;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellHeal extends MagicSpell {


    public SpellHeal() {
        super(SpellsConfiguration.HEAL);
    }

    @Override
    public void onUse(Player player) {
        player.addPotionEffect(SpellsConfiguration.HEAL.effect("regeneration").toBukkit());
        MagicUtils.healParticles(player.getLocation().clone().add(0,0.5f,0),5,1f);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_heal", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

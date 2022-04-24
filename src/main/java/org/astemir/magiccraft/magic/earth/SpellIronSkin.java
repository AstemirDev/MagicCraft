package org.astemir.magiccraft.magic.earth;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SpellIronSkin extends MasterSpell {


    public SpellIronSkin() {
        super(SpellsConfiguration.IRON_SKIN);
    }


    @Override
    public void onUse(Player player) {
        player.addPotionEffect(SpellsConfiguration.IRON_SKIN.effect("resistance").toBukkit());
        player.addPotionEffect(SpellsConfiguration.IRON_SKIN.effect("slowness").toBukkit());
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_ATTACK_IRON_DOOR, 1, RandomUtils.randomFloat(1.5f,1.6f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.DIRT_SPELL;
    }
}

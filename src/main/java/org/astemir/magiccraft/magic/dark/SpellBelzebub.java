package org.astemir.magiccraft.magic.dark;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.BelzebubBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellBelzebub extends MagicSpell {

    public SpellBelzebub() {
        super(SpellsConfiguration.BELZEBUB);
    }

    @Override
    public void onUse(Player player) {
        BelzebubBeam beam = new BelzebubBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_0", 1, RandomUtils.randomFloat(0.5f,1.6f));
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_0", 1, RandomUtils.randomFloat(0.9f,1.1f));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 0.5f);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_HORSE_DEATH, 1, 2);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 2);
    }
}

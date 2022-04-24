package org.astemir.magiccraft.magic.space;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.BlackHoleBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellBlackHole extends MagicSpell {


    public SpellBlackHole() {
        super(SpellsConfiguration.BLACK_HOLE);
    }


    @Override
    public void onUse(Player player) {
        BlackHoleBeam beam = new BlackHoleBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_void", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

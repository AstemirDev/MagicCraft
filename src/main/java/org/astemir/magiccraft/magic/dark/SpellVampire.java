package org.astemir.magiccraft.magic.dark;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.VampiricBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellVampire extends MagicSpell {


    public SpellVampire() {
        super(SpellsConfiguration.VAMPIRISM);
    }


    @Override
    public void onUse(Player player) {
        VampiricBeam beam = new VampiricBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_0", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

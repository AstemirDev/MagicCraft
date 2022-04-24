package org.astemir.magiccraft.magic.lightning;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.ChargeBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellCharge extends MagicSpell {


    public SpellCharge() {
        super(SpellsConfiguration.CHARGE);
    }


    @Override
    public void onUse(Player player) {
        ChargeBeam beam = new ChargeBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_lightning", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

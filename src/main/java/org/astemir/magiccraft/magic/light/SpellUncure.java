package org.astemir.magiccraft.magic.light;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.UncureBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellUncure extends MasterSpell {


    public SpellUncure() {
        super(SpellsConfiguration.UNCURE);
    }


    @Override
    public void onUse(Player player) {
        UncureBeam beam = new UncureBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_heal", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.LIGHT_HEAL_SPELL;
    }

}

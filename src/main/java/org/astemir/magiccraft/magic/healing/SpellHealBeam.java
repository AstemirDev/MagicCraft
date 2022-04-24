package org.astemir.magiccraft.magic.healing;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.HealBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellHealBeam extends MasterSpell {


    public SpellHealBeam() {
        super(SpellsConfiguration.HEAL_BEAM);
    }


    @Override
    public void onUse(Player player) {
        HealBeam beam = new HealBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_heal", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.HEAL_SPELL;
    }
}

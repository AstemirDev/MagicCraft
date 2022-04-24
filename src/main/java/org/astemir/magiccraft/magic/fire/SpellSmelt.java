package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.SmeltBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellSmelt extends MasterSpell {


    public SpellSmelt() {
        super(SpellsConfiguration.SMELT);
    }


    @Override
    public void onUse(Player player) {
        SmeltBeam beam = new SmeltBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ZOMBIE_INFECT, 1, 1);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.COOK_SPELL;
    }
}

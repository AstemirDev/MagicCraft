package org.astemir.magiccraft.magic.ice;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.FreezeBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellFreezeBeam extends MasterSpell {


    public SpellFreezeBeam() {
        super(SpellsConfiguration.FREEZE_BEAM);
    }


    @Override
    public void onUse(Player player) {
        FreezeBeam beam = new FreezeBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ILLUSIONER_CAST_SPELL, 1, 1);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.ICE_SPELL;
    }
}

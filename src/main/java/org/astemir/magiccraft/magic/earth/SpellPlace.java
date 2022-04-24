package org.astemir.magiccraft.magic.earth;


import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.PlaceBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.entity.Player;

public class SpellPlace extends MasterSpell {


    public SpellPlace() {
        super(SpellsConfiguration.PLACE);
    }


    @Override
    public void onUse(Player player) {
        PlaceBeam beam = new PlaceBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.DIRT_SPELL;
    }
}

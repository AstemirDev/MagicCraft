package org.astemir.magiccraft.magic.earth;


import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.DigBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.IHasCooldownException;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.entity.Player;

public class SpellDig extends MasterSpell implements IHasCooldownException {


    public SpellDig() {
        super(SpellsConfiguration.DIG);
    }


    @Override
    public void onUse(Player player) {
        if (!DigBeam.hasPlayer(player)) {
            DigBeam beam = new DigBeam(player);
            MagicCraft.beamHandler.addBeam(beam);
        }else{
            DigBeam.digBeams.forEach((beam,uuid)->{
                if (uuid.equals(player.getUniqueId())){
                    beam.disable();
                }
            });
        }
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.DIRT_SPELL;
    }

    @Override
    public boolean uniqueCondition(Player player) {
        return !DigBeam.hasPlayer(player);
    }

}

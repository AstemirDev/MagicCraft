package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.FireBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellFireBeam extends MasterSpell {


    public SpellFireBeam() {
        super(SpellsConfiguration.FIRE_BEAM);
    }


    @Override
    public void onUse(Player player) {
        FireBeam beam = new FireBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.FIRE_SPELL;
    }
}

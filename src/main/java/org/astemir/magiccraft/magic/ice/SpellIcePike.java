package org.astemir.magiccraft.magic.ice;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.IcePikeBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellIcePike extends MasterSpell {


    public SpellIcePike() {
        super(SpellsConfiguration.ICE_PIKE);
    }


    @Override
    public void onUse(Player player) {
        IcePikeBeam beam = new IcePikeBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_EVOKER_PREPARE_ATTACK, 1, RandomUtils.randomFloat(1.5f,1.6f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.ICE_SPELL;
    }
}

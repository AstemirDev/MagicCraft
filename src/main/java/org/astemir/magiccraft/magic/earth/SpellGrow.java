package org.astemir.magiccraft.magic.earth;


import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.GrowBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellGrow extends MasterSpell {


    public SpellGrow() {
        super(SpellsConfiguration.GROW);
    }


    @Override
    public void onUse(Player player) {
        GrowBeam beam = new GrowBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE,3, RandomUtils.randomFloat(1.5f,1.6f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.DIRT_SPELL;
    }
}

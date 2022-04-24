package org.astemir.magiccraft.magic.lightning;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellLightning extends MasterSpell {


    public SpellLightning() {
        super(SpellsConfiguration.LIGHTNING);
    }


    @Override
    public void onUse(Player player) {
        int distance = SpellsConfiguration.LIGHTNING.getInt("distance");
        player.getWorld().strikeLightning(player.getTargetBlock(distance).getLocation());
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_lightning", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.CHARGE_SPELL;
    }
}

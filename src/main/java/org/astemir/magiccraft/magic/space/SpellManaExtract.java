package org.astemir.magiccraft.magic.space;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.item.Items;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellManaExtract extends MagicSpell {


    public SpellManaExtract() {
        super(SpellsConfiguration.MANA_EXTRACT);
    }

    @Override
    public void onUse(Player player) {
        player.getWorld().dropItem(player.getLocation(), Items.EXTRACTED_MANA.toItemStack());
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_void", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

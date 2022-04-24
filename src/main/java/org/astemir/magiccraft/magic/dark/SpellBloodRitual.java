package org.astemir.magiccraft.magic.dark;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.IHasManaUniqueCondition;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellBloodRitual extends MagicSpell implements IHasManaUniqueCondition {


    public SpellBloodRitual() {
        super(SpellsConfiguration.BLOOD_RITUAL);
    }


    @Override
    public void onUse(Player player) {
        if (player.getNoDamageTicks() == 0) {
            PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
            data.addMana(SpellsConfiguration.BLOOD_RITUAL.get("mana_regeneration",Integer.class));
            player.damage(SpellsConfiguration.BLOOD_RITUAL.get("damage",Integer.class));
            player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_dark_1", 1, RandomUtils.randomFloat(0.9f,1.1f));
        }
    }

    @Override
    public boolean manaUniqueCondition(Player player) {
        return player.getNoDamageTicks() == 0;
    }
}

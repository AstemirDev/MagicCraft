package org.astemir.magiccraft.magic;


import org.astemir.magiccraft.config.ConfigurableSpell;
import org.astemir.magiccraft.item.Item;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.entity.Player;


public abstract class QuirkSpell extends MagicSpell implements ISecretSpell{

    public QuirkSpell(ConfigurableSpell configurable) {
        super(configurable);
    }


    @Override
    public abstract void onUse(Player player);

    @Override
    public boolean showing(Item item, Player player, PlayerData data) {
        if (data.hasLearnedSpell(this)) {
            return true;
        }
        return false;
    }
}

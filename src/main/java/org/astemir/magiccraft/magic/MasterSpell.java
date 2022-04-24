package org.astemir.magiccraft.magic;

import net.kyori.adventure.text.Component;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.ConfigurableSpell;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.entity.Player;

//Used for spells, that required base spells to learn

public abstract class MasterSpell extends MagicSpell implements IHasUniqueCondition{

    public MasterSpell(ConfigurableSpell configurable) {
        super(configurable);
    }

    @Override
    public boolean uniqueCondition(Player player) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        return data.hasLearnedSpell(getRequiredSpell());
    }

    @Override
    public void conditionIsNotSatisfied(Player player) {
        player.sendMessage(Component.translatable("msg.magiccraft.spell_not_learned").append(Component.translatable("spell.magiccraft."+getRequiredSpell().getRegisterKey())));
    }

    @Override
    public abstract void onUse(Player player);

    public abstract MagicSpell getRequiredSpell();
}

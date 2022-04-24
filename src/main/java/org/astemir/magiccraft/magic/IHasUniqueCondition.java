package org.astemir.magiccraft.magic;

import org.bukkit.entity.Player;

public interface IHasUniqueCondition{

    public boolean uniqueCondition(Player player);

    public void conditionIsNotSatisfied(Player player);
}

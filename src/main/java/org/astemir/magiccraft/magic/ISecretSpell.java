package org.astemir.magiccraft.magic;

import org.astemir.magiccraft.item.Item;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.entity.Player;

public interface ISecretSpell {

    boolean showing(Item item, Player player, PlayerData data);
}

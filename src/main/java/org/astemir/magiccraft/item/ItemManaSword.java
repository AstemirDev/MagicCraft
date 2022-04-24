package org.astemir.magiccraft.item;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;


public class ItemManaSword extends ItemCustomSword{


    public ItemManaSword(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }


    @Override
    public boolean onRightClick(PlayerClickEvent e) {
        return false;
    }

    @Override
    public boolean onLeftClick(PlayerClickEvent e) {
        return false;
    }

    @Override
    public boolean onAttackEntity(EntityDamageByEntityEvent e) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData((Player)e.getDamager());
        data.addMana(1);
        return super.onAttackEntity(e);
    }

    @Override
    public double getDamage(){
        return 3;
    }

    @Override
    public double getAttackSpeed(){
        return -2.4;
    }
}

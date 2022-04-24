package org.astemir.magiccraft.item;

import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ItemMageKiller extends ItemCustomSword{


    public ItemMageKiller(ItemModel texture, String nameKey) {
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
        return super.onAttackEntity(e);
    }

    @Override
    public double getDamage(){
        return 7;
    }

    @Override
    public double getAttackSpeed(){
        return -2.4;
    }
}

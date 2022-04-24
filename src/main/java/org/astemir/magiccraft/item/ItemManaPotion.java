package org.astemir.magiccraft.item;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.event.PlayerClickEvent;
import org.astemir.magiccraft.graphics.ItemModel;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.Color;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;

public class ItemManaPotion extends Item{


    public ItemManaPotion(ItemModel texture, String nameKey) {
        super(texture, nameKey);
    }

    @Override
    public boolean onConsume(PlayerItemConsumeEvent e) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(e.getPlayer());
        data.addMana(5);
        return false;
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
    public ItemStack toItemStack() {
        ItemStack itemStack = super.toItemStack();
        itemStack.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
        PotionMeta meta = (PotionMeta) itemStack.getItemMeta();
        meta.setColor(Color.WHITE);
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}

package org.astemir.magiccraft.utils;

import org.astemir.magiccraft.magic.ElementType;
import org.bukkit.Location;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;

public class PlayerUtils {

    public static Location getPlayerEyeLocation(Player player){
        return player.getEyeLocation().clone().add(0,- 0.10000000149011612D,0);
    }

    public static void dropItem(Player player,ItemStack itemStack){
        Item item = (Item)player.getWorld().spawnEntity(player.getLocation().clone().add(0,0.5f,0), EntityType.DROPPED_ITEM);
        item.setItemStack(itemStack);
    }

    public static ItemStack[] getHotbarItems(Player player){
        ItemStack[] res = new ItemStack[9];
        for (int i = 0;i<res.length;i++){
            ItemStack itemStack = player.getInventory().getItem(i);
            if (itemStack == null){
                res[i] = null;
            }else
            if (itemStack.getType().isAir()){
                res[i] = null;
            }else {
                res[i] = itemStack;
            }
        }
        return res;
    }

    public static float calculateMagicDamage(Player player, LivingEntity target, ElementType damageType, float damage){
        return damage;
    }
}

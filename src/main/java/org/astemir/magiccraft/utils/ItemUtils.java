package org.astemir.magiccraft.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.Material.*;

public class ItemUtils {

    public static boolean isEmpty(ItemStack itemStack){
        if (itemStack == null) {
            return true;
        }else{
            return itemStack.getType().isAir();
        }
    }


    public static boolean isDye(Material material){
        switch (material){
            case YELLOW_DYE:
            case RED_DYE:
            case PINK_DYE:
            case MAGENTA_DYE:
            case LIME_DYE:
            case LIGHT_GRAY_DYE:
            case LIGHT_BLUE_DYE:
            case GREEN_DYE:
            case GRAY_DYE:
            case CYAN_DYE:
            case BROWN_DYE:
            case BLUE_DYE:
            case BLACK_DYE:
            case PURPLE_DYE:
            case WHITE_DYE:
            case ORANGE_DYE:
                return true;
        }
        return false;
    }


    public static Material cookFood(Material material){
        switch (material){
            case PORKCHOP: return COOKED_PORKCHOP;
            case BEEF: return COOKED_BEEF;
            case RABBIT: return COOKED_RABBIT;
            case CHICKEN: return COOKED_CHICKEN;
            case MUTTON: return COOKED_MUTTON;
            case SALMON: return COOKED_SALMON;
            case COD: return COOKED_COD;
            case KELP: return DRIED_KELP;
            case POTATO: return BAKED_POTATO;
        }
        return material;
    }

    public static Material blastItem(Material material){
        switch (material){
            case COBBLESTONE: return STONE;
            case COAL_ORE: return COAL;
            case ACACIA_LOG:
            case DARK_OAK_LOG:
            case JUNGLE_LOG:
            case OAK_LOG:
            case BIRCH_LOG:
            case SPRUCE_LOG:
                return CHARCOAL;
            case IRON_ORE: return IRON_INGOT;
            case GOLD_ORE:
            case NETHER_GOLD_ORE:
                return GOLD_INGOT;
            case LAPIS_ORE: return LAPIS_LAZULI;
            case EMERALD_ORE: return EMERALD;
            case REDSTONE_ORE: return REDSTONE;
            case SAND: return GLASS;
            case STONE: return SMOOTH_STONE;
            case NETHERRACK: return NETHER_BRICK;
            case NETHER_QUARTZ_ORE: return QUARTZ;
            case DIAMOND_ORE: return DIAMOND;
        }
        return material;
    }
}

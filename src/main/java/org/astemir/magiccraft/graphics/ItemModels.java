package org.astemir.magiccraft.graphics;

import org.bukkit.Material;

public class ItemModels {

    public static final ItemModel MAGIC_STICK_WOODEN = new ItemModel(Material.STICK,1);
    public static final ItemModel MAGIC_STICK_IRON = new ItemModel(Material.STICK,2);
    public static final ItemModel MAGIC_STICK_GOLDEN = new ItemModel(Material.STICK,3);
    public static final ItemModel MAGIC_STICK_ENDER = new ItemModel(Material.STICK,4);
    public static final ItemModel MAGIC_STICK_DISCORD = new ItemModel(Material.STICK,5);

    public static final ItemModel MAGIC_STAFF_WOODEN = new ItemModel(Material.STICK,6);
    public static final ItemModel MAGIC_STAFF_IRON = new ItemModel(Material.STICK,7);
    public static final ItemModel MAGIC_STAFF_GOLDEN = new ItemModel(Material.STICK,8);
    public static final ItemModel MAGIC_STAFF_ENDER = new ItemModel(Material.STICK,9);
    public static final ItemModel MAGIC_STAFF_DISCORD = new ItemModel(Material.STICK,10);


    public static final ItemModel MANA_SWORD = new ItemModel(Material.DIAMOND_SWORD,2);
    public static final ItemModel MAGE_KILLER = new ItemModel(Material.DIAMOND_SWORD,1);

    public static final ItemModel EXTRACTED_MANA = new ItemModel(Material.IRON_NUGGET,1);
    public static final ItemModel MANA_CRYSTAL = new ItemModel(Material.IRON_NUGGET,2);
    public static final ItemModel ANTIMAGIUM = new ItemModel(Material.IRON_NUGGET,3);

    public static final ItemModel ANTIMAGIC_APPLE = new ItemModel(Material.APPLE,1);


    public static final ItemModel MANA_INGOT = new ItemModel(Material.IRON_INGOT,1);
    public static final ItemModel ANTIMAGIUM_INGOT = new ItemModel(Material.IRON_INGOT,2);

    public static final ItemModel ICE_PIKE = new ItemModel(Material.IRON_NUGGET,4);
    public static final ItemModel BOULDER = new ItemModel(Material.COBBLESTONE,0);
    public static final ItemModel BLACK_HOLE = new ItemModel(Material.IRON_NUGGET,5);


    public static final ItemModel MANA_POTION = new ItemModel(Material.POTION,1);
    public static final ItemModel MAGIC_POTION = new ItemModel(Material.POTION,2);

    public static final ItemModel SPELL_BOOK = new ItemModel(Material.BOOK,1);
    public static final ItemModel SPELL_BOOK_ANIMATED = new ItemModel(Material.LEATHER_HORSE_ARMOR,2);


    public static final ItemModel SPELL_BOOK_PAGE_EMPTY = new ItemModel(Material.PAPER,1);
    public static final ItemModel SPELL_BOOK_PAGE_AIR = new ItemModel(Material.PAPER,2);
    public static final ItemModel SPELL_BOOK_PAGE_DARKNESS = new ItemModel(Material.PAPER,3);
    public static final ItemModel SPELL_BOOK_PAGE_ENDER = new ItemModel(Material.PAPER,4);
    public static final ItemModel SPELL_BOOK_PAGE_FIRE = new ItemModel(Material.PAPER,5);
    public static final ItemModel SPELL_BOOK_PAGE_HEAL = new ItemModel(Material.PAPER,6);
    public static final ItemModel SPELL_BOOK_PAGE_ICE = new ItemModel(Material.PAPER,7);
    public static final ItemModel SPELL_BOOK_PAGE_LIGHT = new ItemModel(Material.PAPER,8);
    public static final ItemModel SPELL_BOOK_PAGE_LIGHTNING = new ItemModel(Material.PAPER,9);
    public static final ItemModel SPELL_BOOK_PAGE_NATURE = new ItemModel(Material.PAPER,10);
    public static final ItemModel SPELL_BOOK_PAGE_PLAGUE = new ItemModel(Material.PAPER,11);
    public static final ItemModel SPELL_BOOK_PAGE_SOULFLAME = new ItemModel(Material.PAPER,12);
    public static final ItemModel SPELL_BOOK_PAGE_TIME = new ItemModel(Material.PAPER,13);
    public static final ItemModel SPELL_BOOK_PAGE_VOID = new ItemModel(Material.PAPER,14);
    public static final ItemModel SPELL_BOOK_PAGE_WATER = new ItemModel(Material.PAPER,15);
    public static final ItemModel SPELL_BOOK_PAGE_WITHER = new ItemModel(Material.PAPER,16);
    public static final ItemModel SPELL_BOOK_PAGE_DIVINE = new ItemModel(Material.PAPER,17);



    public static ItemModel textureOfSpellSymbol(String symbol){
        switch (symbol){
            case UnicodeSymbols.DIVINE_SPELL: return SPELL_BOOK_PAGE_DIVINE;
            case UnicodeSymbols.NULL_SPELL: return SPELL_BOOK_PAGE_EMPTY;
            case UnicodeSymbols.AIR_SPELL: return SPELL_BOOK_PAGE_AIR;
            case UnicodeSymbols.DARKNESS_SPELL: return SPELL_BOOK_PAGE_DARKNESS;
            case UnicodeSymbols.ENDER_SPELL: return SPELL_BOOK_PAGE_ENDER;
            case UnicodeSymbols.FIRE_SPELL: return SPELL_BOOK_PAGE_FIRE;
            case UnicodeSymbols.HEAL_SPELL: return SPELL_BOOK_PAGE_HEAL;
            case UnicodeSymbols.ICE_SPELL: return SPELL_BOOK_PAGE_ICE;
            case UnicodeSymbols.LIGHT_SPELL: return SPELL_BOOK_PAGE_LIGHT;
            case UnicodeSymbols.LIGHTNING_SPELL: return SPELL_BOOK_PAGE_LIGHTNING;
            case UnicodeSymbols.EARTH_SPELL: return SPELL_BOOK_PAGE_NATURE;
            case UnicodeSymbols.PLAGUE_SPELL: return SPELL_BOOK_PAGE_PLAGUE;
            case UnicodeSymbols.SOULFLAME_SPELL: return SPELL_BOOK_PAGE_SOULFLAME;
            case UnicodeSymbols.TIME_SPELL: return SPELL_BOOK_PAGE_TIME;
            case UnicodeSymbols.VOID_SPELL: return SPELL_BOOK_PAGE_VOID;
            case UnicodeSymbols.WATER_SPELL: return SPELL_BOOK_PAGE_WATER;
            case UnicodeSymbols.WITHER_SPELL: return SPELL_BOOK_PAGE_WITHER;
        }
        return SPELL_BOOK_PAGE_EMPTY;
    }

}

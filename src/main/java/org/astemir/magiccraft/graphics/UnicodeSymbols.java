package org.astemir.magiccraft.graphics;

import net.kyori.adventure.text.format.TextColor;

public class UnicodeSymbols {

    public final static String MANA_OFFSET = "\uF801";
    public final static String MANA_UI = "\uF803";
    public final static String COOLDOWN_UI = "\uF903";
    public final static String MANA_EMPTY = "\uF810";
    public final static String MANA_FULL = "\uF811";
    public final static String MANA_EMPTY_UP = "\uF812";
    public final static String MANA_FULL_UP = "\uF813";

    public final static String NULL_SPELL = "\uF814";
    public final static String AIR_SPELL = "\uF820";
    public final static String DARKNESS_SPELL = "\uF824";
    public final static String ENDER_SPELL = "\uF819";
    public final static String FIRE_SPELL = "\uF815";
    public final static String HEAL_SPELL = "\uF818";
    public final static String ICE_SPELL = "\uF81A";
    public final static String LIGHT_SPELL = "\uF822";
    public final static String LIGHTNING_SPELL = "\uF81C";
    public final static String EARTH_SPELL = "\uF817";
    public final static String PLAGUE_SPELL = "\uF825";
    public final static String SOULFLAME_SPELL = "\uF826";
    public final static String TIME_SPELL = "\uF81B";
    public final static String VOID_SPELL = "\uF821";
    public final static String WATER_SPELL = "\uF816";
    public final static String WITHER_SPELL = "\uF823";
    public final static String DIVINE_SPELL = "\uF827";


    public final static String BOOK_SELECT_BUTTON = "\uF802";
    public final static String BOOK_RESET_BUTTON = "\uF804";
    public final static String BOOK_UNLOCK_BUTTON = "\uF805";
    public final static String BOOK_HOTKEY_BUTTON = "\uF850";
    public final static String BOOK_HOTKEY_BUTTON_DISABLED = "\uF851";

    public final static String MAGIC_BAR_EMPTY = "\uF806";
    public final static String MAGIC_BAR_FULL = "\uF807";
    public final static String MAGIC_BAR_NSPACE = "\uF808";


    public static TextColor colorOfSpellSymbol(String symbol){
        switch (symbol){
            case UnicodeSymbols.DIVINE_SPELL: return TextColor.color(255, 152, 0);
            case UnicodeSymbols.NULL_SPELL: return TextColor.color(0, 0, 0);
            case UnicodeSymbols.AIR_SPELL: return TextColor.color(255,255,255);
            case UnicodeSymbols.DARKNESS_SPELL: return TextColor.color(5, 5, 5);
            case UnicodeSymbols.ENDER_SPELL: return TextColor.color(255, 0, 247);
            case UnicodeSymbols.FIRE_SPELL: return TextColor.color(255, 0, 0);
            case UnicodeSymbols.HEAL_SPELL: return TextColor.color(255, 161, 161);
            case UnicodeSymbols.ICE_SPELL: return TextColor.color(0, 234, 255);
            case UnicodeSymbols.LIGHT_SPELL: return TextColor.color(255, 255, 79);
            case UnicodeSymbols.LIGHTNING_SPELL: return TextColor.color(123, 237, 197);
            case UnicodeSymbols.EARTH_SPELL: return TextColor.color(105, 57, 18);
            case UnicodeSymbols.PLAGUE_SPELL: return TextColor.color(83, 173, 107);
            case UnicodeSymbols.SOULFLAME_SPELL: return TextColor.color(46, 198, 240);
            case UnicodeSymbols.TIME_SPELL: return TextColor.color(128, 128, 128);
            case UnicodeSymbols.VOID_SPELL: return TextColor.color(48, 0, 82);
            case UnicodeSymbols.WATER_SPELL: return TextColor.color(0, 110, 255);
            case UnicodeSymbols.WITHER_SPELL: return TextColor.color(54, 54, 54);
        }
        return TextColor.color(0,0,0);
    }

}

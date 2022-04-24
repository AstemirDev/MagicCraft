package org.astemir.magiccraft.magic;


import org.astemir.magiccraft.graphics.UnicodeSymbols;

public class SpellType {

    public static final SpellType AIR = new SpellType(UnicodeSymbols.AIR_SPELL,ElementType.AIR);
    public static final SpellType DARK = new SpellType(UnicodeSymbols.DARKNESS_SPELL,ElementType.DARK);
    public static final SpellType EARTH = new SpellType(UnicodeSymbols.EARTH_SPELL,ElementType.EARTH);
    public static final SpellType FIRE = new SpellType(UnicodeSymbols.FIRE_SPELL,ElementType.FIRE);
    public static final SpellType DIVINE = new SpellType(UnicodeSymbols.DIVINE_SPELL,ElementType.GOD);
    public static final SpellType ICE = new SpellType(UnicodeSymbols.ICE_SPELL,ElementType.ICE);
    public static final SpellType LIGHT = new SpellType(UnicodeSymbols.LIGHT_SPELL,ElementType.LIGHT);
    public static final SpellType LIGHTNING = new SpellType(UnicodeSymbols.LIGHTNING_SPELL,ElementType.LIGHTNING);
    public static final SpellType WATER = new SpellType(UnicodeSymbols.WATER_SPELL,ElementType.WATER);
    public static final SpellType VOID = new SpellType(UnicodeSymbols.VOID_SPELL,ElementType.VOID);
    public static final SpellType TIME = new SpellType(UnicodeSymbols.TIME_SPELL,ElementType.TIME);
    public static final SpellType ENDER = new SpellType(UnicodeSymbols.ENDER_SPELL,ElementType.ENDER);
    public static final SpellType HEAL = new SpellType(UnicodeSymbols.HEAL_SPELL,ElementType.HEAL);
    public static final SpellType NULL = new SpellType(UnicodeSymbols.NULL_SPELL,ElementType.EMPTY);

    private String texture;
    private ElementType type;

    public SpellType(String texture, ElementType type) {
        this.texture = texture;
        this.type = type;
    }

    public String getTexture() {
        return texture;
    }

    public ElementType getType() {
        return type;
    }
}

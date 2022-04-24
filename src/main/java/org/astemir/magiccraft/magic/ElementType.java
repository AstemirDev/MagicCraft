package org.astemir.magiccraft.magic;

public enum ElementType {

    AIR,DARK,EARTH,FIRE,GOD,ICE,LIGHT,LIGHTNING,WATER,VOID,TIME,ENDER,HEAL,EMPTY;


    public static ElementType[] betterAgainst(ElementType damageType){
        switch (damageType){
            case AIR:
                return new ElementType[]{FIRE};
            case EARTH:
                return new ElementType[]{WATER};
            case FIRE:
                return new ElementType[]{ICE};
            case ICE:
                return new ElementType[]{AIR};
            case WATER:
                return new ElementType[]{FIRE,EARTH};
            case LIGHT:
                return new ElementType[]{DARK};
            case DARK:
                return new ElementType[]{LIGHT};
            case LIGHTNING:
                return new ElementType[]{WATER,ICE,AIR};
            case VOID:
                return new ElementType[]{LIGHTNING,LIGHT,DARK};
            case GOD:
                return new ElementType[]{AIR,DARK,EARTH,FIRE,GOD,ICE,LIGHT,LIGHTNING,WATER,VOID,TIME,ENDER,HEAL};
        }
        return new ElementType[]{};
    }

}

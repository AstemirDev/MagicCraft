package org.astemir.magiccraft.utils;

import org.astemir.magiccraft.config.ConfigurableSpell;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PotionUtils {

    public static PotionEffect load(ConfigurableSpell.PotionEffect configurable){
        return new PotionEffect(configurable.type(),configurable.duration(),configurable.amplifier(),false,false);
    }

    public static boolean isHarmful(PotionEffectType type){
        if (type.equals(PotionEffectType.WITHER) || type.equals(PotionEffectType.BLINDNESS) || type.equals(PotionEffectType.HUNGER) || type.equals(PotionEffectType.BAD_OMEN) || type.equals(PotionEffectType.SLOW) || type.equals(PotionEffectType.SLOW_DIGGING) || type.equals(PotionEffectType.WEAKNESS) || type.equals(PotionEffectType.HARM) || type.equals(PotionEffectType.POISON)){
            return true;
        }
        return false;
    }
}

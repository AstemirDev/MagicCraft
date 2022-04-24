package org.astemir.magiccraft.magic.light;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpellLightExplosion extends MagicSpell {


    public SpellLightExplosion() {
        super(SpellsConfiguration.LIGHT_EXPLOSION);
    }

    @Override
    public void onUse(Player player) {
        int radius = SpellsConfiguration.LIGHT_EXPLOSION.getInt("radius");
        player.getNearbyEntities(radius,radius,radius).forEach((e)->{
            if (e instanceof LivingEntity){
                if (!e.getUniqueId().equals(player.getUniqueId())){
                    MagicUtils.lightDamage(player,e,SpellsConfiguration.LIGHT_EXPLOSION.getInt("damage"));
                    ((LivingEntity)e).addPotionEffect(SpellsConfiguration.LIGHT_EXPLOSION.effect("blindness").toBukkit());
                }
            }
        });
        player.getWorld().spawnParticle(Particle.FLASH,player.getLocation().clone().add(0,1,0),0,0,0,0,0);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_light", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }
}

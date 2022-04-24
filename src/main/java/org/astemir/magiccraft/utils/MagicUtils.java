package org.astemir.magiccraft.utils;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.LivingEntity;

public class MagicUtils {

    public static void lightDamage(Entity attacker,Entity entity,double damage){
        if (entity instanceof LivingEntity){
            if (((LivingEntity)entity).getCategory().equals(EntityCategory.UNDEAD)){
                ((LivingEntity)entity).setFireTicks(120);
                damage *=2;
            }
            ((LivingEntity)entity).damage(damage,attacker);
        }
    }

    public static void healParticles(Location loc,int count,float size){
        for (int i = 0;i<count;i++){
            loc.getWorld().spawnParticle(Particle.HEART,loc.getX()+RandomUtils.randomFloat(-size,size),loc.getY()+RandomUtils.randomFloat(-size,size),loc.getZ()+RandomUtils.randomFloat(-size,size),0,0,0,0);
        }
    }

    public static void darknessParticles(Location loc,int count,float size){
        for (int i = 0;i<count;i++){
            loc.getWorld().spawnParticle(Particle.SQUID_INK,loc.getX()+RandomUtils.randomFloat(-size,size),loc.getY()+RandomUtils.randomFloat(-size,size),loc.getZ()+RandomUtils.randomFloat(-size,size),0,0,0,0);
        }
    }
}

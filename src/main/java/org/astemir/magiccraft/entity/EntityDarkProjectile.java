package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityDarkProjectile extends EntityMagic{

    public EntityDarkProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityDarkProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityDarkProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        hide();
    }

    @Override
    public void onTouchBlock(Block block) {

    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<5;i++) {
            double d0 = this.locX()+randomFloat(-0.25f,0.25f);
            double d1 = this.locY()+randomFloat(-0.25f,0.25f);
            double d2 = this.locZ()+randomFloat(-0.25f,0.25f);
            world.getWorld().spawnParticle(org.bukkit.Particle.SQUID_INK, d0, d1, d2, 0, 0,0,0);
        }
        for (int i = 0;i<5;i++) {
            double d0 = this.locX()+randomFloat(-0.25f,0.25f);
            double d1 = this.locY()+randomFloat(-0.25f,0.25f);
            double d2 = this.locZ()+randomFloat(-0.25f,0.25f);
            world.getWorld().spawnParticle(org.bukkit.Particle.SQUID_INK, d0, d1, d2, 0, 0,0,0);
        }
    }

    @Override
    public float getGravityScale() {
        return super.getGravityScale();
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        if (entity instanceof LivingEntity){
            ((LivingEntity) entity).addPotionEffect(SpellsConfiguration.DARK_SHOT.effect("blindness").toBukkit());
        }
        return super.onDamageEntity(entity);
    }



    @Override
    public float getDamage() {
        return SpellsConfiguration.DARK_SHOT.get("damage",Integer.class);
    }
}

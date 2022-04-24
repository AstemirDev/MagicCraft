package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;

public class EntityFireArrowProjectile extends EntityMagic{

    public EntityFireArrowProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityFireArrowProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityFireArrowProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        hide();
        setFireTicks(999999);
    }

    @Override
    public void onTouchBlock(Block block) {

    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<10;i++) {
            double d0 = this.locX()+randomFloat(-0.3f,0.3f);
            double d1 = this.locY()+randomFloat(-0.3f,0.3f);
            double d2 = this.locZ()+randomFloat(-0.3f,0.3f);
            world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, d0, d1, d2, 0, 0,0,0);
        }
        world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, locX(), locY(), locZ(), 1, 0,0,0);
    }

    public float getGravityScale(){
        return 0.01f;
    }
    @Override
    public boolean onDamageEntity(Entity entity) {
        entity.setFireTicks(SpellsConfiguration.FIRE_ARROW.getInt("ticks"));
        return super.onDamageEntity(entity);
    }


    @Override
    public void tick() {
        super.tick();
        if (isInWaterOrRain()){
            for (int i = 0;i<10;i++) {
                world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, locX()+randomFloat(-0.25f,0.25f), locY()+0.75f, locZ()+randomFloat(-0.25f,0.25f), 0, 0, 0, 0);
            }
            playSound(SoundEffects.BLOCK_FIRE_EXTINGUISH,1,1);
            die();
        }
    }


    @Override
    public float getDamage() {
        return SpellsConfiguration.FIRE_ARROW.getInt("damage");
    }
}

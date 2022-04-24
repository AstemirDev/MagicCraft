package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.util.Vector;

public class EntityArmagedonProjectile extends EntityMagic{

    public EntityArmagedonProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityArmagedonProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityArmagedonProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    private int explosionTicks = -1;

    @Override
    public void onCreate() {
        hide();
        setFireTicks(999999);
        setDestroyOnTouchBlock(false);
        setDestroyOnTouchEntity(false);
    }

    @Override
    public void onTouchBlock(Block block) {
        if (explosionTicks == -1) {
            explosionTicks = 60;
        }
        setMot(0,0,0);
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        if (explosionTicks == -1) {
            explosionTicks = 60;
        }
        setMot(0,0,0);
        return super.onDamageEntity(entity);
    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<30;i++) {
            double d0 = this.locX()+randomFloat(-0.1f,0.1f);
            double d1 = this.locY()+randomFloat(-0.1f,0.1f);
            double d2 = this.locZ()+randomFloat(-0.1f,0.1f);
            world.getWorld().spawnParticle(org.bukkit.Particle.LAVA, d0, d1, d2, 0, 0,0,0);
            world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, d0, d1, d2, 0, 0,0,0);
        }
        world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, locX(), locY(), locZ(), 5, 0,0,0);
    }

    public float getGravityScale(){
        return 0.01f;
    }


    public void explode(){
        for (int i = 0;i<20;i++) {
            playSound(SoundEffects.ENTITY_GENERIC_EXPLODE,2,0.5f);
            world.getWorld().spawnParticle(org.bukkit.Particle.EXPLOSION_HUGE, locX()+ RandomUtils.randomFloat(-10, 10), locY()+ RandomUtils.randomFloat(-10, 10), locZ()+ RandomUtils.randomFloat(-10, 10), 0);
            for (int j = 0; j < 10; j++) {
                world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, locX() + RandomUtils.randomFloat(-10, 10), locY() + RandomUtils.randomFloat(-10, 10), locZ() + RandomUtils.randomFloat(-10, 10), 5);
            }
        }
        world.getEntities(this,getBoundingBox().grow(10,10,10),(e)->{
            if (!(e.getUniqueID().equals(getShooterUUID()))) {
                Vector direction = e.getBukkitEntity().getLocation().getDirection();
                e.damageEntity(DamageSource.a(getShooter()),SpellsConfiguration.ARMAGEDON.getInt("damage"));
                e.setOnFire(SpellsConfiguration.ARMAGEDON.getInt("ticks"));
                e.setMot(direction.getX()*5,direction.getY()*5,direction.getZ()*5);
                e.velocityChanged = true;
            }
            return false;
        });
    }

    @Override
    public void tick() {
        super.tick();
        if (explosionTicks == 0){
            die();
        }else{
            if (explosionTicks % 10 == 0){
                explode();
            }
            if (explosionTicks > 0) {
                explosionTicks--;
            }
        }
    }

    @Override
    public float getDamage() {
        return 0;
    }
}

package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

public class EntityFireExplosionProjectile extends EntityMagic{

    public EntityFireExplosionProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityFireExplosionProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityFireExplosionProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        hide();
        setFireTicks(999999);
    }

    @Override
    public void onTouchBlock(Block block) {
        explode();
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        explode();
        return super.onDamageEntity(entity);
    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<10;i++) {
            double d0 = this.locX()+randomFloat(-0.5f,0.5f);
            double d1 = this.locY()+randomFloat(-0.5f,0.5f);
            double d2 = this.locZ()+randomFloat(-0.5f,0.5f);
            world.getWorld().spawnParticle(org.bukkit.Particle.LAVA, d0, d1, d2, 0, 0,0,0);
            world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, d0, d1, d2, 0, 0,0,0);
        }
        world.getWorld().spawnParticle(org.bukkit.Particle.FLAME, locX(), locY(), locZ(), 1, 0,0,0);
    }

    public float getGravityScale(){
        return 0.01f;
    }


    public void explode(){
        world.getWorld().spawnParticle(org.bukkit.Particle.EXPLOSION_LARGE,locX(),locY(),locZ(),0);
        playSound(SoundEffects.ENTITY_GENERIC_EXPLODE,1,1);
        for (int i = 0;i<10;i++){
            world.getWorld().spawnParticle(org.bukkit.Particle.FLAME,locX()+RandomUtils.randomFloat(-2,2),locY()+RandomUtils.randomFloat(-2,2),locZ()+RandomUtils.randomFloat(-2,2),5);
        }
        world.getEntities(this,getBoundingBox().grow(2,2,2),(e)->{
            if (!(e instanceof EntityWindProjectile)) {
                if (!(e.getUniqueID().equals(getShooterUUID()))) {
                    Vector direction = e.getBukkitEntity().getLocation().getDirection();
                    e.damageEntity(DamageSource.a(getShooter()),SpellsConfiguration.FIRE_EXPLOSION.getInt("damage"));
                    e.setFireTicks(SpellsConfiguration.FIRE_EXPLOSION.getInt("ticks"));
                    e.setMot(direction.getX(),direction.getY(),direction.getZ());
                    e.velocityChanged = true;
                }
            }
            return false;
        });
    }


    @Override
    public float getDamage() {
        return 0;
    }
}

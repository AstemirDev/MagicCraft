package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class EntityWindProjectile extends EntityMagic{


    public EntityWindProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityWindProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityWindProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }


    @Override
    public void onCreate() {
        hide();
        setDestroyOnTouchEntity(false);
    }


    @Override
    public void onTouchBlock(Block block) {

    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<10;i++) {
            double d0 = this.locX()+randomFloat(-0.5f,0.5f);
            double d1 = this.locY()+randomFloat(-0.5f,0.5f);
            double d2 = this.locZ()+randomFloat(-0.5f,0.5f);
            world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, d0, d1, d2, 0, 0,0,0);
        }
    }



    @Override
    public float getGravityScale() {
        return 0;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        return false;
    }


    @Override
    public void tick() {
        super.tick();
        if (ticksLived > 10){
            if (!this.world.isClientSide) {
                this.world.broadcastEntityEffect(this, (byte)3);
                if (getPlayerOwner() != null){
                    getPlayerOwner().setFallDistance(0);
                }
                this.die();
            }
        }else{
            world.getEntities(this,getBoundingBox().grow(1.5f,1.5f,1.5f),(e)->{
                if (!(e instanceof EntityWindProjectile)) {
                    if (!(e.getUniqueID().equals(getShooterUUID()))) {
                        e.setMot(getMot().x, getMot().y * 0.5f, getMot().z);
                        e.velocityChanged = true;
                    }
                }
                return false;
            });
        }
    }

    @Override
    public float getDamage() {
        return 0;
    }
}

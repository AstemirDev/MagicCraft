package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;

public class EntityAirJumpProjectile extends EntityMagic{


    public EntityAirJumpProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityAirJumpProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityAirJumpProjectile(World var0, double var1, double var3, double var5) {
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
            double d0 = this.locX()+randomFloat(-0.25f,0.25f);
            double d1 = this.locY()+randomFloat(-0.25f,0.25f);
            double d2 = this.locZ()+randomFloat(-0.25f,0.25f);
            world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, d0, d1, d2, 1, 0,0,0);
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
        if (ticksLived > 4){
            if (!this.world.isClientSide) {
                this.world.broadcastEntityEffect(this, (byte)3);
                this.die();
            }
        }else{
            world.getEntities(this,getBoundingBox().grow(2f,2f,2f),(e)->{
                if (!(e.getUniqueID().equals(getShooterUUID()))) {
                    e.setMot(getMot().x / 2, getMot().y / 2, getMot().z / 2);
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

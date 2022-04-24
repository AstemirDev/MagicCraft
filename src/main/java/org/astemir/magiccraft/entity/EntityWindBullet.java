package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;

public class EntityWindBullet extends EntityMagic{

    public EntityWindBullet(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityWindBullet(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityWindBullet(World var0, double var1, double var3, double var5) {
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
            double d0 = this.locX()+randomFloat(-0.1f,0.1f);
            double d1 = this.locY()+randomFloat(-0.1f,0.1f);
            double d2 = this.locZ()+randomFloat(-0.1f,0.1f);
            world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, d0, d1, d2, 0, 0,0,0);
        }
    }

    @Override
    public float getGravityScale() {
        return 0;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        return super.onDamageEntity(entity);
    }


    @Override
    public void tick() {
        super.tick();
    }


    @Override
    public float getDamage() {
        return SpellsConfiguration.WIND_BULLET.getInt("damage");
    }
}

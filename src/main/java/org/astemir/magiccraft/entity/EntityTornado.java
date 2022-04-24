package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.EntityUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class EntityTornado extends EntityMagic implements ITornadoIgnorable{


    public EntityTornado(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityTornado(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityTornado(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }


    @Override
    public void onCreate() {
        hide();
        setDestroyOnTouchEntity(false);
        setDestroyOnTouchBlock(false);
    }


    @Override
    public void onTouchBlock(Block block) {
        setMot(getMot().x,0,getMot().z);
    }


    @Override
    public void spawnParticles() {
        float height = SpellsConfiguration.TORNADO.getInt("height");
        for (int i = 0; i < height; i++) {
            float radius = i * 0.33f;
            world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, locX() + Math.cos(ticksLived) * radius, locY() + i, locZ() + Math.sin(ticksLived) * radius, 1, 0, 0, 0);
            for (int j = 0; j < 4; j++) {
                double x = locX() + (Math.cos(ticksLived + i * 20) * radius) + randomFloat(-0.25f, 0.25f);
                double y = locY() + i + randomFloat(-0.25f, 0.25f);
                double z = locZ() + (Math.sin(ticksLived + i * 20) * radius) + randomFloat(-0.25f, 0.25f);
                EntityUtils.suckEntities(this,getBoundingBox().grow(radius,radius,radius),x,y,z);
                spawnParticle( x,y ,z );
            }
        }
    }

    private void spawnParticle(double x,double y,double z){
        for (int i = 0;i<4;i++) {
            world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, x+randomFloat(-0.5f,0.5f), y+randomFloat(-0.5f,0.5f), z+randomFloat(-0.5f,0.5f), 0, 0, 0, 0);
        }
    }




    @Override
    public float getGravityScale() {
        return 0.01f;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        return false;
    }


    @Override
    public void tick() {
        super.tick();
        if (ticksLived % 5 == 0){
            world.getWorld().playSound(new Location(world.getWorld(),locX(),locY(),locZ()),"magiccraft.tornado.ambient",8, RandomUtils.randomFloat(0.9f,1.1f));
        }
        if (ticksLived > SpellsConfiguration.TORNADO.getInt("life_time")){
            if (!this.world.isClientSide) {
                this.die();
            }
        }
    }

    @Override
    public float getDamage() {
        return 0;
    }
}

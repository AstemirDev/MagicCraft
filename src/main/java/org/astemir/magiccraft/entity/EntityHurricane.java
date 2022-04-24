package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.EntityUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.astemir.magiccraft.utils.WorldUtils;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.util.Vector;

public class EntityHurricane extends EntityMagic implements ITornadoIgnorable{


    public EntityHurricane(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityHurricane(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityHurricane(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }


    @Override
    public void onCreate() {
        hide();
        setDestroyOnTouchEntity(false);
        setDestroyOnTouchBlock(false);
        world.getWorld().setStorm(true);
        world.getWorld().setThundering(true);
        world.getWorld().setThunderDuration(650);
        world.getWorld().setWeatherDuration(650);
    }


    @Override
    public void onTouchBlock(Block block) {
        setMot(getMot().x,0,getMot().z);
    }


    private void absorbNearbyBlocks(int height,int radius){
        WorldUtils.breakBlockToEntity(world.getWorld(), locX() + Math.cos(ticksLived)*RandomUtils.randomInt(radius), locY() - 1 + height, locZ() + Math.sin(ticksLived)*RandomUtils.randomInt(radius));
    }

    @Override
    public void spawnParticles() {
        float height = SpellsConfiguration.HURRICANE.getInt("height");
        for (int i = 0; i < height; i++) {
            float radius = i * 0.33f;
            if (ticksLived % 20 == 0) {
                if (random.nextInt(50) == 0) {
                    world.getWorld().strikeLightning(new Location(world.getWorld(), locX() + Math.cos(ticksLived) * radius, locY(), locZ() + Math.sin(ticksLived) * radius));
                }
                for (int k = 0;k<1;k++) {
                    absorbNearbyBlocks(i,(int) (height * 0.33f));
                }
            }
            world.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, locX() + Math.cos(ticksLived) * radius, locY() + i, locZ() + Math.sin(ticksLived) * radius, 1, 0,0,0,1,new org.bukkit.Particle.DustOptions(Color.GRAY,1));
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
        for (int i = 0;i<1;i++) {
            world.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, x+randomFloat(-0.5f,0.5f), y+randomFloat(-0.5f,0.5f), z+randomFloat(-0.5f,0.5f), 0, 0,0,0,new org.bukkit.Particle.DustOptions(Color.fromBGR(84,84,84),10));
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
        if (ticksLived > SpellsConfiguration.HURRICANE.getInt("life_time")){
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

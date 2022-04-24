package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class EntityWaterBoltProjectile extends EntityMagic{


    private int distance = 1;

    private boolean falling = false;

    private int touchedTicks = 0;

    public EntityWaterBoltProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityWaterBoltProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityWaterBoltProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        hide();
    }

    @Override
    public void onTouchBlock(Block block) {
        world.getEntities(this,getBoundingBox().grow(2,1,2),(e)->{
            if (e instanceof EntityLiving && getPlayerOwner() != null) {
                if (!e.getUniqueID().equals(getPlayerOwner().getUniqueId())) {
                    ((EntityLiving) e).damageEntity(DamageSource.a(((CraftPlayer) getPlayerOwner()).getHandle()), getDamage());
                }
            }
            return false;
        });
        extinguishBlock(getChunkCoordinates().up());
        extinguishBlock(getChunkCoordinates().down());
        extinguishBlock(getChunkCoordinates().north());
        extinguishBlock(getChunkCoordinates().west());
        extinguishBlock(getChunkCoordinates().east());
        extinguishBlock(getChunkCoordinates().south());
        extinguishBlock(getChunkCoordinates());
        splashEffect();
    }

    private void extinguishBlock(BlockPosition pos){
        if (world.getType(pos).a(Blocks.FIRE)) {
            world.setTypeUpdate(pos, Blocks.AIR.getBlockData());
            playSound(SoundEffects.BLOCK_FIRE_EXTINGUISH,1,1);
        }
    }



    @Override
    public void spawnParticles() {
        for (int i = 0;i<20;i++) {
            double d0 = this.locX()+randomFloat(-0.5f,0.5f);
            double d1 = this.locY()+randomFloat(-0.5f,0.5f);
            double d2 = this.locZ()+randomFloat(-0.5f,0.5f);
            world.getWorld().spawnParticle(org.bukkit.Particle.WATER_SPLASH, d0, d1, d2, 0, 0,0,0);
        }
        world.getWorld().spawnParticle(org.bukkit.Particle.DRIP_WATER, locX(), locY(), locZ(), 0, 0,0,0);
    }

    private void splashEffect(){
        playSound(SoundEffects.ENTITY_PLAYER_SPLASH,2,1);
        for (int i = 0;i<30;i++) {
            world.getWorld().spawnParticle(org.bukkit.Particle.WATER_WAKE, locX()+randomFloat(-1,1f), locY(), locZ()+randomFloat(-1,1f), 10, 0, 0, 0, 0.05f);
        }
    }

    @Override
    public float getGravityScale() {
        if (falling){
            return super.getGravityScale();
        }else{
            return 0;
        }
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        splashEffect();
        entity.extinguish();
        return super.onDamageEntity(entity);
    }


    @Override
    public void die() {
        super.die();
        if (getPlayerOwner() != null){
            Spells.WATER_BOLT_SPELL.cooldown(getPlayerOwner());
            MagicCraft.playerDataHandler.getPlayerData(getPlayerOwner()).removeCustomVariable("waterBolt");
        }
    }

    @Override
    public void tick() {
        super.tick();
        if (touchedTicks > 0){
            touchedTicks--;
        }
        Player owner = getPlayerOwner();
        if (ticksLived > SpellsConfiguration.WATER_BOLT.getInt("flying_ticks")){
            falling = true;
        }else
        if (owner != null){
            if (touchedTicks <= 0){
                if (distance > 1) {
                    distance--;
                }
            }
            Location loc = PlayerUtils.getPlayerEyeLocation(owner).add(owner.getLocation().getDirection().clone().multiply(distance));
            setPosition(loc.getX(),loc.getY(),loc.getZ());
        }


        if (isBurning()){
            for (int i = 0;i<10;i++) {
                world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, locX()+randomFloat(-0.25f,0.25f), locY()+1f, locZ()+randomFloat(-0.25f,0.25f), 0, 0, 0, 0);
            }
            playSound(SoundEffects.BLOCK_FIRE_EXTINGUISH,1,1);
            die();
        }
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
        this.touchedTicks = SpellsConfiguration.WATER_BOLT.getInt("return_cooldown");
    }

    @Override
    public float getDamage() {
        return SpellsConfiguration.WATER_BOLT.getInt("damage");
    }
}

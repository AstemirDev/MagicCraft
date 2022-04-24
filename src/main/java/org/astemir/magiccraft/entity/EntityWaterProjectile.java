package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EntityWaterProjectile extends EntityMagic{

    private boolean launched = false;


    public EntityWaterProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityWaterProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityWaterProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        hide();
    }

    @Override
    public void onTouchBlock(Block block) {
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
        for (int i = 0;i<10;i++) {
            double d0 = this.locX()+randomFloat(-0.1f,0.1f);
            double d1 = this.locY()+randomFloat(-0.1f,0.1f);
            double d2 = this.locZ()+randomFloat(-0.1f,0.1f);
            world.getWorld().spawnParticle(org.bukkit.Particle.WATER_WAKE, d0, d1, d2, 0, 0,0,0);
        }
        world.getWorld().spawnParticle(org.bukkit.Particle.DRIP_WATER, locX(), locY(), locZ(), 0, 0,0,0);
    }

    private void splashEffect(){
        playSound(SoundEffects.ENTITY_PLAYER_SPLASH,2,1);
        for (int i = 0;i<10;i++) {
            world.getWorld().spawnParticle(org.bukkit.Particle.WATER_WAKE, locX(), locY(), locZ(), 10, 0, 0, 0, 0.05f);
        }
    }

    @Override
    public float getGravityScale() {
        if (launched) {
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
    public EntityMagic launch(Player player, float a, float b, float c) {
        super.launch(player, a, b, c);
        launched = true;
        return this;
    }

    @Override
    public void tick() {
        super.tick();
        Player owner = getPlayerOwner();
        int maxTicks = SpellsConfiguration.WATER.getInt("ticks");
        if (ticksLived > maxTicks  && !launched){
            if (owner != null){
                owner.swingMainHand();
                owner.playSound(owner.getLocation(), Sound.ENTITY_FISHING_BOBBER_SPLASH, 1, 1);
                launch(owner,0,1.5f,1f);
            }else{
                launched = true;
            }
        }
        if (ticksLived < maxTicks && owner != null){
            Location loc = PlayerUtils.getPlayerEyeLocation(owner).add(owner.getLocation().getDirection());
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


    @Override
    public float getDamage() {
        return SpellsConfiguration.WATER.getInt("damage");
    }
}

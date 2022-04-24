package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class EntityStoneBulletProjectile extends EntityMagic{


    private int ticksToLaunch = 0;

    public EntityStoneBulletProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityStoneBulletProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityStoneBulletProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        setNoGravity(true);
    }

    @Override
    public void onTouchBlock(Block block) {
        playSound(SoundEffects.BLOCK_STONE_BREAK, 1, 0.8f);
    }


    @Override
    public ItemStack getSnowballItem() {
        return new ItemStack(Blocks.STONE);
    }

    @Override
    public void spawnParticles() {
    }

    @Override
    public float getGravityScale() {
        return 0.005f;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        playSound(SoundEffects.BLOCK_STONE_BREAK, 1, 0.8f);
        return super.onDamageEntity(entity);
    }

    @Override
    public void die() {
        for (int i = 0;i<10;i++) {
            double d0 = this.locX()+randomFloat(-0.1f,0.1f);
            double d1 = this.locY()+randomFloat(-0.1f,0.1f);
            double d2 = this.locZ()+randomFloat(-0.1f,0.1f);
            world.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK, d0, d1, d2, 0, 0,0,0,0.5f,org.bukkit.Material.STONE.createBlockData());
        }
        super.die();
    }

    @Override
    public void tick() {
        super.tick();
        if (ticksToLaunch > 0){
            ticksToLaunch--;
            if (getPlayerOwner() != null){
                Player owner = getPlayerOwner();
                Location loc = PlayerUtils.getPlayerEyeLocation(owner).add(owner.getLocation().getDirection());
                setPosition(loc.getX(),loc.getY(),loc.getZ());
            }
        }else{
            if (ticksToLaunch == 0){
                ticksToLaunch = -1;
                setNoGravity(false);
                getPlayerOwner().getWorld().playSound(getPlayerOwner().getLocation(), Sound.ENTITY_SKELETON_SHOOT, 0.5f, 0.5f);
                launch(getPlayerOwner(),0,2,1);
            }
        }
    }


    public void setTicksToLaunch(int ticksToLaunch) {
        this.ticksToLaunch = ticksToLaunch;
    }

    @Override
    public float getDamage() {
        return SpellsConfiguration.STONE_BULLET.getInt("damage");
    }
}

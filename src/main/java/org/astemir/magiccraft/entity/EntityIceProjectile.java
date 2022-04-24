package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EntityIceProjectile extends EntityMagic{


    public EntityIceProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityIceProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityIceProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }


    @Override
    public void onCreate() {
    }

    @Override
    public void onTouchBlock(Block block) {

    }

    @Override
    public void die() {
        super.die();
        playSound(SoundEffects.BLOCK_GLASS_BREAK,2,0.5f);
    }

    @Override
    public void spawnParticles() {
        double d0 = this.locX();
        double d1 = this.locY();
        double d2 = this.locZ();
        world.getWorld().spawnParticle(org.bukkit.Particle.WHITE_ASH, d0, d1, d2, 0, 0,0,0);
    }

    @Override
    public float getGravityScale() {
        return super.getGravityScale();
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        entity.extinguish();
        if (entity instanceof LivingEntity){
            ((LivingEntity) entity).addPotionEffect(SpellsConfiguration.ICE.effect("slowness").toBukkit());
        }
        return super.onDamageEntity(entity);
    }

    @Override
    public ItemStack getSnowballItem() {
        return Blocks.ICE.getItem().createItemStack();
    }

    @Override
    public void tick() {
        super.tick();
        Player owner = getPlayerOwner();
        if (isBurning()){
            for (int i = 0;i<10;i++) {
                world.getWorld().spawnParticle(org.bukkit.Particle.CLOUD, locX()+randomFloat(-0.25f,0.25f), locY()+0.75f, locZ()+randomFloat(-0.25f,0.25f), 0, 0, 0, 0);
            }
            playSound(SoundEffects.BLOCK_FIRE_EXTINGUISH,1,1);
            die();
        }
    }


    @Override
    public float getDamage() {
        return SpellsConfiguration.ICE.getInt("damage");
    }
}

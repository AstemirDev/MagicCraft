package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.concurrent.CopyOnWriteArrayList;

public class EntityFreezingProjectile extends EntityMagic{


    public EntityFreezingProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityFreezingProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityFreezingProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    private CopyOnWriteArrayList<FallingBlock> iceBlocks = new CopyOnWriteArrayList<>();
    private float length = 2;
    private int ticks = 0;

    private Entity target;

    @Override
    public void onCreate() {
        hide();
        setDestroyOnTouchBlock(false);
        setDestroyOnTouchEntity(false);
    }

    @Override
    public void onTouchBlock(Block block) {

    }

    @Override
    public void die() {
        super.die();
        iceBlocks.forEach((ice)->{
            ice.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK,ice.getLocation().clone().add(0.5f,0,0.5f),5,0.5f,0.5f,0.5f,ice.getBlockData());
            playSound(SoundEffects.BLOCK_GLASS_BREAK,2,0.5f);
            ice.remove();
        });
    }

    @Override
    public void spawnParticles() {

    }

    @Override
    public float getGravityScale() {
        return 0;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        entity.extinguish();
        if (entity instanceof EntityLiving){
            target = entity;
            length = entity.getHeight();
            for (int i = 0;i<length;i++){
                FallingBlock ice = world.getWorld().spawnFallingBlock(getBukkitEntity().getLocation().clone().add(0,i,0),org.bukkit.Material.ICE.createBlockData());
                ice.setGravity(false);
                ice.setDropItem(false);
                ice.setHurtEntities(false);
                iceBlocks.add(ice);
            }
        }
        if (entity instanceof LivingEntity) {
            ((LivingEntity) entity).addPotionEffect(SpellsConfiguration.FREEZE_BEAM.effect("slowness").toBukkit());
        }
        return super.onDamageEntity(entity);
    }

    @Override
    public EntityMagic launch(Player player, float a, float b, float c) {
        super.launch(player, a, b, c);
        return this;
    }

    @Override
    public ItemStack getSnowballItem() {
        return Blocks.ICE.getItem().createItemStack();
    }

    @Override
    public void tick() {
        super.tick();
        if (ticks > SpellsConfiguration.FREEZE_BEAM.getInt("ticks")){
            die();
        }else{
            ticks++;
        }
        if (target != null){
            target.setMot(0,0,0);
            target.velocityChanged = true;
            for (int i = 0;i<length;i++){
                iceBlocks.get(i).teleport(getBukkitEntity().getLocation().clone().add(0,i,0));
            }
            setPosition(target.locX(),target.locY(),target.locZ());
        }
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
        return SpellsConfiguration.FREEZE_BEAM.getInt("damage");
    }
}

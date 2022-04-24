package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.bukkit.entity.Player;

public class EntityDirtProjectile extends EntityMagic{

    private boolean launched = false;


    public EntityDirtProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityDirtProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityDirtProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onTouchBlock(Block block) {
        playSound(SoundEffects.BLOCK_GRAVEL_BREAK, 1, 0.8f);
    }


    @Override
    public ItemStack getSnowballItem() {
        return new ItemStack(Blocks.DIRT);
    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<10;i++) {
            double d0 = this.locX()+randomFloat(-0.1f,0.1f);
            double d1 = this.locY()+randomFloat(-0.1f,0.1f);
            double d2 = this.locZ()+randomFloat(-0.1f,0.1f);
            world.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK, d0, d1, d2, 0, 0,0,0,0.5f,org.bukkit.Material.DIRT.createBlockData());
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
        playSound(SoundEffects.BLOCK_GRAVEL_BREAK, 1, 0.8f);
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
    }




    @Override
    public float getDamage() {
        return SpellsConfiguration.DIRT.getInt("damage");
    }
}

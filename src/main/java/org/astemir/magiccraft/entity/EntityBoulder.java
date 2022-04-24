package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.item.Items;
import org.astemir.magiccraft.utils.EntityUtils;
import org.astemir.magiccraft.utils.RandomUtils;

public class EntityBoulder extends EntityMagic{



    private EntityBoulderModel model;


    public EntityBoulder(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityBoulder(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityBoulder(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }


    @Override
    public void onCreate() {
        hide();
        model = new EntityBoulderModel(world,locX(),locY(),locZ());
        model.position(locX(),locY(),locZ());
    }

    @Override
    public void onTouchBlock(Block block) {
        die();
    }



    @Override
    public EntityMagic join() {
        super.join();
        world.addEntity(model);
        return this;
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
        return 0.03f;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        die();
        return super.onDamageEntity(entity);
    }

    @Override
    public void die() {
        for (int i = 0;i<40;i++) {
            double d0 = this.locX()+randomFloat(-0.25f,0.25f);
            double d1 = this.locY()+randomFloat(-0.25f,0.25f);
            double d2 = this.locZ()+randomFloat(-0.25f,0.25f);
            world.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK, d0, d1, d2, 0, 0,0,0,0.5f,org.bukkit.Material.STONE.createBlockData());
        }
        playSound(SoundEffects.BLOCK_STONE_BREAK, 1, 0.5f);
        playSound(SoundEffects.BLOCK_STONE_BREAK, 1, 0.5f);
        playSound(SoundEffects.BLOCK_STONE_BREAK, 1, 0.5f);
        playSound(SoundEffects.BLOCK_STONE_BREAK, 1, 0.5f);
        model.die();
        super.die();
    }

    @Override
    public void tick() {
        super.tick();
        EntityUtils.damageEntities(this, getBoundingBox().grow(2, 2, 2), 5, RandomUtils.randomFloat(-1, 1), 0.5f, RandomUtils.randomFloat(-1, 1));
        model.position(locX(),locY(),locZ());
    }


    @Override
    public float getDamage() {
        return SpellsConfiguration.BOULDER.getInt("damage");
    }

    private class EntityBoulderModel extends EntityModel{

        public EntityBoulderModel(World world, double d0, double d1, double d2) {
            super(world, d0, d1, d2);
            setModelItem(Items.BOULDER);
        }

    }
}

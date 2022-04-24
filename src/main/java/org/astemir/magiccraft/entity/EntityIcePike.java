package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.item.Items;
import org.astemir.magiccraft.utils.EntityUtils;

import static org.astemir.magiccraft.utils.RandomUtils.randomFloat;

public class EntityIcePike extends EntityModel{


    private float resultY = 0;

    public EntityIcePike(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        setModelItem(Items.ICE_PIKE);
    }

    @Override
    public void die() {
        super.die();
        for (int i = 0;i<15;i++) {
            double d0 = this.locX()+randomFloat(-0.2f,0.2f);
            double d1 = getBottom()+randomFloat(0, getModelLength());
            double d2 = this.locZ()+randomFloat(-0.2f,0.2f);
            world.getWorld().spawnParticle(org.bukkit.Particle.BLOCK_CRACK, d0, d1, d2, 0, 0,0,0,0.5f,org.bukkit.Material.ICE.createBlockData());
        }
        playSound(SoundEffects.BLOCK_GLASS_BREAK,2,0.5f);
    }

    @Override
    public float getModelLength() {
        return 0.8f;
    }

    public void setResultY(float y){
        this.resultY = y;
    }

    private float lerp(float v0, float v1, float t) {
        return (1 - t) * v0 + t * v1;
    }

    @Override
    public void tick() {
        super.tick();
        setActualPosition(locX(),lerp((float) locY(),resultY,0.5f),locZ());
        if (ticksLived > 20){
            die();
        }
        EntityUtils.damageEntities(this,getNewBoundingBox(),SpellsConfiguration.ICE_PIKE.getInt("damage"),0,0,0);
    }
}

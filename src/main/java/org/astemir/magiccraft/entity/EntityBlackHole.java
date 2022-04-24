package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.item.Items;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import static org.astemir.magiccraft.utils.RandomUtils.randomFloat;

public class EntityBlackHole extends EntityModel implements ITornadoIgnorable{


    public EntityBlackHole(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        setModelItem(Items.BLACK_HOLE);
    }

    @Override
    public void die() {
        super.die();
        for (int i = 0;i<50;i++) {
            double d0 = this.locX()+randomFloat(-1.5f,1.5f);
            double d1 = this.locY()+1.4+randomFloat(0,1.5f);
            double d2 = this.locZ()+randomFloat(-1.5f,1.5f);
            world.getWorld().spawnParticle(Particle.SQUID_INK, d0, d1, d2, 0, 0,0,0,0.5f);
        }
        playSound(SoundEffects.ENTITY_EVOKER_PREPARE_SUMMON,2,0.5f);
    }

    @Override
    public float getModelLength() {
        return 1f;
    }


    private float lerp(float v0, float v1, float t) {
        return (1 - t) * v0 + t * v1;
    }

    @Override
    public void tick() {
        super.tick();
        world.getWorld().spawnParticle(Particle.SQUID_INK, locX(), getBottom(),locZ(), 5, 0,0,0,0.5f);
        world.getWorld().spawnParticle(Particle.FLASH, locX(), getBottom(),locZ(), 0, 0,0,0,0.5f);
        setHeadPose(new Vector3f(headPose.getX(),headPose.getY()+ticksLived,headPose.getZ()));
        if (ticksLived > 100){
            die();
        }
        world.getEntities(this,getNewBoundingBox().grow(5,5,5),(e)->{
            if (!isOwner(e.getUniqueID())) {
                if (!(e instanceof EntityBlackHole)) {
                    if (!(e instanceof ITornadoIgnorable)) {
                        Vector direction = new Vector(locX() - e.locX(), getBottom() - e.locY(), locZ() - e.locZ()).normalize();
                        e.damageEntity(DamageSource.a(getServerOwner()), SpellsConfiguration.BLACK_HOLE.getInt("damage"));
                        e.velocityChanged = true;
                        e.setMot(direction.getX(), direction.getY(), direction.getZ());
                    }
                }
            }
            return false;
        });
    }
}

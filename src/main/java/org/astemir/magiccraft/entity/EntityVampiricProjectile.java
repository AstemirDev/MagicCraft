package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class EntityVampiricProjectile extends EntityMagic{

    public EntityVampiricProjectile(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityVampiricProjectile(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityVampiricProjectile(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }

    @Override
    public void onCreate() {
        hide();
        setDestroyOnTouchBlock(false);
    }

    @Override
    public void onTouchBlock(Block block) {

    }

    @Override
    public void spawnParticles() {
        for (int i = 0;i<5;i++) {
            double d0 = this.locX()+randomFloat(-0.25f,0.25f);
            double d1 = this.locY()+randomFloat(-0.25f,0.25f);
            double d2 = this.locZ()+randomFloat(-0.25f,0.25f);
            world.getWorld().spawnParticle(org.bukkit.Particle.REDSTONE, d0, d1, d2, 0, 0,0,0,new org.bukkit.Particle.DustOptions(Color.RED,1));
        }
    }

    @Override
    public float getGravityScale() {
        return 0;
    }

    @Override
    public boolean onDamageEntity(Entity entity) {
        if (getPlayerOwner() != null) {
            if (entity.getUniqueID().equals(getPlayerOwner().getUniqueId()) && !dead) {
                getPlayerOwner().addPotionEffect(SpellsConfiguration.VAMPIRISM.effect("heal").toBukkit());
                die();
            }
        }
        return false;
    }


    @Override
    public void tick() {
        super.tick();
        Player player = getPlayerOwner();
        if (ticksLived > SpellsConfiguration.VAMPIRISM.getInt("life_time")){
            die();
        }
        if (player != null){
            Vector dir = getPlayerOwner().getLocation().clone().add(0,0.5f,0).subtract(new Location(world.getWorld(),locX(),locY(),locZ())).toVector().normalize();
            setMot(dir.getX(),dir.getY(),dir.getZ());
        }else{
            die();
        }
    }


    @Override
    public float getDamage() {
        return 0;
    }
}

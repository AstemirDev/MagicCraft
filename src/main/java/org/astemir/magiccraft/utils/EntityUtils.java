package org.astemir.magiccraft.utils;

import net.minecraft.server.v1_16_R3.AxisAlignedBB;
import net.minecraft.server.v1_16_R3.DamageSource;
import net.minecraft.server.v1_16_R3.Entity;
import org.astemir.magiccraft.entity.IOwnedEntity;
import org.astemir.magiccraft.entity.ITornadoIgnorable;
import org.bukkit.util.Vector;

public class EntityUtils {


    public static void damageEntities(Entity entity, AxisAlignedBB boundingBox,float damage,float velX,float velY,float velZ){
        entity.world.getEntities(entity,boundingBox,(e)->{
            if (entity instanceof IOwnedEntity) {
                if (((IOwnedEntity)entity).hasOwner()) {
                    if (!((IOwnedEntity)entity).isOwner(e.getUniqueID())) {
                        e.damageEntity(DamageSource.a(((IOwnedEntity) entity).getServerOwner()), damage);
                        e.setMot(velX, velY, velZ);
                        e.velocityChanged = true;
                    }
                }
            }else{
                e.damageEntity(DamageSource.a(entity),damage);
                e.setMot(velX, velY, velZ);
                e.velocityChanged = true;
            }
            return false;
        });
    }

    public static void suckEntities(Entity entity, AxisAlignedBB boundingBox,double x,double y,double z){
        entity.world.getEntities(entity,boundingBox,(e)->{
            if (!(e instanceof ITornadoIgnorable)) {
                if (entity instanceof IOwnedEntity){
                    IOwnedEntity ownedEntity = ((IOwnedEntity)entity);
                    if (ownedEntity.hasOwner()) {
                        if (!ownedEntity.isOwner(e.getUniqueID())) {
                            Vector direction = new Vector(x - e.locX(), y - e.locY(), z - e.locZ()).normalize();
                            e.setMot(direction.getX(), direction.getY(), direction.getZ());
                            e.velocityChanged = true;
                        }
                    }
                }else{
                    Vector direction = new Vector(x - e.locX(), y - e.locY(), z - e.locZ()).normalize();
                    e.setMot(direction.getX(), direction.getY(), direction.getZ());
                    e.velocityChanged = true;
                }
            }
            return false;
        });
    }

    public static void suckEntities(Entity entity, AxisAlignedBB boundingBox){
        suckEntities(entity,boundingBox,entity.locX(),entity.locY(),entity.locZ());
    }


}

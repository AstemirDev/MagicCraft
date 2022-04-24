package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public abstract class EntityMagic extends EntityDamagableSnowball implements IOwnedEntity{


    public EntityMagic(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
    }

    public EntityMagic(World var0, EntityLiving var1) {
        super(var0, var1);
    }

    public EntityMagic(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
    }


    public abstract void onCreate();

    public abstract void onTouchBlock(Block block);

    @Override
    protected void a(MovingObjectPositionBlock movingobjectpositionblock) {
        super.a(movingobjectpositionblock);
        onTouchBlock(world.getType(movingobjectpositionblock.getBlockPosition()).getBlock());
    }

    public static <T extends EntityMagic> T create(Class<T> className, org.bukkit.entity.Entity entity){
        Entity entityHandle = ((CraftEntity)entity).getHandle();
        try {
            T t = className.getConstructor(World.class,EntityLiving.class).newInstance(entityHandle.world, entityHandle);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static <T extends EntityMagic> T create(Class<T> className, Player player){
        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        try {
            T t = className.getConstructor(World.class,EntityLiving.class).newInstance(entityPlayer.world, entityPlayer);
            return t;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public EntityMagic join(){
        world.addEntity(this);
        onCreate();
        return this;
    }


    public EntityMagic launch(Player player,float a,float b,float c){
        EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        own(player);
        a(entityPlayer, entityPlayer.pitch, entityPlayer.yaw, a, b, c);
        return this;
    }

    public UUID getShooterUUID(){
        if (getShooter() != null){
            return getShooter().getUniqueID();
        }
        return null;
    }

}

package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.craftbukkit.v1_16_R3.block.CraftBlock;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.ProjectileHitEvent;

import java.util.UUID;


public abstract class EntityDamagableSnowball extends EntitySnowball implements IOwnedEntity{


    private UUID ownerUUID;

    private boolean destroyOnTouchBlock = true;
    private boolean destroyOnTouchEntity = true;

    public EntityDamagableSnowball(EntityTypes<? extends EntitySnowball> var0, World var1) {
        super(var0, var1);
        Init();
    }

    public EntityDamagableSnowball(World var0, EntityLiving var1) {
        super(var0, var1);
        Init();
    }

    public EntityDamagableSnowball(World var0, double var1, double var3, double var5) {
        super(var0, var1, var3, var5);
        Init();
    }

    public void own(Player player){
        ownerUUID = player.getUniqueId();
    }

    public Player getPlayerOwner(){
        return Bukkit.getPlayer(ownerUUID);
    }

    public boolean hasPlayerOwner(){
        return getPlayerOwner() != null;
    }

    private void Init(){
        if (getSnowballItem() != null){
            setItem(getSnowballItem());
        }
    }

    public void hide(){
        PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(getId());
        world.getPlayers().forEach((player)->{
            ((EntityPlayer)player).playerConnection.sendPacket(destroy);
        });
    }

    public ItemStack getSnowballItem(){
        return null;
    }

    public float getDamage(){
        return 8;
    }

    public void spawnParticles(){
    }

    @Override
    public void tick() {
        super.tick();
        spawnParticles();
    }

    public float randomFloat(float min,float max){
        return min + random.nextFloat() * (max - min);
    }

    @Override
    protected float k() {
        return getGravityScale();
    }

    public float getGravityScale(){
        return 0.03f;
    }

    @Override
    protected void a(MovingObjectPositionEntity var0) {
        Entity var1 = var0.getEntity();
        if (onDamageEntity(var1)) {
            var1.damageEntity(DamageSource.projectile(this, this.getShooter()), getDamage());
        }
    }


    @Override
    protected void a(MovingObjectPosition movingobjectposition) {
        callProjectileHitEvent(movingobjectposition);
        MovingObjectPosition.EnumMovingObjectType movingobjectposition_enummovingobjecttype = movingobjectposition.getType();
        if (movingobjectposition_enummovingobjecttype == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
            this.a((MovingObjectPositionEntity)movingobjectposition);
            if (destroyOnTouchEntity){
                if (!this.world.isClientSide) {
                    this.world.broadcastEntityEffect(this, (byte)3);
                    this.die();
                }
            }
        } else if (movingobjectposition_enummovingobjecttype == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
            this.a((MovingObjectPositionBlock)movingobjectposition);
            if (destroyOnTouchBlock){
                if (!this.world.isClientSide) {
                    this.world.broadcastEntityEffect(this, (byte)3);
                    this.die();
                }
            }
        }
    }

    public void callProjectileHitEvent(MovingObjectPosition position) {
        if (position.getType() != MovingObjectPosition.EnumMovingObjectType.MISS) {
            if (position.getType() == MovingObjectPosition.EnumMovingObjectType.BLOCK) {
                this.a((MovingObjectPositionBlock)position);
            }
            if (position.getType() == MovingObjectPosition.EnumMovingObjectType.ENTITY) {
                this.a((MovingObjectPositionEntity)position);
            }
        }
    }


    @Override
    public boolean isOwner(UUID uuid){
        if (hasOwner()){
            return getOwner().getUniqueId().equals(uuid);
        }
        return false;
    }

    @Override
    public boolean hasOwner(){
        return getOwner() != null;
    }

    @Override
    public Player getOwner(){
        return Bukkit.getPlayer(ownerUUID);
    }

    @Override
    public EntityPlayer getServerOwner() {
        return ((CraftPlayer)getOwner()).getHandle();
    }


    public boolean onDamageEntity(Entity entity){
        return true;
    }


    public boolean isDestroyOnTouchBlock() {
        return destroyOnTouchBlock;
    }

    public void setDestroyOnTouchBlock(boolean destroyOnTouchBlock) {
        this.destroyOnTouchBlock = destroyOnTouchBlock;
    }

    public boolean isDestroyOnTouchEntity() {
        return destroyOnTouchEntity;
    }

    public void setDestroyOnTouchEntity(boolean destroyOnTouchEntity) {
        this.destroyOnTouchEntity = destroyOnTouchEntity;
    }
}

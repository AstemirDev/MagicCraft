package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.*;
import org.astemir.magiccraft.item.Item;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_16_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.util.UUID;


public class EntityModel extends EntityArmorStand implements IOwnedEntity{

    private UUID ownerUUID;

    public EntityModel(World world, double d0, double d1, double d2) {
        super(world, d0, d1, d2);
        setInvisible(true);
        setNoGravity(true);
        setInvulnerable(true);
    }


    @Override
    protected void playEquipSound(ItemStack itemstack) {

    }

    @Override
    protected void playEquipSound(ItemStack itemstack, boolean silent) {

    }

    public void setModelItem(Item item){
        setSlot(EnumItemSlot.HEAD, CraftItemStack.asNMSCopy(item.toItemStack()));
    }

    @Override
    public EnumInteractionResult a(EntityHuman entityhuman, Vec3D vec3d, EnumHand enumhand) {
        return EnumInteractionResult.FAIL;
    }

    @Override
    protected void dropInventory() {

    }

    @Nullable
    @Override
    protected SoundEffect getSoundHurt(DamageSource damagesource) {
        return null;
    }

    @Nullable
    @Override
    protected SoundEffect getSoundDeath() {
        return null;
    }

    @Override
    public boolean damageEntity(DamageSource damagesource, float f) {
        return false;
    }


    public float getModelLength(){
        return 0.5f;
    }

    public double getX(){
        return locX();
    }

    public double getZ(){
        return locZ();
    }

    public float getY(){
        return (float) (locY()-getHeight());
    }

    public double getBottom(){
        return getHeadY();
    }

    public double getTop(){
        return getHeadY()+ getModelLength();
    }

    public void setActualPosition(double x, double y, double z){
        setPositionRotation(x, y,z, 0, 0);
    }

    public void position(double x, double y, double z){
        setPositionRotation(x, y-getHeight(),z, 0, 0);
    }

    public void move(double x,double y,double z){
        setActualPosition((float)getX()+x,(float)locY()+y,(float)getZ()+z);
    }

    public AxisAlignedBB getNewBoundingBox(){
        return new AxisAlignedBB(locX(), getBottom(),locZ(),locX(), getTop(),locZ());
    }

    public void create(){
        world.addEntity(this);
    }

    @Override
    public void own(Player player) {
        this.ownerUUID = player.getUniqueId();
    }

    @Override
    public boolean hasOwner() {
        if (ownerUUID != null){
            return Bukkit.getPlayer(ownerUUID) != null;
        }
        return false;
    }

    public boolean isOwner(UUID uuid){
        if (hasOwner()){
            return getOwner().getUniqueId().equals(uuid);
        }
        return false;
    }

    @Override
    public Player getOwner() {
        return Bukkit.getPlayer(ownerUUID);
    }

    @Override
    public EntityPlayer getServerOwner() {
        return ((CraftPlayer)getOwner()).getHandle();
    }
}

package org.astemir.magiccraft.world;

import org.astemir.magiccraft.MagicCraft;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.data.BlockData;

import java.util.function.Predicate;

public class InsistentBlock {

    private Location location;

    private Material type;

    private Material oldType;
    private BlockData oldData;
    private BlockState oldState;

    private Predicate<Material> replaceableBlocks = (a)-> a.isAir() || !a.isSolid();

    private boolean breakable;
    private boolean successfullyBuild;
    private boolean destroyed;
    private long ends;

    public InsistentBlock(Location loc, Material material,int livingTime) {
        Block block = loc.getWorld().getBlockAt(loc);
        this.location = loc;
        this.type = material;
        this.ends = MagicCraft.ticks+livingTime;
        this.oldType = block.getType();
        this.oldData = block.getBlockData();
        this.oldState = block.getState();
    }


    public Block build(){
        Block res = location.getWorld().getBlockAt(location);
        if (!InsistentBlocksHandler.hasBlockAtLocation(location)) {
            if (replaceableBlocks.test(res.getType())) {
                successfullyBuild = true;
                res.setType(type);
                InsistentBlocksHandler.blocks.add(this);
                return res;
            }
        }else{
            InsistentBlock another = InsistentBlocksHandler.getBlockAtLocation(location);
            if (another.isBreakable()){
                another.destroy();
                successfullyBuild = true;
                res.setType(type);
                InsistentBlocksHandler.blocks.add(this);
                return res;
            }
        }
        return res;
    }


    public long getEnds() {
        return ends;
    }

    public long getLeft(){
        return ends- MagicCraft.ticks;
    }

    public boolean isBuild() {
        return successfullyBuild;
    }

    public boolean isBreakable() {
        return breakable;
    }

    public Material getOldType() {
        return oldType;
    }

    public BlockData getOldData() {
        return oldData;
    }

    public BlockState getOldState() {
        return oldState;
    }

    public void breakable() {
        this.breakable = true;
    }

    public void setReplaceableBlocks(Predicate<Material> replaceableBlocks) {
        this.replaceableBlocks = replaceableBlocks;
    }

    protected void update(long ticks){}

    public void destroy(){
        InsistentBlocksHandler.blocks.remove(this);
        destroyed = true;
    }
}

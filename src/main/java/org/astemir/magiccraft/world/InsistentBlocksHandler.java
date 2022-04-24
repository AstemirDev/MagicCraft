package org.astemir.magiccraft.world;


import org.bukkit.Location;

import java.util.concurrent.CopyOnWriteArrayList;

public class InsistentBlocksHandler {


    public static CopyOnWriteArrayList<InsistentBlock> blocks;


    public InsistentBlocksHandler() {
        blocks = new CopyOnWriteArrayList<>();
    }


    public static boolean hasBlockAtLocation(Location loc){
        if (getBlockAtLocation(loc) == null){
            return false;
        }
        return true;
    }

    public static InsistentBlock getBlockAtLocation(Location loc){
        for (InsistentBlock block : blocks) {
            Location compareLoc = loc.getBlock().getLocation();
            if (compareLoc.getX() == loc.getX() && compareLoc.getY() == loc.getY() && compareLoc.getZ() == loc.getZ()){
                return block;
            }
        }
        return null;
    }

    public void update(long ticks){
        for (InsistentBlock block : blocks) {
            if (ticks >= block.getEnds()){
                block.destroy();
            }else {
                block.update(ticks);
            }
        }
    }
}

package org.astemir.magiccraft.beams;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import java.util.UUID;
import java.util.function.Predicate;

public abstract class Beam {


    public Predicate<Block> ignoredBlocks = (block)-> !block.isSolid();

    private Location location;
    private Vector direction;
    private Particle particle = Particle.CRIT;
    private Particle.DustOptions dustOptions;
    private boolean goThroughBlocks = true;
    private boolean ignoresFluids = false;
    private boolean disabled = false;
    private int length = 4;
    private double origin = 0;

    public Beam(Particle particle,Location loc, Vector direction,int length) {
        this.particle = particle;
        this.location = loc;
        this.direction = direction;
        this.length = length;
    }

    private boolean checkIgnoredBlocksCollided(Block block){
        if (ignoredBlocks == null){
            return false;
        }else{
            return ignoredBlocks.test(block);
        }
    }

    public void disable(){
        disabled = true;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setDirection(Vector direction) {
        this.direction = direction;
    }

    protected void setGoThroughBlocks(boolean goThroughBlocks) {
        this.goThroughBlocks = goThroughBlocks;
    }

    protected void touchEntity(Entity entity){}

    protected void touchBlock(Block block){}

    protected void touchWater(Block block){}

    protected void endOfBeam(Location loc){}

    public void stepped(double part){}

    public void spawnParticle(Location loc){}

    public double step(){
        return 0.5f;
    }

    public void update(long ticks){
        if (!disabled) {
            World world = location.getWorld();
            boolean end = false;
            d: for (double i = origin; i < length; i += step()) {
                stepped(i);
                if (!disabled) {
                    Location loc = location.clone().add(direction.clone().multiply(i));
                    if (i == length-1){
                        end = true;
                        endOfBeam(loc);
                    }
                    for (Entity entity : loc.getNearbyEntities(0.5f, 0.5f, 0.5f)) {
                        if (!disabled) {
                            touchEntity(entity);
                        }
                    }
                    if (!loc.getBlock().isEmpty()) {
                        if (loc.getBlock().isLiquid()) {
                            touchWater(loc.getBlock());
                            if (!ignoresFluids){
                                if (!end) {
                                    end = true;
                                    endOfBeam(loc);
                                }
                                break d;
                            }
                        }else{
                            touchBlock(loc.getBlock());
                            if (!goThroughBlocks) {
                                if (!checkIgnoredBlocksCollided(loc.getBlock())) {
                                    if (!end) {
                                        end = true;
                                        endOfBeam(loc);
                                    }
                                    break d;
                                }
                            }
                        }
                    }
                    if (i >= origin) {
                        if (!isInvisible()) {
                            if (dustOptions != null) {
                                world.spawnParticle(particle, loc, 0, 0, 0, 0, dustOptions);
                            } else {
                                world.spawnParticle(particle, loc, 0, 0, 0, 0);
                            }
                            spawnParticle(loc);
                        }
                    }
                }else{
                    break;
                }
            }
        }
    }

    public boolean isInvisible(){
        return false;
    }

    public Vector getDirection() {
        return direction;
    }

    public void setOrigin(double origin) {
        this.origin = origin;
    }

    public boolean isDisabled() {
        return disabled;
    }

    public void setDustOptions(Particle.DustOptions dustOptions) {
        this.dustOptions = dustOptions;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isIgnoresFluids() {
        return ignoresFluids;
    }

    public void setIgnoresFluids(boolean ignore) {
        this.ignoresFluids = ignore;
    }
}

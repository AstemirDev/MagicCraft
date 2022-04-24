package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FireBeam extends PlayerBeam {


    public FireBeam(Player player) {
        super(player,Particle.FLAME, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.FIRE_BEAM.getInt("beam_length"));
        setGoThroughBlocks(false);
        setOrigin(1);
    }

    @Override
    public void touchBlock(Block block) {
        super.touchBlock(block);
//        if (block.getType().isFlammable() && RandomUtils.randomInt(2) == 0){
//            Location loc = block.getLocation().clone().add(0,1,0);
//            loc.getWorld().getBlockAt(loc).setType(Material.FIRE);
//        }
    }

    @Override
    public void spawnParticle(Location loc) {
        loc.getWorld().spawnParticle(Particle.FLAME,loc,1,0.1f,0.1f,0.1f,0.1f);
    }

    @Override
    public void touchEntity(Entity entity) {
        if (entity instanceof LivingEntity && !isOwner(entity)){
            if (hasOwner()) {
                ((LivingEntity) entity).damage(SpellsConfiguration.FIRE_BEAM.getInt("damage"), getOwner());
                ((LivingEntity)entity).setFireTicks(SpellsConfiguration.FIRE_BEAM.getInt("ticks"));
            }else{
                ((LivingEntity) entity).damage(SpellsConfiguration.FIRE_BEAM.getInt("damage"), null);
            }
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

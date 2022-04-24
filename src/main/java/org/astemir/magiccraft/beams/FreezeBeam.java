package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityFreezingProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class FreezeBeam extends PlayerBeam {


    public FreezeBeam(Player player) {
        super(player,Particle.SNOW_SHOVEL, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.FREEZE_BEAM.get("beam_length",Integer.class));
        setGoThroughBlocks(false);
        setOrigin(1);
    }

    @Override
    public void touchBlock(Block block) {
        super.touchBlock(block);
    }

    @Override
    public void touchEntity(Entity entity) {
        if (entity instanceof LivingEntity && !isOwner(entity)){
            if (hasOwner()) {
                EntityFreezingProjectile projectile = EntityMagic.create(EntityFreezingProjectile.class,getOwner());
                projectile.setPosition(entity.getLocation().getX(),entity.getLocation().getY(),entity.getLocation().getZ());
                projectile.join();
                projectile.onDamageEntity(((CraftEntity)entity).getHandle());
            }
            disable();
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

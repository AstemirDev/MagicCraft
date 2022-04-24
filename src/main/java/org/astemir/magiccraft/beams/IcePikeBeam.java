package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityIcePike;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class IcePikeBeam extends PlayerBeam {


    public IcePikeBeam(Player player) {
        super(player,Particle.SNOW_SHOVEL, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.ICE_PIKE.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setOrigin(1);
    }

    @Override
    public boolean isInvisible() {
        return true;
    }

    @Override
    public void touchBlock(Block block) {
        super.touchBlock(block);
        Block up = block.getLocation().clone().add(0,1,0).getBlock();
        if (block.isSolid() && (up.isEmpty() || !up.isSolid())) {
            Location loc = block.getLocation();
            Player player = getOwner();
            if (hasOwner()) {
                EntityIcePike projectile = new EntityIcePike(((CraftWorld) loc.getWorld()).getHandle(), 0, 0, 0);
                projectile.setResultY((float) loc.getY()-0.5f);
                projectile.setPosition(loc.getX() + 0.5f, loc.getY() - (1f+projectile.getModelLength()), loc.getZ() + 0.5f);
                projectile.own(player);
                projectile.create();
                player.getWorld().playSound(block.getLocation(), Sound.BLOCK_GLASS_BREAK, 1, RandomUtils.randomFloat(1.4f,1.5f));
                player.getWorld().playSound(block.getLocation(), Sound.ENTITY_EVOKER_FANGS_ATTACK, 1, RandomUtils.randomFloat(1.4f,1.5f));
            }
        }
    }

    @Override
    public void touchEntity(Entity entity) {
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

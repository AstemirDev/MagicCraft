package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityBlackHole;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class BlackHoleBeam extends PlayerBeam {


    public BlackHoleBeam(Player player) {
        super(player,Particle.SNOW_SHOVEL, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.BLACK_HOLE.getInt("beam_length"));
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
    }

    @Override
    public void touchEntity(Entity entity) {
    }



    @Override
    public void endOfBeam(Location loc) {
        disable();
        if (hasOwner()) {
            Player player = getOwner();
            EntityBlackHole projectile = new EntityBlackHole(((CraftWorld) loc.getWorld()).getHandle(), 0, 0, 0);
            projectile.setPosition(loc.getX() + 0.5f, loc.getY(), loc.getZ() + 0.5f);
            projectile.own(player);
            projectile.create();
        }
    }
}

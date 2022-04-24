package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.*;

public class ChargeBeam extends PlayerBeam {


    public ChargeBeam(Player player) {
        super(player,Particle.CRIT_MAGIC, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.CHARGE.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setOrigin(1);
    }


    @Override
    public void touchEntity(Entity entity) {

        if (entity instanceof Creeper && !isOwner(entity)){
            ((Creeper)entity).setPowered(true);
        }else
        if (entity instanceof Villager && !isOwner(entity)){
            entity.getWorld().spawnEntity(entity.getLocation(),EntityType.WITCH);
            ((Villager)entity).remove();
        }else
        if (entity instanceof Pig && !isOwner(entity)){
            entity.getWorld().spawnEntity(entity.getLocation(),EntityType.ZOMBIFIED_PIGLIN);
            ((Pig)entity).remove();
        }

        disable();
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class WindHookBeam extends PlayerBeam {

    private int ticksLived = 0;

    public WindHookBeam(Location loc, Player player) {
        super(player,Particle.CLOUD,loc, loc.clone().subtract(PlayerUtils.getPlayerEyeLocation(player)).toVector().normalize().multiply(-1), SpellsConfiguration.WIND_HOOK.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setIgnoresFluids(true);
        setOrigin(1);
    }

    @Override
    public void touchEntity(Entity entity) {
        if (hasOwner()){
            Player player = getOwner();
            if (isOwner(entity)) {
                player.setVelocity(getDirection().clone().multiply(-1));
            }
        }else{
            disable();
        }
        super.touchEntity(entity);
    }

    @Override
    public void update(long ticks) {
        ticksLived++;
        if (ticksLived > 120){
            disable();
        }
        if (hasOwner()) {
            Player player = getOwner();
            if (player.getLocation().distanceSquared(getLocation().clone()) > 5) {
                setDirection(getLocation().clone().subtract(PlayerUtils.getPlayerEyeLocation(player)).toVector().normalize().multiply(-1));
            }else{
                disable();
            }
        }else{
            disable();
        }
        super.update(ticks);
    }
}

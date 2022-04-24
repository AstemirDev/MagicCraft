package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public class InstaKillBeam extends PlayerBeam {


    public InstaKillBeam(Player player) {
        super(player,Particle.EXPLOSION_HUGE, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.INSTAKILL.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setIgnoresFluids(true);
        setOrigin(1);
    }

    @Override
    public boolean isInvisible() {
        return true;
    }

    @Override
    public void touchEntity(Entity entity) {
        if (entity instanceof LivingEntity && !isOwner(entity)){
            ((LivingEntity) entity).damage(SpellsConfiguration.INSTAKILL.getInt(DefaultVars.DAMAGE));
            disable();
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

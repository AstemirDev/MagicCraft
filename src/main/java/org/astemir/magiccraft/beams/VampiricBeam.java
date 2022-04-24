package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityVampiricProjectile;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class VampiricBeam extends PlayerBeam {


    public VampiricBeam(Player player) {
        super(player,Particle.REDSTONE, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.VAMPIRISM.getInt(DefaultVars.BEAM_LENGTH));
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
            if (hasOwner()) {
                Player player = getOwner();
                ((LivingEntity) entity).damage(SpellsConfiguration.VAMPIRISM.getInt(DefaultVars.DAMAGE), player);
                EntityVampiricProjectile projectile = EntityVampiricProjectile.create(EntityVampiricProjectile.class,player);
                projectile.setPosition(entity.getLocation().getX()+ RandomUtils.randomFloat(-0.15f,0.15f),entity.getLocation().getY()+((LivingEntity) entity).getEyeHeight(),entity.getLocation().getZ()+ RandomUtils.randomFloat(-0.15f,0.15f));
                projectile.own(player);
                projectile.join();
                disable();
            }
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

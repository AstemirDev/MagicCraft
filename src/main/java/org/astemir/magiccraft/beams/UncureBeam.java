package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.PotionUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.*;

public class UncureBeam extends PlayerBeam {


    public UncureBeam(Player player) {
        super(player,Particle.END_ROD, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.UNCURE.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setIgnoresFluids(true);
        setOrigin(1);
    }


    @Override
    public void touchEntity(Entity entity) {
        if (entity instanceof LivingEntity && !isOwner(entity)){
            Player player = getOwner();
            if (entity instanceof ZombieVillager) {
                ((ZombieVillager)entity).setConversionPlayer(player);
                ((ZombieVillager) entity).setConversionTime(0);
            }else if (((LivingEntity) entity).getCategory().equals(EntityCategory.UNDEAD)){
                MagicUtils.lightDamage(player,entity,SpellsConfiguration.UNCURE.getInt(DefaultVars.DAMAGE));
            }else{
                ((LivingEntity) entity).getActivePotionEffects().forEach((potion)->{
                    if (PotionUtils.isHarmful(potion.getType())){
                        ((LivingEntity) entity).removePotionEffect(potion.getType());
                    }
                });
            }
            entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_ZOMBIE_VILLAGER_CURE,1,1);
            entity.getWorld().spawnParticle(Particle.FLASH,entity.getLocation(),0,0,0,0);
            disable();
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

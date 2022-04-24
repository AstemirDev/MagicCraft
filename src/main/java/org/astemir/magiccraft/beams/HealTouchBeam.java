package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.MagicUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class HealTouchBeam extends PlayerBeam {


    public HealTouchBeam(Player player) {
        super(player,Particle.HEART, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.HEAL_TOUCH.get("beam_length",Integer.class));
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
            ((LivingEntity) entity).addPotionEffect(SpellsConfiguration.HEAL_TOUCH.effect("regeneration").toBukkit());
            MagicUtils.healParticles(entity.getLocation().clone().add(0,0.5f,0),5,1f);
            disable();
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

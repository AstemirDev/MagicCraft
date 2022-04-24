package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.UUID;

public class AbsoluteZeroBeam extends PlayerBeam {


    public AbsoluteZeroBeam(Player player) {
        super(player,Particle.EXPLOSION_HUGE, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.ABSOLUTE_ZERO.getInt("beam_length"));
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
            if (entity instanceof Player){
                PlayerData data = MagicCraft.playerDataHandler.getPlayerData(((Player)entity));
                data.setLearnedSpells("");
                data.setMagicExperience(0);
                data.setMagicLevel(0);
                data.setMagicPoints(1);
            }
            disable();
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

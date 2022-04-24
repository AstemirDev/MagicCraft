package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class BelzebubBeam extends PlayerBeam {


    public BelzebubBeam(Player player) {
        super(player,Particle.SQUID_INK, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.BELZEBUB.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setOrigin(1);
    }

    @Override
    public void touchEntity(Entity entity) {
        if (entity instanceof LivingEntity && !isOwner(entity)){
            float maxDamage = SpellsConfiguration.BELZEBUB.getInt("damage");
            float mana = 5;
            if (entity instanceof Player){
                PlayerData data = MagicCraft.playerDataHandler.getPlayerData((Player)entity);
                if (data.isMage()){
                    mana = data.getMana();
                }
            }
            float damage = maxDamage-((maxDamage/100)*(10*mana));
            if (hasOwner()) {
                ((LivingEntity) entity).damage(damage, getOwner());
            }else{
                ((LivingEntity) entity).damage(damage, null);
            }
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

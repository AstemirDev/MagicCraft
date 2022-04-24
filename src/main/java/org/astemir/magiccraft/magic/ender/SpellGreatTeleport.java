package org.astemir.magiccraft.magic.ender;


import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellGreatTeleport extends MasterSpell {


    public SpellGreatTeleport() {
        super(SpellsConfiguration.GREAT_TELEPORT);
    }


    @Override
    public void onUse(Player player) {
        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20,0.5f,0.5f,0.5f,0.05f);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        Location loc = player.getTargetBlock(SpellsConfiguration.GREAT_TELEPORT.getInt("range")).getLocation().add(0,1,0);
        loc.setPitch(player.getLocation().getPitch());
        loc.setYaw(player.getLocation().getYaw());
        player.teleport(loc);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20,0.5f,0.5f,0.5f,0.05f);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.MEDIUM_TELEPORT_SPELL;
    }
}

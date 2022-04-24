package org.astemir.magiccraft.magic.ender;


import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellChaoticTeleport extends MasterSpell {


    public SpellChaoticTeleport() {
        super(SpellsConfiguration.CHAOTIC_TELEPORT);
    }


    private Location randomLocation(Location loc){
        int range = SpellsConfiguration.CHAOTIC_TELEPORT.getInt("range");
        Location randomLoc = new Location(loc.getWorld(),loc.getX()+ RandomUtils.randomInt(-range,range),loc.getY(),loc.getZ()+ RandomUtils.randomInt(-range,range));
        randomLoc.setPitch(loc.getPitch());
        randomLoc.setYaw(loc.getYaw());
        if (randomLoc.getBlock().isEmpty() || !randomLoc.getBlock().getType().isSolid()) {
            return randomLoc;
        }else{
            return randomLocation(loc);
        }
    }

    @Override
    public void onUse(Player player) {
        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20,0.5f,0.5f,0.5f,0.05f);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        Location loc = randomLocation(player.getLocation());
        player.teleport(loc);
        player.damage(SpellsConfiguration.CHAOTIC_TELEPORT.getInt(DefaultVars.DAMAGE));
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20,0.5f,0.5f,0.5f,0.05f);
    }


    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.RANDOM_TELEPORT_SPELL;
    }
}

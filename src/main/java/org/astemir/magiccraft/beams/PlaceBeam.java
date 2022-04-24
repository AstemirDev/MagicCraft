package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class PlaceBeam extends PlayerBeam {


    public PlaceBeam(Player player) {
        super(player,Particle.REDSTONE, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.PLACE.getInt(DefaultVars.BEAM_LENGTH));
        setDustOptions(new Particle.DustOptions(Color.GREEN,1));
        setGoThroughBlocks(false);
        setIgnoresFluids(true);
        setOrigin(1);
    }

    @Override
    public void endOfBeam(Location loc) {
        for (double i = 0; i <= Math.PI; i += Math.PI / 10) {
            double radius = Math.sin(i);
            double y = Math.cos(i);
            for (double a = 0; a < Math.PI * 2; a += Math.PI / 10) {
                double x = Math.cos(a) * radius;
                double z = Math.sin(a) * radius;
                placeBlock(loc.clone().add(x, y, z));
                loc.getWorld().spawnParticle(Particle.BLOCK_CRACK,loc.clone().add(x,y,z),5,0.5f,0.5f,0.5f,0.1,Material.DIRT.createBlockData());
            }
        }
        loc.getWorld().playSound(loc,Sound.BLOCK_GRAVEL_BREAK,3,0.8f);
        disable();
    }

    private void placeBlock(Location loc){
        loc.getWorld().getBlockAt(loc).setType(Material.DIRT);
    }

}

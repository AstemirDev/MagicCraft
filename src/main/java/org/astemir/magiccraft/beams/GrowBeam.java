package org.astemir.magiccraft.beams;

import net.minecraft.server.v1_16_R3.BlockPosition;
import net.minecraft.server.v1_16_R3.ItemBoneMeal;
import net.minecraft.server.v1_16_R3.Items;
import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

public class GrowBeam extends PlayerBeam {


    public GrowBeam(Player player) {
        super(player,Particle.REDSTONE, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.GROW.getInt(DefaultVars.BEAM_LENGTH));
        setDustOptions(new Particle.DustOptions(Color.GREEN,1));
        setGoThroughBlocks(false);
        setIgnoresFluids(true);
        setOrigin(1);
    }


    @Override
    public void touchBlock(Block block) {
        Location loc = block.getLocation();
        int radius = SpellsConfiguration.GROW.getInt("radius");
        for (int i = -radius; i<radius; i++) {
            for (int j = -radius;j<radius;j++) {
                Location growLoc = loc.clone().add(i, 0, j);
                ItemBoneMeal.a(Items.BONE_MEAL.createItemStack(),(((CraftWorld)loc.getWorld())).getHandle(),new BlockPosition(loc.getBlockX(),loc.getBlockY(),loc.getBlockZ()));
                loc.getWorld().spawnParticle(Particle.VILLAGER_HAPPY, growLoc, 5, 0.5f, 0.5f, 0.5f, 0.1);
            }
        }
    }


    @Override
    public void endOfBeam(Location loc) {
        disable();
    }

}

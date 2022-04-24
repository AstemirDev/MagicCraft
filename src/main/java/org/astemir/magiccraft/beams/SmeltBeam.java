package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.ItemUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SmeltBeam extends PlayerBeam {


    public SmeltBeam(Player player) {
        super(player,Particle.LAVA, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.SMELT.getInt(DefaultVars.BEAM_LENGTH));
        setGoThroughBlocks(false);
        setOrigin(1);
    }


    @Override
    public void touchEntity(Entity entity) {
        if (entity instanceof Item && !isOwner(entity)){
            Item item = (Item)entity;
            for (int i = 0;i<5;i++) {
                item.getWorld().spawnParticle(Particle.FLAME, item.getLocation().clone().add(RandomUtils.randomFloat(-0.15f,0.15f),RandomUtils.randomFloat(-0.15f,0.15f),RandomUtils.randomFloat(-0.15f,0.15f)), 0, 0, 0, 0, 0);
            }
            ItemStack itemStack = item.getItemStack();
            Material cooked = ItemUtils.blastItem(ItemUtils.cookFood(itemStack.getType()));
            if (itemStack.getAmount() == 1){
                itemStack.setType(cooked);
            }else{
                ItemStack newItemStack = new ItemStack(cooked);
                item.getWorld().dropItem(item.getLocation(),newItemStack);
                itemStack.subtract(1);
            }
            item.setItemStack(itemStack);
            disable();
        }
    }

    @Override
    public void endOfBeam(Location loc) {
        disable();
    }
}

package org.astemir.magiccraft.beams;

import net.minecraft.server.v1_16_R3.BlockPosition;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.DefaultVars;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.item.ItemMagicStick;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.PlayerUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.craftbukkit.v1_16_R3.CraftWorld;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DigBeam extends Beam{

    public static ConcurrentHashMap<DigBeam,UUID> digBeams = new ConcurrentHashMap<>();
    private Block targetBlock = null;
    private float breakValue = 0;
    private boolean breaking = false;


    public DigBeam(Player player) {
        super(Particle.REDSTONE, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(), SpellsConfiguration.DIG.getInt(DefaultVars.BEAM_LENGTH));
        DigBeam.digBeams.put(this,player.getUniqueId());
        setDustOptions(new Particle.DustOptions(Color.GREEN,1));
        setGoThroughBlocks(false);
        setIgnoresFluids(true);
        setOrigin(1);
    }

    public static boolean hasPlayer(Player player){
        for (Map.Entry<DigBeam,UUID> entry : digBeams.entrySet()){
            if (entry.getValue().equals(player.getUniqueId())){
                return true;
            }
        }
        return false;
    }

    public boolean isBlockTarget(Block block){
        if (targetBlock == null){
            return false;
        }
        if (targetBlock.getX() == block.getX() && targetBlock.getY() == block.getY() && targetBlock.getZ() == block.getZ()){
            return true;
        }
        return false;
    }

    @Override
    public void touchBlock(Block block) {
        super.touchBlock(block);
        if (isBlockTarget(block)){
            breaking = true;
        }else{
            breakValue = 0;
            breaking = false;
            targetBlock = block;
        }
    }

    private void turnOff(Player player){
        if (targetBlock != null){
            player.sendBlockDamage(targetBlock.getLocation(),0);
        }
        DigBeam.digBeams.remove(this);
        disable();
    }



    @Override
    public void update(long ticks) {
        super.update(ticks);
        if (DigBeam.digBeams.containsKey(this)){
            UUID uuid = DigBeam.digBeams.get(this);
            Player player = Bukkit.getPlayer(uuid);
            player.swingMainHand();
            if (!org.astemir.magiccraft.item.Items.isItem(player.getInventory().getItemInMainHand(), ItemMagicStick.class)){
                disable();
            }
            if (ticks % 2 == 0){
                player.playSound(player.getLocation(),"magiccraft.items.magic_1",0.25f,RandomUtils.randomFloat(1f,1.2f));
            }
            if (ticks % 10 == 0){
                MagicCraft.playerDataHandler.getPlayerData(player).removeMana(1);
                if (!MagicCraft.playerDataHandler.getPlayerData(player).hasEnoughMana(1)) {
                    turnOff(player);
                }
            }
            if (ticks % 2 == 0 && targetBlock != null){
                float blockStrength = ((CraftWorld)targetBlock.getWorld()).getHandle().getType(new BlockPosition(targetBlock.getX(),targetBlock.getY(),targetBlock.getZ())).strength;
                float breakSpeed = 1/blockStrength;
                if (breakValue + breakSpeed < 1){
                    breakValue+=breakSpeed;
                    if (targetBlock != null) {
                        targetBlock.getWorld().spawnParticle(Particle.BLOCK_CRACK,targetBlock.getLocation().clone().add(0.5f,0,0.5f),5,0.5f,0.5f,0.5f,targetBlock.getBlockData());
                        targetBlock.getLocation().getWorld().playSound(targetBlock.getLocation(), targetBlock.getSoundGroup().getHitSound(), 1, RandomUtils.randomFloat(0.5f,0.7f));
                    }
                }else{
                    breakValue = 1;
                    breaking = false;
                    if (targetBlock != null) {
                        targetBlock.getWorld().playEffect(targetBlock.getLocation().clone().add(0.5f,0,0.5f),Effect.STEP_SOUND,targetBlock.getType());
                        targetBlock.breakNaturally();
                    }
                    targetBlock = null;
                }
                if (targetBlock != null) {
                    player.sendBlockDamage(targetBlock.getLocation(),Math.max(0,Math.min(breakValue,1)));
                }
            }

            MagicCraft.playerDataHandler.getPlayerData(player).cooldownManaRegen();

            setLocation(PlayerUtils.getPlayerEyeLocation(player).add(player.getVelocity()));
            setDirection(player.getLocation().getDirection());
        }
    }

    @Override
    public void disable() {
        super.disable();
        UUID uuid = DigBeam.digBeams.get(this);
        if (uuid != null) {
            Player player = Bukkit.getPlayer(uuid);
            if (player != null) {
                Spells.DIG_SPELL.cooldown(player);
            }
        }
        DigBeam.digBeams.remove(this);
    }
}

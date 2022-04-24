package org.astemir.magiccraft.magic.ender;


import net.kyori.adventure.text.Component;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.IHasUniqueCondition;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellRespawnTeleport extends MagicSpell implements IHasUniqueCondition {


    public SpellRespawnTeleport() {
        super(SpellsConfiguration.RESPAWN_TELEPORT);
    }



    @Override
    public void onUse(Player player) {
        Location loc = player.getBedSpawnLocation();
        if (loc == null){
            loc = player.getPotentialBedLocation();
        }
        if (loc != null) {
            player.getWorld().spawnParticle(Particle.PORTAL,player.getLocation(),20,0.5f,0.5f,0.5f,0.05f);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.teleport(loc);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 20, 0.5f, 0.5f, 0.5f, 0.05f);
        }
    }

    @Override
    public boolean uniqueCondition(Player player) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        return data.hasLearnedSpell(Spells.SMALL_TELEPORT_SPELL);
    }

    @Override
    public void conditionIsNotSatisfied(Player player) {
        player.sendMessage(Component.translatable("msg.magiccraft.spell_not_learned").append(Component.translatable("spell.magiccraft."+Spells.SMALL_TELEPORT_SPELL.getRegisterKey())));
    }
}

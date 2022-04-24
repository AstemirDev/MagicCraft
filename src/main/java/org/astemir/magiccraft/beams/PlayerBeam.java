package org.astemir.magiccraft.beams;

import org.astemir.magiccraft.utils.PlayerUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

public abstract class PlayerBeam extends Beam{


    private UUID playerUUID;

    public PlayerBeam(Player player,Particle particle, int length) {
        super(particle, PlayerUtils.getPlayerEyeLocation(player), player.getLocation().getDirection(),length);
        this.playerUUID = player.getUniqueId();
    }

    public PlayerBeam(Player player,Particle particle, Location loc, Vector direction, int length) {
        super(particle, loc, direction, length);
        this.playerUUID = player.getUniqueId();
    }

    protected boolean isOwner(Entity entity){
        if (playerUUID == null){
            return false;
        }
        return playerUUID.equals(entity.getUniqueId());
    }

    public Player getOwner(){
        return Bukkit.getPlayer(playerUUID);
    }

    public boolean hasOwner(){
        if (getOwner() == null){
            return false;
        }
        return true;
    }

    public UUID getPlayerUUID() {
        return playerUUID;
    }
}

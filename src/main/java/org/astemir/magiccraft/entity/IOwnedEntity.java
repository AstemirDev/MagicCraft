package org.astemir.magiccraft.entity;

import net.minecraft.server.v1_16_R3.EntityPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IOwnedEntity {

    void own(Player player);

    boolean hasOwner();

    boolean isOwner(UUID uuid);

    Player getOwner();

    EntityPlayer getServerOwner();
}

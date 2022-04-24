package org.astemir.magiccraft.magic.water;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityWaterBoltProjectile;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.*;
import org.astemir.magiccraft.player.PlayerCustomVariable;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.entity.Player;

public class SpellWaterBolt extends MasterSpell implements IHasCooldownException, IHasManaUniqueCondition {


    public SpellWaterBolt() {
        super(SpellsConfiguration.WATER_BOLT);
    }


    @Override
    public void onUse(Player player) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        if (!data.hasCustomVariable("waterBolt")) {
            EntityWaterBoltProjectile projectile = EntityWaterBoltProjectile.create(EntityWaterBoltProjectile.class, player);
            projectile.own(player);
            projectile.join();
            data.addCustomVariable("waterBolt",new PlayerCustomVariable(projectile));
        }else{
            EntityWaterBoltProjectile projectile = (EntityWaterBoltProjectile) data.getCustomVariable("waterBolt").getValue();
            projectile.setDistance(projectile.getDistance()+1);
        }
    }

    @Override
    public boolean manaUniqueCondition(Player player) {
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        boolean consumeMana = !data.hasCustomVariable("waterBolt");
        return consumeMana;
    }


    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.WATER_SPELL;
    }
}

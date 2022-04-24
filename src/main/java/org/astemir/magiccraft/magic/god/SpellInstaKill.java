package org.astemir.magiccraft.magic.god;

import net.kyori.adventure.text.Component;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.beams.InstaKillBeam;
import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.item.Item;
import org.astemir.magiccraft.magic.IHasUniqueCondition;
import org.astemir.magiccraft.magic.ISecretSpell;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.player.PlayerData;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.entity.Player;

public class SpellInstaKill extends MagicSpell implements IHasUniqueCondition, ISecretSpell {


    public SpellInstaKill() {
        super(SpellsConfiguration.INSTAKILL);
    }


    @Override
    public void onUse(Player player) {
        InstaKillBeam beam = new InstaKillBeam(player);
        MagicCraft.beamHandler.addBeam(beam);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_light", 1, RandomUtils.randomFloat(0.5f,0.6f));
    }

    @Override
    public boolean uniqueCondition(Player player) {
        return player.isOp();
    }

    @Override
    public void conditionIsNotSatisfied(Player player) {
        player.sendMessage(Component.translatable("msg.magiccraft.not_god"));
    }

    @Override
    public boolean showing(Item item, Player player, PlayerData data) {
        return player.isOp();
    }
}

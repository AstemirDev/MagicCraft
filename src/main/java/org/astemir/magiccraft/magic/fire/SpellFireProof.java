package org.astemir.magiccraft.magic.fire;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.event.IHurtEventListener;
import org.astemir.magiccraft.event.PlayerHurtEvent;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.*;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class SpellFireProof extends MasterSpell implements IPassiveSpell, IHurtEventListener {


    public SpellFireProof() {
        super(SpellsConfiguration.FIRE_PROOF);
    }

    @Override
    public void onUse(Player player) {
        player.setFireTicks(0);
        player.addPotionEffect(SpellsConfiguration.FIRE_PROOF.effect("fire_resistance").toBukkit());
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BLAZE_AMBIENT, 0.5f, 1.5f);
        player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 0.5f, 1.5f);
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.FIRE_SPELL;
    }


    @Override
    public void onHurt(PlayerHurtEvent e) {
        if (e.getCause().equals(EntityDamageEvent.DamageCause.FIRE) || e.getCause().equals(EntityDamageEvent.DamageCause.LAVA) || e.getCause().equals(EntityDamageEvent.DamageCause.HOT_FLOOR) || e.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK)){
            if (isCanBeUsedAgain(e.getPlayer())) {
                e.setDamage(0);
                e.setCancelled(true);
                use(e.getPlayer());
            }else{
                broadcastCooldown(e.getPlayer());
            }
        }
    }
}

package org.astemir.magiccraft.magic.light;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.MasterSpell;
import org.astemir.magiccraft.magic.Spells;
import org.astemir.magiccraft.utils.MagicUtils;
import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Particle;
import org.bukkit.entity.EntityCategory;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class SpellHolyLight extends MasterSpell {


    public SpellHolyLight() {
        super(SpellsConfiguration.HOLY_LIGHT);
    }

    @Override
    public void onUse(Player player) {
        int radius = SpellsConfiguration.HOLY_LIGHT.getInt("radius");
        player.getNearbyEntities(radius,radius,radius).forEach((e)->{
            if (e instanceof LivingEntity){
                if (((LivingEntity) e).getCategory().equals(EntityCategory.UNDEAD)) {
                    if (!e.getUniqueId().equals(player.getUniqueId())) {
                        MagicUtils.lightDamage(player, e, SpellsConfiguration.HOLY_LIGHT.getInt("damage"));
                    }
                }
            }
        });
        player.getWorld().spawnParticle(Particle.FLASH,player.getLocation().clone().add(0,1,0),0,radius,radius,radius,0);
        player.getWorld().playSound(player.getLocation(), "magiccraft.items.magic_light", 1, RandomUtils.randomFloat(0.9f,1.1f));
    }

    @Override
    public MagicSpell getRequiredSpell() {
        return Spells.LIGHT_EXPLOSION_SPELL;
    }

}

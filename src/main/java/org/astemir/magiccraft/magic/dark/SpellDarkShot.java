package org.astemir.magiccraft.magic.dark;

import org.astemir.magiccraft.config.SpellsConfiguration;
import org.astemir.magiccraft.entity.EntityDarkProjectile;
import org.astemir.magiccraft.entity.EntityFireProjectile;
import org.astemir.magiccraft.entity.EntityMagic;
import org.astemir.magiccraft.graphics.UnicodeSymbols;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.sound.Sounds;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SpellDarkShot extends MagicSpell {


    public SpellDarkShot() {
        super(SpellsConfiguration.DARK_SHOT);
    }


    @Override
    public void onUse(Player player) {
        createProjectile(EntityDarkProjectile.class,player).
                launch(player,0,1.25f,1).
                join();
        Sounds.DARKNESS_SPELL_1.play(player,0.5f,0.6f);
    }
}

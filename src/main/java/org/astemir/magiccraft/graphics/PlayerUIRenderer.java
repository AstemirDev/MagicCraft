package org.astemir.magiccraft.graphics;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.player.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

public class PlayerUIRenderer {


    private BossbarHandler bossbarHandler = new BossbarHandler();


    private String getMagicSpellTexture(Player player){
        MagicSpell spell = MagicCraft.playerDataHandler.getPlayerData(player).getSelectedSpell();
        if (spell != null){
            return spell.getTexture();
        }
        return UnicodeSymbols.NULL_SPELL;
    }

    public void render(Player player){
        PlayerData data = MagicCraft.playerDataHandler.getPlayerData(player);
        if (data.isMage()) {
            StringBuilder builder = new StringBuilder();
            //Spell Drawing
            builder.append(UnicodeSymbols.MANA_OFFSET);
            builder.append(getMagicSpellTexture(player));
            builder.append(" ");
            //Mana Drawing
            for (int i = 0; i < data.getMaxMana(); i++) {
                String manaFull = UnicodeSymbols.MANA_FULL;
                String manaEmpty = UnicodeSymbols.MANA_EMPTY;
                if (player.getRemainingAir() < player.getMaximumAir() || player.hasPotionEffect(PotionEffectType.HEALTH_BOOST)) {
                    manaFull = UnicodeSymbols.MANA_FULL_UP;
                    manaEmpty = UnicodeSymbols.MANA_EMPTY_UP;
                }
                if (i < data.getMana()) {
                    builder.append(manaFull);
                } else {
                    builder.append(manaEmpty);
                }
            }
            player.sendActionBar(builder.toString());
        }
        bossbarHandler.update();
    }

    public BossbarHandler getBossbarHandler() {
        return bossbarHandler;
    }
}

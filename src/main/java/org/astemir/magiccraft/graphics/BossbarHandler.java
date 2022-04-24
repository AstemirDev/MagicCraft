package org.astemir.magiccraft.graphics;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.entity.Player;

import java.util.concurrent.CopyOnWriteArrayList;

public class BossbarHandler {

    private CopyOnWriteArrayList<SpellCooldownBossbar> bossBars = new CopyOnWriteArrayList<>();


    public void update(){
        for (SpellCooldownBossbar bossBar : bossBars) {
            if (bossBar.getTicksShowed() > 0){
                bossBar.update();
            }else{
                bossBars.remove(bossBar);
                bossBar.hide();
            }
        }
    }
    public void disableAllBossbars(){
        for (SpellCooldownBossbar bossBar : bossBars) {
            bossBars.remove(bossBar);
            bossBar.hide();
        }
    }

    public void broadcastSpellCooldown(Player player,MagicSpell spell,int ticks){
        if (!containsSpell(player,spell)) {
            SpellCooldownBossbar bossBar = new SpellCooldownBossbar(player, spell, ticks);
            bossBars.add(bossBar);
            bossBar.show();
        }else{
            getSpellCooldownBossbar(player,spell).setTicksShowed(ticks);
        }
    }

    public SpellCooldownBossbar getSpellCooldownBossbar(Player player,MagicSpell spell) {
        for (SpellCooldownBossbar bossbar : bossBars) {
            if (bossbar.getPlayer().getUniqueId().equals(player.getUniqueId())) {
                if (bossbar.getSpell().equals(spell)) {
                    return bossbar;
                }
            }
        }
        return null;
    }

    public boolean containsSpell(Player player,MagicSpell spell) {
        return getSpellCooldownBossbar(player,spell) != null;
    }

    private class SpellCooldownBossbar{

        private MagicSpell spell;
        private Player player;
        private int ticksShowed = 0;
        private BossBar bossBar;

        public SpellCooldownBossbar(Player player, MagicSpell spell,int ticksShowed) {
            this.spell = spell;
            this.player = player;
            this.ticksShowed = ticksShowed;
            if (player != null && spell != null) {
                bossBar = BossBar.bossBar(Component.translatable("spell.magiccraft." + spell.getRegisterKey()).append(Component.text(" "+ UnicodeSymbols.COOLDOWN_UI+" ")).append(Component.text(getSpellCooldownLast() / 20 + "/" + getSpellCooldown() / 20)), getProgress(), BossBar.Color.WHITE, BossBar.Overlay.PROGRESS);
            }
        }

        public void show(){
            player.showBossBar(bossBar);
        }

        public void hide(){
            player.hideBossBar(bossBar);
        }

        private int getSpellCooldown(){
            return spell.getTicksCooldown();
        }

        private int getSpellCooldownLast(){
           return MagicCraft.cooldownManager.getCooldownInTicks(player,spell);
        }

        private float getProgress(){
            return (float)getSpellCooldownLast()/(float)getSpellCooldown();
        }

        private void update(){
            if (ticksShowed > 0) {
                if (player != null && spell != null && bossBar != null) {
                    bossBar.name(Component.translatable("spell.magiccraft." + spell.getRegisterKey()).append(Component.text(" "+ UnicodeSymbols.COOLDOWN_UI+" ")).append(Component.text(getSpellCooldownLast() / 20 + "/" + getSpellCooldown() / 20)));
                    bossBar.progress(getProgress());
                }else{
                    ticksShowed = 0;
                }
                ticksShowed--;
            }
        }

        public void setTicksShowed(int ticksShowed) {
            this.ticksShowed = ticksShowed;
        }

        public MagicSpell getSpell() {
            return spell;
        }

        public Player getPlayer() {
            return player;
        }

        public int getTicksShowed() {
            return ticksShowed;
        }

        public BossBar getBossBar() {
            return bossBar;
        }
    }
}

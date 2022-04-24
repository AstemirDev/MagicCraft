package org.astemir.magiccraft.player;

import org.astemir.magiccraft.magic.MagicSpell;
import org.bukkit.entity.Player;

import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerCooldownManager {

    private CopyOnWriteArrayList<PlayerCooldown> cooldowns = new CopyOnWriteArrayList<>();

    public void update(){
        for (PlayerCooldown cooldown : cooldowns) {
            if (cooldown.getTime() <= 0){
                cooldowns.remove(cooldown);
            }else{
                cooldown.update();
            }
        }
    }

    public void setCooldown(Player player,MagicSpell spell,int time){
        if (time > 0) {
            PlayerCooldown cooldown = getCooldown(player, spell);
            if (cooldown == null) {
                cooldowns.add(new PlayerCooldown(player, spell, time));
            } else {
                cooldowns.remove(cooldown);
                cooldown.setTime(time);
                cooldowns.add(cooldown);
            }
        }
    }

    public boolean hasCooldown(Player player,MagicSpell spell){
        if (getCooldown(player,spell) == null){
            return false;
        }
        return true;
    }

    public int getCooldownInTicks(Player player,MagicSpell spell){
        PlayerCooldown cooldown = getCooldown(player,spell);
        if (cooldown != null){
            return cooldown.getTime();
        }
        return 0;
    }


    private PlayerCooldown getCooldown(Player player,MagicSpell spell){
        for (PlayerCooldown cooldown : cooldowns) {
            if (cooldown.getPlayerUUID().equals(player.getUniqueId())){
                if (cooldown.getSpell().equals(spell)){
                    return cooldown;
                }
            }
        }
        return null;
    }

    private class PlayerCooldown
    {
        private UUID playerUUID;
        private MagicSpell spell;
        private int time = 0;

        public PlayerCooldown(Player player,MagicSpell spell,int time) {
            this.playerUUID = player.getUniqueId();
            this.spell = spell;
            this.time = time;
        }

        public void update(){
            if (time > 0){
                time--;
            }
        }

        public UUID getPlayerUUID() {
            return playerUUID;
        }

        public MagicSpell getSpell() {
            return spell;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }
    }
}

package org.astemir.magiccraft.sound;

import org.astemir.magiccraft.utils.RandomUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class Sounds {

    public static SoundEffect DARKNESS_SPELL_0 = new SoundEffect().
            customSound("magiccraft.items.magic_dark_0").
            volume(1);

    public static SoundEffect DARKNESS_SPELL_1 = new SoundEffect().
            customSound("magiccraft.items.magic_dark_1").
            volume(1);


    public static class SoundEffect{

        private float volume = 1;
        private float pitch = 1;
        private Sound sound;
        private String customSound;

        public float volume() {
            return volume;
        }

        public SoundEffect volume(float volume) {
            this.volume = volume;
            return this;
        }

        public float pitch() {
            return pitch;
        }

        public SoundEffect pitch(float pitch) {
            this.pitch = pitch;
            return this;
        }

        public SoundEffect customSound(String name) {
            this.customSound = name;
            return this;
        }

        public SoundEffect sound(Sound sound) {
            this.sound = sound;
            return this;
        }

        public Sound sound() {
            return sound;
        }

        public String customSound(){
            return customSound;
        }

        public void play(Player player){
            if (sound() == null){
                player.getWorld().playSound(player.getLocation(),customSound(),volume(),pitch());
            }else{
                player.getWorld().playSound(player.getLocation(),sound(),volume(),pitch());
            }
        }

        public void play(Player player, float pitchMin,float pitchMax){
            if (sound() == null){
                player.getWorld().playSound(player.getLocation(),customSound(),volume(), RandomUtils.randomFloat(pitchMin,pitchMax));
            }else{
                player.getWorld().playSound(player.getLocation(),sound(),volume(),RandomUtils.randomFloat(pitchMin,pitchMax));
            }
        }
    }
}

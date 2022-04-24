package org.astemir.magiccraft.config;

import com.google.common.collect.Sets;
import org.astemir.magiccraft.magic.SpellType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConfigurableSpell {


    private Map<String,PotionEffect> potionEffects = new HashMap<>();
    private Map<String,CustomField> fields = new HashMap<>();
    private Set<String> activationPhrases = new HashSet<>();
    private SpellType type = SpellType.NULL;
    private int manaCost = 1;
    private int pointsCost = 1;
    private int levelRequired = 1;
    private int cooldown = 0;


    public ConfigurableSpell mana(int mana){
        this.manaCost = mana;
        return this;
    }

    public ConfigurableSpell points(int p){
        this.pointsCost = p;
        return this;
    }

    public ConfigurableSpell type(SpellType type){
        this.type = type;
        return this;
    }

    public ConfigurableSpell level(int level){
        this.levelRequired = level;
        return this;
    }

    public ConfigurableSpell cooldown(int cd){
        this.cooldown = cd;
        return this;
    }

    public ConfigurableSpell phrases(String... phrases){
        this.activationPhrases = Sets.newHashSet(phrases);
        return this;
    }

    public PotionEffect effect(String name){
        PotionEffect effect = new PotionEffect();
        if (potionEffects.containsKey(name)){
            return potionEffects.get(name);
        }else {
            potionEffects.put(name, effect);
            effect.setSpell(this);
        }
        return effect;
    }

    public ConfigurableSpell set(String name, Object value){
        CustomField field = new CustomField();
        field.set(value);
        fields.put(name,field);
        return this;
    }

    public <T> T get(String name,Class<T> className){
        if (className.equals(Integer.class)){
            return className.cast((Integer)fields.get(name).getInt());
        }else
        if (className.equals(Double.class)){
            return className.cast((Double)fields.get(name).getDouble());
        }else
        if (className.equals(Float.class)){
            return className.cast((Float)fields.get(name).getFloat());
        }else
        if (className.equals(String.class)){
            return className.cast((String)fields.get(name).getString());
        }
        return null;
    }

    public int getInt(String name){
        return get(name,Integer.class);
    }

    public int mana() {
        return manaCost;
    }

    public int points() {
        return pointsCost;
    }

    public int level() {
        return levelRequired;
    }

    public int cooldown() {
        return cooldown;
    }

    public Set<String> activationPhrases() {
        return activationPhrases;
    }

    public SpellType type() {return type;}

    public class PotionEffect{

        private int amplifier = 0;
        private int duration = 0;
        private PotionEffectType type;
        private ConfigurableSpell spell;

        public int amplifier() {
            return amplifier;
        }

        public PotionEffect amplifier(int amplifier) {
            this.amplifier = amplifier;
            return this;
        }

        public int duration() {
            return duration;
        }

        public PotionEffect duration(int duration) {
            this.duration = duration;
            return this;
        }

        public PotionEffectType type() {
            return type;
        }

        public PotionEffect type(PotionEffectType type) {
            this.type = type;
            return this;
        }

        public ConfigurableSpell build() {
            return spell;
        }

        private void setSpell(ConfigurableSpell spell) {
            this.spell = spell;
        }

        public org.bukkit.potion.PotionEffect toBukkit(){
            return new org.bukkit.potion.PotionEffect(type,duration,amplifier,false,false);
        }
    }

    public class CustomField{

        private Object value;

        public int getInt() {
            return (int)value;
        }

        public double getDouble(){
            return (double)value;
        }

        public float getFloat(){
            return (float)value;
        }

        public String getString(){
            return (String)value;
        }

        public CustomField set(Object value) {
            this.value = value;
            return this;
        }
    }
}

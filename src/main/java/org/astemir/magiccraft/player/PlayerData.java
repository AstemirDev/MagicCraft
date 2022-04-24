package org.astemir.magiccraft.player;

import org.astemir.magiccraft.MagicCraft;
import org.astemir.magiccraft.magic.MagicSpell;
import org.astemir.magiccraft.magic.Spells;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PlayerData {

    private int maxMana = 10;
    private int mana = 0;
    private int manaRegeneration = 1;
    private int manaRegenerationCooldown = 0;
    private int manaRegenerationMaxCooldown = 40;
    private int magicLevel = 1;
    private double magicExperience = 0;
    private int magicPoints = 1;
    private boolean mage = false;
    private CopyOnWriteArrayList<PlayerCustomVariable> customVariables = new CopyOnWriteArrayList<>();


    private MagicSpell selectedSpell;

    private List<Integer> learnedSpells;


    public PlayerData() {
        learnedSpells = new ArrayList<>();
    }

    public void regulateManaByLevel(){
//        int speed = (int) ((int)40-(magicLevel/(40*0.25)));
//        if (speed < 1){
//            speed = 1;
//        }
//        manaRegenerationMaxCooldown = speed;
//        manaRegeneration = 1+magicLevel/100;
        PlayerStats stats = PlayerStats.getStatsForLevel(magicLevel);
        manaRegenerationMaxCooldown = stats.getManaRegenerationMaxCooldown();
        manaRegeneration = stats.getManaRegeneration();
    }

    public void update(long ticks){
        customVariables.forEach((var)->{
            var.update(ticks);
        });
        if (manaRegenerationCooldown > 0){
            manaRegenerationCooldown--;
        }else{
            if (mana < maxMana){
                if (ticks % manaRegenerationMaxCooldown == 0) {
                    addMana(manaRegeneration);
                }
            }
        }
    }

    public void addMana(int mana){
        if (this.mana+mana < maxMana){
            this.mana+=mana;
        }else{
            this.mana = maxMana;
        }
    }

    public void removeMana(int mana){
        if (hasEnoughMana(mana)) {
            if (this.mana - mana > 0) {
                this.mana -= mana;
            } else {
                this.mana = 0;
            }
        }
        cooldownManaRegen();
    }

    public void cooldownManaRegen(){
        manaRegenerationCooldown = manaRegenerationMaxCooldown;
    }


    public int getMana(){
        return mana;
    }

    public int getMaxMana(){
        return maxMana;
    }

    public boolean hasEnoughMana(int manaUsage){
        if (getMana() >= manaUsage){
            return true;
        }
        return false;
    }

    public MagicSpell getSelectedSpell(){
        return selectedSpell;
    }

    public String getSelectedSpellKey(){
        if (selectedSpell == null){
            return "empty";
        }
        return selectedSpell.getRegisterKey();
    }

    public void  setSelectedSpell(MagicSpell spell){
        this.selectedSpell = spell;
    }

    public void setMaxMana(int maxMana) {
        this.maxMana = maxMana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }

    public void setManaRegeneration(int manaRegeneration) {
        this.manaRegeneration = manaRegeneration;
    }

    public void setManaRegenerationMaxCooldown(int manaRegenerationMaxCooldown) {
        this.manaRegenerationMaxCooldown = manaRegenerationMaxCooldown;
    }

    public int getMagicLevel() {
        return magicLevel;
    }


    private void levelUp(){
        magicLevel++;
        magicPoints++;
        Player player = MagicCraft.playerDataHandler.getPlayer(this);
        if (player != null){
            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP,1,0.5f);
        }
        regulateManaByLevel();
    }

    public double getExpToLevelUp(){
        return magicLevel*magicLevel/4*100;
    }

    private void checkForLevelUp(){
        double expToLevelup = getExpToLevelUp();
        if (magicExperience >= expToLevelup){
            magicExperience = magicExperience-expToLevelup;
            levelUp();
        }
        double newExpToLevelup = getExpToLevelUp();
        if (magicExperience > newExpToLevelup){
            checkForLevelUp();
        }
    }

    public void addMagicExperience(double expGain){
        double expToLevelup = getExpToLevelUp();
        if (magicExperience + expGain >= expToLevelup){
            magicExperience = expGain-(expToLevelup-magicExperience);
            levelUp();
        }else{
            magicExperience+=expGain;
        }
        checkForLevelUp();
    }

    public int getManaRegeneration() {
        return manaRegeneration;
    }

    public int getManaRegenerationMaxCooldown() {
        return manaRegenerationMaxCooldown;
    }

    public double getMagicExperience() {
        return magicExperience;
    }

    public void learnSpell(MagicSpell spell){
        int index = Spells.getIndexOfSpell(spell);
        learnedSpells.add(index);
    }

    public boolean hasLearnedSpell(MagicSpell spell){
        int spellIndex = Spells.getIndexOfSpell(spell);
        if (learnedSpells.contains(spellIndex)){
            return true;
        }
        return false;
    }

    public void setLearnedSpells(String spellsString){
        List<Integer> spells = new ArrayList<>();
        String[] split = spellsString.split(",");
        if (split.length > 0) {
            for (String s : split) {
                if (!s.isEmpty()) {
                    spells.add(Integer.parseInt(s));
                }
            }
        }
        learnedSpells = spells;
    }

    public String getLearnedSpells(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0;i<learnedSpells.size();i++)
        {
            builder.append(learnedSpells.get(i));
            if (i != learnedSpells.size()-1) {
                builder.append(",");
            }
        }
        return builder.toString();
    }

    public void addCustomVariable(String name,PlayerCustomVariable var){
        var.setName(name);
        customVariables.add(var);
    }

    public PlayerCustomVariable getCustomVariable(String name){
        for (PlayerCustomVariable customVariable : customVariables) {
            if (customVariable.getName().equals(name)){
                return customVariable;
            }
        }
        return null;
    }

    public boolean hasCustomVariable(String name){
        if (getCustomVariable(name) == null){
            return false;
        }
        return true;
    }

    public void removeCustomVariable(String name){
        customVariables.remove(getCustomVariable(name));
    }

    public int getMagicPoints() {
        return magicPoints;
    }

    public void setMagicPoints(int magicPoints) {
        this.magicPoints = magicPoints;
    }

    public void setMagicExperience(double magicExperience) {
        this.magicExperience = magicExperience;
    }

    public void setMagicLevel(int magicLevel) {
        this.magicLevel = magicLevel;
    }

    public boolean isMage() {
        return mage;
    }

    public void setMage(boolean mage) {
        this.mage = mage;
    }
}
